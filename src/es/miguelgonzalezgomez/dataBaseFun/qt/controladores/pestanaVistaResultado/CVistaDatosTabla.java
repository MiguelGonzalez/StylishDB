package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.VistaDatosTabla;

/**
 *
 * @author Miguel González
 */
public class CVistaDatosTabla {
    
    private VistaDatosTabla vistaDatos;
    
    public CVistaDatosTabla() {
        vistaDatos = new VistaDatosTabla(this);
    }
    
    public VistaDatosTabla getVistaDatosTabla() {
        return vistaDatos;
    }
    
    public void pintarDatosTabla(ResultadoEjecutarConsulta resultadoConsulta) {
        vistaDatos.pintarDatosTabla(resultadoConsulta);
    }
}
