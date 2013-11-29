package com.stylishdb.qt.pestanaVistaResultado;

import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.qt.controladores.pestanaVistaResultado.CWidgetResultadosConsulta;

/**
 *
 ** @author StylishDB
 */
public class WidgetResultadosConsulta extends QWidget {
    
    private QVBoxLayout widgetLayout;
    private QTabWidget pestanasTiposVistas;
    private CWidgetResultadosConsulta controlador;
    
    public WidgetResultadosConsulta(CWidgetResultadosConsulta controlador) {
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
    
    public void pintarVistaDatosConsulta(VistaDatosConsulta vistaDatos) {
        pestanasTiposVistas.addTab(
                vistaDatos,
                "Datos consulta"
        );
    }
    
    public void pintarVistaDatosTextoPlano(VistaDatosTextoPlano vistaDatos) {
        pestanasTiposVistas.addTab(
                vistaDatos,
                "Texto plano"
        );
    }
    
    public void pintarVistaDatosTabla(VistaDatosTabla vistaDatos) {
        pestanasTiposVistas.addTab(
                vistaDatos,
                "Datos columnas"
        );
    }
    
    public void pintarVistaDatosInformacion(VistaDatosInformacion vistaDatos) {
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
