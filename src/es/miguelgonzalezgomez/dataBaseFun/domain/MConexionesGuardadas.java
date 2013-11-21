package es.miguelgonzalezgomez.dataBaseFun.domain;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Miguel González
 */
public class MConexionesGuardadas {
    private CopyOnWriteArrayList<MConexion> conexionesGuardadas;
    
    private transient CopyOnWriteArrayList<ConexionesGuardadasListener> conexionListeners;
    
    public MConexionesGuardadas() {
        conexionesGuardadas = new CopyOnWriteArrayList<>();
        conexionListeners = new CopyOnWriteArrayList<>();
    }
    
    public MConexion getMConexion(UUID uuidConexion) {
        for(MConexion conexion : conexionesGuardadas) {
            if(conexion.uuidConexion.equals(uuidConexion)) {
                return conexion;
            }
        }
        return null;
    }
    
    public List<MConexion> getConexionesGuardadas() {
        return conexionesGuardadas;
    }
    
    public boolean existeNombreConexion(String nombreConexion) {
        for(MConexion conexion : conexionesGuardadas) {
            if(conexion.getNombre().equals(nombreConexion)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void addConexionListener(ConexionesGuardadasListener conexionListener) {
        conexionListeners.add(conexionListener);
    }
    
    public void removeConexionListener(ConexionesGuardadasListener conexionListener) {
        conexionListeners.remove(conexionListener);
    }
    
    public void addNuevaConexion(MConexion conexion) {
        conexionesGuardadas.add(conexion);
        
        for(ConexionesGuardadasListener conexionListener : conexionListeners) {
            conexionListener.nuevaConexion(conexion);
        }
    }
    
    public void removeConexion(MConexion conexion) {
        conexionesGuardadas.remove(conexion);
        
        for(ConexionesGuardadasListener conexionListener : conexionListeners) {
            conexionListener.eliminadaConexion(conexion);
        }
    }
}
