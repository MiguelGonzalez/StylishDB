package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.EditorTexto;
import es.miguelgonzalezgomez.dataBaseFun.qt.WidgetPestanasEditores;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Miguel González
 */
public class CWidgetPestanasEditores extends CMiControladorGenerico {

    private WidgetPestanasEditores widgetPestanasEditores;
    
    private CWidgetPestanasEditoresEscuchaCambios escuchaCambiosPestana;
    private CPestanasEditores cPestanasEditores;
    
    private Map<MPestanaEditor, CPestanaEditor> relacionPestanaEditor;
    
    public CWidgetPestanasEditores() {
        super();
        cPestanasEditores = new CPestanasEditores(this);
        relacionPestanaEditor = new HashMap<>();
        
        inicializarWidget();
        establecerTabBar();
        cargarPestanasAbiertas();
        
        escuchaEditoresAplicacion();
    }
    
    private void inicializarWidget() {
        widgetPestanasEditores = new WidgetPestanasEditores(this);
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
        for(MPestanaEditor pestanaEditor :
                gestionadorEditores.getPestanasEditores()) {
            addTab(pestanaEditor);
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
    
    private void escuchaEditoresAplicacion() {
        escuchaCambiosPestana = new
                CWidgetPestanasEditoresEscuchaCambios(this);
        gestionadorEditores.addPestanasEditorListener(escuchaCambiosPestana);
    }

    public void addTab(MPestanaEditor pestanaEditor) {
        CPestanaEditor cPestanaEditor = new CPestanaEditor(pestanaEditor);
        
        relacionPestanaEditor.put(pestanaEditor, cPestanaEditor);
        
        int index = widgetPestanasEditores.addTab(
                cPestanaEditor.getPestanaEditor(),
                pestanaEditor.nombrePestana
        );
        widgetPestanasEditores.setCurrentIndex(index);
    }
    
    public void buscarYDeshacerPestana(MPestanaEditor pestanaEditor) {
        CPestanaEditor cPestanaEditor = relacionPestanaEditor.get(pestanaEditor);
        
        if(cPestanaEditor != null) {
            cPestanaEditor.deshacer();
        }
    }
    
    public void buscarYRehacerPestana(MPestanaEditor pestanaEditor) {
        CPestanaEditor cPestanaEditor = relacionPestanaEditor.get(pestanaEditor);
        
        if(cPestanaEditor != null) {
            cPestanaEditor.rehacer();
        }
    }
    
    private void cambiadaPestana() {
        EditorTexto pestanaEditorTexto = (EditorTexto) widgetPestanasEditores.
                currentWidget();
        
        if(pestanaEditorTexto != null) {
            pestanaEditorTexto.establecerEditorVisible();
        } else {
            gestionadorEditores.establecerPestanaActiva(null);
        }
    }
    
    public void comprobarYRenombrarPestanaEditor(MPestanaEditor pestanaEditorEditada) {
        for(int i=0; i<relacionPestanaEditor.size(); i++) {
            EditorTexto pestanaEditorTexto = (EditorTexto) widgetPestanasEditores.widget(i);
            
            if(pestanaEditorEditada.equals(pestanaEditorTexto.getModeloEditor())) {
                widgetPestanasEditores.setTabText(i, pestanaEditorEditada.nombrePestana);
            }
        }
    }

    void buscarYCerrarPestana(MPestanaEditor pestanaEditor) {
        int posicionPestana = -1;
        for(int i=0; i<relacionPestanaEditor.size() && posicionPestana == -1; i++) {
            EditorTexto pestanaEditorTexto = (EditorTexto) widgetPestanasEditores.widget(i);
            
            if(pestanaEditor.equals(pestanaEditorTexto.getModeloEditor())) {
                posicionPestana = i;
            }
        }
        if(posicionPestana != -1) {
            widgetPestanasEditores.removeTab(posicionPestana);
        }
    }

    public void cambiarSiguientePestana() {
        int index = widgetPestanasEditores.currentIndex();
        int numPestanas = widgetPestanasEditores.count();
        
        int anteriorIndex = (index + 1) % numPestanas;
        widgetPestanasEditores.setCurrentIndex(anteriorIndex);
    }

    public void cambiarAnteriorPestana() {
        int index = widgetPestanasEditores.currentIndex();
        int numPestanas = widgetPestanasEditores.count();
        
        int anteriorIndex = index - 1;
        if(anteriorIndex < 0) {
            anteriorIndex = numPestanas - 1;
        }
        
        widgetPestanasEditores.setCurrentIndex(anteriorIndex);
        
    }
}
