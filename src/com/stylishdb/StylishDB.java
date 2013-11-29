package com.stylishdb;

import com.trolltech.qt.core.QTextCodec;
import com.stylishdb.qt.controllers.CMainWindow;
import com.trolltech.qt.gui.QApplication;
import com.stylishdb.languages.LoadLanguage;

/**
 *
 ** @author StylishDB
 */
public class StylishDB {

    private static CMainWindow controladorVentanaPrincipal;
    
    public static void main(String args[]) {
        QApplication.initialize(args);
        QTextCodec.setCodecForCStrings(QTextCodec.codecForName("UTF-8"));
        QTextCodec.setCodecForLocale(QTextCodec.codecForName("UTF-8"));

        LoadLanguage.cargarIdiomaDefecto();
        LoadApplicationModel.cargarModeloAplicacion();
        
        controladorVentanaPrincipal = new
                CMainWindow();
        
        QApplication.exec();
    }
    
    public static void salirAplicacion() {
        SaveApplicationModel.guardarModeloAplicacion();
        
        controladorVentanaPrincipal.salirAplicacion();
    }
        
    public static int getLeftVentana() {
        return controladorVentanaPrincipal.ventanaPrincipal.x();
    }
    
    public static int getTopVentana() {
        return controladorVentanaPrincipal.ventanaPrincipal.y();
    }
    
    public static int getHeightVentana() {
        return controladorVentanaPrincipal.ventanaPrincipal.height();
    }
    
    public static int getWidthVentana() {
        return controladorVentanaPrincipal.ventanaPrincipal.width();
    }
}
