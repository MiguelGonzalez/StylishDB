package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CMiControladorGenerico;
import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.WidgetResultadosConsulta;

/**
 *
 * @author Miguel González
 */
public class CWidgetResultadosConsulta extends CMiControladorGenerico {
    
    private WidgetResultadosConsulta pestanaResultado;
    
    private CVistaDatosConsulta controladorVistaDatosConsulta;
    private CVistaDatosTabla controladorVistaDatosTabla;
    private CVistaDatosInformacion controladorVistaDatosInformacion;
    
    public CWidgetResultadosConsulta()  {
        super();
        
        crearControladores();
        
        crearWidget();
        
        pintarComponentes();
    }
    
    private void crearControladores() {
        controladorVistaDatosConsulta = new CVistaDatosConsulta();
        controladorVistaDatosTabla = new CVistaDatosTabla();
        controladorVistaDatosInformacion = new CVistaDatosInformacion();
    }
    
    private void crearWidget() {
        pestanaResultado = new WidgetResultadosConsulta(this);
    }
    
    private void pintarComponentes() {
        pestanaResultado.pintarVistaDatosConsulta(
                controladorVistaDatosConsulta.getVistaDatosConsulta()
        );
        pestanaResultado.pintarVistaDatosTabla(
                controladorVistaDatosTabla.getVistaDatosTabla()
        );
        pestanaResultado.pintarVistaDatosInformacion(
                controladorVistaDatosInformacion.getVistaDatosInformacion()
        );
    }
    
    public QWidget getPestanaResultado() {
        return pestanaResultado;
    }

    public void pintarResultados(final ResultadoEjecutarConsulta resultadoEjecutarConsulta) {
        QApplication.invokeLater(new Runnable() {
            @Override
            public void run() {
                controladorVistaDatosConsulta.pintarDatosConsulta(
                        resultadoEjecutarConsulta);
                }
        });

        controladorVistaDatosTabla.pintarDatosTabla(resultadoEjecutarConsulta);
        controladorVistaDatosInformacion.pintarDatosInformacion(resultadoEjecutarConsulta);
    }

    public void liberarWidget() {
        controladorVistaDatosConsulta.liberarWidget();
        controladorVistaDatosTabla.liberarWidget();
        controladorVistaDatosInformacion.liberarWidget();
        
        controladorVistaDatosConsulta = null;
        controladorVistaDatosTabla = null;
        controladorVistaDatosInformacion = null;
        
        pestanaResultado.dispose();
        pestanaResultado = null;
    }
}
