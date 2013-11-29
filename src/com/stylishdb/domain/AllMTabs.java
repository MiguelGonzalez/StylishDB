package com.stylishdb.domain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 ** @author StylishDB
 */
public class AllMTabs {
    
    public CopyOnWriteArrayList<MTab> pestanasAbiertas;
    
    private transient CopyOnWriteArrayList<AllMTabsListener> listeners;
    private transient MTab pestanaActiva;
    
    public AllMTabs() {
        pestanasAbiertas = new CopyOnWriteArrayList<>();
        listeners = new CopyOnWriteArrayList<>();
        
        pestanaActiva = null;
    }
    
    public void addListener(AllMTabsListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(AllMTabsListener listener) {
        listeners.remove(listener);
    }
    
    public List<MTab> getPestanasAbiertas() {
        return pestanasAbiertas;
    }
    
    public boolean hayPestanaActiva() {
        return pestanaActiva != null;
    }

    public MTab getPestanaActiva() {
        if(hayPestanaActiva()) {
            return pestanaActiva;
        } else {
            return null;
        }
    }
    
    public void establecerEditorActivo(MTab mPestana) {
        for(MTab mPestanaR : pestanasAbiertas) {
            if(mPestanaR.equals(mPestana)) {
                pestanaActiva = mPestana;
            }
        }
        
        comprobarInicializacionListeners();
        for(AllMTabsListener listener : listeners) {
            listener.pestanaActiva(mPestana);
        }
    }
    
    public void addPestana(MTab mPestana) {
        pestanasAbiertas.add(mPestana);
        
        comprobarInicializacionListeners();
        for(AllMTabsListener listener : listeners) {
            listener.anadidaPestana(mPestana);
        }
    }
    
    public void removePestana(MTab mPestana) {
        pestanasAbiertas.remove(mPestana);
        
        comprobarInicializacionListeners();
        for(AllMTabsListener listener : listeners) {
            listener.eliminadaPestana(mPestana);
        }
    }
    
    private void comprobarInicializacionListeners() {
        if(listeners == null) {
            listeners = new CopyOnWriteArrayList();
        }
    }
}
