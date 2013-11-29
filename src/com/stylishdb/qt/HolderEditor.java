package com.stylishdb.qt;

import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QSizePolicy;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.qt.controllers.CEditor;

/**
 *
 ** @author StylishDB
 */
public class HolderEditor extends QWidget {

    private CEditor controlador;
    private QHBoxLayout boxLayout;
    
    private TextEditor editorTexto;
    
    public HolderEditor(CEditor controlador) {
        this.controlador = controlador;
        
        crearLayout();
    }
    
    private void crearLayout() {
        boxLayout = new QHBoxLayout(this);
        boxLayout.setContentsMargins(0,0,0,0);
        boxLayout.setSpacing(0);
    }
    
    public void establecerContadorLineas(LineCounter contadorLineas) {
        boxLayout.addWidget(contadorLineas);
        setSizePolicy(new QSizePolicy(QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Expanding));
    }
    
    public void establecerEditorTexto(TextEditor editorTexto) {
        this.editorTexto = editorTexto;
        boxLayout.addWidget(editorTexto);
    }

    public TextEditor getEditorTexto() {
        return editorTexto;
    }
    
    
    
}
