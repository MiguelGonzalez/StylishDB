package com.stylishdb.qt.tabResultViews.controllers;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.db.ParserTextSQL;
import com.stylishdb.db.ExecutorTextSQLException;
import com.stylishdb.db.domain.ResultExecutes;
import com.stylishdb.style.GetStyle;
import com.stylishdb.domain.MConnection;
import com.stylishdb.domain.MTab;
import com.stylishdb.domain.controllers.CFocusTabListener;
import com.stylishdb.qt.controllers.Controller;
import com.stylishdb.qt.modals.ModalAlert;
import com.stylishdb.qt.tabResultViews.TabQueriesExecuted;
import com.stylishdb.qt.tabResultViews.TabQueryExecute;
import com.stylishdb.qt.tabResultViews.TabUpdates;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class CTabQueriesExecuted extends Controller
        implements CFocusTabListener, ThreadExecuteQueryListener {

    private TabQueriesExecuted panelConsultas;
    
    public CTabQueriesExecuted() {
        super();
        
        panelConsultas = new TabQueriesExecuted(this);
        
        establecerEstilo();
        establecerListener();
    }
    
    private void establecerEstilo() {
        panelConsultas.setStyleSheet(
                GetStyle.getEstiloVentana("widgetConsultas.css")
        );
        panelConsultas.setContentsMargins(0, 0, 0, 0);
    }
    
    private void establecerListener() {
        controladorPestanaActiva.addListener(this);
    }
    
    private void ejecutarConsultas(MTab mPestana,
            List<String> consultasEjecutar,
            boolean nuevaPestana) {
        MConnection mConexion = conexionesGuardadas.getMConexion(
                mPestana.uuidConexion);
        
        boolean esPrimeraConsultaEjecutar = true;
        for(String consultaEjecutar : consultasEjecutar) {
            ejecutarConsulta(
                    mConexion,
                    consultaEjecutar,
                    esPrimeraConsultaEjecutar ? nuevaPestana : true);
            
             esPrimeraConsultaEjecutar = false;
        }
    }
    
    private void ejecutarConsulta(
            MConnection mConexion,
            String consultaEjecutar,
            boolean nuevaPestana) {
        ThreadExecuteQuery thread = new ThreadExecuteQuery(
            mConexion,
            consultaEjecutar,
                nuevaPestana
        );
        thread.addListener(this);
        thread.start();
    }
    
    private void actualizarConsultas(
            MTab mPestana,
            List<String> consultasActualizar,
            boolean nuevaPestana) {
        try {
            MConnection mConexion = mAplicacion.mConexionesGuardadas.
                getMConexion(mPestana.uuidConexion);            
            CTabUpdates cPestanaActualizarConsultas = new
                CTabUpdates(
                    mConexion,
                    consultasActualizar);

            String nombrePestana = mPestana.getNombrePestana();

            anadirNuevaPestana(
                    nombrePestana,
                    cPestanaActualizarConsultas.getPestanaResultado(),
                    nuevaPestana
            );
        } catch (ExecutorTextSQLException ex) {
            ModalAlert.mostrarErrorEnPantalla(
                "Error al actualizar la consulta",
                ex.getMessage()
            );
        }
    }
    
    public QTabWidget getPanelConsultas() {
        return panelConsultas;
    }
    
    private void anadirNuevaPestana(
            String nombrePestana,
            QWidget widget,
            boolean nuevaPestana) {
        
        int index = -1;
        if(panelConsultas.isTabActive() && !nuevaPestana) {
            index = panelConsultas.currentIndex();
            
            panelConsultas.removeTab(index);
        }
        
        panelConsultas.addTab(nombrePestana, widget, index);        
        if(index != -1) {
            panelConsultas.setCurrentIndex(index);
        } else {
            panelConsultas.setCurrentIndex(
                    panelConsultas.count() - 1
            );
        }
    }
    
    protected void cerrarPestana(int indexPestana) {
        try {
            TabQueryExecute tab = (TabQueryExecute) panelConsultas.
                    widget(indexPestana);

            tab.liberarControlador();
        } catch(ClassCastException ex) {
            TabUpdates tab = (TabUpdates) panelConsultas.
                    widget(indexPestana);
            tab.liberarControlador();
        }
    }

    @Override
    public void deshacer(MTab pestana) {}

    @Override
    public void rehacer(MTab pestana) {}

    @Override
    public void eliminada(MTab pestana) {
    }

    @Override
    public void ejecutarConsulta(MTab mPestana, boolean nuevaPestana) {
        MConnection mConexion = mAplicacion.mConexionesGuardadas.
                getMConexion(mPestana.uuidConexion);
        String textoConsultaLanzar = mPestana.getTextoConsultaLanzar();
        
        ParserTextSQL analizarTextoConsulta = new ParserTextSQL(
                mConexion.getTipoDeBaseDeDatos(), textoConsultaLanzar
        );
        
        List<String> consultasEjecutar = analizarTextoConsulta.getConsultasEjecutar();
        List<String> consultasActualizar = analizarTextoConsulta.getConsultasActualizar();
        
        if(!consultasEjecutar.isEmpty()) {
            ejecutarConsultas(
                    mPestana,
                    consultasEjecutar,
                    nuevaPestana);
        }
        if(!consultasActualizar.isEmpty()) {
            actualizarConsultas(
                    mPestana,
                    consultasActualizar,
                    nuevaPestana);
        }
    }

    @Override
    public void consultaEjecutada(
            final ResultExecutes resultadoEjecutarConsulta,
            final boolean nuevaPestana) {
        QApplication.invokeLater(new Runnable() {
            @Override
            public void run() {
                CTabQueryExecute cPestanaMostrarConsulta = new
                    CTabQueryExecute();

                MTab mPestana = pestanasAbiertas.getPestanaActiva();

                String nombrePestana = mPestana.getNombrePestana();
                anadirNuevaPestana(
                        nombrePestana,
                        cPestanaMostrarConsulta.getPestanaResultado(),
                        nuevaPestana
                );

                cPestanaMostrarConsulta.pintarResultados(resultadoEjecutarConsulta);
            }
        });
    }

    @Override
    public void errorEjecutarConsulta(final Exception ex) {
        QApplication.invokeLater(new Runnable() {
            @Override
            public void run() {
                ModalAlert.mostrarErrorEnPantalla(
                    "Error al ejecutar la consulta",
                    ex.getMessage()
                );
            }
        });
    }
}
