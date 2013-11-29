package com.stylishdb.db;

import com.stylishdb.domain.MConnection;
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
public class LoadTablesDB {
    
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rsQuery = null;
    
    private MConnection mConexion;
    
    public LoadTablesDB(MConnection mConexion) {
        this.mConexion = mConexion;
        
    }
    
    public void conectarContraBaseDeDatos()
            throws NoConnectionException {
        try {
            String urlConexion = mConexion.getUrlConexion();
            connection = DriverManager.getConnection(
                    urlConexion,
                    mConexion.getUsuario(),
                    mConexion.getPassword()
            );
        } catch (SQLException ex) {
            throw new NoConnectionException(ex);
        }
    }
    
    public List<String> obtenerNombresTablasBD()
            throws ExecutorTextSQLException {
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
            throw new ExecutorTextSQLException(ex);
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
