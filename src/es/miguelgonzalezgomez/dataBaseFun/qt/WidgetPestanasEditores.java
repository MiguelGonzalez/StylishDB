package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QKeyEvent;
import com.trolltech.qt.gui.QTabBar;
import com.trolltech.qt.gui.QTabWidget;
import es.miguelgonzalezgomez.dataBaseFun.domain.MPestana;
import es.miguelgonzalezgomez.dataBaseFun.domain.PestanaListener;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CPestanaEditor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class WidgetPestanasEditores extends QTabWidget 
        implements PestanaListener {
    
    private List<MPestana> editoresTexto;
    
    
    public WidgetPestanasEditores() {
        editoresTexto = new ArrayList<>();
    }
    
    public void addTabEditorTexto(MPestana mPestana) {
        CPestanaEditor cPestanaEditor = new CPestanaEditor(mPestana);
        EditorTexto editorTexto = cPestanaEditor.getPestanaEditor();
        
        editoresTexto.add(mPestana);
        
        int index =addTab(
                editorTexto, mPestana.toString()
                );
        setCurrentIndex(index);
        
        mPestana.addPestanaListener(this);
    }
    
    public void removeTabEditorTexto(MPestana mPestana) {
        int posicionPestana = -1;
        
        for(int i=0; i<editoresTexto.size() && posicionPestana == -1; i++) {
            MPestana mPestanaEditor = editoresTexto.get(i);
            
            if(mPestanaEditor.equals(mPestana)) {
                posicionPestana = i;
            }
        }
        
        if(posicionPestana != -1) {
            editoresTexto.remove(mPestana);
            removeTab(posicionPestana);
        }
        
        mPestana.removePestanaListener(this);
    }
    
    public void setPestanasEditor(QTabBar tabBar) {
        setTabBar(tabBar);
    }
    
    @Override
    protected void keyPressEvent(QKeyEvent event) {
         if(esCambiarSiguientePestana(event)) {
            return;
        }
        
        if(esCambiarAnteriorPestana(event)) {
            return;
        }
        
        super.keyPressEvent(event);
    }
    
    private boolean esCambiarSiguientePestana(QKeyEvent e) {
        Qt.KeyboardModifiers modifiers = e.modifiers();
        if(modifiers.isSet(Qt.KeyboardModifier.ControlModifier)) {
            if(e.key() == Qt.Key.Key_Tab.value()) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean esCambiarAnteriorPestana(QKeyEvent e) {
        Qt.KeyboardModifiers modifiers = e.modifiers();
        
        if(modifiers.isSet(Qt.KeyboardModifier.ControlModifier) &&
                modifiers.isSet(Qt.KeyboardModifier.ShiftModifier)) {
            if(e.key() == Qt.Key.Key_Backtab.value()) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public void textoModificado(MPestana mPestana) {}

    @Override
    public void textoSeleccionado(MPestana mPestana) {}

    @Override
    public void renombrada(MPestana mPestana) {
        for(int i=0; i<editoresTexto.size(); i++) {
            MPestana mPestanaEditor = editoresTexto.get(i);
            
            if(mPestanaEditor.equals(mPestana)) {
                setTabText(i, mPestana.toString());
            }
        }
    }
}
