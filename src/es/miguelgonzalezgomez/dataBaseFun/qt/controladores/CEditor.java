package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.EditorTexto;
import es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor.ConstruirSyntaxHighlighter;

/**
 *
 * @author Miguel González
 */
public class CEditor {
    
    private GEditoresAplicacion editoresAplicacion;
    private MPestanaEditor mPestanaEditor;
    private EditorTexto editorTexto;
    
    public CEditor(MPestanaEditor mPestanaEditor) {
        this.mPestanaEditor = mPestanaEditor;
        editoresAplicacion = new GEditoresAplicacion();
        
        construirEditorTexto();
        establecerResaltadoSintaxis();
        establecerTextoModeloPestana();
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

    public MPestanaEditor getModeloEditor() {
        return mPestanaEditor;
    }
    
    public void eventoTextoCambiado() {
        editoresAplicacion.textoCambiadoPestanaActiva(
                editorTexto.document().toPlainText()
        );
    }

    private void establecerTextoModeloPestana() {
        editorTexto.setText(mPestanaEditor.contenidoTexto);
    }
}
