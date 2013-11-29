package com.stylishdb.qt.tabResultViews;

import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.qt.tabResultViews.controllers.CTabWidgetConsultas;

/**
 *
 ** @author StylishDB
 */
public class TabWidgetConsultas extends QTabWidget {
    
    private CTabWidgetConsultas controlador;
    
    public TabWidgetConsultas(CTabWidgetConsultas controlador) {
        this.controlador = controlador;
        
        setTabsClosable(true);
        
        iniciarEventos();
    }
    
    private void iniciarEventos() {
        tabCloseRequested.connect(controlador, "cerrarPestana(int)");
    }
    
    public void addTab(String name, QWidget widget) {
        addTab(widget, name);
    }
}
