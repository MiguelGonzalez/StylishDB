package com.stylishdb.qt.modals;

import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QListWidget;
import com.trolltech.qt.gui.QListWidgetItem;
import com.trolltech.qt.gui.QVBoxLayout;
import com.stylishdb.qt.controllers.CShowTables;
import java.util.List;

/**
 *
 * @author StylishDB
 */
public class ModalShowTables extends QDialog {

    private CShowTables controlador;
    private QListWidget listadoTablas;
    
    public ModalShowTables(CShowTables controlador) {
        this.controlador = controlador;
        
        setWindowTitle(tr(tr("Tablas de la BD")));
        setModal(true);
    }
    
    public void construirInterfaz() {
        crearComponentesInterfaz();
        establecerEventosInterfaz();
        posicionarComponentesInterfaz();
    }
    
    private void crearComponentesInterfaz() {
        listadoTablas = new QListWidget(this);
    }
    
    private void establecerEventosInterfaz() {
        
    }
    
    private void posicionarComponentesInterfaz() {
        QVBoxLayout ventanaLayout = new QVBoxLayout();
        ventanaLayout.setMargin(5);
        
        ventanaLayout.addLayout(
                getLayoutDatosConexion()
        );
        
        setLayout(ventanaLayout);
    }
    
    private QGridLayout getLayoutDatosConexion() {
        QGridLayout datoListadoTablasBD = new QGridLayout();
     
        datoListadoTablasBD.addWidget(listadoTablas, 0, 0);
        
        return datoListadoTablasBD;
    }
    
    public void cargarNombresTablas(List<String> nombresTablas) {
        for(String nombreTabla : nombresTablas) {
            cargarNombreTabla(nombreTabla);
        }
    }
    
    private void cargarNombreTabla(String nombreTabla) {
        QListWidgetItem listWidgetItem = new QListWidgetItem(nombreTabla);
        listadoTablas.insertItem(0, listWidgetItem);
    }
    
}
