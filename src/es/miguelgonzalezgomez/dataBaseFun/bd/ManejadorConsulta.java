package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.bd.domain.DatosColumna;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
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
 * @author Miguel González
 */
public class ManejadorConsulta {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rsQuery = null;
    
    private MConexion mConexion;
    private String consultaSQL;
    private ResultadoEjecutarConsulta resultadoEjecutar;
    private long tiempoParaConectarContraBaseDeDatos;

    public ManejadorConsulta(MConexion mConexion, String consultaSQL) {
        this.mConexion = mConexion;
        this.consultaSQL = consultaSQL;
        
        resultadoEjecutar= new ResultadoEjecutarConsulta();
    }

    public void conectarContraBaseDeDatos()
            throws ManejadorConsultaNoHayConexion {
        try {
            long time_start = System.currentTimeMillis();
            String urlConexion = mConexion.getUrlConexion();
            connection = DriverManager.getConnection(
                    urlConexion,
                    mConexion.usuario,
                    mConexion.password
            );
            long time_end = System.currentTimeMillis();
            tiempoParaConectarContraBaseDeDatos = time_end - time_start;
            
        } catch (SQLException ex) {
            throw new ManejadorConsultaNoHayConexion(ex);
        }
    }
    
    public void ejecutarConsulta()
            throws ManejadorConsultaErrorSQL {
        
        try {
            long time_start = System.currentTimeMillis();
            statement = connection.createStatement();
            rsQuery = statement.executeQuery(
                    consultaSQL
            );
            long time_end = System.currentTimeMillis();

            rellenarDatosConsultaEjecutada(time_end - time_start);
        } catch (SQLException ex) {
            throw new ManejadorConsultaErrorSQL(ex);
        } finally {
            cerrarConexion();
        }
    }
    
    public ResultadoEjecutarConsulta getDatosConsultaEjecutada() {
        return resultadoEjecutar;
    }
    
    private void rellenarDatosConsultaEjecutada(long tiempoEjecucionConsulta)
            throws ManejadorConsultaErrorSQL {
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
    
    private boolean haySiguienteFila() throws ManejadorConsultaErrorSQL {
        try {
            return rsQuery.next();
        } catch (SQLException ex) {
            throw new ManejadorConsultaErrorSQL(ex);
        }
    }
    
    private int getNumColumnas() throws ManejadorConsultaErrorSQL {
        try {
            return rsQuery.getMetaData().getColumnCount();
        } catch (SQLException ex) {
            throw new ManejadorConsultaErrorSQL(ex);
        }
    }
    
    private List<String> getNombresColumnas() throws ManejadorConsultaErrorSQL {
        List<String> nombresColumnas = new ArrayList<>();
        
        try {
            int numColumnas = getNumColumnas();
            for(int i=1; i<=numColumnas; i++) {
                nombresColumnas.add(rsQuery.getMetaData().getColumnName(i));
            }
        } catch (SQLException ex) {
            throw new ManejadorConsultaErrorSQL(ex);
        }
        
        return nombresColumnas;
    }
    
    private List<DatosColumna> getTiposColumnas() throws ManejadorConsultaErrorSQL {
        List<DatosColumna> tiposColumna = new ArrayList<>();
        
        try {
            int numColumnas = getNumColumnas(); 
            for(int i=1; i<=numColumnas; i++) {
                tiposColumna.add(
                        getDatosColumna(i)
                );
            }
        } catch (SQLException ex) {
            throw new ManejadorConsultaErrorSQL(ex);
        }
        
        return tiposColumna;
    }
    
    private DatosColumna getDatosColumna(int numColumna) throws SQLException {
        DatosColumna datosColumna = new DatosColumna();
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
    
    private String[] getFila(ResultadoEjecutarConsulta resultado)
            throws ManejadorConsultaErrorSQL {
        String[] datosFila = new String[resultado.numColumnas];
        ManejadorDatoConsulta datoConsulta = new
                ManejadorDatoConsulta(rsQuery);
        try {
            for(int i=1; i<=resultado.numColumnas; i++) {
                DatosColumna datosColumna = resultado.datosColumnas.get(i - 1);
                
                String datoColumna = datoConsulta.getDatoColumna(
                        i,
                        datosColumna.columnType,
                        datosColumna
                );
                if(datosColumna.scale == 0 && datoColumna != null) {
                    if(datoColumna.endsWith(".0")) {
                        datoColumna = datoColumna.substring(
                                0,
                                datoColumna.length() - 2
                        );
                    }
                }
               
                datosFila[i-1] = datoColumna;
            }
        } catch (SQLException ex) {
            throw new ManejadorConsultaErrorSQL(ex);
        }
        
        return datosFila;
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
