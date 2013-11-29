package com.stylishdb.qt.pestanaVistaResultado;

import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import com.stylishdb.bd.domain.ResultadoEjecutarConsulta;
import com.stylishdb.qt.controladores.pestanaVistaResultado.CVistaDatosConsulta;

/**
 *
 ** @author StylishDB
 */
public class VistaDatosConsulta extends QWidget {

    private QLineEdit textConsultaSQL;
    private QTableWidget tablaResultadoConsulta;
    
    private CVistaDatosConsulta controlador;
    
    public VistaDatosConsulta(CVistaDatosConsulta controlador) {
        this.controlador = controlador;
        
        crearComponentesInterfaz();
        
        posicionarComponentesInterfaz();
    }
    
    private void crearComponentesInterfaz() {
        textConsultaSQL = new QLineEdit();
        textConsultaSQL.setReadOnly(true);
        textConsultaSQL.setTextMargins(3, 6, 3, 6);
        tablaResultadoConsulta = new QTableWidget();
    }
    
    private void posicionarComponentesInterfaz() {
        QVBoxLayout ventanaLayout = new QVBoxLayout();
        
        ventanaLayout.addWidget(textConsultaSQL);
        ventanaLayout.addWidget(tablaResultadoConsulta);
        
        setLayout(ventanaLayout);
    }
    
    public void pintarDatos(ResultadoEjecutarConsulta resultadoEjecutarConsulta) {
        textConsultaSQL.setText(resultadoEjecutarConsulta.consultaSQL);
        
        int numFilas = resultadoEjecutarConsulta.datosFilas.size();
        int numColumnas = resultadoEjecutarConsulta.datosColumnas.size();
        
        tablaResultadoConsulta.setRowCount(numFilas);
        tablaResultadoConsulta.setColumnCount(numColumnas);
        tablaResultadoConsulta.setHorizontalHeaderLabels(
                resultadoEjecutarConsulta.nombresColumnas);
        
        PintadoProgresivoTabla pintadoProgresivo = new
                PintadoProgresivoTabla(
            tablaResultadoConsulta,
            resultadoEjecutarConsulta.datosFilas
        );
        pintadoProgresivo.iniciarPintado();
    }
}
