package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CMiControladorGenerico;
import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.VistaDatosConsulta;

/**
 *
 * @author Miguel González
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
}
