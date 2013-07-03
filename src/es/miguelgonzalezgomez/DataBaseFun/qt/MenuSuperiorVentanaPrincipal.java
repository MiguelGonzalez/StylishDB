package es.miguelgonzalezgomez.DataBaseFun.qt;

import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QKeySequence;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QMenuBar;
import es.miguelgonzalezgomez.DataBaseFun.DataBaseFun;

/**
 *
 * @author Miguel González
 */
public class MenuSuperiorVentanaPrincipal extends QMenuBar {
    
    private QMenu fileMenu;
    
    public MenuSuperiorVentanaPrincipal() {
        crearOpcionesFichero();
    }
    
    private void crearOpcionesFichero() {
        fileMenu = addMenu(tr("&File"));

        crearOpcionSalir();
    }
    
    private void crearOpcionSalir() {
        QAction salirAction = new QAction(tr("Salir"), this);

        salirAction.setShortcut(new QKeySequence(tr("Ctrl+S")));
        salirAction.setStatusTip(tr("Salir de la aplicación"));
        salirAction.triggered.connect(this, "salirAplicacion()");
        
        fileMenu.addAction(salirAction);
    }
    
    public void salirAplicacion() {
        DataBaseFun.salirAplicacion();
    }
}
