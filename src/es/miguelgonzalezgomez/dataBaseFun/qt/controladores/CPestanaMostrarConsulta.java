package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsulta;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsultaErrorSQL;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsultaNoHayConexion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.qt.PestanaMostrarResultadoConsulta;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalMostrarAviso;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class CPestanaMostrarConsulta {
    
    private ManejadorConsulta manejadorConsulta;
    private PestanaMostrarResultadoConsulta pestanaResultado;
    
    private String consultaSQL;
    private MConexion mConexion;
    
    public CPestanaMostrarConsulta(MConexion mConexion,
            String consultaSQL) {
        this.consultaSQL = consultaSQL;
        this.mConexion = mConexion;
        
        pestanaResultado = new PestanaMostrarResultadoConsulta(this);
        manejadorConsulta = new ManejadorConsulta();
        
        if(conectarContraBaseDeDatos()) {
            if(lanzarConsulta()) {
                pintarRespuestaConsulta();
                
                cerrarConexionContraBaseDeDatos();
            }
        }
    }
    
    public QWidget getPestanaResultado() {
        return pestanaResultado;
    }
    
    private boolean conectarContraBaseDeDatos() {
        try {
            manejadorConsulta.conectarContraBaseDeDatos(mConexion);
            return true;
        } catch (ManejadorConsultaNoHayConexion ex) {
            mostrarErrorEnPantalla(
                    "Connection exception",
                    ex.getMessage()
            );
        }
        return false;
    }
    
    private boolean lanzarConsulta() {
        try {
            manejadorConsulta.lanzarConsulta(consultaSQL);
            return true;
        } catch (ManejadorConsultaErrorSQL ex) {
            mostrarErrorEnPantalla(
                    "Error al ejecutar la consulta",
                    ex.getMessage()
            );
        }
        return false;
    }
    
    private void pintarRespuestaConsulta() {
        try {
            List<String> columnas = manejadorConsulta.getNombresColumnas();
            pestanaResultado.establecerColumnas(columnas);
            
            int numFilas = 0;
            while(manejadorConsulta.haySiguienteFila() && numFilas < 100) {
                List<String> datosFila = manejadorConsulta.getFila();
                
                pestanaResultado.anadirDatosFila(datosFila);
                numFilas++;
            }
        } catch (ManejadorConsultaErrorSQL ex) {
            mostrarErrorEnPantalla(
                    "Error al recuperar los datos de la tabla",
                    ex.getMessage()
            );
        }
    }
    
    private void cerrarConexionContraBaseDeDatos() {
        manejadorConsulta.cerrarConexion();
    }
    
    private void mostrarErrorEnPantalla(String tituloError, String mensajeError) {
        ModalMostrarAviso.mostrarErrorEnPantalla(tituloError,
                mensajeError);
    }
    
}
