package es.miguelgonzalezgomez.DataBaseFun.Controladores;

import es.miguelgonzalezgomez.DataBaseFun.DataBaseFun;
import es.miguelgonzalezgomez.DataBaseFun.qt.MenuSuperiorVentanaPrincipal;

/**
 *
 * @author Miguel González
 */
public class CMenuSupVentanaPrincipal {
    private MenuSuperiorVentanaPrincipal menuSuperior;
    
    
    public CMenuSupVentanaPrincipal() {
        menuSuperior = new MenuSuperiorVentanaPrincipal(this);
    }
    
    public MenuSuperiorVentanaPrincipal getVistaMenuSuperior() {
        return menuSuperior;
    }
    
    public void salirAplicacion() {
        DataBaseFun.salirAplicacion();
    }
    
}
