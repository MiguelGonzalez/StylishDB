package com.stylishdb.qt.tabResultViews.controllers;

import com.trolltech.qt.gui.QWidget;
import com.stylishdb.db.ManejadorConsulta;
import com.stylishdb.db.ManejadorConsultaErrorSQL;
import com.stylishdb.db.ManejadorConsultaNoHayConexion;
import com.stylishdb.db.domain.ResultadoActualizarConsultas;
import com.stylishdb.domain.MConexion;
import com.stylishdb.qt.controllers.CMiControladorGenerico;
import com.stylishdb.qt.modals.ModalMostrarAviso;
import com.stylishdb.qt.tabResultViews.PestanaActualizarConsultas;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class CPestanaActualizarConsultas extends CMiControladorGenerico {
    private ManejadorConsulta manejadorConsulta;
    private PestanaActualizarConsultas pestanaConsultas;
    
    private MConexion mConexion;
    private List<String> consultasActualizar;
    
    public CPestanaActualizarConsultas(MConexion mConexion,
            List<String> consultasActualizar) throws ManejadorConsultaErrorSQL {
        super();
        
        this.mConexion = mConexion;
        this.consultasActualizar = consultasActualizar;
        
        crearControladoresYComponentes();
        ejecutarPasosLanzarQuery();
    }

    public QWidget getPestanaResultado() {
        return pestanaConsultas;
    }
    
    private void crearControladoresYComponentes() {
        manejadorConsulta = new ManejadorConsulta(
                mConexion,
                consultasActualizar
        );
        
        pestanaConsultas = new PestanaActualizarConsultas(this);
    }
    
    private void ejecutarPasosLanzarQuery() throws ManejadorConsultaErrorSQL {
        if(conectarContraBaseDeDatos()) {
            ejecutarActualizarConsultas();
            pintarRespuestasActualizacion();
        }
    }
    
    private boolean conectarContraBaseDeDatos() {
        try {
            manejadorConsulta.conectarContraBaseDeDatos();
            return true;
        } catch (ManejadorConsultaNoHayConexion ex) {
            mostrarErrorEnPantalla(
                    "Connection exception",
                    ex.getMessage()
            );
        }
        return false;
    }
    
    private void ejecutarActualizarConsultas() throws ManejadorConsultaErrorSQL {
        manejadorConsulta.actualizarConsultas();
    }
    
    private void pintarRespuestasActualizacion() {
        ResultadoActualizarConsultas resultadoActualizarConsultas =
                manejadorConsulta.getResultadoActualizacionConsultas();
        
        pestanaConsultas.pintarResultado(
                resultadoActualizarConsultas
        );
    }
    
    private void mostrarErrorEnPantalla(String tituloError, String mensajeError) {
        ModalMostrarAviso.mostrarErrorEnPantalla(tituloError,
                mensajeError);
    }
}
