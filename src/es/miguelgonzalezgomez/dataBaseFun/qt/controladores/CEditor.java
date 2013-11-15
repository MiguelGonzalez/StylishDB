package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.EditorTexto;
import es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor.ConstruirSyntaxHighlighter;

/**
 *
 * @author Miguel González
 */
public class CEditor {
    
    private GEditoresAplicacion editoresAplicacion;
    private EditorTexto editorTexto;
    
    private MPestanaEditor mPestanaEditor;
    private MAplicacion mAplicacion;
    
    public CEditor(MPestanaEditor mPestanaEditor) {
        this.mPestanaEditor = mPestanaEditor;
        mAplicacion = MAplicacion.getInstance();
        
        editoresAplicacion = new GEditoresAplicacion();
        
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
        editoresAplicacion.textoCambiadoPestana(mPestanaEditor,
                editorTexto.document().toPlainText()
        );
    }
    
    public void eventoSeleccionCambiado() {
        boolean hayTextoSeleccionado = editorTexto.textCursor().hasSelection();
        String textoSeleccionado = "";
        if(hayTextoSeleccionado) {
            textoSeleccionado = editorTexto.textCursor().selectedText();
        }
        editoresAplicacion.establecerEstadoTextoSeleccionado(
                hayTextoSeleccionado, textoSeleccionado
        );
    }

    public void cambiarSiguientePestana() {
        editoresAplicacion.cambiarSiguientePestana();
    }

    public void cambiarAnteriorPestana() {
        editoresAplicacion.cambiarAnteriorPestana();
    }

    public void establecerEditorVisible() {
        editoresAplicacion.establecerPestanaActiva(
                mPestanaEditor);
    }
}
