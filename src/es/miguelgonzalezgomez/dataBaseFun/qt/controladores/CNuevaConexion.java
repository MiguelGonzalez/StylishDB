package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.bd.ComprobacionConexion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
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
        if(isRellenoModeloConexion()) {
            MConexion mConexion = obtenerModeloConexion();
            
            if(ComprobacionConexion.hayConexion(mConexion)) {
                mostrarConexionOk();
            } else {
                mostrarConexionError();
            }
        } else {
            pintarErroresCampos();
        }
    }
    
    protected void eventoCrearConexion() {
        if(isRellenoModeloConexion()) {
            MConexion mConexion = obtenerModeloConexion();
            
        } else {
            pintarErroresCampos();
        }
    }
    
    private void mostrarConexionOk() {
        modalGestionConexiones.mostrarHayConexion();
    }
    
    private void mostrarConexionError() {
        modalGestionConexiones.mostrarNoHayConexion();
    }
    
    private MConexion obtenerModeloConexion() {
        MConexion mConexion = new MConexion();
        
        mConexion.nombre = modalGestionConexiones.nombreEdit.text();
        int indexGestor = modalGestionConexiones.gestorCombo.currentIndex();
        mConexion.gestor = modalGestionConexiones.gestorCombo.itemText(indexGestor);
        mConexion.sid = modalGestionConexiones.sidEdit.text();
        mConexion.ip = modalGestionConexiones.ipEdit.text();
        mConexion.puerto = modalGestionConexiones.puertoEdit.text();
        mConexion.usuario = modalGestionConexiones.usuarioEdit.text();
        mConexion.password = modalGestionConexiones.passwordEdit.text();
        
        return null;
    }
    
    private boolean isRellenoModeloConexion() {
        return !modalGestionConexiones.nombreEdit.text().isEmpty() &&
                modalGestionConexiones.gestorCombo.currentIndex() >= 0 &&
                !modalGestionConexiones.sidEdit.text().isEmpty() &&
                !modalGestionConexiones.ipEdit.text().isEmpty() &&
                !modalGestionConexiones.puertoEdit.text().isEmpty() &&
                !modalGestionConexiones.usuarioEdit.text().isEmpty() &&
                !modalGestionConexiones.passwordEdit.text().isEmpty();
    }
    
    private void pintarErroresCampos() {
        if(modalGestionConexiones.nombreEdit.text().isEmpty()) {
            modalGestionConexiones.nombreEdit.setStyleSheet("background: #FA8072;");
        }
        if(modalGestionConexiones.gestorCombo.currentIndex() == -1) {
            modalGestionConexiones.gestorCombo.setStyleSheet("background: #FA8072");
        }
        if(modalGestionConexiones.sidEdit.text().isEmpty()) {
            modalGestionConexiones.sidEdit.setStyleSheet("background: #FA8072");
        }
        if(modalGestionConexiones.ipEdit.text().isEmpty()) {
            modalGestionConexiones.ipEdit.setStyleSheet("background: #FA8072");
        }
        if(modalGestionConexiones.puertoEdit.text().isEmpty()) {
            modalGestionConexiones.puertoEdit.setStyleSheet("background: #FA8072");
        }
        if(modalGestionConexiones.usuarioEdit.text().isEmpty()) {
            modalGestionConexiones.usuarioEdit.setStyleSheet("background: #FA8072");
        }
        if(modalGestionConexiones.passwordEdit.text().isEmpty()) {
            modalGestionConexiones.passwordEdit.setStyleSheet("background: #FA8072");
        }
    }
}
