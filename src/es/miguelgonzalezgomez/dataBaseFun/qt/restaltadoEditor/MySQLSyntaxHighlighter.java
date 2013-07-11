package es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor;

import com.trolltech.qt.core.QRegExp;
import com.trolltech.qt.core.Qt.CaseSensitivity;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QSyntaxHighlighter;
import com.trolltech.qt.gui.QTextCharFormat;
import com.trolltech.qt.gui.QTextDocument;
import java.util.ArrayList;
import java.util.List;
import static es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor.lenguajes.MySQL.*;

/**
 *
 * @author Miguel González
 */
public class MySQLSyntaxHighlighter extends QSyntaxHighlighter {

    private List<HighlightingRule> highlightingRules;
    
    private QTextCharFormat mySQLPalabrasReservadas;
    private QTextCharFormat mySQLFuncionesFormat;
    private QTextCharFormat comentariosFormat;
    private QTextCharFormat encryptionCompressionFormat;
    private QTextCharFormat informationFormat;
    
    private QRegExp commentStartExpression;
    private QRegExp commentEndExpression;
    
    public MySQLSyntaxHighlighter(QTextDocument parent) {
        super(parent);
        
        highlightingRules = new ArrayList<>();
        
        mySQLPalabrasReservadas= new QTextCharFormat();
        mySQLFuncionesFormat = new QTextCharFormat();
        comentariosFormat = new QTextCharFormat();
        encryptionCompressionFormat = new QTextCharFormat();
        informationFormat = new QTextCharFormat();
        
        construirResaltadoPalabrasClavesMySQL();
        construirResaltadoFunciones();
        construirResaltadoEncriptacionComprension();
        construirResaltadoComentarios();
        construirRestaltadoInformacion();
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
        
        int startIndex = 0;
            if (previousBlockState() != 1)
                startIndex = commentStartExpression.indexIn(text);


            while (startIndex >= 0) {
                int endIndex = commentEndExpression.indexIn(text, startIndex);
                int commentLength;
                if (endIndex == -1) {
                    setCurrentBlockState(1);
                    commentLength = text.length() - startIndex;
                } else {
                    commentLength = endIndex - startIndex + commentEndExpression.matchedLength();
                }
                setFormat(startIndex, commentLength, comentariosFormat);
                startIndex = commentStartExpression.indexIn(text, startIndex + commentLength);
            }
    }
    
    private void construirResaltadoPalabrasClavesMySQL() {
        QBrush brush = new QBrush(QColor.darkRed);
        mySQLPalabrasReservadas.setForeground(brush);
        mySQLPalabrasReservadas.setFontWeight(QFont.Weight.Bold.value());
        
        for (String keyword : palabrasClavesReservadasMySQL) {
            QRegExp pattern = new QRegExp("\\b" + keyword + "\\b",
                    CaseSensitivity.CaseInsensitive);
            HighlightingRule rule = new HighlightingRule(pattern, mySQLPalabrasReservadas);
            highlightingRules.add(rule);
        }
    }
    
    private void construirResaltadoFunciones() {
        QBrush brush = new QBrush(QColor.darkMagenta);
        mySQLFuncionesFormat.setForeground(brush);
        mySQLFuncionesFormat.setFontWeight(QFont.Weight.Bold.value());
        
        for (String keyword : funcionesMySQL) {
            QRegExp pattern = new QRegExp("\\b" + keyword + "\\b",
                    CaseSensitivity.CaseInsensitive);
            HighlightingRule rule = new HighlightingRule(pattern, mySQLFuncionesFormat);
            highlightingRules.add(rule);
        }
    }

    private void construirResaltadoComentarios() {
        QBrush brush = new QBrush(QColor.lightGray);
        comentariosFormat.setForeground(brush);
        comentariosFormat.setFontWeight(QFont.Weight.Bold.value());
        
        QRegExp patronDobleLinea = new QRegExp("--[^\n]*");
        QRegExp patronComentarioLinea = new QRegExp("/\\*[^\n]*\\*/");
        
        HighlightingRule reglaComentarioDobleLinea = new HighlightingRule(
                patronDobleLinea,
                comentariosFormat);
        highlightingRules.add(reglaComentarioDobleLinea);
        
        HighlightingRule reglaComentarioLinea = new HighlightingRule(
                patronComentarioLinea,
                comentariosFormat);
        highlightingRules.add(reglaComentarioLinea);
        
        commentStartExpression = new QRegExp("/\\*");
        commentEndExpression = new QRegExp("\\*/");
    }

    private void construirResaltadoEncriptacionComprension() {  
        QBrush brush = new QBrush(QColor.red);
        encryptionCompressionFormat.setForeground(brush);
        encryptionCompressionFormat.setFontWeight(QFont.Weight.Bold.value());
        
        for (String keyword : encryptionCompressionFunctions) {
            QRegExp pattern = new QRegExp("\\b" + keyword + "\\b",
                    CaseSensitivity.CaseInsensitive);
            HighlightingRule rule = new HighlightingRule(pattern, encryptionCompressionFormat);
            highlightingRules.add(rule);
        }     
    }

    private void construirRestaltadoInformacion() {
        
        QBrush brush = new QBrush(QColor.darkMagenta);
        informationFormat.setForeground(brush);
        informationFormat.setFontWeight(QFont.Weight.Bold.value());
        
        for (String keyword : informationFunctions) {
            QRegExp pattern = new QRegExp("\\b" + keyword + "\\b",
                    CaseSensitivity.CaseInsensitive);
            HighlightingRule rule = new HighlightingRule(pattern, informationFormat);
            highlightingRules.add(rule);
        } 
    }
}
