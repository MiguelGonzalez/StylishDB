package com.stylishdb.domain;

/**
 *
 ** @author StylishDB
 */
public interface MConnectionListener {
    public void modificadoNombre(MConnection mConexion);
    public void modificadoTipoBaseDatos(MConnection mConexion);
    public void modificadoSid(MConnection mConexion);
    public void modificadaIp(MConnection mConexion);
    public void modificadoPuerto(MConnection mConexion);
    public void modificadoUsuario(MConnection mConexion);
    public void modificadoPassword(MConnection mConexion);
}
