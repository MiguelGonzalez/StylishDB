package com.stylishdb.qt.tabResultViews.controllers;

import com.stylishdb.db.domain.ResultadoEjecutarConsulta;
import com.stylishdb.qt.controllers.CMiControladorGenerico;
import com.stylishdb.qt.tabResultViews.VistaDatosTextoPlano;

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
