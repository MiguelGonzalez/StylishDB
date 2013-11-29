package com.stylishdb.qt.tabResultViews;

import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.qt.tabResultViews.controllers.CTabQueryExecute;

/**
 *
 ** @author StylishDB
 */
public class TabQueryExecute extends QWidget {
    
    private QVBoxLayout widgetLayout;
    private QTabWidget pestanasTiposVistas;
    private CTabQueryExecute controlador;
    
    public TabQueryExecute(CTabQueryExecute controlador) {
        this.controlador = controlador;
        
        crearComponentesInterfaz();
        posicionarComponentesInterfaz();
    }
    
    public void liberarControlador() {
        controlador.liberarWidget();
    }
    
    private void crearComponentesInterfaz() {
        pestanasTiposVistas = new QTabWidget();
        pestanasTiposVistas.setContentsMargins(0, 0, 0, 0);
        
        widgetLayout = new QVBoxLayout();
        widgetLayout.setContentsMargins(0, 2, 0, 0);
        widgetLayout.setSpacing(0);
    }
    
    public void pintarVistaDatosConsulta(TabExecutes vistaDatos) {
        pestanasTiposVistas.addTab(
                vistaDatos,
                "Datos consulta"
        );
    }
    
    public void pintarVistaDatosTextoPlano(TabPlainText vistaDatos) {
        pestanasTiposVistas.addTab(
                vistaDatos,
                "Texto plano"
        );
    }
    
    public void pintarVistaDatosTabla(TabRowsInformation vistaDatos) {
        pestanasTiposVistas.addTab(
                vistaDatos,
                "Datos columnas"
        );
    }
    
    public void pintarVistaDatosInformacion(TabInformation vistaDatos) {
        pestanasTiposVistas.addTab(
                vistaDatos,
                "Información"
        );
    }
    
    private void posicionarComponentesInterfaz() {
        widgetLayout.addWidget(pestanasTiposVistas);
        setLayout(widgetLayout);
    }
}
