/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.miguelgonzalezgomez.dataBaseFun.domain;

/**
 *
 * @author paracaidista
 */
public interface ConexionListener {
    public void nuevaConexion(MConexion mConexion);
    public void eliminadaConexion(MConexion mConexion);
    public void modificadaConexion(MConexion mConexionVieja, MConexion mConexionEditada);
}
