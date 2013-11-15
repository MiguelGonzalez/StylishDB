package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.Preferencias;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GConexionesAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MAplicacion;

/**
 *
 * @author Miguel González
 */
public class CMiControladorGenerico {

    protected MAplicacion mAplicacion;
    protected GConexionesAplicacion gestionadorConexiones;
    protected GEditoresAplicacion gestionadorEditores;
    protected Preferencias prefs;
    
    public CMiControladorGenerico() {
        mAplicacion = MAplicacion.getInstance();
        gestionadorConexiones = new
                GConexionesAplicacion();
        gestionadorEditores = new GEditoresAplicacion();
        prefs = Preferencias.getInstance();
    }
    
}
