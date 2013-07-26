package es.miguelgonzalezgomez.dataBaseFun.bd;

import java.sql.SQLException;

/**
 *
 * @author Miguel González
 */
public class ManejadorConsultaNoHayConexion extends SQLException {

    ManejadorConsultaNoHayConexion(SQLException ex) {
        super(ex);
    }
}
