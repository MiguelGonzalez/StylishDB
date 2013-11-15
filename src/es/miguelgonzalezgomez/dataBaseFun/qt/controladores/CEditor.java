package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.EditorTexto;
import es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor.ConstruirSyntaxHighlighter;

/**
 *
 * @author Miguel González
 */
public class CEditor extends CMiControladorGenerico {
    
    private EditorTexto editorTexto;
    private MPestanaEditor mPestanaEditor;

    public CEditor(MPestanaEditor mPestanaEditor) {
        super();
        
        this.mPestanaEditor = mPestanaEditor;
    
        construirEditorTexto();
        establecerResaltadoSintaxis();
        establecerTextoModeloPestana();
    }
    
    private void construirEditorTexto() {
        editorTexto = new EditorTexto(this);
        editorTexto.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("editor.css")
        );
    }
    
    private void establecerResaltadoSintaxis() {
        MConexion mConexion = mAplicacion.mConexionesGuardadas.
                getMConexion(mPestanaEditor.uuidConexion);
        ConstruirSyntaxHighlighter.establecerSyntaxHighlighter(
                mConexion.tipoDeBaseDeDatos,
                editorTexto.document()
        );
    }
    
    private void establecerTextoModeloPestana() {
        editorTexto.setPlainText(mPestanaEditor.contenidoTexto);
    }
    
    public EditorTexto getEditorTexto() {
        return editorTexto;
    }

    public void deshacer() {
        editorTexto.undo();
    }

    public void rehacer() {
        editorTexto.redo();
    }

    public MPestanaEditor getModeloEditor() {
        return mPestanaEditor;
    }
    
    public void eventoTextoCambiado() {
        pestanasAbiertas.textoCambiadoPestana(mPestanaEditor,
                editorTexto.document().toPlainText()
        );
    }
    
    public void eventoSeleccionCambiado() {
        boolean hayTextoSeleccionado = editorTexto.textCursor().hasSelection();
        String textoSeleccionado = "";
        if(hayTextoSeleccionado) {
            textoSeleccionado = editorTexto.textCursor().selectedText();
        }
        pestanasAbiertas.establecerEstadoTextoSeleccionado(
                hayTextoSeleccionado, textoSeleccionado
        );
    }

    public void cambiarSiguientePestana() {
        pestanasAbiertas.cambiarSiguientePestana();
    }

    public void cambiarAnteriorPestana() {
        pestanasAbiertas.cambiarAnteriorPestana();
    }

    public void establecerEditorVisible() {
        pestanasAbiertas.establecerEditorActivo(mPestanaEditor);
    }
}
