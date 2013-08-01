package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.DockWidgetAreas;
import com.trolltech.qt.gui.QDockWidget;
import com.trolltech.qt.gui.QWidget;

/**
 *
 * @author Miguel González
 */
public class WidgetConsultas extends QDockWidget {
    public WidgetConsultas(QWidget parent) {
        super(parent);
        
        setMinimumHeight(100);
        
        establecerAreasPermitidas();
        establecerFuncionalidades();
    }
    
    private void establecerAreasPermitidas() {
        DockWidgetAreas qtAreas = new DockWidgetAreas();
        qtAreas.set(Qt.DockWidgetArea.TopDockWidgetArea);
        qtAreas.set(Qt.DockWidgetArea.BottomDockWidgetArea);
        
        setAllowedAreas(qtAreas);
    }
    
    private void establecerFuncionalidades() {
        DockWidgetFeatures qtFeatures = new DockWidgetFeatures();
        qtFeatures.set(QDockWidget.DockWidgetFeature.DockWidgetFloatable);
        qtFeatures.set(QDockWidget.DockWidgetFeature.DockWidgetMovable);
        setFeatures(qtFeatures);
    }
    
    
}
