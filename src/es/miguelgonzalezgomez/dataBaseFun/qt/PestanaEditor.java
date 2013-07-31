package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QSplitter;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QTextEdit;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CPestanaEditor;

/**
 *
 * @author Miguel González
 */
public class PestanaEditor extends QSplitter {
    
    private CPestanaEditor controlador;
    
    public PestanaEditor(CPestanaEditor controlador) {
        super(Qt.Orientation.Vertical);
        
        this.controlador = controlador;
    }
    
    
    public void establecerEditorTexto(QTextEdit textEdit) {
        addWidget(textEdit);
    }
    
    public void establecerPanelConsultas(QTabWidget panelConsultas) {
        addWidget(panelConsultas);
    }

    public void estaVisible() {
        controlador.estaVisible();
    }

    public MPestanaEditor getModeloEditor() {
        return controlador.getModeloEditor();
    }
}
