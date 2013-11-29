package com.stylishdb.qt.tabResultViews.controllers;

import com.trolltech.qt.QThread;
import com.stylishdb.db.ManejadorConsulta;
import com.stylishdb.db.ManejadorConsultaErrorSQL;
import com.stylishdb.db.ManejadorConsultaNoHayConexion;
import com.stylishdb.db.domain.ResultadoEjecutarConsulta;
import com.stylishdb.domain.MConexion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class ThreadEjecutarConsulta {

    private ManejadorConsulta manejadorConsulta;
    private MConexion mConexion;
    private String consultaSQL;
    private List<ThreadEjecutarConsultaListener> listeners;

    public ThreadEjecutarConsulta(MConexion mConexion, String consultaSQL) {
        this.mConexion = mConexion;
        this.consultaSQL = consultaSQL;
        listeners = new ArrayList<>();
    }
    
    public void addListener(ThreadEjecutarConsultaListener listener) {
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
                    manejadorConsulta = new ManejadorConsulta(mConexion, consultaSQL);
                    manejadorConsulta.conectarContraBaseDeDatos();
                    manejadorConsulta.ejecutarConsulta();
                    ResultadoEjecutarConsulta res = manejadorConsulta.
                            getDatosConsultaEjecutada();

                    for(ThreadEjecutarConsultaListener listener : listeners) {
                        listener.consultaEjecutada(res);
                    }
                } catch (ManejadorConsultaErrorSQL ex) {
                    for(ThreadEjecutarConsultaListener listener : listeners) {
                        listener.errorEjecutarConsulta(ex);
                    }
                } catch (ManejadorConsultaNoHayConexion ex) {
                    for(ThreadEjecutarConsultaListener listener : listeners) {
                        listener.errorEjecutarConsulta(ex);
                    }
                }
            }
        });
        qtThread.start();
    }
}
