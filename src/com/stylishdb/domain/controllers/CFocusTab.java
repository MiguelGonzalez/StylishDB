package com.stylishdb.domain.controllers;

import com.stylishdb.domain.MApplication;
import com.stylishdb.domain.AllMTabs;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 ** @author StylishDB
 */
public class CFocusTab {

    private static CFocusTab INSTANCE;
    
    static {
        INSTANCE = null;
    }
    
    private AllMTabs pestanasAbiertas;
    private static CopyOnWriteArrayList<CFocusTabListener> listeners;
    
    public CFocusTab() {
        MApplication mAplicacion = MApplication.getInstance();
        pestanasAbiertas = mAplicacion.mPestanasEditorAbiertas;
        
        listeners = new CopyOnWriteArrayList<>();
    }
    
    public static CFocusTab getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CFocusTab();
        }
        
        return INSTANCE;
    }
        
    public void addListener(CFocusTabListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(CFocusTabListener listener) {
        listeners.remove(listener);
    }
    
    public void deshacerPestana() {
        comprobarInicializacionListeners();
        for(CFocusTabListener listener : listeners) {
            listener.deshacer(
                    pestanasAbiertas.getPestanaActiva());
        }
    }
    
    public void rehacerPestana() {
        comprobarInicializacionListeners();
        for(CFocusTabListener listener : listeners) {
            listener.rehacer(
                    pestanasAbiertas.getPestanaActiva());
        }
    }
    
    public void ejecutarConsulta() {
        comprobarInicializacionListeners();
        for(CFocusTabListener listener : listeners) {
            listener.ejecutarConsulta(
                    pestanasAbiertas.getPestanaActiva());
        }
    }
    
    public void eliminada() {
        comprobarInicializacionListeners();
        for(CFocusTabListener listener : listeners) {
            listener.eliminada(
                    pestanasAbiertas.getPestanaActiva());
        }
    }
    
    private void comprobarInicializacionListeners() {
        if(listeners == null) {
            listeners = new CopyOnWriteArrayList();
        }
    }
}
