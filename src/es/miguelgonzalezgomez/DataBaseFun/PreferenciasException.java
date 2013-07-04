package es.miguelgonzalezgomez.DataBaseFun;

/**
 *
 * @author Miguel González
 */
public class PreferenciasException extends Exception {
    public PreferenciasException(String error) {
        super(error);
    }
    
    public PreferenciasException(String error, Exception ex) {
        super(error, ex);
    }
}
