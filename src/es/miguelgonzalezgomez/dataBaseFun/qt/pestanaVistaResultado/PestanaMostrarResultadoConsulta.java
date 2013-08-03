package es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado;

import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado.CPestanaMostrarConsulta;

/**
 *
 * @author Miguel González
 */
public class PestanaMostrarResultadoConsulta extends QWidget {
    
    private QVBoxLayout widgetLayout;
    private QTabWidget pestanasTiposVistas;
    private CPestanaMostrarConsulta controlador;
    
    public PestanaMostrarResultadoConsulta(CPestanaMostrarConsulta controlador) {
        this.controlador = controlador;
        
        crearComponentesInterfaz();
        posicionarComponentesInterfaz();
    }
    
    private void crearComponentesInterfaz() {
        widgetLayout = new QVBoxLayout();   
        pestanasTiposVistas = new QTabWidget();
    }
    
    public void pintarVistaDatosConsulta(VistaDatosConsulta vistaDatos) {
        pestanasTiposVistas.addTab(
                vistaDatos,
                "Datos consulta"
        );
    }
    
    public void pintarVistaDatosTabla(VistaDatosTabla vistaDatos) {
        pestanasTiposVistas.addTab(
                vistaDatos,
                "Datos columnas"
        );
    }
    
    private void posicionarComponentesInterfaz() {
        widgetLayout.addWidget(pestanasTiposVistas);
        setLayout(widgetLayout);
    }
}
