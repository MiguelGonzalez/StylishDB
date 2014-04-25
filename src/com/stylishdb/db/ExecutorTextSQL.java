package com.stylishdb.db;

import com.stylishdb.db.domain.ColumnDatas;
import com.stylishdb.db.domain.ResultUpdates;
import com.stylishdb.db.domain.ResultExecutes;
import com.stylishdb.domain.MConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class ExecutorTextSQL {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rsQuery = null;
    
    private MConnection mConexion;
    private String consultaSQL;
    private List<String> consultasSQL;
    
    private ResultExecutes resultadoEjecutar;
    private ResultUpdates resultadoActualizarConsultas;
    
    private long tiempoParaConectarContraBaseDeDatos;

    public ExecutorTextSQL(MConnection mConexion, String consultaSQL) {
        this.mConexion = mConexion;
        this.consultaSQL = consultaSQL;
        
        resultadoEjecutar= new ResultExecutes();
        resultadoActualizarConsultas = new ResultUpdates();
    }
    
    public ExecutorTextSQL(MConnection mConexion, List<String> consultasSQL) {
        this.mConexion = mConexion;
        this.consultasSQL = consultasSQL;
        
        resultadoEjecutar= new ResultExecutes();
        resultadoActualizarConsultas = new ResultUpdates();
    }

    public void conectarContraBaseDeDatos()
            throws NoConnectionException {
        try {
            long time_start = System.currentTimeMillis();
            String urlConexion = mConexion.getUrlConexion();
            connection = DriverManager.getConnection(
                    urlConexion,
                    mConexion.getUsuario(),
                    mConexion.getPassword()
            );
            long time_end = System.currentTimeMillis();
            tiempoParaConectarContraBaseDeDatos = time_end - time_start;
            
        } catch (SQLException ex) {
            throw new NoConnectionException(ex);
        }
    }
    
    public void ejecutarConsulta()
            throws ExecutorTextSQLException {
        
        try {
            long time_start = System.currentTimeMillis();
            statement = connection.createStatement();
            rsQuery = statement.executeQuery(
                    consultaSQL
            );
            long time_end = System.currentTimeMillis();

            rellenarDatosConsultaEjecutada(time_end - time_start);
        } catch (SQLException ex) {
            throw new ExecutorTextSQLException(ex);
        } finally {
            cerrarConexion();
        }
    }
    
    public void actualizarConsultas() throws ExecutorTextSQLException {
        List<Integer> numFilasAfectadas = new ArrayList<>();
        
        try {
            connection.setAutoCommit(false);
            
            long time_start = System.currentTimeMillis();
            statement = connection.createStatement();
            
            for(String consultaSQLActual : consultasSQL) {
                int filasAfectadas = statement.executeUpdate(
                        consultaSQLActual
                );
                numFilasAfectadas.add(filasAfectadas);
            }
            
            long time_end = System.currentTimeMillis();

            rellenarDatosConsultasActualizadas(
                    numFilasAfectadas,
                    time_end - time_start
            );
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                throw new ExecutorTextSQLException(ex);
            }
            throw new ExecutorTextSQLException(ex);
        } finally {
            try {
                connection.commit();
            } catch (SQLException ex) {
                throw new ExecutorTextSQLException(ex);
            } finally {
                cerrarConexion();
            }
        }
    }
    
    public ResultExecutes getDatosConsultaEjecutada() {
        return resultadoEjecutar;
    }
    
    private void rellenarDatosConsultaEjecutada(long tiempoEjecucionConsulta)
            throws ExecutorTextSQLException {
        resultadoEjecutar.consultaSQL = consultaSQL;
        resultadoEjecutar.tiempoEjecucionConsultaMilisegundos = tiempoEjecucionConsulta;
        
        long time_start = System.currentTimeMillis();
        
        resultadoEjecutar.datosColumnas = getTiposColumnas();
        resultadoEjecutar.numColumnas = getNumColumnas();
        resultadoEjecutar.nombresColumnas = getNombresColumnas();

        while(haySiguienteFila()) {
            resultadoEjecutar.datosFilas.add(
                    getFila(resultadoEjecutar)
            );
        }
        resultadoEjecutar.numFilas = resultadoEjecutar.datosFilas.size();
        long time_end = System.currentTimeMillis();
        resultadoEjecutar.tiempoObtenerDatosConsulta = time_end - time_start;
        resultadoEjecutar.tiempoParaConectarContraBaseDeDatos = 
                tiempoParaConectarContraBaseDeDatos;
    }
    
    private void rellenarDatosConsultasActualizadas(
            List<Integer> numFilasAfectadas,
            long tiempoActualizacionConsultas) {
        resultadoActualizarConsultas.consultasSQLActualizar = consultasSQL;
        resultadoActualizarConsultas.numeroFilasAfectadas = numFilasAfectadas;
        resultadoActualizarConsultas.tiempoActualizacionConsultas = 
                tiempoActualizacionConsultas;
        resultadoActualizarConsultas.tiempoParaConectarContraBaseDeDatos =
                tiempoParaConectarContraBaseDeDatos;
    }
    
    public ResultUpdates getResultadoActualizacionConsultas() {
        return resultadoActualizarConsultas;
    }
    
    private boolean haySiguienteFila() throws ExecutorTextSQLException {
        try {
            return rsQuery.next();
        } catch (SQLException ex) {
            throw new ExecutorTextSQLException(ex);
        }
    }
    
    private int getNumColumnas() throws ExecutorTextSQLException {
        try {
            return rsQuery.getMetaData().getColumnCount();
        } catch (SQLException ex) {
            throw new ExecutorTextSQLException(ex);
        }
    }
    
    private List<String> getNombresColumnas() throws ExecutorTextSQLException {
        List<String> nombresColumnas = new ArrayList<>();
        
        try {
            int numColumnas = getNumColumnas();
            for(int i=1; i<=numColumnas; i++) {
                nombresColumnas.add(rsQuery.getMetaData().getColumnName(i));
            }
        } catch (SQLException ex) {
            throw new ExecutorTextSQLException(ex);
        }
        
        return nombresColumnas;
    }
    
    private List<ColumnDatas> getTiposColumnas() throws ExecutorTextSQLException {
        List<ColumnDatas> tiposColumna = new ArrayList<>();
        
        try {
            int numColumnas = getNumColumnas(); 
            for(int i=1; i<=numColumnas; i++) {
                tiposColumna.add(
                        getDatosColumna(i)
                );
            }
        } catch (SQLException ex) {
            throw new ExecutorTextSQLException(ex);
        }
        
        return tiposColumna;
    }
    
    private ColumnDatas getDatosColumna(int numColumna) throws SQLException {
        ColumnDatas datosColumna = new ColumnDatas();
        ResultSetMetaData rsMetaData = rsQuery.getMetaData();
        
        datosColumna.columnLabel = rsMetaData.getColumnLabel(numColumna);
        datosColumna.columnName = rsMetaData.getColumnName(numColumna);
        datosColumna.catalogName = rsMetaData.getCatalogName(numColumna);
        datosColumna.schemaName = rsMetaData.getSchemaName(numColumna);
        datosColumna.tableName = rsMetaData.getTableName(numColumna);
        datosColumna.columnType = rsMetaData.getColumnType(numColumna);
        datosColumna.columnTypeName = rsMetaData.getColumnTypeName(numColumna);
        datosColumna.columnClassName = rsMetaData.getColumnClassName(numColumna);
        datosColumna.isReadOnly = rsMetaData.isReadOnly(numColumna);
        datosColumna.isAutoIncrement = rsMetaData.isAutoIncrement(numColumna);
        datosColumna.isNullable = rsMetaData.isNullable(numColumna);
        datosColumna.precision = rsMetaData.getPrecision(numColumna);
        datosColumna.scale = rsMetaData.getScale(numColumna);
        
        return datosColumna;
        
    }
    
    private String[] getFila(ResultExecutes resultado)
            throws ExecutorTextSQLException {
        String[] datosFila = new String[resultado.numColumnas];
        DataTypeResultSet datoConsulta = new
                DataTypeResultSet(rsQuery);
        try {
            for(int i=1; i<=resultado.numColumnas; i++) {
                ColumnDatas datosColumna = resultado.datosColumnas.get(i - 1);
                
                String dataColumn = datoConsulta.getDatoColumna(
                        i,
                        datosColumna.columnType,
                        datosColumna
                );
                dataColumn = cleanDataColumScale(dataColumn, datosColumna.scale);
                
                datosFila[i-1] = dataColumn;
            }
        } catch (SQLException ex) {
            throw new ExecutorTextSQLException(ex);
        }
        
        return datosFila;
    }
    
    private String cleanDataColumScale(String dataColumn, int scale) {
        if(scale <= 0 && dataColumn != null) {
            if(dataColumn.endsWith(".0")) {
                dataColumn = dataColumn.substring(
                        0,
                        dataColumn.length() - 2
                );
            }
        }
        return dataColumn;
    }
    
    private void cerrarConexion() {
        if(rsQuery != null) {
            try {
                rsQuery.close();
            } catch (SQLException ex) {
                //No manejamos los errores al cerrar la conexión
            }
        }
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                //No manejamos los errores al cerrar la conexión
            }
        }
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                //No manejamos los errores al cerrar la conexión
            }
        }
        
    }
}
