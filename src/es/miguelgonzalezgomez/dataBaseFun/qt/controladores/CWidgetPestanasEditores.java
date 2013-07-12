package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.bd.GestionadorEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanasEditorAbiertas;
import es.miguelgonzalezgomez.dataBaseFun.modelos.PestanaEditorListener;
import es.miguelgonzalezgomez.dataBaseFun.qt.EditorTexto;
import es.miguelgonzalezgomez.dataBaseFun.qt.WidgetPestanasEditores;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Miguel González
 */
public class CWidgetPestanasEditores {

    private WidgetPestanasEditores widgetPestanasEditores;

    private GestionadorEditoresAplicacion editoresAplicacion;
    private CPestanasEditores cPestanasEditores;
    
    private Map<MPestanaEditor, CEditor> relacionPestanaEditor;
    
    public CWidgetPestanasEditores() {
        editoresAplicacion = new GestionadorEditoresAplicacion();
        cPestanasEditores = new CPestanasEditores(this);
        relacionPestanaEditor = new HashMap<>();
        
        inicializarWidget();
        establecerTabBar();
        
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
        MPestanasEditorAbiertas mPestanasEditorAbiertas = 
                editoresAplicacion.getMPestanasEditorAbiertas();
        mPestanasEditorAbiertas.addPestanaEditorListener(new PestanaEditorListener() {
            @Override
            public void modificadaPestanaEditor(MPestanaEditor pestanaEditorEditada) {
                comprobarYRenombrarPestanaEditor(pestanaEditorEditada);
            }

            @Override
            public void eliminadaPestanaEditor(PestanaEditorListener pestanaEditorListener) {
                
            }

            @Override
            public void nuevaPestanaEditor(MPestanaEditor pestanaEditor) {
                addTab(pestanaEditor);
            }

            @Override
            public void rehacerPestana(MPestanaEditor pestanaEditor) {
                buscarYRehacerPestana(pestanaEditor);
            }

            @Override
            public void deshacerPestana(MPestanaEditor pestanaEditor) {
                buscarYDeshacerPestana(pestanaEditor);
            }
        });
    }

    private void addTab(MPestanaEditor pestanaEditor) {
        CEditor cEditor = new CEditor(pestanaEditor);
        
        relacionPestanaEditor.put(pestanaEditor, cEditor);
        
        widgetPestanasEditores.addTab(
                cEditor.getEditorTexto(),
                pestanaEditor.mConexion.nombre
        );
    }
    
    private void buscarYDeshacerPestana(MPestanaEditor pestanaEditor) {
        CEditor cEditor = relacionPestanaEditor.get(pestanaEditor);
        
        if(cEditor != null) {
            cEditor.deshacer();
        }
    }
    
    private void buscarYRehacerPestana(MPestanaEditor pestanaEditor) {
        CEditor cEditor = relacionPestanaEditor.get(pestanaEditor);
        
        if(cEditor != null) {
            cEditor.rehacer();
        }
    }
    
    private void cambiadaPestana() {
        EditorTexto editorTexto = (EditorTexto) widgetPestanasEditores.
                currentWidget();
        
        editorTexto.estaVisible();
    }
    
    private void comprobarYRenombrarPestanaEditor(MPestanaEditor pestanaEditorEditada) {
        CEditor cEditor = relacionPestanaEditor.get(
                pestanaEditorEditada);
        
        for(int i=0; i<relacionPestanaEditor.size(); i++) {
            EditorTexto editorTexto = (EditorTexto) widgetPestanasEditores.widget(i);
            
            if(pestanaEditorEditada.equals(editorTexto.getModeloEditor())) {
                widgetPestanasEditores.setTabText(i, pestanaEditorEditada.nombrePestana);
            }
        }
    }
}
