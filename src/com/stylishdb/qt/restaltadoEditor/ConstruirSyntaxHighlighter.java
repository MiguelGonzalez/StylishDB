package com.stylishdb.qt.restaltadoEditor;

import static com.stylishdb.bd.domain.TiposBasesDeDatos.*;
import com.trolltech.qt.gui.QTextDocument;

/**
 *
 ** @author StylishDB
 */
public class ConstruirSyntaxHighlighter {
    
    public static void establecerSyntaxHighlighter(
            TIPO_BASE_DATOS tipoBaseDatos,
            QTextDocument parent) {
        
        new SyntaxHighlighterLenguaje(parent, tipoBaseDatos);
    }
    
}
