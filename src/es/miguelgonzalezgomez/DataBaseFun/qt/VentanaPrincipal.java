package es.miguelgonzalezgomez.DataBaseFun.qt;

import com.trolltech.qt.gui.QMainWindow;
import es.miguelgonzalezgomez.DataBaseFun.ControladorVentanaPrincipal;

/**
 *
 * @author Miguel González
 */
public class VentanaPrincipal extends QMainWindow {
    
    private ControladorVentanaPrincipal controlador;
    
    public VentanaPrincipal(String tituloVentana,
            ControladorVentanaPrincipal controlador) {
        setWindowTitle(tituloVentana);
        
        this.controlador = controlador;
    }
}
