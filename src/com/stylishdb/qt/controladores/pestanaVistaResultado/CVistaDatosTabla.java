package com.stylishdb.qt.controladores.pestanaVistaResultado;

import com.stylishdb.bd.domain.ResultadoEjecutarConsulta;
import com.stylishdb.qt.controladores.CMiControladorGenerico;
import com.stylishdb.qt.pestanaVistaResultado.VistaDatosTabla;

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
