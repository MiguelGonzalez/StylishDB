package es.miguelgonzalezgomez.dataBaseFun.modelos;

import java.util.UUID;

/**
 *
 * @author Miguel González
 */
public class MConexion {
    
    public UUID uuidConexion;
    public String nombre;
    public String gestor;
    public String sid;
    public String ip;
    public String puerto;
    public String usuario;
    public String password;
    
    public MConexion() {
        uuidConexion = UUID.randomUUID();
    }
    
    @Override
    public MConexion clone() {
        MConexion mConexion = new MConexion();
        mConexion.uuidConexion = uuidConexion;
        mConexion.nombre = nombre;
        mConexion.gestor = gestor;
        mConexion.sid = sid;
        mConexion.ip = ip;
        mConexion.puerto = puerto;
        mConexion.usuario = usuario;
        mConexion.password = password;
        
        return mConexion;
    }
    
    @Override
    public boolean equals(Object mConexion) {
        if(mConexion instanceof MConexion) {
            return uuidConexion.equals(
                    ((MPestanaEditor) mConexion).uuidPestana
                );
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return uuidConexion.hashCode();
    }
}
