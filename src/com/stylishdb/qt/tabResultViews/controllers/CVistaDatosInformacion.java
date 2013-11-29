package com.stylishdb.qt.tabResultViews.controllers;

import com.stylishdb.db.domain.ResultadoEjecutarConsulta;
import com.stylishdb.qt.controllers.CMiControladorGenerico;
import com.stylishdb.qt.tabResultViews.VistaDatosInformacion;

/**
 *
 ** @author StylishDB
 */
public class CVistaDatosInformacion extends CMiControladorGenerico {
    
    private VistaDatosInformacion vistaDatosInformacion;
    
    public CVistaDatosInformacion() {
        super();
        
        vistaDatosInformacion = new VistaDatosInformacion(this);
    }
    
    public VistaDatosInformacion getVistaDatosInformacion() {
        return vistaDatosInformacion;
    }
    
    public void pintarDatosInformacion(ResultadoEjecutarConsulta resultadoConsulta) {
        vistaDatosInformacion.pintarDatosInformacion(resultadoConsulta);
    }

    void liberarWidget() {
        vistaDatosInformacion.dispose();
    }
}
