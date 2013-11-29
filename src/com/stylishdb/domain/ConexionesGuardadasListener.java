/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stylishdb.domain;

/**
 *
 * @author paracaidista
 */
public interface ConexionesGuardadasListener {
    public void nuevaConexion(MConexion mConexion);
    public void eliminadaConexion(MConexion mConexion);
}
