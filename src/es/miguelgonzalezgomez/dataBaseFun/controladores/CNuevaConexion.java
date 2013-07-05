package es.miguelgonzalezgomez.dataBaseFun.controladores;

import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalNuevaConexion;

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
    
    public void mostrarVentanaModal() {
        modalGestionConexiones.construirInterfaz();
        
        modalGestionConexiones.show();
    }
    
    private void crearVentanaModal() {
        modalGestionConexiones = new ModalNuevaConexion(
                this);
    }
    
    private void posicionarVentanaModal() {
        modalGestionConexiones.resize(
                350, 300
                );
        modalGestionConexiones.move(
                100,100
                );
    }
    
    protected void eventoCancelarCrearConexion() {
        modalGestionConexiones.close();
    }
    
    protected void eventoProbarConexion() {
        
    }
    
    protected void eventoCrearConexion() {
        
    }
}
