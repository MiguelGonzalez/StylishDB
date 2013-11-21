package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.domain.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.domain.MPestana;
import es.miguelgonzalezgomez.dataBaseFun.qt.EditorTexto;
import es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor.ConstruirSyntaxHighlighter;

/**
 *
 * @author Miguel González
 */
public class CEditor extends CMiControladorGenerico {
    
    private EditorTexto editorTexto;
    private MPestana mPestanaEditor;

    public CEditor(MPestana mPestanaEditor) {
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
                mConexion.getTipoDeBaseDeDatos(),
                editorTexto.document()
        );
    }
    
    private void establecerTextoModeloPestana() {
        String textoEditor = mPestanaEditor.getTextoEditor();
        editorTexto.setPlainText(textoEditor);
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

    public MPestana getModeloEditor() {
        return mPestanaEditor;
    }
    
    public void eventoTextoCambiado() {
        String textoEditor = editorTexto.document().toPlainText();
        mPestanaEditor.setTextoEditor(textoEditor);
    }
    
    public void eventoSeleccionCambiado() {
        boolean hayTextoSeleccionado = editorTexto.textCursor().hasSelection();
        String textoSeleccionado = "";
        if(hayTextoSeleccionado) {
            textoSeleccionado = editorTexto.textCursor().selectedText();
        }
        mPestanaEditor.setTextoSeleccionado(
                textoSeleccionado,
                hayTextoSeleccionado);
    }

    public void cambiarSiguientePestana() {
        controladorPestanasAbiertas.cambiarSiguientePestana();
    }

    public void cambiarAnteriorPestana() {
        controladorPestanasAbiertas.cambiarAnteriorPestana();
    }

    public void establecerEditorVisible() {
        pestanasAbiertas.establecerEditorActivo(mPestanaEditor);
    }
}
