package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.KeyboardModifiers;
import com.trolltech.qt.gui.QKeyEvent;
import com.trolltech.qt.gui.QPlainTextEdit;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CEditor;

/**
 *
 * @author Miguel González
 */
public class EditorTexto extends QPlainTextEdit {
    
    private CEditor controlador;
    
    public EditorTexto(CEditor controlador) {
        super();
        
        this.controlador = controlador;
        setLineWrapMode(LineWrapMode.NoWrap);
        //setLineWrapMode(QTextEdit.LineWrapMode.NoWrap);
        //setAcceptRichText(false);
        
        establecerEventos();
    }
    
    private void establecerEventos() {
        textChanged.connect(controlador, "eventoTextoCambiado()");
        selectionChanged.connect(controlador, "eventoSeleccionCambiado()");
    }
   
    public MPestanaEditor getModeloEditor() {
        return controlador.getModeloEditor();
    }
    
    @Override
    protected void keyPressEvent(QKeyEvent event) {
        if(suprimirRehacerNativo(event)) {
            return;
        }
        
        if(esCambiarSiguientePestana(event)) {
            controlador.cambiarSiguientePestana();
            return;
        }
        
        if(esCambiarAnteriorPestana(event)) {
            controlador.cambiarAnteriorPestana();
            return;
        }
        
        super.keyPressEvent(event);
    }
    
    private boolean suprimirRehacerNativo(QKeyEvent e) {
        KeyboardModifiers modifiers = e.modifiers();
        if(
                modifiers.isSet(Qt.KeyboardModifier.ControlModifier) &&
                modifiers.isSet(Qt.KeyboardModifier.ShiftModifier)) {
            if(e.key() == Qt.Key.Key_Z.value()) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean esCambiarSiguientePestana(QKeyEvent e) {
        KeyboardModifiers modifiers = e.modifiers();
        if(
                modifiers.isSet(Qt.KeyboardModifier.ControlModifier)) {
            if(e.key() == Qt.Key.Key_Tab.value()) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean esCambiarAnteriorPestana(QKeyEvent e) {
        KeyboardModifiers modifiers = e.modifiers();
        if(
                modifiers.isSet(Qt.KeyboardModifier.ControlModifier) &&
                modifiers.isSet(Qt.KeyboardModifier.ShiftModifier)) {
            if(e.key() == Qt.Key.Key_Backtab.value()) {
                return true;
            }
        }
        
        return false;
    }

    public void establecerEditorVisible() {
        controlador.establecerEditorVisible();
    }
}
