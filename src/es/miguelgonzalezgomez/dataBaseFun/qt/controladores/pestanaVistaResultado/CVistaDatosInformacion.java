package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CMiControladorGenerico;
import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.VistaDatosInformacion;

/**
 *
 * @author Miguel González
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
