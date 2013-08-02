package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.gui.QDockWidget;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.qt.PanelMostrarConsultasRealizadas;

/**
 *
 * @author Miguel González
 */
public class CPanelMostrarConsultasRealizadas {
    
    private CEjecutarConsultas controladorEjecutarConsultas;
    private PanelMostrarConsultasRealizadas panelConsultas;
    
    public CPanelMostrarConsultasRealizadas() {
        panelConsultas = new PanelMostrarConsultasRealizadas(this);
        controladorEjecutarConsultas = new CEjecutarConsultas();
        
        establecerPanelConsultas();
    }
    
    private void establecerPanelConsultas() {
        panelConsultas.setWidget(
                controladorEjecutarConsultas.getPanelConsultas()
        );
        panelConsultas.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("widgetConsultas.css")
        );
    }
    
    public QDockWidget getPanelMostrarConsultasRealizadas() {
        return panelConsultas;
    }
}
