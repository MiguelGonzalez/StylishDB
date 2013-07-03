package es.miguelgonzalezgomez.DataBaseFun;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.DataBaseFun.qt.MenuSuperiorVentanaPrincipal;
import es.miguelgonzalezgomez.DataBaseFun.qt.VentanaPrincipal;

/**
 *
 * @author Miguel González
 */
public class DataBaseFun extends QWidget {
    
    private static VentanaPrincipal ventanaPrincipal;
    private static MenuSuperiorVentanaPrincipal menuSuperior;
    
    public static void main(String args[]) {
        QApplication.initialize(args);
        
        crearVentana();
        establecerMenuSuperior();
        posicionarVentanaPrincipal();
        mostrarVentanaPrincipal();
        
        QApplication.exec();
    }
    
    private static void crearVentana() {
        ventanaPrincipal = new VentanaPrincipal("DataBaseFun");
    }
    
    private static void establecerMenuSuperior() {
        menuSuperior = new MenuSuperiorVentanaPrincipal();
        
        ventanaPrincipal.setMenuBar(menuSuperior);
    }
    
    private static void posicionarVentanaPrincipal() {
        ventanaPrincipal.resize(300, 200);
        ventanaPrincipal.move(300, 300);
    }
    
    private static void mostrarVentanaPrincipal() {
        ventanaPrincipal.show();
    }
    
    public static void salirAplicacion() {
        ventanaPrincipal.close();
    }
}
