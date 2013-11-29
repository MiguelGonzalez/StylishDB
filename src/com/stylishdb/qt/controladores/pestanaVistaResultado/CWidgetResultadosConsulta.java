package com.stylishdb.qt.controladores.pestanaVistaResultado;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.bd.domain.ResultadoEjecutarConsulta;
import com.stylishdb.qt.controladores.CMiControladorGenerico;
import com.stylishdb.qt.pestanaVistaResultado.WidgetResultadosConsulta;

/**
 *
 ** @author StylishDB
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
