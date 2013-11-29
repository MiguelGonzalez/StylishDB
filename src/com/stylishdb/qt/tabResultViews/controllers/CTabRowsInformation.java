package com.stylishdb.qt.tabResultViews.controllers;

import com.stylishdb.db.domain.ResultExecutes;
import com.stylishdb.qt.controllers.Controller;
import com.stylishdb.qt.tabResultViews.TabRowsInformation;

/**
 *
 ** @author StylishDB
 */
public class CTabRowsInformation extends Controller {
    
    private TabRowsInformation vistaDatos;
    
    public CTabRowsInformation() {
        super();
        
        vistaDatos = new TabRowsInformation(this);
    }
    
    public TabRowsInformation getVistaDatosTabla() {
        return vistaDatos;
    }
    
    public void pintarDatosTabla(ResultExecutes resultadoConsulta) {
        vistaDatos.pintarDatosTabla(resultadoConsulta);
    }

    void liberarWidget() {
        vistaDatos.dispose();
    }
}
