package com.stylishdb.qt.tabResultViews.controllers;

import com.trolltech.qt.QThread;
import com.stylishdb.db.ExecutorTextSQL;
import com.stylishdb.db.ExecutorTextSQLException;
import com.stylishdb.db.NoConnectionException;
import com.stylishdb.db.domain.ResultExecutes;
import com.stylishdb.domain.MConnection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class ThreadExecuteQuery {

    private ExecutorTextSQL manejadorConsulta;
    
    private final MConnection mConexion;
    private final String consultaSQL;
    private final List<ThreadExecuteQueryListener> listeners;
    private final boolean nuevaPestana;

    public ThreadExecuteQuery(
            MConnection mConexion,
            String consultaSQL,
            boolean nuevaPestana) {
        this.mConexion = mConexion;
        this.consultaSQL = consultaSQL;
        this.nuevaPestana = nuevaPestana;
        
        listeners = new ArrayList<>();
    }
    
    public void addListener(ThreadExecuteQueryListener listener) {
        listeners.add(listener);
    }
    
    public void start() {
        run();
    }
    
    private void run() {
        QThread qtThread = new QThread(new Runnable() {
            @Override
            public void run() {
                try {
                    manejadorConsulta = new ExecutorTextSQL(mConexion, consultaSQL);
                    manejadorConsulta.conectarContraBaseDeDatos();
                    manejadorConsulta.ejecutarConsulta();
                    ResultExecutes res = manejadorConsulta.
                            getDatosConsultaEjecutada();

                    for(ThreadExecuteQueryListener listener : listeners) {
                        listener.consultaEjecutada(
                                res,
                                nuevaPestana);
                    }
                } catch (ExecutorTextSQLException ex) {
                    for(ThreadExecuteQueryListener listener : listeners) {
                        listener.errorEjecutarConsulta(
                                ex);
                    }
                } catch (NoConnectionException ex) {
                    for(ThreadExecuteQueryListener listener : listeners) {
                        listener.errorEjecutarConsulta(
                                ex);
                    }
                }
            }
        });
        qtThread.start();
    }
}
