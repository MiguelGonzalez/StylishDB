package com.stylishdb.domain;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 ** @author StylishDB
 */
public class AllMConnections {
    private CopyOnWriteArrayList<MConnection> conexionesGuardadas;
    
    private transient CopyOnWriteArrayList<AllMConnectionsListener> conexionListeners;
    
    public AllMConnections() {
        conexionesGuardadas = new CopyOnWriteArrayList<>();
        conexionListeners = new CopyOnWriteArrayList<>();
    }
    
    public MConnection getMConexion(UUID uuidConexion) {
        for(MConnection conexion : conexionesGuardadas) {
            if(conexion.uuidConexion.equals(uuidConexion)) {
                return conexion;
            }
        }
        return null;
    }
    
    public List<MConnection> getConexionesGuardadas() {
        return conexionesGuardadas;
    }
    
    public boolean existeNombreConexion(String nombreConexion) {
        for(MConnection conexion : conexionesGuardadas) {
            if(conexion.getNombre().equals(nombreConexion)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void addConexionListener(AllMConnectionsListener conexionListener) {
        conexionListeners.add(conexionListener);
    }
    
    public void removeConexionListener(AllMConnectionsListener conexionListener) {
        conexionListeners.remove(conexionListener);
    }
    
    public void addNuevaConexion(MConnection conexion) {
        conexionesGuardadas.add(conexion);
        
        for(AllMConnectionsListener conexionListener : conexionListeners) {
            conexionListener.nuevaConexion(conexion);
        }
    }
    
    public void removeConexion(MConnection conexion) {
        conexionesGuardadas.remove(conexion);
        
        for(AllMConnectionsListener conexionListener : conexionListeners) {
            conexionListener.eliminadaConexion(conexion);
        }
    }
}
