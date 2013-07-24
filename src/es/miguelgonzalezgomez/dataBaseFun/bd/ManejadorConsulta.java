package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.ObtenerUrlConexion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel González
 */
public class ManejadorConsulta {
    
    
    private MConexion conexion;
    private String consultaSQL;
    
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rsQuery = null;
    
    public ManejadorConsulta(MConexion conexion) {
        this.conexion = conexion;
    }
    
    public void ejecutarConsulta(String consultaSQL) {
        this.consultaSQL = consultaSQL;
        
        if(ComprobacionConexion.hayConexion(conexion)) {
            conectar();
            
            lanzarConsulta();
        }
    }
    
    private void conectar() {
        String urlConexion = ObtenerUrlConexion.getUrlConexion(
                conexion.gestor,
                conexion.ip,
                conexion.puerto,
                conexion.sid);
        try {
            connection = DriverManager.getConnection(
                    urlConexion,
                    conexion.usuario,
                    conexion.password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void lanzarConsulta() {
        try {
            statement = connection.createStatement();
            rsQuery = statement.executeQuery(consultaSQL);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean haySiguienteFila() {
        try {
            return rsQuery.next();
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getNumColumnas() {
        try {
            return rsQuery.getMetaData().getColumnCount();
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public List<String> getNombresColumnas() {
        List<String> nombresColumnas = new ArrayList<>();
        
        int numColumnas = getNumColumnas();
        for(int i=1; i<=numColumnas; i++) {
            try {
                nombresColumnas.add(rsQuery.getMetaData().getColumnName(i));
            } catch (SQLException ex) {
                nombresColumnas.add("");
                Logger.getLogger(ManejadorConsulta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return nombresColumnas;
    }
    
    public List<String> getFila() {
        List<String> fila = new ArrayList<>();
        
        int numColumnas = getNumColumnas();
        for(int i=1; i<=numColumnas; i++) {
            try {
                int tipoColumna = rsQuery.getMetaData().getColumnType(i);
                String datoColumna = getDatoColumna(i, tipoColumna);
               
                fila.add(datoColumna);
            } catch (SQLException ex) {
                fila.add("");
                Logger.getLogger(ManejadorConsulta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return fila;
    }
    
    private String getDatoColumna(int numColumna, int tipoColumna) {
        try {
            switch(tipoColumna) {
                case java.sql.Types.ARRAY:
                    return rsQuery.getArray(numColumna).getArray().toString();
                case java.sql.Types.BIGINT: 
                    return Long.toString(rsQuery.getLong(numColumna));
                case java.sql.Types.BINARY: 
                    return "BINARY";
                case java.sql.Types.BIT: 
                    return "BIT";
                case java.sql.Types.BLOB: 
                    return rsQuery.getBlob(numColumna).toString();
                case java.sql.Types.BOOLEAN:
                    return Boolean.toString(rsQuery.getBoolean(numColumna));
                case java.sql.Types.CHAR: 
                    return rsQuery.getString(numColumna);
                case java.sql.Types.CLOB: 
                    return rsQuery.getClob(numColumna).toString();
                case java.sql.Types.DATALINK: 
                    return "DATALINK";
                case java.sql.Types.DATE: 
                    return rsQuery.getDate(numColumna).toString();
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
                    return Long.toString(rsQuery.getLong(numColumna));
                case java.sql.Types.LONGVARBINARY: 
                    return Long.toString(rsQuery.getLong(numColumna));
                case java.sql.Types.LONGVARCHAR: 
                    return Long.toString(rsQuery.getLong(numColumna));
                case java.sql.Types.NCHAR: 
                    return rsQuery.getNString(numColumna);
                case java.sql.Types.NCLOB: 
                    return rsQuery.getNClob(numColumna).toString();
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
                    return rsQuery.getTimestamp(numColumna).toString();
                case java.sql.Types.TINYINT: 
                    return Integer.toString(rsQuery.getInt(numColumna));
                case java.sql.Types.VARBINARY: 
                    return "VARBINARY";
                case java.sql.Types.VARCHAR:
                    return rsQuery.getString(numColumna);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public void cerrarConsultaSQL() {
        if(rsQuery != null) {
            try {
                rsQuery.close();
            } catch (SQLException ex) {
                Logger.getLogger(ManejadorConsulta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(ManejadorConsulta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ManejadorConsulta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
}