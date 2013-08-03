package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.bd.domain.DatosColumna;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ObtenerUrlConexion;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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

    public ManejadorConsulta(MConexion mConexion, String consultaSQL) {
        this.mConexion = mConexion;
        this.consultaSQL = consultaSQL;
        
        resultadoEjecutar= new ResultadoEjecutarConsulta();
    }

    public void conectarContraBaseDeDatos()
            throws ManejadorConsultaNoHayConexion {
        try {
            String urlConexion = mConexion.getUrlConexion();
            connection = DriverManager.getConnection(
                    urlConexion,
                    mConexion.usuario,
                    mConexion.password
            );
        } catch (SQLException ex) {
            throw new ManejadorConsultaNoHayConexion(ex);
        }
    }
    
    public void ejecutarConsulta()
            throws ManejadorConsultaErrorSQL {
        
        try {
            statement = connection.createStatement();
            rsQuery = statement.executeQuery(
                    consultaSQL
            );

            rellenarDatosConsultaEjecutada();
        } catch (SQLException ex) {
            throw new ManejadorConsultaErrorSQL(ex);
        } finally {
            cerrarConexion();
        }
    }
    
    public ResultadoEjecutarConsulta getDatosConsultaEjecutada() {
        return resultadoEjecutar;
    }
    
    private void rellenarDatosConsultaEjecutada() throws ManejadorConsultaErrorSQL {
        resultadoEjecutar.datosColumnas = getTiposColumnas();
        resultadoEjecutar.numColumnas = getNumColumnas();
        resultadoEjecutar.nombresColumnas = getNombresColumnas();

        while(haySiguienteFila()) {
            resultadoEjecutar.datosFila.add(
                    getFila(resultadoEjecutar)
            );
        }
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
        datosColumna.precission = rsMetaData.getPrecision(numColumna);
        datosColumna.scale = rsMetaData.getScale(numColumna);
        
        return datosColumna;
        
    }
    
    private String[] getFila(ResultadoEjecutarConsulta resultado)
            throws ManejadorConsultaErrorSQL {
        String[] datosFila = new String[resultado.numColumnas];
        
        try {
            for(int i=1; i<=resultado.numColumnas; i++) {
                DatosColumna datosColumna = resultado.datosColumnas.get(i - 1);
                
                String datoColumna = getDatoColumna(i, datosColumna.columnType);
               
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
    
    private String getDatoColumna(int numColumna, int tipoColumna) throws
                ManejadorConsultaErrorSQL {
        try {
            switch(tipoColumna) {
                case java.sql.Types.ARRAY:
                    Array array = rsQuery.getArray(numColumna);
                    if(array == null) {
                        return "NULL";
                    }
                    return array.getArray().toString();
                case java.sql.Types.BIGINT:
                    return Long.toString(rsQuery.getLong(numColumna));
                case java.sql.Types.BINARY: 
                    return "BINARY";
                case java.sql.Types.BIT: 
                    return "BIT";
                case java.sql.Types.BLOB: 
                    Blob blob = rsQuery.getBlob(numColumna);
                    return blob == null ? "NULL" : blob.toString();
                case java.sql.Types.BOOLEAN:
                    return Boolean.toString(rsQuery.getBoolean(numColumna));
                case java.sql.Types.CHAR: 
                    return rsQuery.getString(numColumna);
                case java.sql.Types.CLOB: 
                    Clob clob = rsQuery.getClob(numColumna);
                    return clob == null ? "NULL" : clob.toString();
                case java.sql.Types.DATALINK: 
                    return "DATALINK";
                case java.sql.Types.DATE: 
                    Date date = rsQuery.getDate(numColumna);
                    return date == null ? "NULL" : date.toString();
                case java.sql.Types.DECIMAL: 
                    return Double.toString(rsQuery.getDouble(numColumna));
                case java.sql.Types.DISTINCT: 
                    return "DISTINCT";
                case java.sql.Types.DOUBLE: 
                    return Double.toString(rsQuery.getDouble(numColumna));
                case java.sql.Types.FLOAT: 
                    return Float.toString(rsQuery.getFloat(numColumna));
                case java.sql.Types.INTEGER: 
                    return Integer.toString(rsQuery.getInt(numColumna));
                case java.sql.Types.JAVA_OBJECT: 
                    return "JAVA_OBJECT";
                case java.sql.Types.LONGNVARCHAR: 
                    return rsQuery.getString(numColumna);
                case java.sql.Types.LONGVARBINARY: 
                    return rsQuery.getString(numColumna);
                case java.sql.Types.LONGVARCHAR: 
                    return rsQuery.getString(numColumna);
                case java.sql.Types.NCHAR: 
                    return rsQuery.getNString(numColumna);
                case java.sql.Types.NCLOB: 
                    NClob nClob = rsQuery.getNClob(numColumna);
                    return nClob == null? "NULL" : nClob.toString();
                case java.sql.Types.NULL: 
                    return "NULL";
                case java.sql.Types.NUMERIC: 
                    return Float.toString(rsQuery.getFloat(numColumna));
                case java.sql.Types.NVARCHAR: 
                    return rsQuery.getNString(numColumna);
                case java.sql.Types.OTHER: 
                    return "OTHER";
                case java.sql.Types.REAL: 
                    return Double.toString(rsQuery.getDouble(numColumna));
                case java.sql.Types.REF: 
                    return rsQuery.getRef(numColumna).getObject().toString();
                case java.sql.Types.ROWID: 
                    return "ROWID";
                case java.sql.Types.SMALLINT: 
                    return Integer.toString(rsQuery.getInt(numColumna));
                case java.sql.Types.SQLXML: 
                    return "SQLXML";
                case java.sql.Types.STRUCT: 
                    return "STRUCT";
                case java.sql.Types.TIME: 
                    return rsQuery.getTime(numColumna).toString();
                case java.sql.Types.TIMESTAMP: 
                    Timestamp timestamp = rsQuery.getTimestamp(numColumna);
                    return timestamp == null? "NULL" : timestamp.toString();
                case java.sql.Types.TINYINT: 
                    return Integer.toString(rsQuery.getInt(numColumna));
                case java.sql.Types.VARBINARY: 
                    return "VARBINARY";
                case java.sql.Types.VARCHAR:
                    return rsQuery.getString(numColumna);
            }
        } catch (SQLException ex) {
            throw new ManejadorConsultaErrorSQL(ex);
        }
        return "";
    }
}
