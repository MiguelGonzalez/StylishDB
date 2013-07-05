package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.core.QEvent;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QMenuBar;
import es.miguelgonzalezgomez.dataBaseFun.controladores.CMenuSuperior;

/**
 *
 * @author Miguel González
 */
public class MenuSuperior extends QMenuBar {
    
    private CMenuSuperior controlador;
    private QMenu achivoMenu;
    private QMenu conexionesMenu;
    private QMenu herramientasMenu;
    
    private QAction salirAction;
    private QAction nuevaConexion;
    private QAction preferencias;
    
    public MenuSuperior(
            CMenuSuperior controlador) {
        this.controlador = controlador;
        
        crearOpcionesFichero();
        crearOpcionesConexiones();
        crearOpcionesHerramientas();
    }
        
    private void crearOpcionesFichero() {
        achivoMenu = addMenu(tr("&Archivo"));

        achivoMenu.addSeparator();
        crearOpcionSalir();
    }

    private void crearOpcionSalir() {
        salirAction = new QAction(tr("Salir"), this);
        salirAction.triggered.connect(controlador, "salirAplicacion()");
        
        achivoMenu.addAction(salirAction);
    }
    
    private void crearOpcionesConexiones() {
        conexionesMenu = addMenu(tr("&Conexiones"));
        
        conexionesMenu.addSeparator();
        crearOpcionNuevaConexion();
    }
    
    private void crearOpcionNuevaConexion() {
        nuevaConexion = new QAction(tr("Nueva conexión"), this);
        nuevaConexion.triggered.connect(controlador, "nuevaConexion()");
        
        conexionesMenu.addAction(nuevaConexion);
    }
    
    private void crearOpcionesHerramientas() {
        herramientasMenu = addMenu(tr("&Preferencias"));
        
        herramientasMenu.addSeparator();
        crearOpcionPreferencias();
    }
    
    private void crearOpcionPreferencias() {
        preferencias = new QAction(tr("Preferencias"), this);
        preferencias.triggered.connect(controlador, "preferencias()");
        
        herramientasMenu.addAction(preferencias);
    }
    
    @Override
    protected void changeEvent(QEvent event) {
        if (event.type() == QEvent.Type.LanguageChange) {
            achivoMenu.setTitle(tr("&Archivo"));
            conexionesMenu.setTitle(tr("&Conexiones"));
            herramientasMenu.setTitle(tr("&Preferencias"));
            
            salirAction.setText(tr("Salir"));
            nuevaConexion.setText(tr("Nueva conexión"));
            preferencias.setText(tr("Preferencias"));
        }
    }
}
