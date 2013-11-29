package com.stylishdb.qt.controladores;

import com.trolltech.qt.core.Qt;
import com.stylishdb.bd.ManejadorConsultaErrorSQL;
import com.stylishdb.bd.ManejadorConsultaNoHayConexion;
import com.stylishdb.bd.ObtencionTablasBaseDatos;
import com.stylishdb.estilos.ObtencionEstilo;
import com.stylishdb.domain.MConexion;
import com.stylishdb.domain.MPestana;
import com.stylishdb.qt.modals.ModalMostrarAviso;
import com.stylishdb.qt.modals.ModalVerTablasBaseDatos;
import com.stylishdb.utilidadesEstaticas.CentroCoordenadas;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class CVerTablasBaseDatos extends CMiControladorGenerico {

    private MPestana mPestanaEditor;
    private ObtencionTablasBaseDatos obtencionTablas;
    private ModalVerTablasBaseDatos modalVerTablasBD;    

    public CVerTablasBaseDatos() {
        super();
    }
    
    void mostrarRenombrarPestanaActiva() {
        if(pestanasAbiertas.hayPestanaActiva()) {
            modalVerTablasBD = new ModalVerTablasBaseDatos(this);        
            mPestanaEditor = obtenerPestanaActiva();
            
            cargarDisenoVentanaModal();
            mostrarVentanaModal();
        }
    }
    
    public MPestana obtenerPestanaActiva() {
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
                CentroCoordenadas.getXCentrada(width),
                CentroCoordenadas.getYCentradaArriba()
                );
    }
    
    protected void recargarEstiloModal() {
        modalVerTablasBD.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("dialogEstilo.css")
        );
    }
    
    private void cargarTablasBD() {
        MConexion mConexion = mAplicacion.mConexionesGuardadas.
                getMConexion(mPestanaEditor.uuidConexion);
        obtencionTablas = new ObtencionTablasBaseDatos(mConexion);
        try {
            obtencionTablas.conectarContraBaseDeDatos();
            
            List<String> nombresTablas = obtencionTablas.obtenerNombresTablasBD();
            
            modalVerTablasBD.cargarNombresTablas(nombresTablas);
        } catch (ManejadorConsultaNoHayConexion ex) {
            mostrarErrorEnPantalla(
                    "Connection exception",
                    ex.getMessage()
            );
        } catch (ManejadorConsultaErrorSQL ex) {
            mostrarErrorEnPantalla(
                    "Connection exception",
                    ex.getMessage()
            );
        }
    }
    
    private void mostrarErrorEnPantalla(String tituloError, String mensajeError) {
        ModalMostrarAviso.mostrarErrorEnPantalla(tituloError,
                mensajeError);
    }
}
