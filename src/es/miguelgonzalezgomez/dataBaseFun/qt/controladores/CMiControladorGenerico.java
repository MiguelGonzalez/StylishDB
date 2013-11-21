package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.Preferencias;
import es.miguelgonzalezgomez.dataBaseFun.domain.MAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.domain.MConexionesGuardadas;
import es.miguelgonzalezgomez.dataBaseFun.domain.MPestanasAbiertas;
import es.miguelgonzalezgomez.dataBaseFun.domain.controladores.CPestanaActiva;
import es.miguelgonzalezgomez.dataBaseFun.domain.controladores.CPestanasAbiertas;

/**
 *
 * @author Miguel González
 */
public class CMiControladorGenerico {

    protected Preferencias prefs;
    protected CPestanaActiva controladorPestanaActiva;
    protected CPestanasAbiertas controladorPestanasAbiertas;
    protected MAplicacion mAplicacion;
    
    protected MConexionesGuardadas conexionesGuardadas;
    protected MPestanasAbiertas pestanasAbiertas;
    
    public CMiControladorGenerico() {
        prefs = Preferencias.getInstance();
        mAplicacion = MAplicacion.getInstance();
        controladorPestanaActiva = CPestanaActiva.getInstance();
        controladorPestanasAbiertas = CPestanasAbiertas.getInstance();
        
        conexionesGuardadas = mAplicacion.mConexionesGuardadas;
        pestanasAbiertas = mAplicacion.mPestanasEditorAbiertas;
    }
}
