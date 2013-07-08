package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.DataBaseFun;
import es.miguelgonzalezgomez.dataBaseFun.modelos.ConexionListener;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.qt.MenuSuperior;

/**
 *
 * @author Miguel González
 */
public class CMenuSuperior {
    private MenuSuperior menuSuperior;
    private MAplicacion aplicacion;
    
    public CMenuSuperior() {
        aplicacion = MAplicacion.getInstance();
        suscribirCambiosConexiones();
        
        menuSuperior = new MenuSuperior(this);
    }
    
    public MenuSuperior getVistaMenuSuperior() {
        return menuSuperior;
    }
    
    private void suscribirCambiosConexiones() {
        aplicacion.addConexionListener(new ConexionListener() {
            @Override
            public void nuevaConexion(MConexion mConexion) {
                menuSuperior.pintarNuevaConexion(mConexion);
            }

            @Override
            public void eliminadaConexion(MConexion mConexion) {
                menuSuperior.despintarConexion(mConexion);
            }
        });
    }
    
    protected void salirAplicacion() {
        DataBaseFun.salirAplicacion();
    }
    
    protected void nuevaConexion() {
        CNuevaConexion nuevaConexion = new
                CNuevaConexion();
        nuevaConexion.mostrarVentanaModal();
    }
    
    protected void preferencias() {
        CPreferencias preferencias = new
                CPreferencias();
        preferencias.mostrarVentanaModal();
    }
}
