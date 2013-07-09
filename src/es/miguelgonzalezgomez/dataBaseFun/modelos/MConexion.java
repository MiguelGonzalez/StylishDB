package es.miguelgonzalezgomez.dataBaseFun.modelos;

/**
 *
 * @author Miguel González
 */
public class MConexion {
    public String nombre;
    public String gestor;
    public String sid;
    public String ip;
    public String puerto;
    public String usuario;
    public String password;
    
    @Override
    public MConexion clone() {
        MConexion mConexion = new MConexion();
        mConexion.nombre = nombre;
        mConexion.gestor = gestor;
        mConexion.sid = sid;
        mConexion.ip = ip;
        mConexion.puerto = puerto;
        mConexion.usuario = usuario;
        mConexion.password = password;
        
        return mConexion;
    }
}
