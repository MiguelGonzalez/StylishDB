package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QLayout;
import com.trolltech.qt.gui.QSizePolicy;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CEditor;

/**
 *
 * @author Miguel González
 */
public class ContenedorEditor extends QWidget {

    private CEditor controlador;
    private QHBoxLayout boxLayout;
    
    private EditorTexto editorTexto;
    
    public ContenedorEditor(CEditor controlador) {
        this.controlador = controlador;
        
        crearLayout();
    }
    
    private void crearLayout() {
        boxLayout = new QHBoxLayout(this);
        boxLayout.setContentsMargins(0,0,0,0);
        boxLayout.setSpacing(0);
    }
    
    public void establecerContadorLineas(ContadorLineas contadorLineas) {
        boxLayout.addWidget(contadorLineas);
        setSizePolicy(new QSizePolicy(QSizePolicy.Policy.Expanding, QSizePolicy.Policy.Expanding));
    }
    
    public void establecerEditorTexto(EditorTexto editorTexto) {
        this.editorTexto = editorTexto;
        boxLayout.addWidget(editorTexto);
    }

    public EditorTexto getEditorTexto() {
        return editorTexto;
    }
    
    
    
}
