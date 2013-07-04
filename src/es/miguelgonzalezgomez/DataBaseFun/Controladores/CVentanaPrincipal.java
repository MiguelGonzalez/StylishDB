/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.miguelgonzalezgomez.DataBaseFun.Controladores;

import com.google.gson.reflect.TypeToken;
import com.trolltech.qt.core.Qt.WindowStates;
import es.miguelgonzalezgomez.DataBaseFun.Configuracion.ConfiguracionVentanaPrincipal;
import es.miguelgonzalezgomez.DataBaseFun.Preferencias;
import es.miguelgonzalezgomez.DataBaseFun.PreferenciasException;
import es.miguelgonzalezgomez.DataBaseFun.qt.VentanaPrincipal;
import java.lang.reflect.Type;

/**
 *
 * @author Miguel González
 */
public class CVentanaPrincipal {
    
    private Preferencias prefs;
    private static String nombreFicheroPreferencias = "confVentanaPrincipal";
    private VentanaPrincipal ventanaPrincipal;
    private ConfiguracionVentanaPrincipal configuracionVentanaPrincipal;
    
    private CMenuSupVentanaPrincipal controladorMenuSup;
    
    public CVentanaPrincipal() {
        controladorMenuSup = new CMenuSupVentanaPrincipal();
        
        cargarPropiedadesVentanaPrincipal();
        
        crearYPosicionarVentana();
        establecerMenuSuperior();
        mostrarVentanaPrincipal();
    }
    
    private void cargarPropiedadesVentanaPrincipal() {
        prefs = Preferencias.getInstance();
        
        Type fooType = new TypeToken<ConfiguracionVentanaPrincipal>() {}.getType();
        try {
            configuracionVentanaPrincipal = prefs.getConfiguracion(
                    fooType,
                    nombreFicheroPreferencias);
        } catch (PreferenciasException ex) {
            ex.printStackTrace();
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
                "DataBaseFun",
                this);
    }
    
    private void establecerMenuSuperior() {
        ventanaPrincipal.setMenuBar(
                controladorMenuSup.getVistaMenuSuperior()
        );
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
