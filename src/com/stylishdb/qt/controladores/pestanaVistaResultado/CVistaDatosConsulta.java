package com.stylishdb.qt.controladores.pestanaVistaResultado;

import com.stylishdb.bd.domain.ResultadoEjecutarConsulta;
import com.stylishdb.qt.controladores.CMiControladorGenerico;
import com.stylishdb.qt.pestanaVistaResultado.VistaDatosConsulta;

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
