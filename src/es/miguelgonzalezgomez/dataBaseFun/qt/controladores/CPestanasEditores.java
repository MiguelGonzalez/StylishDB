package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.gui.QTabBar;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.qt.EstiloSinFoco;
import es.miguelgonzalezgomez.dataBaseFun.qt.PestanasEditores;

/**
 *
 * @author Miguel González
 */
public class CPestanasEditores {
    
    private PestanasEditores pestanasEditores;
    private CWidgetPestanasEditores controlador;
    
    public CPestanasEditores(CWidgetPestanasEditores controlador) {
        this.controlador = controlador;
        
        pestanasEditores = new PestanasEditores(this);
        pestanasEditores.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("tabsEditores.css")
        );
        pestanasEditores.setStyle(new EstiloSinFoco());
    }
    
    public QTabBar getTabBar() {
        return pestanasEditores;
    }

    public void tabMoveRequested(int fromIndex, int toIndex) {
        controlador.tabMoveRequested(fromIndex, toIndex);
    }
}
