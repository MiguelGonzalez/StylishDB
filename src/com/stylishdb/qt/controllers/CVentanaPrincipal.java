package com.stylishdb.qt.controllers;

import com.google.gson.reflect.TypeToken;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.WindowStates;
import com.stylishdb.configuration.ConfiguracionVentanaPrincipal;
import com.stylishdb.PreferenciasException;
import com.stylishdb.style.ObtencionEstilo;
import com.stylishdb.qt.VentanaPrincipal;
import java.lang.reflect.Type;

/**
 *
 ** @author StylishDB
 */
public class CVentanaPrincipal extends CMiControladorGenerico {
    
    
    private static String nombreFicheroPreferencias = "confVentanaPrincipal";
    public VentanaPrincipal ventanaPrincipal;
    private ConfiguracionVentanaPrincipal configuracionVentanaPrincipal;
    
    private CMenuSuperior controladorMenuSup;
    private CWidgetPestanasEditores controladorPestanasEditores;
    private CDockPanelConsultasRealizadas controladorConsultasRealizadas;
    
    public CVentanaPrincipal() {
        super();
        
        controladorMenuSup = new CMenuSuperior();
        controladorPestanasEditores = new CWidgetPestanasEditores();
        controladorConsultasRealizadas = new CDockPanelConsultasRealizadas();
        
        cargarPropiedadesVentanaPrincipal();
        crearYPosicionarVentana();
        
        establecerMenuSuperior();
        establecerPestanasEditores();
        establecerWidgets();
        
        mostrarVentanaPrincipal();
    }
    
    private void cargarPropiedadesVentanaPrincipal() {
        Type fooType = new TypeToken<ConfiguracionVentanaPrincipal>() {}.getType();
        try {
            configuracionVentanaPrincipal = prefs.getConfiguracion(
                    fooType,
                    nombreFicheroPreferencias);
        } catch (PreferenciasException ex) {
            cargarPropiedadesDefectoVentanaPrincipal();
        }
    }
    
    private void cargarPropiedadesDefectoVentanaPrincipal() {
        configuracionVentanaPrincipal = new ConfiguracionVentanaPrincipal();
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
        ventanaPrincipal = new VentanaPrincipal(
                "StylishDB",
                this);
        ventanaPrincipal.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("ventanaPrincipal.css")
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
        Type fooType = new TypeToken<ConfiguracionVentanaPrincipal>() {}.getType();
        prefs.guardarConfiguracion(fooType,
                configuracionVentanaPrincipal,
                nombreFicheroPreferencias);
    }
}
