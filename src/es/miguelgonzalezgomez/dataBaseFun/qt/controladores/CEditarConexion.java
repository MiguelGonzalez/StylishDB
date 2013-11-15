package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;

/**
 *
 * @author Miguel González
 */
public class CEditarConexion extends CNuevaConexion {
    
    private MConexion mConexion;
    
    public CEditarConexion(MConexion mConexion) {
        super();
        
        this.mConexion = mConexion;
    }
    
    @Override
    public void mostrarVentanaModal() {
        super.mostrarVentanaModal();
        
        establecerDatosConexionAEditar();
    }
    
    private void establecerDatosConexionAEditar() {
        modalGestionConexiones.nombreEdit.setText(mConexion.nombre);
        int posGestor = modalGestionConexiones.gestorCombo.findText(
                mConexion.tipoDeBaseDeDatos.toString());
        if(posGestor != -1) {
            modalGestionConexiones.gestorCombo.setCurrentIndex(posGestor);
        }
        modalGestionConexiones.sidEdit.setText(mConexion.sid);
        modalGestionConexiones.ipEdit.setText(mConexion.ip);
        modalGestionConexiones.puertoEdit.setText(mConexion.puerto);
        modalGestionConexiones.usuarioEdit.setText(mConexion.usuario);
        modalGestionConexiones.passwordEdit.setText(mConexion.password);
        
        modalGestionConexiones.establecerTextoGuardarCambios();
    }
    
    @Override
    protected void eventoCrearEditarConexion() {
        if(esValidoSinoMostrarErrores()) {
            MConexion mConexionEditada = obtenerModeloConexion();

            if(nombreConexionRepetido(mConexionEditada)) {
                modalGestionConexiones.
                        mostrarAvisoNombreConexionDuplicado();
            } else {
                conexionesGuardadas.
                        editadaConexion(mConexion, mConexionEditada);
            
                cerrarVentanaModal();
            }
        }
    }
    
    @Override
    protected MConexion obtenerModeloConexion() {
        MConexion mConexionEditada = super.obtenerModeloConexion();
        mConexionEditada.uuidConexion = mConexion.uuidConexion;
        
        return mConexionEditada;
    }
}
