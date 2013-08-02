package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.VistaDatosConsulta;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class CVistaDatosConsulta {
    
    private VistaDatosConsulta vistaDatos;
    
    public CVistaDatosConsulta() {
        vistaDatos = new VistaDatosConsulta(this);
    }

    void establecerColumnas(List<String> columnas) {
        vistaDatos.establecerColumnas(columnas);
    }

    void anadirDatosFila(List<String> datosFila) {
        vistaDatos.anadirDatosFila(datosFila);
    }
    
    public VistaDatosConsulta getVistaDatosConsulta() {
        return vistaDatos;
    }
}
