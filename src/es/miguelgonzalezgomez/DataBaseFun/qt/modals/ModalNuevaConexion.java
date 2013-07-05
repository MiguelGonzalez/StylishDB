package es.miguelgonzalezgomez.DataBaseFun.qt.modals;

import com.trolltech.qt.gui.QDialog;
import es.miguelgonzalezgomez.DataBaseFun.Controladores.CNuevaConexion;

/**
 *
 * @author Miguel González
 */
public class ModalNuevaConexion extends QDialog {
    
    private CNuevaConexion controlador;
    
    public ModalNuevaConexion(String tituloVentana,
            CNuevaConexion controlador) {
        this.controlador = controlador;
        
        setWindowTitle(tr(tituloVentana));
        setModal(true);
    }
    
}
