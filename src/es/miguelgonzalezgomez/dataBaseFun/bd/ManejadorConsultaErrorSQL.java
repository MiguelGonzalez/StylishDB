package es.miguelgonzalezgomez.dataBaseFun.bd;

import java.sql.SQLException;

/**
 *
 * @author Miguel González
 */
public class ManejadorConsultaErrorSQL extends SQLException {
    public ManejadorConsultaErrorSQL(SQLException ex) {
        super(ex);
    }
}
