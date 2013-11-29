package com.stylishdb.qt.tabResultViews.controllers;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.db.domain.ResultExecutes;
import com.stylishdb.qt.controllers.Controller;
import com.stylishdb.qt.tabResultViews.TabQueryExecute;

/**
 *
 ** @author StylishDB
 */
public class CTabQueryExecute extends Controller {
    
    private TabQueryExecute pestanaResultado;
    
    private CTabExecutes controladorVistaDatosConsulta;
    private CTabRowsInformation controladorVistaDatosTabla;
    private CTabInformation controladorVistaDatosInformacion;
    
    public CTabQueryExecute()  {
        super();
        
        crearControladores();
        
        crearWidget();
        
        pintarComponentes();
    }
    
    private void crearControladores() {
        controladorVistaDatosConsulta = new CTabExecutes();
        controladorVistaDatosTabla = new CTabRowsInformation();
        controladorVistaDatosInformacion = new CTabInformation();
    }
    
    private void crearWidget() {
        pestanaResultado = new TabQueryExecute(this);
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

    public void pintarResultados(final ResultExecutes resultadoEjecutarConsulta) {
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
