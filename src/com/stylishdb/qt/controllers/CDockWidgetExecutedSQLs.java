package com.stylishdb.qt.controllers;

import com.stylishdb.qt.tabResultViews.controllers.CTabQueriesExecuted;
import com.trolltech.qt.gui.QDockWidget;
import com.trolltech.qt.gui.QTabWidget;
import com.stylishdb.style.GetStyle;
import com.stylishdb.qt.DockWidgetExcutedSQLs;

/**
 *
 ** @author StylishDB
 */
public class CDockWidgetExecutedSQLs extends Controller {
    
    private CTabQueriesExecuted controladorEjecutarConsultas;
    private DockWidgetExcutedSQLs panelConsultas;
    
    public CDockWidgetExecutedSQLs() {
        super();
        
        panelConsultas = new DockWidgetExcutedSQLs(this);
        controladorEjecutarConsultas = new CTabQueriesExecuted();
        
        decorarPanelMostrarConsultas();
    }
    
    private void decorarPanelMostrarConsultas() {
        panelConsultas.setStyleSheet(
                GetStyle.getEstiloVentana("QDockWidgetEstilo.css")
        );
        QTabWidget qTabWidget = controladorEjecutarConsultas.getPanelConsultas();
        panelConsultas.setWidget(qTabWidget);
        panelConsultas.setContentsMargins(0, 0, 0, 0);
        
        qTabWidget.currentChanged.connect(this, "cambiadoPestana()");
    }
    
    private void cambiadoPestana() {
        if(!panelConsultas.isVisible()) {
            panelConsultas.setVisible(true);
        }
    }
    
    public QDockWidget getPanelMostrarConsultasRealizadas() {
        return panelConsultas;
    }
}
