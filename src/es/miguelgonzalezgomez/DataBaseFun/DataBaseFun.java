package es.miguelgonzalezgomez.DataBaseFun;

import es.miguelgonzalezgomez.DataBaseFun.Controladores.CVentanaPrincipal;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QWidget;

/**
 *
 * @author Miguel González
 */
public class DataBaseFun extends QWidget {
    
    private static CVentanaPrincipal controladorVentanaPrincipal;
    
    public static void main(String args[]) {
        QApplication.initialize(args);
        
        controladorVentanaPrincipal = new
                CVentanaPrincipal();
        
        QApplication.exec();
    }
    
    public static void salirAplicacion() {
        controladorVentanaPrincipal.salirAplicacion();
    }
}
