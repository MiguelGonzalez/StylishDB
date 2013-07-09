package es.miguelgonzalezgomez.dataBaseFun.modelos;

/**
 *
 * @author Miguel González
 */
public class MAplicacion {
    
    public MConexionesGuardadas mConexionesGuardadas;
    private static MAplicacion INSTANCE = null;
    
    private MAplicacion() {
        mConexionesGuardadas = new MConexionesGuardadas();
    }
    
    public static MAplicacion getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MAplicacion();
        }
        return INSTANCE;
    }
}
