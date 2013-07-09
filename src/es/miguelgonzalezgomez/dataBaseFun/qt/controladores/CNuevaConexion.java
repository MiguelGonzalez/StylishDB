package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.bd.ComprobacionConexion;
import es.miguelgonzalezgomez.dataBaseFun.bd.GestionadorConexionesAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.bd.ValidadorModeloConexion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalCrearEditarConexion;

/**
 *
 * @author Miguel González
 */
public class CNuevaConexion {
    
    protected ModalCrearEditarConexion modalGestionConexiones;
    protected GestionadorConexionesAplicacion gestionadorConexiones;
    
    public CNuevaConexion() {
        gestionadorConexiones = new GestionadorConexionesAplicacion();
        
        crearVentanaModal();
        
        posicionarVentanaModal();
    }
    
    public void mostrarVentanaModal() {
        modalGestionConexiones.construirInterfaz();
        
        modalGestionConexiones.show();
    }
    
    private void crearVentanaModal() {
        modalGestionConexiones = new ModalCrearEditarConexion(
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
    
    protected void eventoCancelarCrearEditarConexion() {
        cerrarVentanaModal();
    }
    
    protected void eventoProbarConexion() {
        if(esValidoSinoMostrarErrores()) {
            MConexion mConexion = obtenerModeloConexion();
            
            if(ComprobacionConexion.hayConexion(mConexion)) {
                modalGestionConexiones.
                        mostrarAvisoConexionEstablecida();
            } else {
                modalGestionConexiones.
                        mostrarAvisoNoSePudoEstablecerConexion();
            }
        }
    }
    
    protected void eventoCrearEditarConexion() {
        if(esValidoSinoMostrarErrores()) {
            MConexion mConexion = obtenerModeloConexion();
            if(gestionadorConexiones.existeNombreConexion(mConexion.nombre)) {
                modalGestionConexiones.
                        mostrarAvisoNombreConexionDuplicado();
            } else {
                gestionadorConexiones.
                        addNuevaConexion(mConexion);
            
                cerrarVentanaModal();
            }
        }
    }
    
    protected void cerrarVentanaModal() {
        modalGestionConexiones.close();
    }
    
    protected MConexion obtenerModeloConexion() {
        MConexion mConexion = new MConexion();
        
        mConexion.nombre = modalGestionConexiones.nombreEdit.text();
        int indexGestor = modalGestionConexiones.gestorCombo.currentIndex();
        mConexion.gestor = modalGestionConexiones.gestorCombo.itemText(indexGestor);
        mConexion.sid = modalGestionConexiones.sidEdit.text();
        mConexion.ip = modalGestionConexiones.ipEdit.text();
        mConexion.puerto = modalGestionConexiones.puertoEdit.text();
        mConexion.usuario = modalGestionConexiones.usuarioEdit.text();
        mConexion.password = modalGestionConexiones.passwordEdit.text();
        
        return mConexion;
    }
    
    protected boolean esValidoSinoMostrarErrores() {
        MConexion conexion = obtenerModeloConexion();
        ValidadorModeloConexion validador = new ValidadorModeloConexion(conexion);
        
        if(!validador.isNombreValido()) {
            modalGestionConexiones.pintarErrorNombre();
        }
        if(!validador.isGestorValido()) {
            modalGestionConexiones.pintarErrorGestor();
        }
        if(!validador.isSidValido()) {
            modalGestionConexiones.pintarErrorSid();
        }
        if(!validador.isIpValido()) {
            modalGestionConexiones.pintarErrorIp();
        }
        if(!validador.isPuertoValido()) {
            modalGestionConexiones.pintarErrorPuerto();
        }
        if(!validador.isUsuarioValido()) {
            modalGestionConexiones.pintarErrorUsuario();
        }
        if(!validador.isPasswordValido()) {
            modalGestionConexiones.pintarErrorPassword();
        }
        
        return validador.isConexionValida();
    }
}
