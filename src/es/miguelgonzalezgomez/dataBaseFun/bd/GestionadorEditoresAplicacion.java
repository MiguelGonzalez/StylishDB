package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanasEditorAbiertas;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class GestionadorEditoresAplicacion {
    
    private MAplicacion aplicacion;
    
    public GestionadorEditoresAplicacion() {
        aplicacion = MAplicacion.getInstance();
    }
    
    public MPestanasEditorAbiertas getMPestanasEditorAbiertas() {
        return aplicacion.mPestanasEditorAbiertas;
    }
    
    public List<MPestanaEditor> getPestanasEditores() {
        return aplicacion.mPestanasEditorAbiertas.getPestanasEditoresAbiertas();
    }
    
    public void borrarPestanaEditor(MPestanaEditor mPestanaEditor) {
        aplicacion.mPestanasEditorAbiertas.removePestanaEditor(mPestanaEditor);
    }
    
    public void addNuevaConexion(MPestanaEditor mPestanaEditor) {
        aplicacion.mPestanasEditorAbiertas.addNuevaPestanaEditor(mPestanaEditor);
    }
    
    public void editadaPestanaEditor(MPestanaEditor pestanaEditorEditada) {
        aplicacion.mPestanasEditorAbiertas.editadaPestanaEditor(pestanaEditorEditada);
    }
    
    public void establecerPestanaActiva(MPestanaEditor pestanaEditor) {
        aplicacion.mPestanasEditorAbiertas.establecerEditorActivo(pestanaEditor);
    }
    
    public void deshacerPestanaActiva() {
        aplicacion.mPestanasEditorAbiertas.deshacerPestanaActiva();
    }
    
    public void rehacerPestanaActiva() {
        aplicacion.mPestanasEditorAbiertas.rehacerPestanaActiva();
    }

    public boolean hayPestanaActiva() {
        return aplicacion.mPestanasEditorAbiertas.hayPestanaActiva();
    }

    public MPestanaEditor getMPestanaActiva() {
        return aplicacion.mPestanasEditorAbiertas.getPestanaActiva();
    }
}
