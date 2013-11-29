package com.stylishdb.qt.controllers;

import com.trolltech.qt.QSignalEmitter;
import com.trolltech.qt.gui.QAction;
import com.stylishdb.StylishDB;
import com.stylishdb.db.FormatterTextSQL;
import com.stylishdb.domain.MConnectionListener;
import com.stylishdb.domain.AllMConnectionsListener;
import com.stylishdb.domain.MConnection;
import com.stylishdb.domain.MTab;
import com.stylishdb.qt.TopMenu;

/**
 *
 ** @author StylishDB
 */
public class CTopMenu extends Controller implements
            AllMConnectionsListener, MConnectionListener {
    private TopMenu menuSuperior;
    
    public CTopMenu() {
        super();
        
        menuSuperior = new TopMenu(this);
        
        cargarConexionesGuardadas();
        suscribirCambiosConexiones();
    }
    
    public TopMenu getVistaMenuSuperior() {
        return menuSuperior;
    }
    
    private void suscribirCambiosConexiones() {
        mAplicacion.mConexionesGuardadas.addConexionListener(this);
    }
    
    private void cargarConexionesGuardadas() {
        for(MConnection conexion :mAplicacion.mConexionesGuardadas.
                getConexionesGuardadas()) {
            menuSuperior.pintarNuevaConexion(conexion);
        }
    }
    
    protected void nuevoEditor() {
        CNewEditor nuevoEditor = new CNewEditor();
        nuevoEditor.lanzar();
    }
    
    protected void salirAplicacion() {
        StylishDB.salirAplicacion();
    }
    
    protected void nuevaConexion() {
        CNewConnection nuevaConexion = new
                CNewConnection();
        nuevaConexion.mostrarVentanaModal();
    }
    
    protected void preferencias() {
        CPreferencias preferencias = new
                CPreferencias();
        preferencias.mostrarVentanaModal();
    }
    
    protected void borrarConexion() {
        QAction actionBorrar = (QAction) QSignalEmitter.signalSender();
        
        MConnection mConexionBorrar = (MConnection) actionBorrar.data();
        
        conexionesGuardadas.removeConexion(mConexionBorrar);
    }
    
    protected void editarConexion() {
        QAction actionEditar = (QAction) QSignalEmitter.signalSender();
        
        MConnection mConexionEditar = (MConnection) actionEditar.data();
        
        CEditConnection editarConexion = new CEditConnection(
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
            CShowTables verTablasBaseDatos =
                    new CShowTables();
            verTablasBaseDatos.mostrarRenombrarPestanaActiva();
        }
    }
    
    protected void renombrarPestana() {
        if(pestanasAbiertas.hayPestanaActiva()) {
            CModalRenameTab renombrarPestanaActiva =
                    new CModalRenameTab();
            renombrarPestanaActiva.mostrarRenombrarPestanaActiva();
        }
    }
    
    protected void cerrarPestana() {        
        if(pestanasAbiertas.hayPestanaActiva()) {
            MTab pestanaActiva = pestanasAbiertas.getPestanaActiva();
            pestanasAbiertas.removePestana(pestanaActiva);
        }
    }
    
    protected void formatear() {
        if(pestanasAbiertas.hayPestanaActiva()) {
            FormatterTextSQL formatear = new
                    FormatterTextSQL();
            formatear.pestana(pestanasAbiertas.getPestanaActiva());
        }
    }

    @Override
    public void nuevaConexion(MConnection mConexion) {
        menuSuperior.pintarNuevaConexion(mConexion);
        
        mConexion.addMConexionListener(this);
    }

    @Override
    public void eliminadaConexion(MConnection mConexion) {
        menuSuperior.quitarConexion(mConexion);
        
        mConexion.removeMConexionListener(this);
    }

    @Override
    public void modificadoNombre(MConnection mConexion) {
        menuSuperior.modificadoNombre(mConexion);
    }

    @Override
    public void modificadoTipoBaseDatos(MConnection mConexion) {}

    @Override
    public void modificadoSid(MConnection mConexion) {}

    @Override
    public void modificadaIp(MConnection mConexion) {}

    @Override
    public void modificadoPuerto(MConnection mConexion) {}

    @Override
    public void modificadoUsuario(MConnection mConexion) {}

    @Override
    public void modificadoPassword(MConnection mConexion) {}
}