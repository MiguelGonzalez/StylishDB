package com.stylishdb.qt.controladores;

import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.estilos.ObtencionEstilo;
import com.stylishdb.domain.MPestana;
import com.stylishdb.domain.PestanasAbiertasListener;
import com.stylishdb.domain.controladores.CPestanasListener;
import com.stylishdb.qt.ContenedorEditor;
import com.stylishdb.qt.EditorTexto;
import com.stylishdb.qt.WidgetPestanasEditores;

/**
 *
 ** @author StylishDB
 */
public class CWidgetPestanasEditores extends CMiControladorGenerico
        implements CPestanasListener, PestanasAbiertasListener {

    private WidgetPestanasEditores widgetPestanasEditores;
    private CPestanasEditores cPestanasEditores;
        
    public CWidgetPestanasEditores() {
        super();
        cPestanasEditores = new CPestanasEditores(this);
        
        inicializarWidget();
        establecerTabBar();
        cargarPestanasAbiertas();
        
        inicializarListener();
    }
    
    private void inicializarWidget() {
        widgetPestanasEditores = new WidgetPestanasEditores();
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
                ObtencionEstilo.getEstiloVentana("tabsEditoresWidget.css")
        );

    }
    
    private void cargarPestanasAbiertas() {
        for(MPestana pestanaEditor :
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
        ContenedorEditor contenedorEditor = (ContenedorEditor) widgetPestanasEditores.
                currentWidget();
        if(contenedorEditor != null) {
            EditorTexto pestanaEditorTexto = contenedorEditor.getEditorTexto();

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
    public void pestanaActiva(MPestana mPestana) {}

    @Override
    public void anadidaPestana(MPestana mPestana) {
        widgetPestanasEditores.addTabEditorTexto(
                mPestana
        );
    }

    @Override
    public void eliminadaPestana(MPestana mPestana) {
        widgetPestanasEditores.removeTabEditorTexto(mPestana);
    }
}
