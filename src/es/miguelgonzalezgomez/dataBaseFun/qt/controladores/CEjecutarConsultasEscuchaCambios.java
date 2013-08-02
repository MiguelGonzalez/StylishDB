package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.modelos.PestanaEditorListener;

/**
 *
 * @author Miguel González
 */
public class CEjecutarConsultasEscuchaCambios implements PestanaEditorListener {
    
    private CEjecutarConsultas controlador;
    
    public CEjecutarConsultasEscuchaCambios(CEjecutarConsultas controlador) {
        this.controlador = controlador;
    }
    
    @Override
    public void modificadaPestanaEditor(MPestanaEditor pestanaEditorEditada, int tipoEvento) {

    }

    @Override
    public void eliminadaPestanaEditor(MPestanaEditor pestanaEditorEliminada) {

    }

    @Override
    public void nuevaPestanaEditor(MPestanaEditor pestanaEditor) {

    }

    @Override
    public void atajoTeclado(MPestanaEditor pestanaEditor, int tipoEvento) {

    }

    @Override
    public void ejecutarConsultaPestanaEditor(MPestanaEditor pestanaEditor) {
        controlador.lanzarConsultaTexto(pestanaEditor);
    }

    @Override
    public void cambiarSiguientePestana() {

    }

    @Override
    public void cambiarAnteriorPestana() {

    }
}
