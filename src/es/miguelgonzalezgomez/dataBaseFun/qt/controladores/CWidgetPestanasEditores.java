package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QTextEdit;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.qt.WidgetPestanasEditores;

/**
 *
 * @author Miguel González
 */
public class CWidgetPestanasEditores {

    private WidgetPestanasEditores widgetPestanasEditores;
    
    private CPestanasEditores cPestanasEditores;
    
    public CWidgetPestanasEditores() {
        cPestanasEditores = new CPestanasEditores();
        
        inicializarWidget();
        establecerTabBar();
    }
    
    private void inicializarWidget() {
        widgetPestanasEditores = new WidgetPestanasEditores(this);
    }
    
    private void establecerTabBar() {
        widgetPestanasEditores.setPestanasEditor(
                cPestanasEditores.getTabBar()
        );
    }
    
    public QTabWidget getVistaPestanasEditores() {
        return widgetPestanasEditores;
    }
    
    public void addTab(MConexion mConexion) {
        widgetPestanasEditores.addTab(
                new QTextEdit(""),
                mConexion.nombre
        );
    }
    
}
