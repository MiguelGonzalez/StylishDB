package es.miguelgonzalezgomez.dataBaseFun.modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class MPestanasEditorAbiertas {
    
    private List<MPestanaEditor> pestanasEditoresAbiertas;
    private MPestanaEditor pestanaEditorActiva;
    
    private transient List<PestanaEditorListener> pestanasListeners;
    
    public MPestanasEditorAbiertas() {
        pestanasEditoresAbiertas = new ArrayList<>();
        pestanasListeners = new ArrayList<>();
        pestanaEditorActiva = null;
    }
    
    public void establecerEditorActivo(MPestanaEditor mPestanaEditor) {
        for(MPestanaEditor mPestana : pestanasEditoresAbiertas) {
            if(mPestana.equals(mPestanaEditor)) {
                pestanaEditorActiva = mPestana;
            }
        }
    }
    
    public void addPestanaEditorListener(PestanaEditorListener listener) {
        pestanasListeners.add(listener);
    }
    
    public void removePestanaEditorListener(PestanaEditorListener listener) {
        pestanasListeners.remove(listener);
    }
    
    public List<MPestanaEditor> getPestanasEditoresAbiertas() {
        List<MPestanaEditor> pestanasAbiertas = new ArrayList<>();
        for(MPestanaEditor pestanaAbierta : pestanasAbiertas) {
            pestanasAbiertas.add(pestanaAbierta.clone());
        }

        return pestanasAbiertas;
    }
    
    public void addNuevaPestanaEditor(MPestanaEditor pestanaEditor) {
        pestanasEditoresAbiertas.add(pestanaEditor.clone());
        
        notificarNuevaPestanaEditor(pestanaEditor);
    }
    
    public void removePestanaEditor(MPestanaEditor pestanaEditor) {
        pestanasEditoresAbiertas.remove(pestanaEditor);
        
        notificarEliminadaPestanaEditor(pestanaEditor);
    }
    
    public void editadaPestanaEditor(MPestanaEditor pestanaEditorEditada) {
        for(MPestanaEditor pestanaEditor : pestanasEditoresAbiertas) {
            if(pestanaEditor.equals(pestanaEditorEditada)) {
                pestanaEditor.nombrePestana = pestanaEditorEditada.nombrePestana;
            }
        }
        
        notificarModificadaPestanaEditor(pestanaEditorEditada);
    }
    
    private void notificarNuevaPestanaEditor(MPestanaEditor pestanaEditor) {
        for(PestanaEditorListener pestanaEditorListener : getCopiaPestanasListeners()) {
            pestanaEditorListener.nuevaPestanaEditor(pestanaEditor);
        }
    }
    
    private void notificarEliminadaPestanaEditor(MPestanaEditor pestanaEditor) {
        for(PestanaEditorListener pestanaEditorListener : getCopiaPestanasListeners()) {
            pestanaEditorListener.eliminadaPestanaEditor(pestanaEditorListener);
        }
    }
    
    private void notificarModificadaPestanaEditor(MPestanaEditor pestanaEditorEditada) {
        for(PestanaEditorListener pestanaListener : getCopiaPestanasListeners()) {
            pestanaListener.modificadaPestanaEditor(pestanaEditorEditada);
        }
    }
    
    private List<PestanaEditorListener> getCopiaPestanasListeners() {
        List<PestanaEditorListener> pestanasListenersCopy = new
                ArrayList<>(pestanasListeners);
        return pestanasListenersCopy;
    }

    public void deshacerPestanaActiva() {
        if(hayPestanaActiva()) {
            notificarDeshacerPestana(pestanaEditorActiva);
        }
    }
    
    public void notificarDeshacerPestana(MPestanaEditor pestanaEditor) {
        for(PestanaEditorListener pestanaEditorListener : getCopiaPestanasListeners()) {
            pestanaEditorListener.deshacerPestana(pestanaEditor);
        }
    }
    
    public void rehacerPestanaActiva() {
        if(hayPestanaActiva()) {
            notificarRehacerPestana(pestanaEditorActiva);
        }
    }
    
    private void notificarRehacerPestana(MPestanaEditor pestanaEditor) {
        for(PestanaEditorListener pestanaEditorListener : getCopiaPestanasListeners()) {
            pestanaEditorListener.rehacerPestana(pestanaEditor);
        }
    }
    
    public boolean hayPestanaActiva() {
        return pestanaEditorActiva != null;
    }

    public MPestanaEditor getPestanaActiva() {
        if(hayPestanaActiva()) {
            return pestanaEditorActiva.clone();
        } else {
            return null;
        }
    }
}
