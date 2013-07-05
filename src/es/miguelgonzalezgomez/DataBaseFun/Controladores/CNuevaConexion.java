package es.miguelgonzalezgomez.DataBaseFun.Controladores;

import es.miguelgonzalezgomez.DataBaseFun.qt.modals.ModalNuevaConexion;

/**
 *
 * @author Miguel González
 */
public class CNuevaConexion {
    
    private ModalNuevaConexion modalGestionConexiones;
    
    public CNuevaConexion() {
        crearVentanaModal();
        
        posicionarVentanaModal();
    }
    
    private void crearVentanaModal() {
        modalGestionConexiones = new ModalNuevaConexion(
                "Nueva conexión", this);
    }
    
    private void posicionarVentanaModal() {
        modalGestionConexiones.resize(
                500, 300
                );
        modalGestionConexiones.move(
                100,100
                );
    }
    
    public void mostrarVentanaModal() {
        modalGestionConexiones.show();
    }
}
