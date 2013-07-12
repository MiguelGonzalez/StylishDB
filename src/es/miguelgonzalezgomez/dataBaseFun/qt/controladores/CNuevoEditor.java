package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.core.Qt;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GConexionesAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalCrearNuevoEditor;
import es.miguelgonzalezgomez.dataBaseFun.utilidadesEstaticas.CentroCoordenadas;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class CNuevoEditor {
 
    private ModalCrearNuevoEditor modalCrearNuevoEditor;
    private GConexionesAplicacion gestionadorConexiones;
    
    public CNuevoEditor() {
        gestionadorConexiones = new GConexionesAplicacion();
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
        int height = 120;
        modalCrearNuevoEditor.resize(
                width, height
                );
        modalCrearNuevoEditor.move(
                CentroCoordenadas.getXCentrada(width),
                CentroCoordenadas.getYCentradaArriba()
                );
    }
    
    private void pintarComboBoxConexionesGuardadas() {
        List<MConexion> conexiones = gestionadorConexiones.getConexiones();
        
        for(MConexion conexion : conexiones) {
            modalCrearNuevoEditor.conexionCombo.addItem(conexion.nombre, conexion);
        }
    }
    
    protected void eventoCancelar() {
        modalCrearNuevoEditor.close();
    }
    
    protected void eventoCrearEditor() {
        int index = modalCrearNuevoEditor.conexionCombo.currentIndex();
        MConexion mConexion = (MConexion) modalCrearNuevoEditor.
                conexionCombo.itemData(index);
        
        GEditoresAplicacion editoresAplicacion = new
                GEditoresAplicacion();
        
        MPestanaEditor mPestanaEditor = new MPestanaEditor(mConexion);
        editoresAplicacion.addNuevaConexion(mPestanaEditor);
        
        modalCrearNuevoEditor.close();
    }
}
