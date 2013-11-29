package com.stylishdb.qt;

import com.trolltech.qt.gui.QCloseEvent;
import com.trolltech.qt.gui.QMainWindow;
import com.stylishdb.StylishDB;
import com.stylishdb.qt.controllers.CMainWindow;

/**
 *
 ** @author StylishDB
 */
public class MainWindow extends QMainWindow {
    
    private CMainWindow controlador;
    
    public MainWindow(String tituloVentana,
            com.stylishdb.qt.controllers.CMainWindow controlador) {
        setWindowTitle(tr(tituloVentana));
                
        this.controlador = controlador;
    }

    @Override
    protected void closeEvent(QCloseEvent event) {
        super.closeEvent(event);
        
        StylishDB.salirAplicacion();
    }
}
