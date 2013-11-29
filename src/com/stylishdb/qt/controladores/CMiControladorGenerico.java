package com.stylishdb.qt.controladores;

import com.stylishdb.Preferencias;
import com.stylishdb.domain.MAplicacion;
import com.stylishdb.domain.MConexionesGuardadas;
import com.stylishdb.domain.MPestanasAbiertas;
import com.stylishdb.domain.controladores.CPestanaActiva;
import com.stylishdb.domain.controladores.CPestanasAbiertas;

/**
 *
 ** @author StylishDB
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
