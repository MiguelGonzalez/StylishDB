package com.stylishdb.qt.controllers;

import com.trolltech.qt.core.Qt;
import com.stylishdb.db.CheckConnection;
import com.stylishdb.db.CheckConnectionModel;
import com.stylishdb.style.GetStyle;
import com.stylishdb.domain.MConnection;
import com.stylishdb.qt.modals.ModalConnection;
import com.stylishdb.utilities.CoordenatesWindow;
import static com.stylishdb.db.domain.TypeManagers.devolverTipoBaseDatos;

/**
 *
 ** @author StylishDB
 */
public class CNewConnection extends Controller {
    
    protected ModalConnection modalGestionConexiones;
    
    public CNewConnection() {
        super();
        
        crearVentanaModal();
        recargarEstiloModal();
        posicionarVentanaModal();
    }
    
    public void mostrarVentanaModal() {
        modalGestionConexiones.construirInterfaz();
        
        modalGestionConexiones.show();
    }
    
    private void crearVentanaModal() {
        modalGestionConexiones = new ModalConnection(
                this);
        modalGestionConexiones.setWindowFlags(Qt.WindowType.FramelessWindowHint);
    }
    
    private void posicionarVentanaModal() {
        int width = 350;
        int height = 300;
        modalGestionConexiones.resize(
                width, height
                );
        modalGestionConexiones.move(
                CoordenatesWindow.getXCentrada(width),
                CoordenatesWindow.getYCentradaArriba()
                );
    }
    
    protected void eventoCancelarCrearEditarConexion() {
        cerrarVentanaModal();
    }
    
    protected void eventoProbarConexion() {
        MConnection mConexion = obtenerModeloConexion();
        
        if(esValidoSinoMostrarErrores(mConexion)) {
            if(CheckConnection.hayConexion(mConexion)) {
                modalGestionConexiones.
                        mostrarAvisoConexionEstablecida();
            } else {
                modalGestionConexiones.
                        mostrarAvisoNoSePudoEstablecerConexion();
            }
        }
    }
    
    protected void eventoCrearEditarConexion() {
        MConnection mConexion = obtenerModeloConexion();

        if(esValidoSinoMostrarErrores(mConexion)) {
            if(nombreConexionRepetido(mConexion)) {
                modalGestionConexiones.
                        mostrarAvisoNombreConexionDuplicado();
            } else {
                conexionesGuardadas.
                        addNuevaConexion(mConexion);
            
                cerrarVentanaModal();
            }
        }
    }
    
    protected void cerrarVentanaModal() {
        modalGestionConexiones.close();
    }
    
    protected boolean nombreConexionRepetido(MConnection mConexion) {
        return conexionesGuardadas.existeNombreConexion(mConexion.getNombre());
    }
    
    protected MConnection obtenerModeloConexion() {
        MConnection mConexion = new MConnection();
        
        mConexion.setNombre(modalGestionConexiones.nombreEdit.text());
        int indexGestor = modalGestionConexiones.gestorCombo.currentIndex();
        String nombreGestor = modalGestionConexiones.gestorCombo.itemText(indexGestor);
        mConexion.setTipoBaseDatos(devolverTipoBaseDatos(nombreGestor));
        mConexion.setSid(modalGestionConexiones.sidEdit.text());
        mConexion.setIp(modalGestionConexiones.ipEdit.text());
        mConexion.setPuerto(modalGestionConexiones.puertoEdit.text());
        mConexion.setUsuario(modalGestionConexiones.usuarioEdit.text());
        mConexion.setPassword(modalGestionConexiones.passwordEdit.text());
        
        return mConexion;
    }
    
    protected boolean esValidoSinoMostrarErrores(MConnection mConexion) {
        CheckConnectionModel validador = new CheckConnectionModel(mConexion);
        
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
        
        recargarEstiloModal();
        
        return validador.isConexionValida();
    }
    
    protected final void recargarEstiloModal() {
        modalGestionConexiones.setStyleSheet(
                GetStyle.getEstiloVentana("dialogEstilo.css")
        );
    }
}
