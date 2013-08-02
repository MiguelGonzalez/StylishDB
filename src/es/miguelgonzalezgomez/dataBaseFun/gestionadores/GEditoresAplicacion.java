package es.miguelgonzalezgomez.dataBaseFun.gestionadores;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.modelos.PestanaEditorListener;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class GEditoresAplicacion {
    
    private MAplicacion aplicacion;
    
    public GEditoresAplicacion() {
        aplicacion = MAplicacion.getInstance();
    }
    
    public List<MPestanaEditor> getPestanasEditores() {
        return aplicacion.mPestanasEditorAbiertas.getPestanasEditoresAbiertas();
    }
    
    public void borrarPestanaEditor(MPestanaEditor mPestanaEditor) {
        aplicacion.mPestanasEditorAbiertas.removePestanaEditor(mPestanaEditor);
    }
    
    public void addNuevaPestanaEditor(MPestanaEditor mPestanaEditor) {
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

    public void addPestanasEditorListener(PestanaEditorListener
            escuchaCambiosPestana) {
        aplicacion.mPestanasEditorAbiertas.addPestanaEditorListener(
                escuchaCambiosPestana);
    }

    public void cerrarPestanaActiva() {
        aplicacion.mPestanasEditorAbiertas.cerrarPestanaActiva();
    }

    public void ejecutarConsultaPestanaActiva() {
        aplicacion.mPestanasEditorAbiertas.ejecutarConsultaPestanaActiva();
    }

    public void establecerEstadoTextoSeleccionado(boolean hayTextoSeleccionado,
            String textoSeleccionado) {
        aplicacion.mPestanasEditorAbiertas.establecerEstadoTextoSeleccionado(
                hayTextoSeleccionado,
                textoSeleccionado);
    }

    public void textoCambiadoPestana(MPestanaEditor mPestanaEditor, String textoEditor) {
        aplicacion.mPestanasEditorAbiertas.textoCambiadoPestana(
                mPestanaEditor,
                textoEditor);
    }

    public void cambiarAnteriorPestana() {
        aplicacion.mPestanasEditorAbiertas.cambiarAnteriorPestana();
    }

    public void cambiarSiguientePestana() {
        aplicacion.mPestanasEditorAbiertas.cambiarSiguientePestana();
    }
}
