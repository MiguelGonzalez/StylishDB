package com.stylishdb.qt.tabResultViews.controllers;

import com.stylishdb.db.domain.ResultExecutes;
import com.stylishdb.qt.controllers.Controller;
import com.stylishdb.qt.tabResultViews.TabInformation;

/**
 *
 ** @author StylishDB
 */
public class CTabInformation extends Controller {
    
    private TabInformation vistaDatosInformacion;
    
    public CTabInformation() {
        super();
        
        vistaDatosInformacion = new TabInformation(this);
    }
    
    public TabInformation getVistaDatosInformacion() {
        return vistaDatosInformacion;
    }
    
    public void pintarDatosInformacion(ResultExecutes resultadoConsulta) {
        vistaDatosInformacion.pintarDatosInformacion(resultadoConsulta);
    }

    void liberarWidget() {
        vistaDatosInformacion.dispose();
    }
}
