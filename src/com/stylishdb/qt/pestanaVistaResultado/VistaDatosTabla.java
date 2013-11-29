package com.stylishdb.qt.pestanaVistaResultado;

import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.bd.domain.DatosColumna;
import com.stylishdb.bd.domain.ResultadoEjecutarConsulta;
import com.stylishdb.qt.controladores.pestanaVistaResultado.CVistaDatosTabla;
import java.util.ArrayList;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class VistaDatosTabla extends QWidget {

    private QTableWidget tablaDatosTabla;
    
    private CVistaDatosTabla controlador;
    
    public VistaDatosTabla(CVistaDatosTabla controlador) {
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
    
    public void pintarDatosTabla(ResultadoEjecutarConsulta resultadoConsulta) {
        List<DatosColumna> datosFilas = resultadoConsulta.datosColumnas;
        
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
    
    private void pintarDatosFilas(List<DatosColumna> datosColumna) {
        for(DatosColumna datoColumna : datosColumna) {
            pintarDatosFila(datoColumna);
        }
    }
    
    private void pintarDatosFila(DatosColumna datoColumna) {
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
