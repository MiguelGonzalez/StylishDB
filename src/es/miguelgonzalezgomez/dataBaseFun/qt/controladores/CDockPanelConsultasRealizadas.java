package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado.CTabWidgetConsultas;
import com.trolltech.qt.gui.QDockWidget;
import com.trolltech.qt.gui.QTabWidget;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.qt.PanelMostrarConsultasRealizadas;

/**
 *
 * @author Miguel González
 */
public class CDockPanelConsultasRealizadas extends CMiControladorGenerico {
    
    private CTabWidgetConsultas controladorEjecutarConsultas;
    private PanelMostrarConsultasRealizadas panelConsultas;
    
    public CDockPanelConsultasRealizadas() {
        super();
        
        panelConsultas = new PanelMostrarConsultasRealizadas(this);
        controladorEjecutarConsultas = new CTabWidgetConsultas();
        
        decorarPanelMostrarConsultas();
    }
    
    private void decorarPanelMostrarConsultas() {
        panelConsultas.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("QDockWidgetEstilo.css")
        );
        QTabWidget qTabWidget = controladorEjecutarConsultas.getPanelConsultas();
        panelConsultas.setWidget(qTabWidget);
        panelConsultas.setContentsMargins(0, 0, 0, 0);
        
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
