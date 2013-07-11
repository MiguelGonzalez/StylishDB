package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.gui.QTextEdit;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;

/**
 *
 * @author Miguel González
 */
public class EditorTexto extends QTextEdit {
    
    private MPestanaEditor mPestanaEditor;
    
    public EditorTexto(MPestanaEditor mPestanaEditor) {
        super();
        
        this.mPestanaEditor = mPestanaEditor;
        
        setLineWrapMode(QTextEdit.LineWrapMode.NoWrap);
        setAcceptRichText(false);
    }
    
    public MPestanaEditor getMPestanaEditor() {
        return mPestanaEditor;
    }
}
