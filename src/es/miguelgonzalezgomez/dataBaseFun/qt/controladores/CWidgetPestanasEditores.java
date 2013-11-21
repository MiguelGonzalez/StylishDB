package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.domain.MPestana;
import es.miguelgonzalezgomez.dataBaseFun.domain.PestanaListener;
import es.miguelgonzalezgomez.dataBaseFun.domain.PestanasAbiertasListener;
import es.miguelgonzalezgomez.dataBaseFun.domain.controladores.CPestanasListener;
import es.miguelgonzalezgomez.dataBaseFun.qt.EditorTexto;
import es.miguelgonzalezgomez.dataBaseFun.qt.WidgetPestanasEditores;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Miguel González
 */
public class CWidgetPestanasEditores extends CMiControladorGenerico
        implements CPestanasListener, PestanasAbiertasListener, PestanaListener {

    private WidgetPestanasEditores widgetPestanasEditores;
    private CPestanasEditores cPestanasEditores;
    
    private Map<MPestana, CPestanaEditor> relacionPestanaEditor;
    
    public CWidgetPestanasEditores() {
        super();
        cPestanasEditores = new CPestanasEditores(this);
        relacionPestanaEditor = new HashMap<>();
        
        inicializarWidget();
        establecerTabBar();
        cargarPestanasAbiertas();
        
        inicializarListener();
    }
    
    private void inicializarWidget() {
        widgetPestanasEditores = new WidgetPestanasEditores(this);
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
        EditorTexto pestanaEditorTexto = (EditorTexto) widgetPestanasEditores.
                currentWidget();
        
        if(pestanaEditorTexto != null) {
            pestanaEditorTexto.establecerEditorVisible();
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
        CPestanaEditor cPestanaEditor = new CPestanaEditor(mPestana);
        
        relacionPestanaEditor.put(mPestana, cPestanaEditor);
        
        String nombrePestana = mPestana.getNombrePestana();
        int index = widgetPestanasEditores.addTab(
                cPestanaEditor.getPestanaEditor(),
                nombrePestana
        );
        widgetPestanasEditores.setCurrentIndex(index);
        mPestana.addPestanaListener(this);
    }

    @Override
    public void eliminadaPestana(MPestana mPestana) {
        int posicionPestana = -1;
        for(int i=0; i<relacionPestanaEditor.size() && posicionPestana == -1; i++) {
            EditorTexto pestanaEditorTexto = (EditorTexto) widgetPestanasEditores.widget(i);
            
            if(mPestana.equals(pestanaEditorTexto.getModeloEditor())) {
                posicionPestana = i;
            }
        }
        if(posicionPestana != -1) {
            widgetPestanasEditores.removeTab(posicionPestana);
        }
        relacionPestanaEditor.remove(mPestana);
        mPestana.removePestanaListener(this);
    }

    @Override
    public void textoModificado(MPestana mPestana) {}

    @Override
    public void textoSeleccionado(MPestana mPestana) {}

    @Override
    public void renombrada(MPestana mPestana) {
        for(int i=0; i<relacionPestanaEditor.size(); i++) {
            EditorTexto pestanaEditorTexto = (EditorTexto) widgetPestanasEditores.widget(i);
            
            if(mPestana.equals(pestanaEditorTexto.getModeloEditor())) {
                String nombrePestana = mPestana.getNombrePestana();
                widgetPestanasEditores.setTabText(i, nombrePestana);
            }
        }
    }
}
