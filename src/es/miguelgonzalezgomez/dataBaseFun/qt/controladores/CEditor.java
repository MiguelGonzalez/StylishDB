package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.bd.GestionadorEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.EditorTexto;
import es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor.ConstruirSyntaxHighlighter;

/**
 *
 * @author Miguel González
 */
public class CEditor {
    
    private GestionadorEditoresAplicacion editoresAplicacion;
    private MPestanaEditor mPestanaEditor;
    private EditorTexto editorTexto;
    
    public CEditor(MPestanaEditor mPestanaEditor) {
        this.mPestanaEditor = mPestanaEditor;
        editoresAplicacion = new GestionadorEditoresAplicacion();
        
        construirEditorTexto();
        establecerResaltadoSintaxis();
    }
    
    public EditorTexto getEditorTexto() {
        return editorTexto;
    }
            
    
    private void construirEditorTexto() {
        editorTexto = new EditorTexto(this);
        editorTexto.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("editor.css")
        );
    }
    
    private void establecerResaltadoSintaxis() {
        ConstruirSyntaxHighlighter.establecerSyntaxHighlighter(
                mPestanaEditor.mConexion.gestor,
                editorTexto.document()
        );
    }

    public void deshacer() {
        editorTexto.undo();
    }

    public void rehacer() {
        editorTexto.redo();
    }

    public void estaVisible() {
        editoresAplicacion.establecerPestanaActiva(
                mPestanaEditor);
    }
}
