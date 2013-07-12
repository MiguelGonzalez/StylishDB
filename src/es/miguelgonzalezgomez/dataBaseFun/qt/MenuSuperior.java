package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.core.QEvent;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QKeySequence;
import com.trolltech.qt.gui.QKeySequence.StandardKey;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QMenuBar;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CMenuSuperior;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Miguel González
 */
public class MenuSuperior extends QMenuBar {
    
    private CMenuSuperior controlador;
    private QMenu achivoMenu;
    private QMenu edicionMenu;
    private QMenu conexionesMenu;
    private QMenu herramientasMenu;
    
    private QAction nuevoEditor;
    private QAction salirAction;
    
    private QAction deshacer;
    private QAction rehacer;
    private QAction renombrarPestana;
    
    private QAction nuevaConexion;
    private QAction preferencias;
    
    private Map<String, QMenu> conexiones;
    
    public MenuSuperior(
            CMenuSuperior controlador) {
        this.controlador = controlador;
        
        conexiones = new HashMap<>();
        
        crearOpcionesFichero();
        crearOpcionesEdicion();
        crearOpcionesConexiones();
        crearOpcionesHerramientas();
    }
        
    private void crearOpcionesFichero() {
        achivoMenu = addMenu(tr("&Archivo"));

        crearOpcionNuevoEditor();
        
        achivoMenu.addSeparator();
        crearOpcionSalir();
    }
    
    private void crearOpcionNuevoEditor() {
        nuevoEditor = new QAction(tr("Nuevo editor"), this);
        nuevoEditor.setShortcut(StandardKey.New);
        nuevoEditor.triggered.connect(controlador, "nuevoEditor()");
        
        achivoMenu.addAction(nuevoEditor);
    }
    

    private void crearOpcionSalir() {
        salirAction = new QAction(tr("Salir"), this);
        salirAction.setShortcut(StandardKey.Quit);
        salirAction.triggered.connect(controlador, "salirAplicacion()");
        
        achivoMenu.addAction(salirAction);
    }
    
    private void crearOpcionesEdicion() {
        edicionMenu = addMenu(tr("Edición"));
        
        crearOpcionDeshacer();
        crearOpcionRehacer();
        
        edicionMenu.addSeparator();
        crearOpcionRenombrarPestana();
    }
    
    private void crearOpcionDeshacer() {
        deshacer = new QAction(tr("Deshacer"), this);
        deshacer.setShortcut(StandardKey.Undo);
        deshacer.triggered.connect(controlador, "deshacer()");
        edicionMenu.addAction(deshacer);
    }
    
    private void crearOpcionRehacer() {
        rehacer = new QAction(tr("Rehacer"), this);
        rehacer.setShortcut(QKeySequence.fromString("Ctrl+Y"));
        rehacer.triggered.connect(controlador, "rehacer()");
        edicionMenu.addAction(rehacer);
    }
    
    private void crearOpcionRenombrarPestana() {
        renombrarPestana = new QAction(tr("Renombrar pestaña"), this);
        renombrarPestana.setShortcut(QKeySequence.fromString("Ctrl+R"));
        renombrarPestana.triggered.connect(controlador, "renombrarPestana()");
        edicionMenu.addAction(renombrarPestana);
    }
    
    private void crearOpcionesConexiones() {
        conexionesMenu = addMenu(tr("&Conexiones"));
        
        crearOpcionNuevaConexion();
        conexionesMenu.addSeparator();
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

    public void pintarNuevaConexion(MConexion mConexion) {
        QMenu conexion = new QMenu(mConexion.nombre, this);
        
        pintarOpcionesNuevaConexion(conexion, mConexion);
        
        conexiones.put(mConexion.nombre, conexion);

        conexionesMenu.addMenu(conexion);
    }
    
    private void pintarOpcionesNuevaConexion(QMenu conexion, MConexion mConexion) {
        QAction conexionEditar = new QAction(tr("Editar conexión"), this);
        conexionEditar.setData(mConexion);
        conexion.addAction(conexionEditar);
        conexionEditar.triggered.connect(controlador, "editarConexion()");
        
        conexion.addSeparator();
        
        QAction conexionBorrar = new QAction(tr("Borrar conexión"), this);
        conexionBorrar.setData(mConexion);
        conexionBorrar.triggered.connect(controlador, "borrarConexion()");
        
        conexion.addAction(conexionBorrar);
    }

    public void despintarConexion(MConexion mConexion) {
        QMenu conexionBorrar = conexiones.get(mConexion.nombre);
        
        conexionesMenu.removeAction(conexionBorrar.menuAction());
    }

    public void comprobarCambiarNombre(MConexion conexionVieja, MConexion conexionEditada) {
        QMenu menuConexion = conexiones.remove(conexionVieja.nombre);
        conexiones.put(conexionEditada.nombre, menuConexion);
        
        for(QAction action : menuConexion.actions()) {
            action.setData(conexionEditada);
        }

        menuConexion.setTitle(conexionEditada.nombre);
    }
}
