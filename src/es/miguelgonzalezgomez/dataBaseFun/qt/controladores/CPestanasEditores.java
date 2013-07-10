package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.gui.QTabBar;
import es.miguelgonzalezgomez.dataBaseFun.qt.PestanasEditores;

/**
 *
 * @author Miguel González
 */
public class CPestanasEditores {
    
    private PestanasEditores pestanasEditores;
    
    public CPestanasEditores() {
        pestanasEditores = new PestanasEditores(this);

        inicializarInterfazTabBar();
    }
    
    private void inicializarInterfazTabBar() {
        pestanasEditores.setAcceptDrops(true);
        pestanasEditores.setVisible(true);
    }
    
    public QTabBar getTabBar() {
        return pestanasEditores;
    }
}
