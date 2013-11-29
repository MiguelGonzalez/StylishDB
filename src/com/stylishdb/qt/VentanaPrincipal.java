package com.stylishdb.qt;

import com.trolltech.qt.gui.QCloseEvent;
import com.trolltech.qt.gui.QMainWindow;
import com.stylishdb.StylishDB;
import com.stylishdb.qt.controladores.CVentanaPrincipal;

/**
 *
 ** @author StylishDB
 */
public class VentanaPrincipal extends QMainWindow {
    
    private CVentanaPrincipal controlador;
    
    public VentanaPrincipal(String tituloVentana,
            com.stylishdb.qt.controladores.CVentanaPrincipal controlador) {
        setWindowTitle(tr(tituloVentana));
                
        this.controlador = controlador;
    }

    @Override
    protected void closeEvent(QCloseEvent event) {
        super.closeEvent(event);
        
        StylishDB.salirAplicacion();
    }
}
