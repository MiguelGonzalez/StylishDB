package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.core.Qt;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.EstiloSinFoco;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalCrearNuevoEditor;
import es.miguelgonzalezgomez.dataBaseFun.utilidadesEstaticas.CentroCoordenadas;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class CNuevoEditor extends CMiControladorGenerico {
 
    private ModalCrearNuevoEditor modalCrearNuevoEditor;
    
    
    public CNuevoEditor() {
        super();
        
        modalCrearNuevoEditor = new ModalCrearNuevoEditor(this);
        
        posicionarVentanaModal();
    }
    
    public void lanzar() {
        modalCrearNuevoEditor.construirYLanzarInterfaz();
        modalCrearNuevoEditor.setWindowFlags(Qt.WindowType.FramelessWindowHint);
        modalCrearNuevoEditor.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("dialogEstilo.css")
        );
        
        pintarComboBoxConexionesGuardadas();
        
        modalCrearNuevoEditor.show();
    }
    
    private void posicionarVentanaModal() {
        int width = 350; 
        modalCrearNuevoEditor.setFixedWidth(width);
        modalCrearNuevoEditor.move(
                CentroCoordenadas.getXCentrada(width),
                CentroCoordenadas.getYCentradaArriba()
                );
    }
    
    private void pintarComboBoxConexionesGuardadas() {
        List<MConexion> conexiones = gestionadorConexiones.getConexiones();
        
        for(MConexion conexion : conexiones) {
            modalCrearNuevoEditor.conexionListWidget.addItem(conexion.nombre);
        }
        modalCrearNuevoEditor.conexionListWidget.setStyle(new EstiloSinFoco());
    }
    
    protected void eventoCrearEditor() {
        GEditoresAplicacion editoresAplicacion = new
                GEditoresAplicacion();
        
        String nombreConexion = modalCrearNuevoEditor.conexionListWidget.
                currentItem().text();
        
        MConexion conexion = gestionadorConexiones.getMConexionNombre(nombreConexion);
        
        
        MPestanaEditor mPestanaEditor = new MPestanaEditor(
                conexion.uuidConexion,
                conexion.nombre);
        editoresAplicacion.addNuevaPestanaEditor(mPestanaEditor);
        
        modalCrearNuevoEditor.close();
    }
}
