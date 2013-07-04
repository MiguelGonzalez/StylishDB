/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.miguelgonzalezgomez.DataBaseFun;

import es.miguelgonzalezgomez.DataBaseFun.qt.VentanaPrincipal;

/**
 *
 * @author Miguel González
 */
public class ControladorVentanaPrincipal {
    
    private VentanaPrincipal ventanaPrincipal;
    
    private ControladorMenuSupVentanaPrincipal controladorMenuSup;
    //private MenuSuperiorVentanaPrincipal menuSuperior;
    
    public ControladorVentanaPrincipal() {
        controladorMenuSup = new ControladorMenuSupVentanaPrincipal();
        
        crearYPosicionarVentana();
        establecerMenuSuperior();
        mostrarVentanaPrincipal();
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
        ventanaPrincipal.resize(300, 200);
        ventanaPrincipal.move(300, 300);
    }
    
    private void mostrarVentanaPrincipal() {
        ventanaPrincipal.show();
    }
    
    public void salirAplicacion() {
        ventanaPrincipal.close();
    }
}
