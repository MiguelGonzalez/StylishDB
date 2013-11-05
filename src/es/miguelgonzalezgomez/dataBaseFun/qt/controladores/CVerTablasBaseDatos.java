package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.core.Qt;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsultaErrorSQL;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsultaNoHayConexion;
import es.miguelgonzalezgomez.dataBaseFun.bd.ObtencionTablasBaseDatos;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalMostrarAviso;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalVerTablasBaseDatos;
import es.miguelgonzalezgomez.dataBaseFun.utilidadesEstaticas.CentroCoordenadas;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class CVerTablasBaseDatos {

    private GEditoresAplicacion gestionadorEditores;
    private MPestanaEditor mPestana;
    
    private ObtencionTablasBaseDatos obtencionTablas;
    
    private ModalVerTablasBaseDatos modalVerTablasBD;

    public CVerTablasBaseDatos() {
        gestionadorEditores = new GEditoresAplicacion();
    }
    
    void mostrarRenombrarPestanaActiva() {
        if(gestionadorEditores.hayPestanaActiva()) {
            modalVerTablasBD = new ModalVerTablasBaseDatos(this);        
            mPestana = obtenerPestanaActiva();
            
            cargarDisenoVentanaModal();
            mostrarVentanaModal();
        }
    }
    
    public MPestanaEditor obtenerPestanaActiva() {
        return gestionadorEditores.getMPestanaActiva();
    }

    private void cargarDisenoVentanaModal() {
        modalVerTablasBD.construirInterfaz();
        establecerDisenoInterfaz();
        posicionarVentanaModal();
        cargarTablasBD();
    }

    private void mostrarVentanaModal() {
        modalVerTablasBD.show();
    }
    
    private void establecerDisenoInterfaz() {
        modalVerTablasBD.setWindowFlags(Qt.WindowType.FramelessWindowHint);
        recargarEstiloModal();
    }
    
    private void posicionarVentanaModal() {
        int width = 300;
        int height = 500;
        modalVerTablasBD.resize(
                width, height
                );
        modalVerTablasBD.move(
                CentroCoordenadas.getXCentrada(width),
                CentroCoordenadas.getYCentradaArriba()
                );
    }
    
    protected void recargarEstiloModal() {
        modalVerTablasBD.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("dialogEstilo.css")
        );
    }
    
    private void cargarTablasBD() {
        obtencionTablas = new ObtencionTablasBaseDatos(mPestana.mConexion);
        try {
            obtencionTablas.conectarContraBaseDeDatos();
            
            List<String> nombresTablas = obtencionTablas.obtenerNombresTablasBD();
            
            modalVerTablasBD.cargarNombresTablas(nombresTablas);
        } catch (ManejadorConsultaNoHayConexion ex) {
            mostrarErrorEnPantalla(
                    "Connection exception",
                    ex.getMessage()
            );
        } catch (ManejadorConsultaErrorSQL ex) {
            mostrarErrorEnPantalla(
                    "Connection exception",
                    ex.getMessage()
            );
        }
    }
    
    private void mostrarErrorEnPantalla(String tituloError, String mensajeError) {
        ModalMostrarAviso.mostrarErrorEnPantalla(tituloError,
                mensajeError);
    }
}
