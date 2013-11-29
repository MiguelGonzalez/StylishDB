package com.stylishdb.qt.controladores;

import com.trolltech.qt.QSignalEmitter;
import com.trolltech.qt.gui.QAction;
import com.stylishdb.StylishDB;
import com.stylishdb.bd.FormatearTextoPestana;
import com.stylishdb.domain.ConexionListener;
import com.stylishdb.domain.ConexionesGuardadasListener;
import com.stylishdb.domain.MConexion;
import com.stylishdb.domain.MPestana;
import com.stylishdb.qt.MenuSuperior;

/**
 *
 ** @author StylishDB
 */
public class CMenuSuperior extends CMiControladorGenerico implements
            ConexionesGuardadasListener, ConexionListener {
    private MenuSuperior menuSuperior;
    
    public CMenuSuperior() {
        super();
        
        menuSuperior = new MenuSuperior(this);
        
        cargarConexionesGuardadas();
        suscribirCambiosConexiones();
    }
    
    public MenuSuperior getVistaMenuSuperior() {
        return menuSuperior;
    }
    
    private void suscribirCambiosConexiones() {
        mAplicacion.mConexionesGuardadas.addConexionListener(this);
    }
    
    private void cargarConexionesGuardadas() {
        for(MConexion conexion :mAplicacion.mConexionesGuardadas.
                getConexionesGuardadas()) {
            menuSuperior.pintarNuevaConexion(conexion);
        }
    }
    
    protected void nuevoEditor() {
        CNuevoEditor nuevoEditor = new CNuevoEditor();
        nuevoEditor.lanzar();
    }
    
    protected void salirAplicacion() {
        StylishDB.salirAplicacion();
    }
    
    protected void nuevaConexion() {
        CNuevaConexion nuevaConexion = new
                CNuevaConexion();
        nuevaConexion.mostrarVentanaModal();
    }
    
    protected void preferencias() {
        CPreferencias preferencias = new
                CPreferencias();
        preferencias.mostrarVentanaModal();
    }
    
    protected void borrarConexion() {
        QAction actionBorrar = (QAction) QSignalEmitter.signalSender();
        
        MConexion mConexionBorrar = (MConexion) actionBorrar.data();
        
        conexionesGuardadas.removeConexion(mConexionBorrar);
    }
    
    protected void editarConexion() {
        QAction actionEditar = (QAction) QSignalEmitter.signalSender();
        
        MConexion mConexionEditar = (MConexion) actionEditar.data();
        
        CEditarConexion editarConexion = new CEditarConexion(
                mConexionEditar);
        editarConexion.mostrarVentanaModal();
    }
    
    protected void deshacer() {
        controladorPestanaActiva.deshacerPestana();
    }
    
    protected void rehacer() {
        controladorPestanaActiva.rehacerPestana();
    }
    
    protected void ejecutarConsulta() {
        controladorPestanaActiva.ejecutarConsulta();
    }
    
    protected void verTablasBaseDatos() {
        if(pestanasAbiertas.hayPestanaActiva()) {
            CVerTablasBaseDatos verTablasBaseDatos =
                    new CVerTablasBaseDatos();
            verTablasBaseDatos.mostrarRenombrarPestanaActiva();
        }
    }
    
    protected void renombrarPestana() {
        if(pestanasAbiertas.hayPestanaActiva()) {
            CRenombrarPestanaActiva renombrarPestanaActiva =
                    new CRenombrarPestanaActiva();
            renombrarPestanaActiva.mostrarRenombrarPestanaActiva();
        }
    }
    
    protected void cerrarPestana() {        
        if(pestanasAbiertas.hayPestanaActiva()) {
            MPestana pestanaActiva = pestanasAbiertas.getPestanaActiva();
            pestanasAbiertas.removePestana(pestanaActiva);
        }
    }
    
    protected void formatear() {
        if(pestanasAbiertas.hayPestanaActiva()) {
            FormatearTextoPestana formatear = new
                    FormatearTextoPestana();
            formatear.pestana(pestanasAbiertas.getPestanaActiva());
        }
    }

    @Override
    public void nuevaConexion(MConexion mConexion) {
        menuSuperior.pintarNuevaConexion(mConexion);
        
        mConexion.addMConexionListener(this);
    }

    @Override
    public void eliminadaConexion(MConexion mConexion) {
        menuSuperior.quitarConexion(mConexion);
        
        mConexion.removeMConexionListener(this);
    }

    @Override
    public void modificadoNombre(MConexion mConexion) {
        menuSuperior.modificadoNombre(mConexion);
    }

    @Override
    public void modificadoTipoBaseDatos(MConexion mConexion) {}

    @Override
    public void modificadoSid(MConexion mConexion) {}

    @Override
    public void modificadaIp(MConexion mConexion) {}

    @Override
    public void modificadoPuerto(MConexion mConexion) {}

    @Override
    public void modificadoUsuario(MConexion mConexion) {}

    @Override
    public void modificadoPassword(MConexion mConexion) {}
}