package es.miguelgonzalezgomez.DataBaseFun.qt;

import com.trolltech.qt.gui.QMainWindow;

/**
 *
 * @author Miguel González
 */
public class VentanaPrincipal extends QMainWindow {
    
    private es.miguelgonzalezgomez.DataBaseFun.Controladores.CVentanaPrincipal controlador;
    
    public VentanaPrincipal(String tituloVentana,
            es.miguelgonzalezgomez.DataBaseFun.Controladores.CVentanaPrincipal controlador) {
        setWindowTitle(tituloVentana);
        
        this.controlador = controlador;
    }
}
