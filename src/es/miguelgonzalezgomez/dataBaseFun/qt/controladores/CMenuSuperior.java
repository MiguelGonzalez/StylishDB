package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.QSignalEmitter;
import com.trolltech.qt.gui.QAction;
import es.miguelgonzalezgomez.dataBaseFun.DataBaseFun;
import es.miguelgonzalezgomez.dataBaseFun.bd.GestionadorConexionesAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.bd.GestionadorEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.ConexionListener;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.qt.MenuSuperior;

/**
 *
 * @author Miguel González
 */
public class CMenuSuperior {
    private MenuSuperior menuSuperior;
    private MAplicacion aplicacion;
    
    private GestionadorConexionesAplicacion gestionadorConexiones;
    private GestionadorEditoresAplicacion gestionadorEditores;
    
    
    public CMenuSuperior() {
        gestionadorConexiones = new
                GestionadorConexionesAplicacion();
        gestionadorEditores = new GestionadorEditoresAplicacion();
        
        aplicacion = MAplicacion.getInstance();
        suscribirCambiosConexiones();
        
        menuSuperior = new MenuSuperior(this);
        
        cargarConexionesGuardadas();
    }
    
    public MenuSuperior getVistaMenuSuperior() {
        return menuSuperior;
    }
    
    private void suscribirCambiosConexiones() {
        aplicacion.mConexionesGuardadas.addConexionListener(new ConexionListener() {
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
        for(MConexion conexion :aplicacion.mConexionesGuardadas.
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
        
        gestionadorConexiones.borrarConexion(mConexionBorrar);
    }
    
    protected void editarConexion() {
        QAction actionEditar = (QAction) QSignalEmitter.signalSender();
        
        MConexion mConexionEditar = (MConexion) actionEditar.data();
        
        CEditarConexion editarConexion = new CEditarConexion(
                mConexionEditar);
        editarConexion.mostrarVentanaModal();
    }
    
    protected void deshacer() {
        gestionadorEditores.deshacerPestanaActiva();
    }
    
    protected void rehacer() {
        gestionadorEditores.rehacerPestanaActiva();
    }
}
