package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.EditorTexto;
import es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor.ConstruirSyntaxHighlighter;

/**
 *
 * @author Miguel González
 */
public class CEditor {

    private MPestanaEditor mPestanaEditor;
    private EditorTexto editorTexto;
    
    public CEditor(MPestanaEditor mPestanaEditor) {
        this.mPestanaEditor = mPestanaEditor;
        
        construirEditorTexto();
        establecerResaltadoSintaxis();
    }
    
    public EditorTexto getEditorTexto() {
        return editorTexto;
    }
            
    
    private void construirEditorTexto() {
        editorTexto = new EditorTexto();
    }
    
    private void establecerResaltadoSintaxis() {
        ConstruirSyntaxHighlighter.establecerSyntaxHighlighter(
                mPestanaEditor.mConexion.gestor,
                editorTexto.document()
        );
    }
    
}
