package es.miguelgonzalezgomez.DataBaseFun.qt;

import com.trolltech.qt.gui.QCloseEvent;
import com.trolltech.qt.gui.QMainWindow;
import es.miguelgonzalezgomez.DataBaseFun.Controladores.CVentanaPrincipal;

/**
 *
 * @author Miguel González
 */
public class VentanaPrincipal extends QMainWindow {
    
    private CVentanaPrincipal controlador;
    
    public VentanaPrincipal(String tituloVentana,
            es.miguelgonzalezgomez.DataBaseFun.Controladores.CVentanaPrincipal controlador) {
        setWindowTitle(tituloVentana);
                
        this.controlador = controlador;
    }

    @Override
    protected void closeEvent(QCloseEvent event) {
        super.closeEvent(event);
        
        controlador.salirAplicacion();
    }
    
    
}
