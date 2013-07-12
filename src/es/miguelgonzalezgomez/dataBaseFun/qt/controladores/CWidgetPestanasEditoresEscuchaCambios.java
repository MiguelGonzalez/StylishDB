/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import static es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditorEvento.*;
import es.miguelgonzalezgomez.dataBaseFun.modelos.PestanaEditorListener;

/**
 *
 * @author paracaidista
 */
public class CWidgetPestanasEditoresEscuchaCambios implements PestanaEditorListener {
    
    private CWidgetPestanasEditores controlador;

    public CWidgetPestanasEditoresEscuchaCambios(CWidgetPestanasEditores controlador) {
        this.controlador = controlador;
    }
    
    @Override
    public void modificadaPestanaEditor(MPestanaEditor pestanaEditorEditada,
            int evento) {
        if(evento == EVENT_RENOMBRADA) {
            controlador.comprobarYRenombrarPestanaEditor(pestanaEditorEditada);
        }
    }

    @Override
    public void eliminadaPestanaEditor(PestanaEditorListener pestanaEditorListener) {

    }

    @Override
    public void nuevaPestanaEditor(MPestanaEditor pestanaEditor) {
        controlador.addTab(pestanaEditor);
    }

    @Override
    public void atajoTeclado(MPestanaEditor pestanaEditor, int atajo) {
        if(atajo == EVENT_DESHACER) {
            controlador.buscarYDeshacerPestana(pestanaEditor);
        }
        if(atajo == EVENT_REHACER) {
            controlador.buscarYRehacerPestana(pestanaEditor);
        }
        if(atajo == EVENT_CERRAR_PESTANA) {
            controlador.buscarYCerrarPestana(pestanaEditor);
        }
    }
    
    
    
}
