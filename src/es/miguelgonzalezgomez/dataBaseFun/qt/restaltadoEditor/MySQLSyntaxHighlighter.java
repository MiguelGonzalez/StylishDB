package es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor;

import com.trolltech.qt.core.QRegExp;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QSyntaxHighlighter;
import com.trolltech.qt.gui.QTextCharFormat;
import com.trolltech.qt.gui.QTextDocument;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class MySQLSyntaxHighlighter extends QSyntaxHighlighter {

    private List<HighlightingRule> highlightingRules;
    private QTextCharFormat mySQLFormat;
    
    String[] keywords = { "select", "from", "where" };
    
    public MySQLSyntaxHighlighter(QTextDocument parent) {
        super(parent);
        
        highlightingRules = new ArrayList<>();
        mySQLFormat= new QTextCharFormat();
        
        construirResaltadoPalabrasClavesMySQL();
    }
    
    @Override
    protected void highlightBlock(String text) {
        for (HighlightingRule rule : highlightingRules) {
            QRegExp expression = rule.pattern;
            int index = expression.indexIn(text);
            while (index >= 0) {
                int length = expression.matchedLength();
                setFormat(index, length, rule.format);
                index = expression.indexIn(text, index + length);
            }
        }

        setCurrentBlockState(0);
    }
    
    private void construirResaltadoPalabrasClavesMySQL() {
        QBrush brush = new QBrush(QColor.darkRed);
        mySQLFormat.setForeground(brush);
        mySQLFormat.setFontWeight(QFont.Weight.Bold.value());
        
        for (String keyword : keywords) {
            QRegExp pattern = new QRegExp("\\b" + keyword + "\\b");
            HighlightingRule rule = new HighlightingRule(pattern, mySQLFormat);
            highlightingRules.add(rule);
        }
                
    }
}
