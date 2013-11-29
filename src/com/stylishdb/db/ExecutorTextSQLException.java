package com.stylishdb.db;

import java.sql.SQLException;

/**
 *
 ** @author StylishDB
 */
public class ExecutorTextSQLException extends SQLException {
    public ExecutorTextSQLException(SQLException ex) {
        super(ex);
    }
}
