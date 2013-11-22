package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CMiControladorGenerico;
import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.VistaDatosTextoPlano;

/**
 *
 * @author Miguel González
 */
public class CVistaDatosTextoPlano extends CMiControladorGenerico {
    
    private VistaDatosTextoPlano datosTextoPlano;
    
    public CVistaDatosTextoPlano() {
        super();
        
        datosTextoPlano = new VistaDatosTextoPlano(this);
    }
    
    public VistaDatosTextoPlano getVistaDatosTextoPlano() {
        return datosTextoPlano;
    }

    void pintarDatosConsulta(ResultadoEjecutarConsulta resultado) {
        datosTextoPlano.pintarDatosConsulta(resultado);
    }

    void liberarWidget() {
        datosTextoPlano.dispose();
    }
    
}
