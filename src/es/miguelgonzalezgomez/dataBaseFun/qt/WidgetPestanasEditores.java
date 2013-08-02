package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QKeyEvent;
import com.trolltech.qt.gui.QTabBar;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CWidgetPestanasEditores;

/**
 *
 * @author Miguel González
 */
public class WidgetPestanasEditores extends QTabWidget {
    
    private CWidgetPestanasEditores controlador;
    
    public WidgetPestanasEditores(CWidgetPestanasEditores controlador) {
        this.controlador = controlador;
    }
    
    public void addTab(String name, QWidget widget) {
        addTab(widget, name);
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
}
