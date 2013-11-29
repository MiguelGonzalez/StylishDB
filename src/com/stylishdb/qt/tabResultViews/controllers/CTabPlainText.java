package com.stylishdb.qt.tabResultViews.controllers;

import com.stylishdb.db.domain.ResultExecutes;
import com.stylishdb.qt.controllers.Controller;
import com.stylishdb.qt.tabResultViews.TabPlainText;

/**
 *
 ** @author StylishDB
 */
public class CTabPlainText extends Controller {
    
    private TabPlainText datosTextoPlano;
    
    public CTabPlainText() {
        super();
        
        datosTextoPlano = new TabPlainText(this);
    }
    
    public TabPlainText getVistaDatosTextoPlano() {
        return datosTextoPlano;
    }

    void pintarDatosConsulta(ResultExecutes resultado) {
        datosTextoPlano.pintarDatosConsulta(resultado);
    }

    void liberarWidget() {
        datosTextoPlano.dispose();
    }
    
}
