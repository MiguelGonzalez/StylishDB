package es.miguelgonzalezgomez.dataBaseFun.qt;

import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CPestanasEditores;
import com.trolltech.qt.gui.QTabBar;

/**
 *
 * @author Miguel González
 */
public class PestanasEditores extends QTabBar {
    
    private CPestanasEditores controlador;
    
    public PestanasEditores(CPestanasEditores controlador) {
        this.controlador = controlador;
        
    }
    
    
    
}
