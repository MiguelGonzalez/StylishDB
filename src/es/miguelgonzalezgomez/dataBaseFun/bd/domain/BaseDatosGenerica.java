package es.miguelgonzalezgomez.dataBaseFun.bd.domain;

import es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.TiposBasesDeDatos.TIPO_BASE_DATOS;

/**
 *
 * @author Miguel González
 */
public abstract class BaseDatosGenerica {
    
    protected TIPO_BASE_DATOS tipoDeBaseDeDatos;
    
    public BaseDatosGenerica(TIPO_BASE_DATOS tipoDeBaseDeDatos) {
        this.tipoDeBaseDeDatos = tipoDeBaseDeDatos;
    }
}
