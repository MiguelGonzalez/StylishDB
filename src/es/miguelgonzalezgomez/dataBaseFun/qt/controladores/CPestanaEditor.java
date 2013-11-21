package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.gui.QPlainTextEdit;
import es.miguelgonzalezgomez.dataBaseFun.domain.MPestana;

/**
 *
 * @author Miguel González
 */
public class CPestanaEditor extends CMiControladorGenerico {
    
    private CEditor controladorEditor;
    private MPestana mPestanaEditor;
    
    public CPestanaEditor(MPestana mPestanaEditor) {
        super();
        
        this.mPestanaEditor = mPestanaEditor;
        
        controladorEditor = new CEditor(mPestanaEditor);
    }
    
    public QPlainTextEdit getPestanaEditor() {
        return controladorEditor.getEditorTexto();
    }

    public void deshacer() {
        controladorEditor.deshacer();
    }

    public void rehacer() {
        controladorEditor.rehacer();
    }

    public MPestana getModeloEditor() {
        return mPestanaEditor;
    }

    public void estaVisible() {
        pestanasAbiertas.establecerEditorActivo(mPestanaEditor);
    }
}
