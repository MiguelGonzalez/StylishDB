package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsulta;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsultaErrorSQL;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsultaNoHayConexion;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoActualizarConsultas;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalMostrarAviso;
import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.PestanaActualizarConsultas;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class CPestanaActualizarConsultas {
    private ManejadorConsulta manejadorConsulta;
    private PestanaActualizarConsultas pestanaConsultas;
    
    private MConexion mConexion;
    private List<String> consultasActualizar;
    
    public CPestanaActualizarConsultas(MConexion mConexion,
            List<String> consultasActualizar) throws ManejadorConsultaErrorSQL {
        this.mConexion = mConexion;
        this.consultasActualizar = consultasActualizar;
        
        crearControladoresYComponentes();
        ejecutarPasosLanzarQuery();
    }

    public QWidget getPestanaResultado() {
        return pestanaConsultas;
    }
    
    private void crearControladoresYComponentes() {
        manejadorConsulta = new ManejadorConsulta(
                mConexion,
                consultasActualizar
        );
        
        pestanaConsultas = new PestanaActualizarConsultas(this);
    }
    
    private void ejecutarPasosLanzarQuery() throws ManejadorConsultaErrorSQL {
        if(conectarContraBaseDeDatos()) {
            ejecutarActualizarConsultas();
            pintarRespuestasActualizacion();
        }
    }
    
    private boolean conectarContraBaseDeDatos() {
        try {
            manejadorConsulta.conectarContraBaseDeDatos();
            return true;
        } catch (ManejadorConsultaNoHayConexion ex) {
            mostrarErrorEnPantalla(
                    "Connection exception",
                    ex.getMessage()
            );
        }
        return false;
    }
    
    private void ejecutarActualizarConsultas() throws ManejadorConsultaErrorSQL {
        manejadorConsulta.actualizarConsultas();
    }
    
    private void pintarRespuestasActualizacion() {
        ResultadoActualizarConsultas resultadoActualizarConsultas =
                manejadorConsulta.getResultadoActualizacionConsultas();
        
        pestanaConsultas.pintarResultado(
                resultadoActualizarConsultas
        );
    }
    
    private void mostrarErrorEnPantalla(String tituloError, String mensajeError) {
        ModalMostrarAviso.mostrarErrorEnPantalla(tituloError,
                mensajeError);
    }
}
