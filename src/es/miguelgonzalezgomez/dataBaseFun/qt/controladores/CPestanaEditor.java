package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.gui.QTextEdit;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;

/**
 *
 * @author Miguel González
 */
public class CPestanaEditor {
    
    private GEditoresAplicacion editoresAplicacion;
    private CEditor controladorEditor;
    
    private MPestanaEditor mPestanaEditor;
    
    public CPestanaEditor(MPestanaEditor mPestanaEditor) {
        this.mPestanaEditor = mPestanaEditor;
        
        editoresAplicacion = new GEditoresAplicacion();
        controladorEditor = new CEditor(mPestanaEditor);
    }
    
    public QTextEdit getPestanaEditor() {
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
        editoresAplicacion.establecerPestanaActiva(
                mPestanaEditor);
    }
}
