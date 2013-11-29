package com.stylishdb.qt.controllers;

import com.trolltech.qt.core.Qt;
import com.stylishdb.db.ExecutorTextSQLException;
import com.stylishdb.db.NoConnectionException;
import com.stylishdb.db.LoadTablesDB;
import com.stylishdb.style.GetStyle;
import com.stylishdb.domain.MConnection;
import com.stylishdb.domain.MTab;
import com.stylishdb.qt.modals.ModalAlert;
import com.stylishdb.qt.modals.ModalShowTables;
import com.stylishdb.utilities.CoordenatesWindow;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class CShowTables extends Controller {

    private MTab mPestanaEditor;
    private LoadTablesDB obtencionTablas;
    private ModalShowTables modalVerTablasBD;    

    public CShowTables() {
        super();
    }
    
    void mostrarRenombrarPestanaActiva() {
        if(pestanasAbiertas.hayPestanaActiva()) {
            modalVerTablasBD = new ModalShowTables(this);        
            mPestanaEditor = obtenerPestanaActiva();
            
            cargarDisenoVentanaModal();
            mostrarVentanaModal();
        }
    }
    
    public MTab obtenerPestanaActiva() {
        return pestanasAbiertas.getPestanaActiva();
    }

    private void cargarDisenoVentanaModal() {
        modalVerTablasBD.construirInterfaz();
        establecerDisenoInterfaz();
        posicionarVentanaModal();
        cargarTablasBD();
    }

    private void mostrarVentanaModal() {
        modalVerTablasBD.show();
    }
    
    private void establecerDisenoInterfaz() {
        modalVerTablasBD.setWindowFlags(Qt.WindowType.FramelessWindowHint);
        recargarEstiloModal();
    }
    
    private void posicionarVentanaModal() {
        int width = 300;
        int height = 500;
        modalVerTablasBD.resize(
                width, height
                );
        modalVerTablasBD.move(
                CoordenatesWindow.getXCentrada(width),
                CoordenatesWindow.getYCentradaArriba()
                );
    }
    
    protected void recargarEstiloModal() {
        modalVerTablasBD.setStyleSheet(
                GetStyle.getEstiloVentana("dialogEstilo.css")
        );
    }
    
    private void cargarTablasBD() {
        MConnection mConexion = mAplicacion.mConexionesGuardadas.
                getMConexion(mPestanaEditor.uuidConexion);
        obtencionTablas = new LoadTablesDB(mConexion);
        try {
            obtencionTablas.conectarContraBaseDeDatos();
            
            List<String> nombresTablas = obtencionTablas.obtenerNombresTablasBD();
            
            modalVerTablasBD.cargarNombresTablas(nombresTablas);
        } catch (NoConnectionException ex) {
            mostrarErrorEnPantalla(
                    "Connection exception",
                    ex.getMessage()
            );
        } catch (ExecutorTextSQLException ex) {
            mostrarErrorEnPantalla(
                    "Connection exception",
                    ex.getMessage()
            );
        }
    }
    
    private void mostrarErrorEnPantalla(String tituloError, String mensajeError) {
        ModalAlert.mostrarErrorEnPantalla(tituloError,
                mensajeError);
    }
}
