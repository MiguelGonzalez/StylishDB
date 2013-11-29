/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stylishdb.domain;

/**
 *
 * @author paracaidista
 */
public interface AllMConnectionsListener {
    public void nuevaConexion(MConnection mConexion);
    public void eliminadaConexion(MConnection mConexion);
}
