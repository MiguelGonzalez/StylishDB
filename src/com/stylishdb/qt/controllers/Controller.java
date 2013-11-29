package com.stylishdb.qt.controllers;

import com.stylishdb.Preferences;
import com.stylishdb.domain.MApplication;
import com.stylishdb.domain.AllMConnections;
import com.stylishdb.domain.AllMTabs;
import com.stylishdb.domain.controllers.CFocusTab;
import com.stylishdb.domain.controllers.CAllTabs;

/**
 *
 ** @author StylishDB
 */
public class Controller {

    protected Preferences prefs;
    protected CFocusTab controladorPestanaActiva;
    protected CAllTabs controladorPestanasAbiertas;
    protected MApplication mAplicacion;
    
    protected AllMConnections conexionesGuardadas;
    protected AllMTabs pestanasAbiertas;
    
    public Controller() {
        prefs = Preferences.getInstance();
        mAplicacion = MApplication.getInstance();
        controladorPestanaActiva = CFocusTab.getInstance();
        controladorPestanasAbiertas = CAllTabs.getInstance();
        
        conexionesGuardadas = mAplicacion.mConexionesGuardadas;
        pestanasAbiertas = mAplicacion.mPestanasEditorAbiertas;
    }
}
