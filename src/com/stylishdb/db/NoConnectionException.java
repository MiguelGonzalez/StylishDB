package com.stylishdb.db;

import java.sql.SQLException;

/**
 *
 ** @author StylishDB
 */
public class NoConnectionException extends SQLException {

    public NoConnectionException(SQLException ex) {
        super(ex);
    }
}
