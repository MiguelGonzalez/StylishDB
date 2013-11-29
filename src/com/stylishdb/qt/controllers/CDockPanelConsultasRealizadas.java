package com.stylishdb.qt.controllers;

import com.stylishdb.qt.tabResultViews.controllers.CTabWidgetConsultas;
import com.trolltech.qt.gui.QDockWidget;
import com.trolltech.qt.gui.QTabWidget;
import com.stylishdb.style.ObtencionEstilo;
import com.stylishdb.qt.PanelMostrarConsultasRealizadas;

/**
 *
 ** @author StylishDB
 */
public class CDockPanelConsultasRealizadas extends CMiControladorGenerico {
    
    private CTabWidgetConsultas controladorEjecutarConsultas;
    private PanelMostrarConsultasRealizadas panelConsultas;
    
    public CDockPanelConsultasRealizadas() {
        super();
        
        panelConsultas = new PanelMostrarConsultasRealizadas(this);
        controladorEjecutarConsultas = new CTabWidgetConsultas();
        
        decorarPanelMostrarConsultas();
    }
    
    private void decorarPanelMostrarConsultas() {
        panelConsultas.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("QDockWidgetEstilo.css")
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
