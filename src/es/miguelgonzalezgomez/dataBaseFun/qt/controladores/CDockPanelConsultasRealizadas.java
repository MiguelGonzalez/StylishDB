package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado.CPanelPestanasMostrarConsultas;
import com.trolltech.qt.gui.QDockWidget;
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
                ObtencionEstilo.getEstiloVentana("widgetConsultas.css")
        );
        panelConsultas.setWidget(controladorEjecutarConsultas.getPanelConsultas());
    }
    
    public QDockWidget getPanelMostrarConsultasRealizadas() {
        return panelConsultas;
    }
}
