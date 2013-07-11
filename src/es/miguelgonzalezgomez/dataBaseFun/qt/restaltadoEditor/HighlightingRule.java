package es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor;

import com.trolltech.qt.core.QRegExp;
import com.trolltech.qt.gui.QTextCharFormat;

/**
 *
 * @author Miguel González
 */
public class HighlightingRule {
    public QRegExp pattern;
    public QTextCharFormat format;

    public HighlightingRule(QRegExp pattern, QTextCharFormat format) {
        this.pattern = pattern;
        this.format = format;
    }
}
