package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QTextEdit;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.bd.GestionadorEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanasEditorAbiertas;
import es.miguelgonzalezgomez.dataBaseFun.modelos.PestanaEditorListener;
import es.miguelgonzalezgomez.dataBaseFun.qt.WidgetPestanasEditores;

/**
 *
 * @author Miguel González
 */
public class CWidgetPestanasEditores {

    private WidgetPestanasEditores widgetPestanasEditores;

    private GestionadorEditoresAplicacion editoresAplicacion;
    private CPestanasEditores cPestanasEditores;
    
    public CWidgetPestanasEditores() {
        editoresAplicacion = new GestionadorEditoresAplicacion();
        cPestanasEditores = new CPestanasEditores(this);
        
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
            public void modificadaPestanaEditor(MPestanaEditor pestanaEditorVieja,
                    MPestanaEditor pestanaEditorViejaNueva) {
                
            }

            @Override
            public void eliminadaPestanaEditor(PestanaEditorListener pestanaEditorListener) {
                
            }

            @Override
            public void nuevaPestanaEditor(MPestanaEditor pestanaEditor) {
                addTab(pestanaEditor);
            }
        });
    }
    
    private void addTab(MPestanaEditor pestanaEditor) {
        widgetPestanasEditores.addTab(
                new QTextEdit(pestanaEditor.contenidoTexto),
                pestanaEditor.mConexion.nombre
        );
    }
}
