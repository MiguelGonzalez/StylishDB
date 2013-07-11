package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.gui.QTextEdit;

/**
 *
 * @author Miguel González
 */
public class EditorTexto extends QTextEdit {
    public EditorTexto() {
        super();
        
        setLineWrapMode(QTextEdit.LineWrapMode.NoWrap);
        setAcceptRichText(false);
    }
}
