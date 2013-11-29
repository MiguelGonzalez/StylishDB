package com.stylishdb.qt.controladores;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QTabBar;
import com.stylishdb.estilos.ObtencionEstilo;
import com.stylishdb.qt.EstiloSinFoco;
import com.stylishdb.qt.PestanasEditores;

/**
 *
 ** @author StylishDB
 */
public class CPestanasEditores extends CMiControladorGenerico {
    
    private PestanasEditores pestanasEditores;
    private CWidgetPestanasEditores controlador;
    
    public CPestanasEditores(CWidgetPestanasEditores controlador) {
        super();
        
        this.controlador = controlador;
        
        pestanasEditores = new PestanasEditores(this);
        pestanasEditores.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("tabsEditores.css")
        );
        pestanasEditores.setStyle(new EstiloSinFoco());
        pestanasEditores.setFocusPolicy(Qt.FocusPolicy.NoFocus);
    }
    
    public QTabBar getTabBar() {
        return pestanasEditores;
    }

    public void tabMoveRequested(int fromIndex, int toIndex) {
        controlador.tabMoveRequested(fromIndex, toIndex);
    }
}
