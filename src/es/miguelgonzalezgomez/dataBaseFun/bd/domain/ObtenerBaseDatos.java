package es.miguelgonzalezgomez.dataBaseFun.bd.domain;

import static es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.TiposBasesDeDatos.TIPO_BASE_DATOS;

/**
 *
 * @author Miguel González
 */
public class ObtenerBaseDatos {
    public BaseDatosGenerica obtenerBaseDatos(TIPO_BASE_DATOS tipoDeBaseDeDatos) {
        
        if(TIPO_BASE_DATOS.MYSQL.equals(tipoDeBaseDeDatos)) {
            return new BaseDatosMySQL();
        }
        
        return null;
    }
}
