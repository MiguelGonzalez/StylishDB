package com.stylishdb.qt.controladores.pestanaVistaResultado;

import com.stylishdb.bd.domain.ResultadoEjecutarConsulta;
import com.stylishdb.qt.controladores.CMiControladorGenerico;
import com.stylishdb.qt.pestanaVistaResultado.VistaDatosInformacion;

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
