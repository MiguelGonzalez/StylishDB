package es.miguelgonzalezgomez.dataBaseFun.domain;

/**
 *
 * @author Miguel González
 */
public interface ConexionListener {
    public void modificadoNombre(MConexion mConexion);
    public void modificadoTipoBaseDatos(MConexion mConexion);
    public void modificadoSid(MConexion mConexion);
    public void modificadaIp(MConexion mConexion);
    public void modificadoPuerto(MConexion mConexion);
    public void modificadoUsuario(MConexion mConexion);
    public void modificadoPassword(MConexion mConexion);
}
