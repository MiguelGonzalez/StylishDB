package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.gui.QPlainTextEdit;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;

/**
 *
 * @author Miguel González
 */
public class CPestanaEditor extends CMiControladorGenerico {
    
    private CEditor controladorEditor;
    private MPestanaEditor mPestanaEditor;
    
    public CPestanaEditor(MPestanaEditor mPestanaEditor) {
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

    public MPestanaEditor getModeloEditor() {
        return mPestanaEditor;
    }

    public void estaVisible() {
        gestionadorEditores.establecerPestanaActiva(
                mPestanaEditor);
    }
}
