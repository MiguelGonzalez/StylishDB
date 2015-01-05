package com.stylishdb.db;

import com.stylishdb.db.domain.TypeManagers;
import com.stylishdb.db.domain.TypeManagers.TIPO_BASE_DATOS;
import com.stylishdb.domain.MConnection;

/**
 *
 ** @author StylishDB
 */
public class CheckConnectionModel {
    
    private MConnection mConexion;
    
    public CheckConnectionModel(MConnection mConexion) {
        this.mConexion = mConexion;
    }
    
    public boolean isConexionValida() {
        return isNombreValido() &&
                isGestorValido() &&
                isSidValido() &&
                isUsuarioValido() &&
                isPasswordValido() &&
                isIpValido() &&
                isPuertoValido();
    }
    
    public boolean isNombreValido() {
        return !mConexion.getNombre().isEmpty();
    }
    
    public boolean isGestorValido() {
        return mConexion != null;
    }
    
    public boolean isSidValido() {
        if(mConexion.getTipoDeBaseDeDatos().equals(TIPO_BASE_DATOS.SQL_SERVER)) {
            return true;
        }
        return !mConexion.getSid().isEmpty();
    }
    
    public boolean isUsuarioValido() {
        return !mConexion.getUsuario().isEmpty();
    }
    
    public boolean isPasswordValido() {
        return !mConexion.getPassword().isEmpty();
    }
    
    public boolean isIpValido() {
        return !mConexion.getIp().isEmpty();
    }
    
    public boolean isPuertoValido() {
        return !mConexion.getPuerto().isEmpty() &&
                esStringNumero(mConexion.getPuerto());
    }
    
    private boolean esStringNumero(String string) {
        try {
            Integer.parseInt(string);
        } catch(NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
