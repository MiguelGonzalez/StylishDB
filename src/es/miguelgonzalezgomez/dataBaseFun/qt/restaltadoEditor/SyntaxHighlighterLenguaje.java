package es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor;

import com.trolltech.qt.core.QRegExp;
import com.trolltech.qt.core.Qt.CaseSensitivity;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QSyntaxHighlighter;
import com.trolltech.qt.gui.QTextCharFormat;
import com.trolltech.qt.gui.QTextDocument;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.TiposBasesDeDatos.TIPO_BASE_DATOS;
import es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.lenguajes.DatosBaseDatos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class SyntaxHighlighterLenguaje extends QSyntaxHighlighter {

    private List<HighlightingRule> highlightingRules;
    
    private QTextCharFormat palabrasReservadasFormat;
    private QTextCharFormat funcionesReservadasFormat;
    private QTextCharFormat comentariosFormat;
    private QTextCharFormat funcionesEspecialesReservadasFormat;
    
    private QRegExp commentStartExpression;
    private QRegExp commentEndExpression;
    
    private DatosBaseDatos datosBaseDatos;
    
    public SyntaxHighlighterLenguaje(QTextDocument parent,
            TIPO_BASE_DATOS tipoBaseDeDatos) {
        super(parent);
        
        datosBaseDatos = tipoBaseDeDatos.getDatosBaseDatos();
        
        highlightingRules = new ArrayList<>();
        
        palabrasReservadasFormat= new QTextCharFormat();
        funcionesReservadasFormat = new QTextCharFormat();
        comentariosFormat = new QTextCharFormat();
        funcionesEspecialesReservadasFormat = new QTextCharFormat();
        
        construirResaltadoPalabrasClavesMySQL();
        construirResaltadoFunciones();
        construirResaltadoFuncionesEspeciales();
        construirResaltadoComentarios();
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
        QBrush brush = new QBrush(QColor.fromRgb(249, 38, 114));
        palabrasReservadasFormat.setForeground(brush);
        palabrasReservadasFormat.setFontWeight(QFont.Weight.Bold.value());
        
        for (String keyword : datosBaseDatos.getPalabrasReservadas()) {
            QRegExp pattern = new QRegExp("\\b" + keyword + "\\b",
                    CaseSensitivity.CaseInsensitive);
            HighlightingRule rule = new HighlightingRule(pattern, palabrasReservadasFormat);
            highlightingRules.add(rule);
        }
    }
    
    private void construirResaltadoFunciones() {
        QBrush brush = new QBrush(QColor.magenta);
        funcionesReservadasFormat.setForeground(brush);
        funcionesReservadasFormat.setFontWeight(QFont.Weight.Bold.value());
        
        for (String keyword : datosBaseDatos.getFuncionesReservadas()) {
            QRegExp pattern = new QRegExp("\\b" + keyword + "\\b",
                    CaseSensitivity.CaseInsensitive);
            HighlightingRule rule = new HighlightingRule(pattern, funcionesReservadasFormat);
            highlightingRules.add(rule);
        }
    }

    private void construirResaltadoComentarios() {
        QBrush brush = new QBrush(QColor.yellow);
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

    private void construirResaltadoFuncionesEspeciales() {  
        QBrush brush = new QBrush(QColor.red);
        funcionesEspecialesReservadasFormat.setForeground(brush);
        funcionesEspecialesReservadasFormat.setFontWeight(QFont.Weight.Bold.value());
        
        for (String keyword : datosBaseDatos.getFuncionesEspecialesReservadas()) {
            QRegExp pattern = new QRegExp("\\b" + keyword + "\\b",
                    CaseSensitivity.CaseInsensitive);
            HighlightingRule rule = new HighlightingRule(pattern, funcionesEspecialesReservadasFormat);
            highlightingRules.add(rule);
        }     
    }
}
