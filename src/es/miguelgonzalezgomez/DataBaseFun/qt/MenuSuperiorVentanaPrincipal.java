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
    private QMenu fileConexiones;
    
    public MenuSuperiorVentanaPrincipal(
            CMenuSupVentanaPrincipal controlador) {
        this.controlador = controlador;
        
        crearOpcionesFichero();
        crearOpcionesConexiones();
    }
        
    private void crearOpcionesFichero() {
        fileMenu = addMenu(tr("&File"));

        fileMenu.addSeparator();
        crearOpcionSalir();
    }

    private void crearOpcionSalir() {
        QAction salirAction = new QAction(tr("Salir"), this);

        salirAction.setShortcut(new QKeySequence(tr("Ctrl+S")));
        salirAction.setStatusTip(tr("Salir de la aplicación"));
        salirAction.triggered.connect(controlador, "salirAplicacion()");
        
        fileMenu.addAction(salirAction);
    }
    
    private void crearOpcionesConexiones() {
        fileConexiones = addMenu(tr("&Conexiones"));
        
        fileConexiones.addSeparator();
        crearOpcionNuevaConexion();
    }
    
    private void crearOpcionNuevaConexion() {
        QAction gestionarConexiones = new QAction(tr("Nueva conexión"), this);

        //gestionarConexiones.setShortcut(new QKeySequence(tr("Ctrl+G")));
        gestionarConexiones.setStatusTip(tr("Crear nueva conexión"));
        gestionarConexiones.triggered.connect(controlador, "nuevaConexion()");
        
        fileConexiones.addAction(gestionarConexiones);
    }
}
