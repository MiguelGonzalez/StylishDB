package com.stylishdb.qt.tabResultViews;

import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.db.domain.ColumnDatas;
import com.stylishdb.db.domain.ResultExecutes;
import com.stylishdb.qt.tabResultViews.controllers.CTabRowsInformation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class TabRowsInformation extends QWidget {

    private QTableWidget tablaDatosTabla;
    
    private CTabRowsInformation controlador;
    
    public TabRowsInformation(CTabRowsInformation controlador) {
        this.controlador = controlador;
        
        crearComponentesInterfaz();
        
        posicionarComponentesInterfaz();
    }
    
    private void crearComponentesInterfaz() {
        tablaDatosTabla = new QTableWidget();
    }

    private void posicionarComponentesInterfaz() {
        QVBoxLayout ventanaLayout = new QVBoxLayout();
        
        ventanaLayout.addLayout(
                getLayoutDatosTabla()
        );
        
        setLayout(ventanaLayout);
    }
    
    private QGridLayout getLayoutDatosTabla() {
        QGridLayout datosGridConsulta = new QGridLayout();
        
        datosGridConsulta.addWidget(tablaDatosTabla, 0, 0);
        
        return datosGridConsulta;
    }
    
    public void pintarDatosTabla(ResultExecutes resultadoConsulta) {
        List<ColumnDatas> datosFilas = resultadoConsulta.datosColumnas;
        
        pintarNombresColumnas(resultadoConsulta.nombreDatosColumnaMostrar);
        pintarDatosFilas(datosFilas);
    }
    
    private void pintarNombresColumnas(String[] nombresColumnas) {
        tablaDatosTabla.setColumnCount(nombresColumnas.length);
        
        List<String> nombres = getArrayNombresColumna(nombresColumnas);
        
        tablaDatosTabla.setHorizontalHeaderLabels(nombres);
    }
    
    private List<String> getArrayNombresColumna(String[] nombresColumna) {
        List<String> nombres = new ArrayList<>();
        
        for(String nombreColumna : nombresColumna) {
            nombres.add(nombreColumna);
        }
        
        return nombres;
    }
    
    private void pintarDatosFilas(List<ColumnDatas> datosColumna) {
        for(ColumnDatas datoColumna : datosColumna) {
            pintarDatosFila(datoColumna);
        }
    }
    
    private void pintarDatosFila(ColumnDatas datoColumna) {
        int filaActual = tablaDatosTabla.rowCount();
        tablaDatosTabla.insertRow(filaActual);
        
        establecerItemFilaColumna(filaActual, 0, datoColumna.columnLabel);
        establecerItemFilaColumna(filaActual, 1, datoColumna.columnName);
        establecerItemFilaColumna(filaActual, 2, datoColumna.tableName);
        establecerItemFilaColumna(filaActual, 3, datoColumna.columnTypeName);
        establecerItemFilaColumna(filaActual, 4, datoColumna.precision +"");
        establecerItemFilaColumna(filaActual, 5, datoColumna.scale + "");
        establecerItemFilaColumna(filaActual, 6, Boolean.toString(datoColumna.isReadOnly));
        establecerItemFilaColumna(filaActual, 7, Boolean.toString(datoColumna.isAutoIncrement));
        establecerItemFilaColumna(filaActual, 8, datoColumna.isNullable == 0? "false" : "true");
    }
    
    private void establecerItemFilaColumna(int fila, int columna, String dato) {
        QTableWidgetItem columnaFilaWidget = new QTableWidgetItem(
                dato
        );
        tablaDatosTabla.setItem(fila, columna, columnaFilaWidget);
    }
}
