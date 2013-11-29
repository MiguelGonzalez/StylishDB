package com.stylishdb.qt.controllers;

import com.stylishdb.domain.MTab;
import com.stylishdb.domain.controllers.CFocusTabListener;
import com.stylishdb.qt.HolderEditor;

/**
 *
 ** @author StylishDB
 */
public class CTabEditor extends Controller
        implements CFocusTabListener {
    
    private CEditor controladorEditor;
    private MTab mPestanaEditor;
    
    public CTabEditor(MTab mPestanaEditor) {
        super();
        
        this.mPestanaEditor = mPestanaEditor;
        
        controladorEditor = new CEditor(mPestanaEditor);
        
        initListeners();
    }
    
    private void initListeners() {
        controladorPestanaActiva.addListener(this);
    }

    public HolderEditor getContenedorEditor() {
        return controladorEditor.getContenedorEditor();
    }

    public MTab getModeloEditor() {
        return mPestanaEditor;
    }

    public void estaVisible() {
        pestanasAbiertas.establecerEditorActivo(mPestanaEditor);
    }

    @Override
    public void deshacer(MTab pestana) {
        if(this.mPestanaEditor.equals(pestana)) {
            controladorEditor.deshacerTexto();
        }
    }

    @Override
    public void rehacer(MTab pestana) {
        if(this.mPestanaEditor.equals(pestana)) {
            controladorEditor.rehacerTexto();
        }
    }

    @Override
    public void eliminada(MTab pestana) {
        if(this.mPestanaEditor.equals(pestana)) {
            controladorEditor.quitarListeners();
            controladorPestanaActiva.removeListener(this);
        }
    }

    @Override
    public void ejecutarConsulta(MTab pestana) {}
}
