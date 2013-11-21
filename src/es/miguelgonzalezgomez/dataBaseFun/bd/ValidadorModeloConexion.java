package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.domain.MConexion;

/**
 *
 * @author Miguel González
 */
public class ValidadorModeloConexion {
    
    private MConexion mConexion;
    
    public ValidadorModeloConexion(MConexion mConexion) {
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
