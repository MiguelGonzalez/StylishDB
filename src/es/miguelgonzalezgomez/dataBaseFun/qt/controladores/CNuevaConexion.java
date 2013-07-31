package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.core.Qt;
import es.miguelgonzalezgomez.dataBaseFun.bd.ComprobacionConexion;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GConexionesAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.bd.ValidadorModeloConexion;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalCrearEditarConexion;
import es.miguelgonzalezgomez.dataBaseFun.utilidadesEstaticas.CentroCoordenadas;
import static es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.TiposBasesDeDatos.devolverTipoBaseDatos;

/**
 *
 * @author Miguel González
 */
public class CNuevaConexion {
    
    protected ModalCrearEditarConexion modalGestionConexiones;
    protected GConexionesAplicacion gestionadorConexiones;
    
    public CNuevaConexion() {
        gestionadorConexiones = new GConexionesAplicacion();
        
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
            if(nombreConexionRepetido(mConexion)) {
                modalGestionConexiones.
                        mostrarAvisoNombreConexionDuplicado();
            } else {
                gestionadorConexiones.
                        addNuevaConexion(mConexion);
            
                cerrarVentanaModal();
            }
        }
    }
    
    protected boolean nombreConexionRepetido(MConexion mConexion) {
        return gestionadorConexiones.existeNombreConexion(mConexion);
    }
    
    protected void cerrarVentanaModal() {
        modalGestionConexiones.close();
    }
    
    protected MConexion obtenerModeloConexion() {
        MConexion mConexion = new MConexion();
        
        mConexion.nombre = modalGestionConexiones.nombreEdit.text();
        int indexGestor = modalGestionConexiones.gestorCombo.currentIndex();
        String nombreGestor = modalGestionConexiones.gestorCombo.itemText(indexGestor);
        mConexion.tipoDeBaseDeDatos = devolverTipoBaseDatos(nombreGestor);
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
        
        recargarEstiloModal();
        
        return validador.isConexionValida();
    }
    
    protected final void recargarEstiloModal() {
        modalGestionConexiones.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("dialogEstilo.css")
        );
    }
}
