package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;

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
        return !mConexion.nombre.isEmpty();
    }
    
    public boolean isGestorValido() {
        return !mConexion.gestor.isEmpty();
    }
    
    public boolean isSidValido() {
        return !mConexion.sid.isEmpty();
    }
    
    public boolean isUsuarioValido() {
        return !mConexion.usuario.isEmpty();
    }
    
    public boolean isPasswordValido() {
        return !mConexion.password.isEmpty();
    }
    
    public boolean isIpValido() {
        return !mConexion.ip.isEmpty();
    }
    
    public boolean isPuertoValido() {
        return !mConexion.puerto.isEmpty() &&
                esStringNumero(mConexion.puerto);
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
