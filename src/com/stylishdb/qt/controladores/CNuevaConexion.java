package com.stylishdb.qt.controladores;

import com.trolltech.qt.core.Qt;
import com.stylishdb.bd.ComprobacionConexion;
import com.stylishdb.bd.ValidadorModeloConexion;
import com.stylishdb.estilos.ObtencionEstilo;
import com.stylishdb.domain.MConexion;
import com.stylishdb.qt.modals.ModalCrearEditarConexion;
import com.stylishdb.utilidadesEstaticas.CentroCoordenadas;
import static com.stylishdb.bd.domain.TiposBasesDeDatos.devolverTipoBaseDatos;

/**
 *
 ** @author StylishDB
 */
public class CNuevaConexion extends CMiControladorGenerico {
    
    protected ModalCrearEditarConexion modalGestionConexiones;
    
    public CNuevaConexion() {
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
        modalGestionConexiones = new ModalCrearEditarConexion(
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
                CentroCoordenadas.getXCentrada(width),
                CentroCoordenadas.getYCentradaArriba()
                );
    }
    
    protected void eventoCancelarCrearEditarConexion() {
        cerrarVentanaModal();
    }
    
    protected void eventoProbarConexion() {
        MConexion mConexion = obtenerModeloConexion();
        
        if(esValidoSinoMostrarErrores(mConexion)) {
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
        MConexion mConexion = obtenerModeloConexion();

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
    
    protected boolean nombreConexionRepetido(MConexion mConexion) {
        return conexionesGuardadas.existeNombreConexion(mConexion.getNombre());
    }
    
    protected MConexion obtenerModeloConexion() {
        MConexion mConexion = new MConexion();
        
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
    
    protected boolean esValidoSinoMostrarErrores(MConexion mConexion) {
        ValidadorModeloConexion validador = new ValidadorModeloConexion(mConexion);
        
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
                ObtencionEstilo.getEstiloVentana("dialogEstilo.css")
        );
    }
}