package com.stylishdb.qt.controllers;

import com.stylishdb.style.GetStyle;
import com.stylishdb.domain.MConnection;
import com.stylishdb.domain.MTab;
import com.stylishdb.domain.MTabListener;
import com.stylishdb.qt.TextEditor;
import com.stylishdb.qt.LineCounter;
import com.stylishdb.qt.HolderEditor;
import com.stylishdb.qt.highlighting.BuiltSyntaxHighlighter;

/**
 *
 ** @author StylishDB
 */
public class CEditor extends Controller
        implements MTabListener {
    
    private MTab mPestanaEditor;
    
    private TextEditor editorTexto;
    private LineCounter contadorLineas;
    private HolderEditor contenedorEditor;
    

    public CEditor(MTab mPestanaEditor) {
        super();

        this.mPestanaEditor = mPestanaEditor;
        
        construirEditorTexto();
        construirContadorLineas();
        establecerResaltadoSintaxis();
        establecerTextoModeloPestana();
        construirContenedorEditorTexto();
        
        inicializarListeners();
    }
    
    private void inicializarListeners() {
        mPestanaEditor.addPestanaListener(this);
    }
    
    private void construirEditorTexto() {
        editorTexto = new TextEditor(this);
        editorTexto.setStyleSheet(
                GetStyle.getEstiloVentana("editor.css")
        );
    }
    
    private void construirContadorLineas() {
        contadorLineas = new LineCounter(this);
        contadorLineas.setStyleSheet(
                GetStyle.getEstiloVentana("editorContadorLineas.css")
        );
    }
    
    private void construirContenedorEditorTexto() {
        contenedorEditor = new HolderEditor(this);
 
        contenedorEditor.establecerContadorLineas(contadorLineas);
        contenedorEditor.establecerEditorTexto(editorTexto);
    }
    
    private void establecerResaltadoSintaxis() {
        MConnection mConexion = mAplicacion.mConexionesGuardadas.
                getMConexion(mPestanaEditor.uuidConexion);
        BuiltSyntaxHighlighter.establecerSyntaxHighlighter(
                mConexion.getTipoDeBaseDeDatos(),
                editorTexto.document()
        );
    }
    
    private void establecerTextoModeloPestana() {
        String textoEditor = mPestanaEditor.getTextoEditor();
        editorTexto.establecerTexto(textoEditor);
    }
        
    public HolderEditor getContenedorEditor() {
        return contenedorEditor;
    }
    
    public MTab getModeloEditor() {
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
    
    public void cursorTextoCambiado() {
        actualizarSeleccion();
        actualizarScrollBar();
    }
    
    public void scrollBarCambiado() {
        actualizarScrollBar();
    }
    
    public void blockCountCambiado() {
        int numLineas = editorTexto.getNumLineas();
        contadorLineas.pintarContadorLineas(numLineas);
        
        actualizarScrollBar();
        actualizarSeleccion();
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
    
    private synchronized void actualizarSeleccion() {
        int lineasSeleccionadas[] = editorTexto.getLineasSeleccionadas();
        contadorLineas.lineasSeleccionadas(lineasSeleccionadas);
    }
    
    private synchronized void actualizarScrollBar() {
        int pos = editorTexto.getPositionScrollBar();
        contadorLineas.setPosicionScrollBar(pos);
    }

    @Override
    public void textoModificado(MTab mPestana) {}

    @Override
    public void textoSeleccionado(MTab mPestana) {}

    @Override
    public void renombrada(MTab mPestana) {}
    
    @Override
    public void textoFormateado(MTab mPestana) {
        editorTexto.establecerTexto(
            mPestana.getTextoEditor()
        );
    }
    
    protected void deshacerTexto() {
        editorTexto.undo();
    }
    
    protected void rehacerTexto() {
        editorTexto.redo();
    }
    
    public void quitarListeners() {
        mPestanaEditor.removePestanaListener(this);
    }
}
