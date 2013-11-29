package com.stylishdb.qt.controllers;

import com.google.gson.reflect.TypeToken;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.WindowStates;
import com.stylishdb.configuration.MainWindowConfiguration;
import com.stylishdb.PreferencesException;
import com.stylishdb.style.GetStyle;
import com.stylishdb.qt.MainWindow;
import java.lang.reflect.Type;

/**
 *
 ** @author StylishDB
 */
public class CMainWindow extends Controller {
    
    
    private static String nombreFicheroPreferencias = "confVentanaPrincipal";
    public MainWindow ventanaPrincipal;
    private MainWindowConfiguration configuracionVentanaPrincipal;
    
    private CTopMenu controladorMenuSup;
    private CTabEditors controladorPestanasEditores;
    private CDockWidgetExecutedSQLs controladorConsultasRealizadas;
    
    public CMainWindow() {
        super();
        
        controladorMenuSup = new CTopMenu();
        controladorPestanasEditores = new CTabEditors();
        controladorConsultasRealizadas = new CDockWidgetExecutedSQLs();
        
        cargarPropiedadesVentanaPrincipal();
        crearYPosicionarVentana();
        
        establecerMenuSuperior();
        establecerPestanasEditores();
        establecerWidgets();
        
        mostrarVentanaPrincipal();
    }
    
    private void cargarPropiedadesVentanaPrincipal() {
        Type fooType = new TypeToken<MainWindowConfiguration>() {}.getType();
        try {
            configuracionVentanaPrincipal = prefs.getConfiguracion(
                    fooType,
                    nombreFicheroPreferencias);
        } catch (PreferencesException ex) {
            cargarPropiedadesDefectoVentanaPrincipal();
        }
    }
    
    private void cargarPropiedadesDefectoVentanaPrincipal() {
        configuracionVentanaPrincipal = new MainWindowConfiguration();
        configuracionVentanaPrincipal.estadoVentana = new WindowStates();
        configuracionVentanaPrincipal.width = 300;
        configuracionVentanaPrincipal.height = 200;
        configuracionVentanaPrincipal.posX = 100;
        configuracionVentanaPrincipal.posY = 100;
    }
    
    private void crearYPosicionarVentana() {
        crearVentana();
        posicionarVentanaPrincipal();
    }
    
    private void crearVentana() {
        ventanaPrincipal = new MainWindow(
                "StylishDB",
                this);
        ventanaPrincipal.setStyleSheet(
                GetStyle.getEstiloVentana("ventanaPrincipal.css")
        );
    }
    
    private void establecerMenuSuperior() {
        ventanaPrincipal.setMenuBar(
                controladorMenuSup.getVistaMenuSuperior()
        );
    }
    
    private void establecerPestanasEditores() {
        ventanaPrincipal.setCentralWidget(
                controladorPestanasEditores.
                        getVistaPestanasEditores()
        );
    }
    
    private void establecerWidgets() {
        ventanaPrincipal.addDockWidget(
                Qt.DockWidgetArea.BottomDockWidgetArea,
                controladorConsultasRealizadas.getPanelMostrarConsultasRealizadas(),
                Qt.Orientation.Horizontal);
    }
     
    private void posicionarVentanaPrincipal() {
        ventanaPrincipal.resize(
                configuracionVentanaPrincipal.width,
                configuracionVentanaPrincipal.height);
        ventanaPrincipal.move(
                configuracionVentanaPrincipal.posX,
                configuracionVentanaPrincipal.posY);
        ventanaPrincipal.setWindowState(
                configuracionVentanaPrincipal.estadoVentana);
        
    }
    
    private void mostrarVentanaPrincipal() {
        ventanaPrincipal.show();
    }
    
    public void salirAplicacion() {
        guardarEstadoVentana();
        guardarPreferencias();
        
        ventanaPrincipal.close();
    }
    
    private void guardarEstadoVentana() {
        configuracionVentanaPrincipal.estadoVentana = ventanaPrincipal.windowState();
        configuracionVentanaPrincipal.width = ventanaPrincipal.size().width();
        configuracionVentanaPrincipal.height = ventanaPrincipal.size().height();
        configuracionVentanaPrincipal.posX = ventanaPrincipal.pos().x();
        configuracionVentanaPrincipal.posY = ventanaPrincipal.pos().y();
    }
    
    private void guardarPreferencias() {
        Type fooType = new TypeToken<MainWindowConfiguration>() {}.getType();
        prefs.guardarConfiguracion(fooType,
                configuracionVentanaPrincipal,
                nombreFicheroPreferencias);
    }
}
