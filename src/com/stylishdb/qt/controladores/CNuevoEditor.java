package com.stylishdb.qt.controladores;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QListWidgetItem;
import com.stylishdb.estilos.ObtencionEstilo;
import com.stylishdb.domain.MConexion;
import com.stylishdb.domain.MPestana;
import com.stylishdb.qt.EstiloSinFoco;
import com.stylishdb.qt.modals.ModalCrearNuevoEditor;
import com.stylishdb.utilidadesEstaticas.CentroCoordenadas;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class CNuevoEditor extends CMiControladorGenerico {
 
    private ModalCrearNuevoEditor modalCrearNuevoEditor;
    
    public CNuevoEditor() {
        super();
        
        modalCrearNuevoEditor = new ModalCrearNuevoEditor(this);
        
        posicionarVentanaModal();
    }
    
    public void lanzar() {
        modalCrearNuevoEditor.construirYLanzarInterfaz();
        modalCrearNuevoEditor.setWindowFlags(Qt.WindowType.FramelessWindowHint);
        modalCrearNuevoEditor.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("dialogEstilo.css")
        );
        
        pintarComboBoxConexionesGuardadas();
        
        modalCrearNuevoEditor.show();
    }
    
    private void posicionarVentanaModal() {
        int width = 350; 
        modalCrearNuevoEditor.setFixedWidth(width);
        modalCrearNuevoEditor.move(
                CentroCoordenadas.getXCentrada(width),
                CentroCoordenadas.getYCentradaArriba()
                );
    }
    
    private void pintarComboBoxConexionesGuardadas() {
        List<MConexion> mConexiones = conexionesGuardadas.getConexionesGuardadas();
        
        for(MConexion mConexion : mConexiones) {
            QListWidgetItem widgetItem = new QListWidgetItem(mConexion.getNombre());
            widgetItem.setData(0, mConexion);
            modalCrearNuevoEditor.conexionListWidget.addItem(widgetItem);
        }
        modalCrearNuevoEditor.conexionListWidget.setStyle(new EstiloSinFoco());
    }
    
    protected void eventoCrearEditor() {
        QListWidgetItem widgetItem = modalCrearNuevoEditor.conexionListWidget.
                currentItem();
        
        MConexion conexion = (MConexion) widgetItem.data(0);
        
        MPestana mPestanaEditor = new MPestana(
                conexion.uuidConexion,
                conexion.getNombre());
        pestanasAbiertas.addPestana(mPestanaEditor);
        
        modalCrearNuevoEditor.close();
    }
}
