package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
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
    
    public VistaDatosConsulta getVistaDatosConsulta() {
        return vistaDatos;
    }

    public void pintarDatosConsulta(ResultadoEjecutarConsulta resultado) {
        establecerColumnas(resultado.nombresColumnas);

        anadirDatosConsulta(resultado.datosFila);
    }
    
    private void establecerColumnas(List<String> columnas) {
        vistaDatos.establecerColumnas(columnas);
    }

    private void anadirDatosConsulta(List<String[]> datosConsulta) {
        vistaDatos.anadirDatosConsulta(datosConsulta);
    }
}