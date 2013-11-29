package com.stylishdb.qt;

import com.trolltech.qt.core.QEvent;
import com.trolltech.qt.gui.QAction;
import com.trolltech.qt.gui.QKeySequence;
import com.trolltech.qt.gui.QKeySequence.StandardKey;
import com.trolltech.qt.gui.QMenu;
import com.trolltech.qt.gui.QMenuBar;
import com.stylishdb.domain.MConnection;
import com.stylishdb.qt.controllers.CTopMenu;
import java.util.HashMap;
import java.util.Map;

/**
 *
 ** @author StylishDB
 */
public class TopMenu extends QMenuBar {
    
    private CTopMenu controlador;
    private QMenu achivoMenu;
    private QMenu edicionMenu;
    private QMenu herramientasMenu;
    private QMenu preferenciasMenu;
    private QMenu conexionesMenu;
    
    private QAction nuevoEditor;
    private QAction salirAction;
    
    private QAction deshacer;
    private QAction rehacer;
    private QAction ejecutarConsulta;
    private QAction verTablasBaseDatos;
    private QAction renombrarPestana;
    private QAction cerrarPestana;
    
    private QAction formatear;
    
    private QAction nuevaConexion;
    private QAction preferencias;
    
    private Map<String, QMenu> conexiones;
    
    public TopMenu(
            CTopMenu controlador) {
        this.controlador = controlador;
        
        conexiones = new HashMap<>();
        
        crearOpcionesFichero();
        crearOpcionesEdicion();
        crearOpcionesHerramientas();
        crearOpcionesConexiones();
        crearOpcionesPreferencias();
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
        crearOpcionEjecutarConsulta();
        crearOpcionVerTablaseBaseDatos();
        
        edicionMenu.addSeparator();
        crearOpcionRenombrarPestana();
        crearOpcionCerrarPestana();
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
    
    private void crearOpcionEjecutarConsulta() {
        ejecutarConsulta = new QAction(tr("Ejecutar consulta"), this);
        ejecutarConsulta.setShortcut(QKeySequence.fromString("F5"));
        ejecutarConsulta.triggered.connect(controlador, "ejecutarConsulta()");
        edicionMenu.addAction(ejecutarConsulta);
    }
    
    private void crearOpcionVerTablaseBaseDatos() {
        verTablasBaseDatos = new QAction(tr("Ver tablas BD"), this);
        verTablasBaseDatos.setShortcut(QKeySequence.fromString("Ctrl+T"));
        verTablasBaseDatos.triggered.connect(controlador, "verTablasBaseDatos()");
        edicionMenu.addAction(verTablasBaseDatos);
    }
    
    private void crearOpcionRenombrarPestana() {
        renombrarPestana = new QAction(tr("Renombrar pestaña"), this);
        renombrarPestana.setShortcut(QKeySequence.fromString("Ctrl+R"));
        renombrarPestana.triggered.connect(controlador, "renombrarPestana()");
        edicionMenu.addAction(renombrarPestana);
    }
    
    private void crearOpcionCerrarPestana() {
        cerrarPestana = new QAction(tr("Cerrar pestaña"), this);
        cerrarPestana.setShortcut(QKeySequence.fromString("Ctrl+W"));
        cerrarPestana.triggered.connect(controlador, "cerrarPestana()");
        edicionMenu.addAction(cerrarPestana);
    }
    
    private void crearOpcionesHerramientas() {
        herramientasMenu = addMenu(tr("Herramientas"));
        
        crearOpcionFormatear();
    }
    
    private void crearOpcionFormatear() {
        formatear = new QAction(tr("Formatear"), this);
        formatear.setShortcut(QKeySequence.fromString("Ctrl+F"));
        formatear.triggered.connect(controlador, "formatear()");
        herramientasMenu.addAction(formatear);
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
    
    private void crearOpcionesPreferencias() {
        preferenciasMenu = addMenu(tr("&Preferencias"));
        
        preferenciasMenu.addSeparator();
        crearOpcionPreferencias();
    }
    
    private void crearOpcionPreferencias() {
        preferencias = new QAction(tr("Preferencias"), this);
        preferencias.triggered.connect(controlador, "preferencias()");
        
        preferenciasMenu.addAction(preferencias);
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

    public void pintarNuevaConexion(MConnection mConexion) {
        QMenu conexion = new QMenu(mConexion.getNombre(), this);
        
        pintarOpcionesNuevaConexion(conexion, mConexion);
        
        conexiones.put(mConexion.uuidConexion.toString(), conexion);

        conexionesMenu.addMenu(conexion);
    }
    
    private void pintarOpcionesNuevaConexion(QMenu conexion, MConnection mConexion) {
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

    public void modificadoNombre(MConnection mConexionModificada) {
        QMenu menuConexion = conexiones.get(
                mConexionModificada.uuidConexion.toString());
        
        menuConexion.setTitle(mConexionModificada.getNombre());
    }
    
    public void quitarConexion(MConnection mConexion) {
        QMenu conexionBorrar = conexiones.remove(
                mConexion.uuidConexion.toString());
        
        conexionesMenu.removeAction(conexionBorrar.menuAction());
    }
}
