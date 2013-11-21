package es.miguelgonzalezgomez.dataBaseFun.domain;

/**
 *
 * @author Miguel González
 */
public class MAplicacion {
    
    public MConexionesGuardadas mConexionesGuardadas;
    public MPestanasAbiertas mPestanasEditorAbiertas;
    
    private static MAplicacion INSTANCE = null;
    
    private MAplicacion() {
        inicializarModelos();
    }
    
    private void inicializarModelos() {
        mConexionesGuardadas = new MConexionesGuardadas();
        mPestanasEditorAbiertas = new MPestanasAbiertas();
    }
    
    public static MAplicacion getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MAplicacion();
        }
        return INSTANCE;
    }
}
