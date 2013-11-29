package com.stylishdb.qt.controllers;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QListWidgetItem;
import com.stylishdb.style.GetStyle;
import com.stylishdb.domain.MConnection;
import com.stylishdb.domain.MTab;
import com.stylishdb.utilities.StyleWithoutFocus;
import com.stylishdb.qt.modals.ModalNewEditor;
import com.stylishdb.utilities.CoordenatesWindow;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class CNewEditor extends Controller {
 
    private ModalNewEditor modalCrearNuevoEditor;
    
    public CNewEditor() {
        super();
        
        modalCrearNuevoEditor = new ModalNewEditor(this);
        
        posicionarVentanaModal();
    }
    
    public void lanzar() {
        modalCrearNuevoEditor.construirYLanzarInterfaz();
        modalCrearNuevoEditor.setWindowFlags(Qt.WindowType.FramelessWindowHint);
        modalCrearNuevoEditor.setStyleSheet(
                GetStyle.getEstiloVentana("dialogEstilo.css")
        );
        
        pintarComboBoxConexionesGuardadas();
        
        modalCrearNuevoEditor.show();
    }
    
    private void posicionarVentanaModal() {
        int width = 350; 
        modalCrearNuevoEditor.setFixedWidth(width);
        modalCrearNuevoEditor.move(
                CoordenatesWindow.getXCentrada(width),
                CoordenatesWindow.getYCentradaArriba()
                );
    }
    
    private void pintarComboBoxConexionesGuardadas() {
        List<MConnection> mConexiones = conexionesGuardadas.getConexionesGuardadas();
        
        for(MConnection mConexion : mConexiones) {
            QListWidgetItem widgetItem = new QListWidgetItem(mConexion.getNombre());
            widgetItem.setData(0, mConexion);
            modalCrearNuevoEditor.conexionListWidget.addItem(widgetItem);
        }
        modalCrearNuevoEditor.conexionListWidget.setStyle(new StyleWithoutFocus());
    }
    
    protected void eventoCrearEditor() {
        QListWidgetItem widgetItem = modalCrearNuevoEditor.conexionListWidget.
                currentItem();
        
        MConnection conexion = (MConnection) widgetItem.data(0);
        
        MTab mPestanaEditor = new MTab(
                conexion.uuidConexion,
                conexion.getNombre());
        pestanasAbiertas.addPestana(mPestanaEditor);
        
        modalCrearNuevoEditor.close();
    }
}
