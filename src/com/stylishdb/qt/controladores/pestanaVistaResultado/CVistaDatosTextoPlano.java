package com.stylishdb.qt.controladores.pestanaVistaResultado;

import com.stylishdb.bd.domain.ResultadoEjecutarConsulta;
import com.stylishdb.qt.controladores.CMiControladorGenerico;
import com.stylishdb.qt.pestanaVistaResultado.VistaDatosTextoPlano;

/**
 *
 ** @author StylishDB
 */
public class CVistaDatosTextoPlano extends CMiControladorGenerico {
    
    private VistaDatosTextoPlano datosTextoPlano;
    
    public CVistaDatosTextoPlano() {
        super();
        
        datosTextoPlano = new VistaDatosTextoPlano(this);
    }
    
    public VistaDatosTextoPlano getVistaDatosTextoPlano() {
        return datosTextoPlano;
    }

    void pintarDatosConsulta(ResultadoEjecutarConsulta resultado) {
        datosTextoPlano.pintarDatosConsulta(resultado);
    }

    void liberarWidget() {
        datosTextoPlano.dispose();
    }
    
}
