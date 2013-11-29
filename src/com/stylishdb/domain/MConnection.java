package com.stylishdb.domain;

import com.stylishdb.db.domain.GetUrlConnection;
import com.stylishdb.db.domain.TypeManagers.TIPO_BASE_DATOS;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 ** @author StylishDB
 */
public class MConnection {
    
    public final UUID uuidConexion;
    
    private TIPO_BASE_DATOS tipoBaseDeDatos;
    private String nombre;
    private String sid;
    private String ip;
    private String puerto;
    private String usuario;
    private String password;
    
    private transient CopyOnWriteArrayList<MConnectionListener> listeners;
    
    public MConnection() {
        uuidConexion = UUID.randomUUID();
        
        listeners = new CopyOnWriteArrayList<>();
    }
    
    public void addMConexionListener(MConnectionListener listener) {
        listeners.add(listener);
    }
    
    public void removeMConexionListener(MConnectionListener listener) {
        listeners.remove(listener);
    }
    
    @Override
    public boolean equals(Object mConexion) {
        if(mConexion instanceof MConnection) {
            return uuidConexion.equals(
                    ((MConnection) mConexion).uuidConexion
                );
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return uuidConexion.hashCode();
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    public String getUrlConexion() {
        return GetUrlConnection.getUrlConexion(
                tipoBaseDeDatos,
                ip,
                puerto,
                sid);
    }
    
    public TIPO_BASE_DATOS getTipoDeBaseDeDatos() {
        return tipoBaseDeDatos;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getSid() {
        return sid;
    }
    
    public String getIp() {
        return ip;
    }
    
    public String getPuerto() {
        return puerto;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setTipoBaseDatos(TIPO_BASE_DATOS tipoBaseDatos) {
        this.tipoBaseDeDatos = tipoBaseDatos;
        
        for(MConnectionListener listener : listeners) {
            listener.modificadoTipoBaseDatos(this);
        }
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
        
        for(MConnectionListener listener : listeners) {
            listener.modificadoNombre(this);
        }
    }
    
    public void setIp(String ip) {
        this.ip = ip;
        
        for(MConnectionListener listener : listeners) {
            listener.modificadaIp(this);
        }
    }
    
    public void setSid(String sid) {
        this.sid = sid;
        
        for(MConnectionListener listener : listeners) {
            listener.modificadoSid(this);
        }
    }
    
    public void setPuerto(String puerto) {
        this.puerto = puerto;
        
        for(MConnectionListener listener : listeners) {
            listener.modificadoPuerto(this);
        }
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
        
        for(MConnectionListener listener : listeners) {
            listener.modificadoUsuario(this);
        }
    }
    
    public void setPassword(String password) {
        this.password = password;
        
        for(MConnectionListener listener : listeners) {
            listener.modificadoPassword(this);
        }
    }
}
