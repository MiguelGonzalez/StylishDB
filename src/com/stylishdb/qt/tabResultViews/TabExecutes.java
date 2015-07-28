package com.stylishdb.qt.tabResultViews;

import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.db.domain.ResultExecutes;
import com.stylishdb.qt.tabResultViews.controllers.CTabExecutes;

/**
 *
 ** @author StylishDB
 */
public class TabExecutes extends QWidget {

    private QLineEdit textConsultaSQL;
    private QTableWidget tablaResultadoConsulta;
    
    private final CTabExecutes controlador;
    private ExecutesTablePaint pintadoProgresivo = null;
    
    public TabExecutes(CTabExecutes controlador) {
        this.controlador = controlador;
        
        crearComponentesInterfaz();
        
        posicionarComponentesInterfaz();
    }
        
    private void crearComponentesInterfaz() {
        textConsultaSQL = new QLineEdit();
        textConsultaSQL.setReadOnly(true);
        textConsultaSQL.setTextMargins(3, 6, 3, 6);
        
        tablaResultadoConsulta = new QTableWidgetCopyEnhancement();
    }
    
    private void posicionarComponentesInterfaz() {
        QVBoxLayout ventanaLayout = new QVBoxLayout();
        
        ventanaLayout.addWidget(textConsultaSQL);
        ventanaLayout.addWidget(tablaResultadoConsulta);
        
        setLayout(ventanaLayout);
    }
    
    public void pintarDatos(ResultExecutes resultadoEjecutarConsulta) {
        textConsultaSQL.setText(resultadoEjecutarConsulta.consultaSQL);
        
        int numFilas = resultadoEjecutarConsulta.datosFilas.size();
        int numColumnas = resultadoEjecutarConsulta.datosColumnas.size();
        
        tablaResultadoConsulta.setRowCount(numFilas);
        tablaResultadoConsulta.setColumnCount(numColumnas);
        tablaResultadoConsulta.setHorizontalHeaderLabels(
                resultadoEjecutarConsulta.nombresColumnas);
        
        pintadoProgresivo = new
                ExecutesTablePaint(
            tablaResultadoConsulta,
            resultadoEjecutarConsulta.datosFilas
        );
        pintadoProgresivo.iniciarPintado();
    }

    public void liberarWidget() {
        pintadoProgresivo.pararPintado();
        pintadoProgresivo = null;
    }
}
