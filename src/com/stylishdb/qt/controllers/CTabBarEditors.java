package com.stylishdb.qt.controllers;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QTabBar;
import com.stylishdb.style.GetStyle;
import com.stylishdb.utilities.StyleWithoutFocus;
import com.stylishdb.qt.TabBarEditors;

/**
 *
 ** @author StylishDB
 */
public class CTabBarEditors extends Controller {
    
    private TabBarEditors pestanasEditores;
    private CTabEditors controlador;
    
    public CTabBarEditors(CTabEditors controlador) {
        super();
        
        this.controlador = controlador;
        
        pestanasEditores = new TabBarEditors(this);
        pestanasEditores.setStyleSheet(
                GetStyle.getEstiloVentana("tabsEditores.css")
        );
        pestanasEditores.setStyle(new StyleWithoutFocus());
        pestanasEditores.setFocusPolicy(Qt.FocusPolicy.NoFocus);
    }
    
    public QTabBar getTabBar() {
        return pestanasEditores;
    }

    public void tabMoveRequested(int fromIndex, int toIndex) {
        controlador.tabMoveRequested(fromIndex, toIndex);
    }
}
