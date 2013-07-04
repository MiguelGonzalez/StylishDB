package es.miguelgonzalezgomez.DataBaseFun.qt;

import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QKeySequence;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QMenuBar;
import es.miguelgonzalezgomez.DataBaseFun.Controladores.CMenuSupVentanaPrincipal;

/**
 *
 * @author Miguel González
 */
public class MenuSuperiorVentanaPrincipal extends QMenuBar {
    
    private CMenuSupVentanaPrincipal controlador;
    private QMenu fileMenu;
    
    public MenuSuperiorVentanaPrincipal(
            CMenuSupVentanaPrincipal controlador) {
        this.controlador = controlador;
        
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
        salirAction.triggered.connect(controlador, "salirAplicacion()");
        
        fileMenu.addAction(salirAction);
    }
}
