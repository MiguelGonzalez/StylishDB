package es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor;

import static es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.TiposBasesDeDatos.*;
import com.trolltech.qt.gui.QTextDocument;

/**
 *
 * @author Miguel González
 */
public class ConstruirSyntaxHighlighter {
    
    public static void establecerSyntaxHighlighter(
            TIPO_BASE_DATOS tipoBaseDatos,
            QTextDocument parent) {
        
        if(TIPO_BASE_DATOS.MYSQL.equals(tipoBaseDatos)) {
            new MySQLSyntaxHighlighter(parent);
        } else if(TIPO_BASE_DATOS.ORACLE.equals(tipoBaseDatos)) {
            
        }
    }
    
}
