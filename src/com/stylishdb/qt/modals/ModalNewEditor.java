package com.stylishdb.qt.modals;

import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QListWidget;
import com.trolltech.qt.gui.QVBoxLayout;
import com.stylishdb.qt.controllers.CNewEditor;

/**
 *
 ** @author StylishDB
 */
public class ModalNewEditor extends QDialog {
    
    private QLabel conexionLabel;
    
    public QListWidget conexionListWidget;

    private CNewEditor controlador;
    
    public ModalNewEditor(CNewEditor controlador) {
        this.controlador = controlador;
        
        setWindowTitle(tr(tr("Nuevo editor")));
        setModal(true);      
    }
    
    public void construirYLanzarInterfaz() {
        crearComponentesInterfaz();
        establecerEventosInterfaz();
        posicionarComponentesInterfaz();
    }
    
    private void crearComponentesInterfaz() {
        conexionLabel = new QLabel(tr("Elegir conexión"));
        conexionListWidget = new QListWidget();
    }
    
    private void establecerEventosInterfaz() {
        conexionListWidget.itemActivated.connect(
                controlador,
                "eventoCrearEditor()");
        conexionListWidget.itemPressed.connect(
                controlador,
                "eventoCrearEditor()");
    }
    
    private void posicionarComponentesInterfaz() {
        QVBoxLayout ventanaLayout = new QVBoxLayout();
        ventanaLayout.setMargin(10);
        
        ventanaLayout.addLayout(
                getLayoutConexion()
        );
        
        setLayout(ventanaLayout);
    }
    
    private QGridLayout getLayoutConexion() {
        QGridLayout datosConexionGrid = new QGridLayout();
        
        datosConexionGrid.addWidget(conexionLabel, 0, 0);
        datosConexionGrid.addWidget(conexionListWidget, 1, 0);
        
        return datosConexionGrid;
    }
}
