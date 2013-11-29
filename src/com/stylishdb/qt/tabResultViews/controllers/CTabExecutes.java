package com.stylishdb.qt.tabResultViews.controllers;

import com.stylishdb.db.domain.ResultExecutes;
import com.stylishdb.qt.controllers.Controller;
import com.stylishdb.qt.tabResultViews.TabExecutes;

/**
 *
 ** @author StylishDB
 */
public class CTabExecutes extends Controller {
    
    private TabExecutes vistaDatos;
    
    public CTabExecutes() {
        super();
        
        vistaDatos = new TabExecutes(this);
    }
    
    public TabExecutes getVistaDatosConsulta() {
        return vistaDatos;
    }

    public void pintarDatosConsulta(ResultExecutes resultadoEjecutarConsulta) {
        vistaDatos.pintarDatos(resultadoEjecutarConsulta);
    }

    void liberarWidget() {
        vistaDatos.dispose();
    }
}
