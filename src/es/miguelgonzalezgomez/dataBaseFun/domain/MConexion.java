package es.miguelgonzalezgomez.dataBaseFun.domain;

import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ObtenerUrlConexion;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.TiposBasesDeDatos.TIPO_BASE_DATOS;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Miguel González
 */
public class MConexion {
    
    public final UUID uuidConexion;
    
    private TIPO_BASE_DATOS tipoBaseDeDatos;
    private String nombre;
    private String sid;
    private String ip;
    private String puerto;
    private String usuario;
    private String password;
    
    private transient CopyOnWriteArrayList<ConexionListener> listeners;
    
    public MConexion() {
        uuidConexion = UUID.randomUUID();
        
        listeners = new CopyOnWriteArrayList<>();
    }
    
    public void addMConexionListener(ConexionListener listener) {
        listeners.add(listener);
    }
    
    public void removeMConexionListener(ConexionListener listener) {
        listeners.remove(listener);
    }
    
    @Override
    public boolean equals(Object mConexion) {
        if(mConexion instanceof MConexion) {
            return uuidConexion.equals(
                    ((MConexion) mConexion).uuidConexion
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
        return ObtenerUrlConexion.getUrlConexion(
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
        
        for(ConexionListener listener : listeners) {
            listener.modificadoTipoBaseDatos(this);
        }
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
        
        for(ConexionListener listener : listeners) {
            listener.modificadoNombre(this);
        }
    }
    
    public void setIp(String ip) {
        this.ip = ip;
        
        for(ConexionListener listener : listeners) {
            listener.modificadaIp(this);
        }
    }
    
    public void setSid(String sid) {
        this.sid = sid;
        
        for(ConexionListener listener : listeners) {
            listener.modificadoSid(this);
        }
    }
    
    public void setPuerto(String puerto) {
        this.puerto = puerto;
        
        for(ConexionListener listener : listeners) {
            listener.modificadoPuerto(this);
        }
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
        
        for(ConexionListener listener : listeners) {
            listener.modificadoUsuario(this);
        }
    }
    
    public void setPassword(String password) {
        this.password = password;
        
        for(ConexionListener listener : listeners) {
            listener.modificadoPassword(this);
        }
    }
}
