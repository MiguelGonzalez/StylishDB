package es.miguelgonzalezgomez.dataBaseFun.domain.controladores;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Miguel González
 */
public class CPestanasAbiertas {
    
    private static CPestanasAbiertas INSTANCE;
    
    static {
        INSTANCE = null;
    }
    
    private CopyOnWriteArrayList<CPestanasListener> listeners;
    
    private CPestanasAbiertas() {
        listeners = new CopyOnWriteArrayList<>();
    }
    
    public static CPestanasAbiertas getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CPestanasAbiertas();
        }
        
        return INSTANCE;
    }
    
    public void addListener(CPestanasListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(CPestanasListener listener) {
        listeners.remove(listener);
    }
    
    public void cambiarAnteriorPestana() {
        comprobarInicializacionListeners();
        for(CPestanasListener pestanasListener : listeners) {
            pestanasListener.cambiarAnterior();
        }
    }

    public void cambiarSiguientePestana() {
        comprobarInicializacionListeners();
        for(CPestanasListener pestanasListener : listeners) {
            pestanasListener.cambiarSiguiente();
        }
    }
    
    private void comprobarInicializacionListeners() {
        if(listeners == null) {
            listeners = new CopyOnWriteArrayList();
        }
    }
}
