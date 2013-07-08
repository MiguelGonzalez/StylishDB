package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Miguel González
 */
public class ComprobacionConexion {

    public static boolean hayConexion(MConexion conexion) {
        try {
            cargarDriver(ObtenerClaseDriver.obtenerClaseDriver(
                    conexion.gestor));
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
        
        return validarConexion(conexion);
    }
    
    private static void cargarDriver(String jdbcDriverClass)
            throws ClassNotFoundException {
        Class.forName(jdbcDriverClass);
    }
    
    private static boolean validarConexion(MConexion conexion) {
        Connection connection = null;

        String urlConexion = ObtenerUrlConexion.getUrlConexion(
                conexion.gestor,
                conexion.ip,
                conexion.puerto,
                conexion.sid);
            
        boolean seHaPodidoConectar = false;
        
        try {
            connection = DriverManager.getConnection(
                    urlConexion,
                    conexion.usuario,
                    conexion.password);

            if(connection != null) {
                seHaPodidoConectar = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return seHaPodidoConectar;
    }
    
}
