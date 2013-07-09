package es.miguelgonzalezgomez.dataBaseFun.modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class MAplicacion {
    
    private List<MConexion> conexionesGuardadas;
    
    private transient List<ConexionListener> conexionListeners;
    
    private static MAplicacion INSTANCE = null;
    
    private MAplicacion() {
        conexionesGuardadas = new ArrayList<>();
        conexionListeners = new ArrayList<>();
    }
    
    public static MAplicacion getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MAplicacion();
        }
        return INSTANCE;
    }
    
    public List<MConexion> getConexionesGuardadas() {
        return conexionesGuardadas;
    }
    
    public void addConexionListener(ConexionListener conexionListener) {
        conexionListeners.add(conexionListener);
    }
    
    public void removeConexionListener(ConexionListener conexionListener) {
        conexionListeners.remove(conexionListener);
    }
    
    public void addNuevaConexion(MConexion conexion) {
        conexionesGuardadas.add(conexion);
        
        notificarNuevaConexion(conexion);
    }
    
    
    
    public void removeConexion(MConexion conexion) {
        conexionesGuardadas.remove(conexion);
        
        notificarEliminadaConexion(conexion);
    }
    
    private void notificarNuevaConexion(MConexion conexion) {
        for(ConexionListener conexionListener : getCopiaConexionListeners()) {
            conexionListener.nuevaConexion(conexion);
        }
    }
    
    private void notificarEliminadaConexion(MConexion conexion) {
        for(ConexionListener conexionListener : getCopiaConexionListeners()) {
            conexionListener.eliminadaConexion(conexion);
        }
    }
    
    private List<ConexionListener> getCopiaConexionListeners() {
        List<ConexionListener> conexionListenersCopy = new
                ArrayList<>(conexionListeners);
        return conexionListenersCopy;
    }
    
    
}
