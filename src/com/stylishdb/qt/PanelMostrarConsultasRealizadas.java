package com.stylishdb.qt;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.DockWidgetAreas;
import com.trolltech.qt.gui.QDockWidget;
import com.stylishdb.qt.controllers.CDockPanelConsultasRealizadas;

/**
 *
 ** @author StylishDB
 */
public class PanelMostrarConsultasRealizadas extends QDockWidget {
    
    private CDockPanelConsultasRealizadas controlador;
    
    public PanelMostrarConsultasRealizadas(CDockPanelConsultasRealizadas controlador) {
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
