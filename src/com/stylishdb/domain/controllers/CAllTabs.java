package com.stylishdb.domain.controllers;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 ** @author StylishDB
 */
public class CAllTabs {
    
    private static CAllTabs INSTANCE;
    
    static {
        INSTANCE = null;
    }
    
    private CopyOnWriteArrayList<CAllTabsListener> listeners;
    
    private CAllTabs() {
        listeners = new CopyOnWriteArrayList<>();
    }
    
    public static CAllTabs getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CAllTabs();
        }
        
        return INSTANCE;
    }
    
    public void addListener(CAllTabsListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(CAllTabsListener listener) {
        listeners.remove(listener);
    }
    
    public void cambiarAnteriorPestana() {
        comprobarInicializacionListeners();
        for(CAllTabsListener pestanasListener : listeners) {
            pestanasListener.cambiarAnterior();
        }
    }

    public void cambiarSiguientePestana() {
        comprobarInicializacionListeners();
        for(CAllTabsListener pestanasListener : listeners) {
            pestanasListener.cambiarSiguiente();
        }
    }
    
    private void comprobarInicializacionListeners() {
        if(listeners == null) {
            listeners = new CopyOnWriteArrayList();
        }
    }
}
