package es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor;

import static es.miguelgonzalezgomez.dataBaseFun.bd.domain.TiposBasesDeDatos.*;
import com.trolltech.qt.gui.QTextDocument;

/**
 *
 * @author Miguel González
 */
public class ConstruirSyntaxHighlighter {
    
    public static void establecerSyntaxHighlighter(
            TIPO_BASE_DATOS tipoBaseDatos,
            QTextDocument parent) {
        
        new SyntaxHighlighterLenguaje(parent, tipoBaseDatos);
    }
    
}
