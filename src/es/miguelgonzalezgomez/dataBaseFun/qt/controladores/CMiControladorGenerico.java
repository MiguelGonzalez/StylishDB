package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.Preferencias;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexionesGuardadas;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanasEditorAbiertas;

/**
 *
 * @author Miguel González
 */
public class CMiControladorGenerico {

    protected MAplicacion mAplicacion;
    protected Preferencias prefs;
    protected MConexionesGuardadas conexionesGuardadas;
    protected MPestanasEditorAbiertas pestanasAbiertas;
    
    public CMiControladorGenerico() {
        mAplicacion = MAplicacion.getInstance();
        conexionesGuardadas = mAplicacion.mConexionesGuardadas;
        pestanasAbiertas = mAplicacion.mPestanasEditorAbiertas;
        prefs = Preferencias.getInstance();
    }
    
}
