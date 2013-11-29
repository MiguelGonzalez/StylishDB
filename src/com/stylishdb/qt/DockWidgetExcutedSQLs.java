package com.stylishdb.qt;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.DockWidgetAreas;
import com.trolltech.qt.gui.QDockWidget;
import com.stylishdb.qt.controllers.CDockWidgetExecutedSQLs;

/**
 *
 ** @author StylishDB
 */
public class DockWidgetExcutedSQLs extends QDockWidget {
    
    private CDockWidgetExecutedSQLs controlador;
    
    public DockWidgetExcutedSQLs(CDockWidgetExecutedSQLs controlador) {
        this.controlador = controlador;
        
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
