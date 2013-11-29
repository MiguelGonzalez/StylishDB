package com.stylishdb.domain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 ** @author StylishDB
 */
public class MPestanasAbiertas {
    
    public CopyOnWriteArrayList<MPestana> pestanasAbiertas;
    
    private transient CopyOnWriteArrayList<PestanasAbiertasListener> listeners;
    private transient MPestana pestanaActiva;
    
    public MPestanasAbiertas() {
        pestanasAbiertas = new CopyOnWriteArrayList<>();
        listeners = new CopyOnWriteArrayList<>();
        
        pestanaActiva = null;
    }
    
    public void addListener(PestanasAbiertasListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(PestanasAbiertasListener listener) {
        listeners.remove(listener);
    }
    
    public List<MPestana> getPestanasAbiertas() {
        return pestanasAbiertas;
    }
    
    public boolean hayPestanaActiva() {
        return pestanaActiva != null;
    }

    public MPestana getPestanaActiva() {
        if(hayPestanaActiva()) {
            return pestanaActiva;
        } else {
            return null;
        }
    }
    
    public void establecerEditorActivo(MPestana mPestana) {
        for(MPestana mPestanaR : pestanasAbiertas) {
            if(mPestanaR.equals(mPestana)) {
                pestanaActiva = mPestana;
            }
        }
        
        comprobarInicializacionListeners();
        for(PestanasAbiertasListener listener : listeners) {
            listener.pestanaActiva(mPestana);
        }
    }
    
    public void addPestana(MPestana mPestana) {
        pestanasAbiertas.add(mPestana);
        
        comprobarInicializacionListeners();
        for(PestanasAbiertasListener listener : listeners) {
            listener.anadidaPestana(mPestana);
        }
    }
    
    public void removePestana(MPestana mPestana) {
        pestanasAbiertas.remove(mPestana);
        
        comprobarInicializacionListeners();
        for(PestanasAbiertasListener listener : listeners) {
            listener.eliminadaPestana(mPestana);
        }
    }
    
    private void comprobarInicializacionListeners() {
        if(listeners == null) {
            listeners = new CopyOnWriteArrayList();
        }
    }
}
