package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class ObtencionTablasBaseDatos {
    
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rsQuery = null;
    
    private MConexion mConexion;
    
    public ObtencionTablasBaseDatos(MConexion mConexion) {
        this.mConexion = mConexion;
        
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
    
    public List<String> obtenerNombresTablasBD()
            throws ManejadorConsultaErrorSQL {
        List<String> nombresTablasBD = new ArrayList<>();
        
        try {
            statement = connection.createStatement();
            DatabaseMetaData metaData = connection.getMetaData();
            
            ResultSet rsMetaData = metaData.getTables(null, null, "%", null);
            while (rsMetaData.next()) {
                String nombreTabla = rsMetaData.getString(3);
                nombresTablasBD.add(nombreTabla);
            }
            
        } catch (SQLException ex) {
            throw new ManejadorConsultaErrorSQL(ex);
        } finally {
            cerrarConexion();
        }
        
        return nombresTablasBD;
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
