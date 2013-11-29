package com.stylishdb.qt.controllers;

import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.style.GetStyle;
import com.stylishdb.domain.MTab;
import com.stylishdb.domain.AllMTabsListener;
import com.stylishdb.domain.controllers.CAllTabsListener;
import com.stylishdb.qt.HolderEditor;
import com.stylishdb.qt.TextEditor;
import com.stylishdb.qt.TabEditors;

/**
 *
 ** @author StylishDB
 */
public class CTabEditors extends Controller
        implements CAllTabsListener, AllMTabsListener {

    private TabEditors widgetPestanasEditores;
    private CTabBarEditors cPestanasEditores;
        
    public CTabEditors() {
        super();
        cPestanasEditores = new CTabBarEditors(this);
        
        inicializarWidget();
        establecerTabBar();
        cargarPestanasAbiertas();
        
        inicializarListener();
    }
    
    private void inicializarWidget() {
        widgetPestanasEditores = new TabEditors();
    }
    
    private void inicializarListener() {
        controladorPestanasAbiertas.addListener(this);
        pestanasAbiertas.addListener(this);
    }
    
    private void establecerTabBar() {
        widgetPestanasEditores.setPestanasEditor(
                cPestanasEditores.getTabBar()
        );
        
        widgetPestanasEditores.currentChanged.connect(this, "cambiadaPestana()");
        
        widgetPestanasEditores.setStyleSheet(
                GetStyle.getEstiloVentana("tabsEditoresWidget.css")
        );

    }
    
    private void cargarPestanasAbiertas() {
        for(MTab pestanaEditor :
                pestanasAbiertas.getPestanasAbiertas()) {
            anadidaPestana(pestanaEditor);
        }
    }
    
    public QTabWidget getVistaPestanasEditores() {
        return widgetPestanasEditores;
    }
    
    public void tabMoveRequested(int fromIndex, int toIndex) {
        QWidget w = widgetPestanasEditores.widget(fromIndex);
        QIcon icon = widgetPestanasEditores.tabIcon(fromIndex);
        String text = widgetPestanasEditores.tabText(fromIndex);

        widgetPestanasEditores.removeTab(fromIndex);
        widgetPestanasEditores.insertTab(toIndex, w, icon, text);
        widgetPestanasEditores.setCurrentIndex(toIndex);
    }
           
    private void cambiadaPestana() {
        HolderEditor contenedorEditor = (HolderEditor) widgetPestanasEditores.
                currentWidget();
        if(contenedorEditor != null) {
            TextEditor pestanaEditorTexto = contenedorEditor.getEditorTexto();

            if(pestanaEditorTexto != null) {
                pestanaEditorTexto.establecerEditorVisible();
            } else {
                pestanasAbiertas.establecerEditorActivo(null);
            }
        } else {
            pestanasAbiertas.establecerEditorActivo(null);
        }
    }
    
    @Override
    public void cambiarSiguiente() {
        int index = widgetPestanasEditores.currentIndex();
        int numPestanas = widgetPestanasEditores.count();
        
        int anteriorIndex = (index + 1) % numPestanas;
        widgetPestanasEditores.setCurrentIndex(anteriorIndex);
    }

    @Override
    public void cambiarAnterior() {
        int index = widgetPestanasEditores.currentIndex();
        int numPestanas = widgetPestanasEditores.count();
        
        int anteriorIndex = index - 1;
        if(anteriorIndex < 0) {
            anteriorIndex = numPestanas - 1;
        }
        
        widgetPestanasEditores.setCurrentIndex(anteriorIndex);
    }

    @Override
    public void pestanaActiva(MTab mPestana) {}

    @Override
    public void anadidaPestana(MTab mPestana) {
        widgetPestanasEditores.addTabEditorTexto(
                mPestana
        );
    }

    @Override
    public void eliminadaPestana(MTab mPestana) {
        widgetPestanasEditores.removeTabEditorTexto(mPestana);
    }
}
