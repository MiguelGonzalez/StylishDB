package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import java.util.regex.Pattern;

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
        boolean valido = !mConexion.ip.isEmpty();
        
        if(valido) {
            if("localhost".equals(mConexion.ip)) {
                return true;
            }
        }
        if(valido) {
            return esValidaIP(mConexion.ip);
        }
        return false;
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
    
    private boolean esValidaIP(String ip) {
        String PATTERN = 
        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        Pattern patronIp = Pattern.compile(PATTERN);
        
        return patronIp.matcher(ip).matches();
    }
}
