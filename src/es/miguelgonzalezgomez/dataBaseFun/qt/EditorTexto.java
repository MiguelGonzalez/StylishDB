package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.gui.QTextEdit;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CEditor;

/**
 *
 * @author Miguel González
 */
public class EditorTexto extends QTextEdit {
    
    private CEditor controlador;
    
    public EditorTexto(CEditor controlador) {
        super();
        
        this.controlador = controlador;
        
        setLineWrapMode(QTextEdit.LineWrapMode.NoWrap);
        setAcceptRichText(false);
    }
   
    public void estaVisible() {
        controlador.estaVisible();
    }
}
