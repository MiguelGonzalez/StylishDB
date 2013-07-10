package es.miguelgonzalezgomez.dataBaseFun.qt;

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
    
    public void moveTab(int posicionInicial, int posicionFinal) {
        
    }
    
}
