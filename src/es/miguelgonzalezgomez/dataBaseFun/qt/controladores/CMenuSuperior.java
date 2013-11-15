package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.QSignalEmitter;
import com.trolltech.qt.gui.QAction;
import es.miguelgonzalezgomez.dataBaseFun.DataBaseFun;
import es.miguelgonzalezgomez.dataBaseFun.modelos.ConexionListener;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.qt.MenuSuperior;

/**
 *
 * @author Miguel González
 */
public class CMenuSuperior extends CMiControladorGenerico {
    private MenuSuperior menuSuperior;
    
    public CMenuSuperior() {
        super();
        
        suscribirCambiosConexiones();
        
        menuSuperior = new MenuSuperior(this);
        
        cargarConexionesGuardadas();
    }
    
    public MenuSuperior getVistaMenuSuperior() {
        return menuSuperior;
    }
    
    private void suscribirCambiosConexiones() {
        mAplicacion.mConexionesGuardadas.addConexionListener(new ConexionListener() {
            @Override
            public void nuevaConexion(MConexion mConexion) {
                menuSuperior.pintarNuevaConexion(mConexion);
            }

            @Override
            public void eliminadaConexion(MConexion mConexion) {
                menuSuperior.despintarConexion(mConexion);
            }

            @Override
            public void modificadaConexion(MConexion mConexionVieja, 
                    MConexion mConexionEditada) {
                menuSuperior.comprobarCambiarNombre(mConexionVieja,
                        mConexionEditada);

            }
        });
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
        DataBaseFun.salirAplicacion();
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
        pestanasAbiertas.deshacerPestanaActiva();
    }
    
    protected void rehacer() {
        pestanasAbiertas.rehacerPestanaActiva();
    }
    
    protected void ejecutarConsulta() {
        pestanasAbiertas.ejecutarConsultaPestanaActiva();
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
        pestanasAbiertas.cerrarPestanaActiva();
    }
}