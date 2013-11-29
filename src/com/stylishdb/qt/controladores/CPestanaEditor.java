package com.stylishdb.qt.controladores;

import com.stylishdb.domain.MPestana;
import com.stylishdb.domain.controladores.CPestanaActivaListener;
import com.stylishdb.qt.ContenedorEditor;

/**
 *
 ** @author StylishDB
 */
public class CPestanaEditor extends CMiControladorGenerico
        implements CPestanaActivaListener {
    
    private CEditor controladorEditor;
    private MPestana mPestanaEditor;
    
    public CPestanaEditor(MPestana mPestanaEditor) {
        super();
        
        this.mPestanaEditor = mPestanaEditor;
        
        controladorEditor = new CEditor(mPestanaEditor);
        
        initListeners();
    }
    
    private void initListeners() {
        controladorPestanaActiva.addListener(this);
    }

    public ContenedorEditor getContenedorEditor() {
        return controladorEditor.getContenedorEditor();
    }

    public MPestana getModeloEditor() {
        return mPestanaEditor;
    }

    public void estaVisible() {
        pestanasAbiertas.establecerEditorActivo(mPestanaEditor);
    }

    @Override
    public void deshacer(MPestana pestana) {
        if(this.mPestanaEditor.equals(pestana)) {
            controladorEditor.deshacerTexto();
        }
    }

    @Override
    public void rehacer(MPestana pestana) {
        if(this.mPestanaEditor.equals(pestana)) {
            controladorEditor.rehacerTexto();
        }
    }

    @Override
    public void eliminada(MPestana pestana) {
        if(this.mPestanaEditor.equals(pestana)) {
            controladorEditor.quitarListeners();
            controladorPestanaActiva.removeListener(this);
        }
    }

    @Override
    public void ejecutarConsulta(MPestana pestana) {}
}
