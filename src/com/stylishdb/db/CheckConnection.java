package com.stylishdb.db;

import com.stylishdb.domain.MConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 ** @author StylishDB
 */
public class CheckConnection {

    public static boolean hayConexion(MConnection conexion) {
        try {
            cargarDriver(conexion.getTipoDeBaseDeDatos().
                    getDatosBaseDatos().getClaseDriver());
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
    
    private static boolean validarConexion(MConnection conexion) {
        Connection connection = null;

        String urlConexion = conexion.getUrlConexion();
            
        boolean seHaPodidoConectar = false;
        
        try {
            connection = DriverManager.getConnection(
                    urlConexion,
                    conexion.getUsuario(),
                    conexion.getPassword());

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
