package com.stylishdb.db;

import com.stylishdb.domain.MConexion;
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
 ** @author StylishDB
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
                    mConexion.getUsuario(),
                    mConexion.getPassword()
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
            
            ResultSet rsMetaData = metaData.getTables(null,
                    null,
                    "%",
                    new String[] {"TABLE"}
            );
            while (rsMetaData.next()) {
                String nombreTabla = rsMetaData.getString("TABLE_NAME");
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
