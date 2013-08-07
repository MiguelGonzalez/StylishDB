package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.VistaDatosInformacion;

/**
 *
 * @author Miguel González
 */
public class CVistaDatosInformacion {
    
    private VistaDatosInformacion vistaDatosInformacion;
    
    public CVistaDatosInformacion() {
        vistaDatosInformacion = new VistaDatosInformacion(this);
    }
    
    public VistaDatosInformacion getVistaDatosInformacion() {
        return vistaDatosInformacion;
    }
    
    public void pintarDatosInformacion(ResultadoEjecutarConsulta resultadoConsulta) {
        vistaDatosInformacion.pintarDatosInformacion(resultadoConsulta);
    }
}
