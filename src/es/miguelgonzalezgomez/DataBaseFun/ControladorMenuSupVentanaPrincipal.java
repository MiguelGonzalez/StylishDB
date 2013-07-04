package es.miguelgonzalezgomez.DataBaseFun;

import es.miguelgonzalezgomez.DataBaseFun.qt.MenuSuperiorVentanaPrincipal;

/**
 *
 * @author Miguel González
 */
public class ControladorMenuSupVentanaPrincipal {
    private MenuSuperiorVentanaPrincipal menuSuperior;
    
    
    public ControladorMenuSupVentanaPrincipal() {
        menuSuperior = new MenuSuperiorVentanaPrincipal(this);
    }
    
    public MenuSuperiorVentanaPrincipal getVistaMenuSuperior() {
        return menuSuperior;
    }
    
}
