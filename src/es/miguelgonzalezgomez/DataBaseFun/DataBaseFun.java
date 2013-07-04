package es.miguelgonzalezgomez.DataBaseFun;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QWidget;

/**
 *
 * @author Miguel González
 */
public class DataBaseFun extends QWidget {
    
    private static ControladorVentanaPrincipal controladorVentanaPrincipal;
    
    public static void main(String args[]) {
        QApplication.initialize(args);
        
        controladorVentanaPrincipal = new
                ControladorVentanaPrincipal();
        
        QApplication.exec();
    }
    
    public static void salirAplicacion() {
        controladorVentanaPrincipal.salirAplicacion();
    }
}
