package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CEjecutarConsultas;

/**
 *
 * @author Miguel González
 */
public class PanelConsultas extends QTabWidget {
    
    private CEjecutarConsultas controlador;
    
    public PanelConsultas(CEjecutarConsultas controlador) {
        this.controlador = controlador;
    }
    
    public void addTab(String name, QWidget widget) {
        addTab(widget, name);
    }
}
