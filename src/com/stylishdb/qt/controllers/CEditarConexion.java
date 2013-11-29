package com.stylishdb.qt.controllers;

import com.stylishdb.domain.MConexion;

/**
 *
 ** @author StylishDB
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
        MConexion mConexionEditada = obtenerModeloConexion();
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
    
    private void actualizarModelo(MConexion mConexionEditada) {
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
    protected MConexion obtenerModeloConexion() {
        return super.obtenerModeloConexion();
    }
}
