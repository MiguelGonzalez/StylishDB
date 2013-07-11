package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.gui.QTextEdit;
import es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor.ConstruirSyntaxHighlighter;
import es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor.MySQLSyntaxHighlighter;

/**
 *
 * @author Miguel González
 */
public class EditorTexto extends QTextEdit {
    public EditorTexto() {
        super();
        
        setLineWrapMode(QTextEdit.LineWrapMode.NoWrap);
    }
}
