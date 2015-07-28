package com.stylishdb.qt.tabResultViews;

import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.qt.tabResultViews.controllers.CTabQueriesExecuted;

/**
 *
 ** @author StylishDB
 */
public class TabQueriesExecuted extends QTabWidget {
    
    private CTabQueriesExecuted controlador;
    
    public TabQueriesExecuted(CTabQueriesExecuted controlador) {
        this.controlador = controlador;
        
        setTabsClosable(true);
        
        iniciarEventos();
    }
    
    private void iniciarEventos() {
        tabCloseRequested.connect(controlador, "cerrarPestana(int)");
    }
    
    public void addTab(String name, QWidget widget, int index) {
        if(index == -1) {
            addTab(widget, name);
        } else {
            insertTab(index, widget, name);
        }        
    }
    
    public boolean isTabActive() {
        return count() != 0;
    }
    
    public QWidget getTabActive() {
        return currentWidget();
    }
}
