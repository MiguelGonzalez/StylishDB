package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado.CPanelPestanasMostrarConsultas;
import com.trolltech.qt.gui.QDockWidget;
import com.trolltech.qt.gui.QTabWidget;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.qt.PanelMostrarConsultasRealizadas;

/**
 *
 * @author Miguel González
 */
public class CDockPanelConsultasRealizadas {
    
    private CPanelPestanasMostrarConsultas controladorEjecutarConsultas;
    private PanelMostrarConsultasRealizadas panelConsultas;
    
    public CDockPanelConsultasRealizadas() {
        panelConsultas = new PanelMostrarConsultasRealizadas(this);
        controladorEjecutarConsultas = new CPanelPestanasMostrarConsultas();
        
        decorarPanelMostrarConsultas();
    }
    
    private void decorarPanelMostrarConsultas() {
        panelConsultas.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("QDockWidgetEstilo.css")
        );
        QTabWidget qTabWidget = controladorEjecutarConsultas.getPanelConsultas();
        panelConsultas.setWidget(qTabWidget);
        
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
