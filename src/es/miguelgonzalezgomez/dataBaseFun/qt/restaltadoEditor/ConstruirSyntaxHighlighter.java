package es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor;

import static es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.TiposBasesDeDatos.*;
import com.trolltech.qt.gui.QTextDocument;

/**
 *
 * @author Miguel González
 */
public class ConstruirSyntaxHighlighter {
    
    public static void establecerSyntaxHighlighter(
            String nombreGestor,
            QTextDocument parent) {
        switch(nombreGestor) {
            case MYSQL:
                new MySQLSyntaxHighlighter(parent);
                break;
            default:
                
                break;
        }
    }
    
}
