package es.miguelgonzalezgomez.dataBaseFun.domain.controladores;

import es.miguelgonzalezgomez.dataBaseFun.domain.MAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.domain.MPestanasAbiertas;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Miguel González
 */
public class CPestanaActiva {

    private static CPestanaActiva INSTANCE;
    
    static {
        INSTANCE = null;
    }
    
    private MPestanasAbiertas pestanasAbiertas;
    private static CopyOnWriteArrayList<CPestanaActivaListener> listeners;
    
    public CPestanaActiva() {
        MAplicacion mAplicacion = MAplicacion.getInstance();
        pestanasAbiertas = mAplicacion.mPestanasEditorAbiertas;
        
        listeners = new CopyOnWriteArrayList<>();
    }
    
    public static CPestanaActiva getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CPestanaActiva();
        }
        
        return INSTANCE;
    }
        
    public void addListener(CPestanaActivaListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(CPestanaActivaListener listener) {
        listeners.remove(listener);
    }
    
    public void deshacerPestana() {
        comprobarInicializacionListeners();
        for(CPestanaActivaListener listener : listeners) {
            listener.deshacer(
                    pestanasAbiertas.getPestanaActiva());
        }
    }
    
    public void rehacerPestana() {
        comprobarInicializacionListeners();
        for(CPestanaActivaListener listener : listeners) {
            listener.rehacer(
                    pestanasAbiertas.getPestanaActiva());
        }
    }
    
    public void ejecutarConsulta() {
        comprobarInicializacionListeners();
        for(CPestanaActivaListener listener : listeners) {
            listener.ejecutarConsulta(
                    pestanasAbiertas.getPestanaActiva());
        }
    }
    
    public void eliminada() {
        comprobarInicializacionListeners();
        for(CPestanaActivaListener listener : listeners) {
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
