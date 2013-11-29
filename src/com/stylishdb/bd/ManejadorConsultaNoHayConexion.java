package com.stylishdb.bd;

import java.sql.SQLException;

/**
 *
 ** @author StylishDB
 */
public class ManejadorConsultaNoHayConexion extends SQLException {

    ManejadorConsultaNoHayConexion(SQLException ex) {
        super(ex);
    }
}
