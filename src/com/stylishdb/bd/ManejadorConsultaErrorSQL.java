package com.stylishdb.bd;

import java.sql.SQLException;

/**
 *
 ** @author StylishDB
 */
public class ManejadorConsultaErrorSQL extends SQLException {
    public ManejadorConsultaErrorSQL(SQLException ex) {
        super(ex);
    }
}
