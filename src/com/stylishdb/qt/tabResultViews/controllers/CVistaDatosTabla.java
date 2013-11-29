package com.stylishdb.qt.tabResultViews.controllers;

import com.stylishdb.db.domain.ResultadoEjecutarConsulta;
import com.stylishdb.qt.controllers.CMiControladorGenerico;
import com.stylishdb.qt.tabResultViews.VistaDatosTabla;

/**
 *
 ** @author StylishDB
 */
public class CVistaDatosTabla extends CMiControladorGenerico {
    
    private VistaDatosTabla vistaDatos;
    
    public CVistaDatosTabla() {
        super();
        
        vistaDatos = new VistaDatosTabla(this);
    }
    
    public VistaDatosTabla getVistaDatosTabla() {
        return vistaDatos;
    }
    
    public void pintarDatosTabla(ResultadoEjecutarConsulta resultadoConsulta) {
        vistaDatos.pintarDatosTabla(resultadoConsulta);
    }

    void liberarWidget() {
        vistaDatos.dispose();
    }
}
