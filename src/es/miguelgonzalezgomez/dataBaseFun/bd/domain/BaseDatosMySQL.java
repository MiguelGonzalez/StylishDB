package es.miguelgonzalezgomez.dataBaseFun.bd.domain;

import es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.TiposBasesDeDatos;

/**
 *
 * @author Miguel González
 */
public class BaseDatosMySQL extends BaseDatosGenerica {

    public BaseDatosMySQL() {
        super(TiposBasesDeDatos.TIPO_BASE_DATOS.MYSQL);
    }
    
}
