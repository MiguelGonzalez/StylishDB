package com.stylishdb.qt.tabResultViews.controllers;

import com.stylishdb.db.domain.ResultadoEjecutarConsulta;
import com.stylishdb.qt.controllers.CMiControladorGenerico;
import com.stylishdb.qt.tabResultViews.VistaDatosConsulta;

/**
 *
 ** @author StylishDB
 */
public class CVistaDatosConsulta extends CMiControladorGenerico {
    
    private VistaDatosConsulta vistaDatos;
    
    public CVistaDatosConsulta() {
        super();
        
        vistaDatos = new VistaDatosConsulta(this);
    }
    
    public VistaDatosConsulta getVistaDatosConsulta() {
        return vistaDatos;
    }

    public void pintarDatosConsulta(ResultadoEjecutarConsulta resultadoEjecutarConsulta) {
        vistaDatos.pintarDatos(resultadoEjecutarConsulta);
    }

    void liberarWidget() {
        vistaDatos.dispose();
    }
}
