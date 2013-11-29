package com.stylishdb.qt.highlighting;

import static com.stylishdb.db.domain.TiposBasesDeDatos.*;
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
