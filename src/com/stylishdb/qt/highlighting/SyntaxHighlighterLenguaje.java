package com.stylishdb.qt.highlighting;

import com.trolltech.qt.core.QRegExp;
import com.trolltech.qt.core.Qt.CaseSensitivity;
import com.trolltech.qt.gui.QBrush;
import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QSyntaxHighlighter;
import com.trolltech.qt.gui.QTextCharFormat;
import com.trolltech.qt.gui.QTextDocument;
import com.stylishdb.db.domain.TypeManagers.TIPO_BASE_DATOS;
import com.stylishdb.db.managers.Manager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class SyntaxHighlighterLenguaje extends QSyntaxHighlighter {

    private List<HighlightingRule> highlightingRules;
    
    private QTextCharFormat palabrasReservadasFormat;
    private QTextCharFormat funcionesReservadasFormat;
    private QTextCharFormat comentariosFormat;
    private QTextCharFormat funcionesEspecialesReservadasFormat;
    
    private QRegExp commentStartExpression;
    private QRegExp commentEndExpression;
    
    private Manager datosBaseDatos;
    
    public SyntaxHighlighterLenguaje(QTextDocument parent,
            TIPO_BASE_DATOS tipoBaseDeDatos) {
        super(parent);
        
        datosBaseDatos = tipoBaseDeDatos.getDatosBaseDatos();
        
        iniciarDatos();
        
        construirDatos();
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
        
        if(datosBaseDatos.tieneComentarioDeBloque()) {
            resaltarBloquesTexto(text);
        }
    }
    
    private void resaltarBloquesTexto(String text) {
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
    
    private void iniciarDatos() {
        commentStartExpression = new QRegExp("/\\*");
        commentEndExpression = new QRegExp("\\*/");
        
        highlightingRules = new ArrayList<>();
        
        palabrasReservadasFormat= new QTextCharFormat();
        funcionesReservadasFormat = new QTextCharFormat();
        comentariosFormat = new QTextCharFormat();
        funcionesEspecialesReservadasFormat = new QTextCharFormat();
    }
    
    private void construirDatos() {
        construirResaltadoPalabrasClavesMySQL();
        construirResaltadoFunciones();
        construirResaltadoFuncionesEspeciales();
        if(datosBaseDatos.tieneComentarioDeLinea()) {
            construirResaltadoComentariosLinea();
        }
        if(datosBaseDatos.tieneComentarioDeBloque()) {
            construirResaltadoComentarioBloque();
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

    private void construirResaltadoComentariosLinea() {
        QBrush brush = new QBrush(QColor.gray);
        comentariosFormat.setForeground(brush);
        comentariosFormat.setFontWeight(QFont.Weight.Bold.value());
        
        QRegExp patronDobleLinea = new QRegExp("--[^\n]*");
        
        
        HighlightingRule reglaComentarioDobleLinea = new HighlightingRule(
                patronDobleLinea,
                comentariosFormat);
        highlightingRules.add(reglaComentarioDobleLinea);
    }
    
    private void construirResaltadoComentarioBloque() {
        QRegExp patronComentarioLinea = new QRegExp("/\\*[^\n]*\\*/");
        
        HighlightingRule reglaComentarioLinea = new HighlightingRule(
                patronComentarioLinea,
                comentariosFormat);
        highlightingRules.add(reglaComentarioLinea);
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
