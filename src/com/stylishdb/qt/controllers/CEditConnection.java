package com.stylishdb.qt.controllers;

import com.stylishdb.domain.MConnection;

/**
 *
 ** @author StylishDB
 */
public class CEditConnection extends CNewConnection {
    
    private MConnection mConexion;
    
    public CEditConnection(MConnection mConexion) {
        super();
        
        this.mConexion = mConexion;
    }
    
    @Override
    public void mostrarVentanaModal() {
        super.mostrarVentanaModal();
        
        establecerDatosConexionAEditar();
    }
    
    private void establecerDatosConexionAEditar() {
        modalGestionConexiones.nombreEdit.setText(mConexion.getNombre());
        int posGestor = modalGestionConexiones.gestorCombo.findText(
                mConexion.getTipoDeBaseDeDatos().toString());
        if(posGestor != -1) {
            modalGestionConexiones.gestorCombo.setCurrentIndex(posGestor);
        }
        modalGestionConexiones.sidEdit.setText(mConexion.getSid());
        modalGestionConexiones.ipEdit.setText(mConexion.getIp());
        modalGestionConexiones.puertoEdit.setText(mConexion.getPuerto());
        modalGestionConexiones.usuarioEdit.setText(mConexion.getUsuario());
        modalGestionConexiones.passwordEdit.setText(mConexion.getPassword());
        
        modalGestionConexiones.establecerTextoGuardarCambios();
    }
    
    @Override
    protected void eventoCrearEditarConexion() {
        MConnection mConexionEditada = obtenerModeloConexion();
        if(esValidoSinoMostrarErrores(mConexionEditada)) {
            if(mConexion.getNombre().equals(mConexionEditada.getNombre())) {
                actualizarModelo(mConexionEditada);
            
                cerrarVentanaModal();
            } else {
                if(nombreConexionRepetido(mConexionEditada)) {
                    actualizarModelo(mConexionEditada);

                    cerrarVentanaModal();
                } else {
                    modalGestionConexiones.
                        mostrarAvisoNombreConexionDuplicado();
                }
            }
        }
    }
    
    private void actualizarModelo(MConnection mConexionEditada) {
        if(!mConexion.getNombre().equals(mConexionEditada.getNombre())) {
            mConexion.setNombre(mConexionEditada.getNombre());
        }
        if(!mConexion.getIp().equals(mConexionEditada.getIp())) {
            mConexion.setIp(mConexionEditada.getIp());
        }
        if(!mConexion.getPassword().equals(mConexionEditada.getPassword())) {
            mConexion.setPassword(mConexionEditada.getPassword());
        }
        if(!mConexion.getPuerto().equals(mConexionEditada.getPuerto())) {
            mConexion.setPuerto(mConexionEditada.getPuerto());
        }
        if(!mConexion.getSid().equals(mConexionEditada.getSid())) {
            mConexion.setSid(mConexionEditada.getSid());
        }
        if(!mConexion.getTipoDeBaseDeDatos().equals(mConexionEditada.getTipoDeBaseDeDatos())) {
            mConexion.setTipoBaseDatos(mConexionEditada.getTipoDeBaseDeDatos());
        }
        if(!mConexion.getUsuario().equals(mConexionEditada.getUsuario())) {
            mConexion.setUsuario(mConexionEditada.getUsuario());
        }
    }
    
    @Override
    protected MConnection obtenerModeloConexion() {
        return super.obtenerModeloConexion();
    }
}
