package com.stylishdb.qt.highlighting;

import static com.stylishdb.db.domain.TypeManagers.*;
import com.trolltech.qt.gui.QTextDocument;

/**
 *
 ** @author StylishDB
 */
public class BuiltSyntaxHighlighter {
    
    public static void establecerSyntaxHighlighter(
            TIPO_BASE_DATOS tipoBaseDatos,
            QTextDocument parent) {
        
        new SyntaxHighlighterLenguaje(parent, tipoBaseDatos);
    }
    
}
