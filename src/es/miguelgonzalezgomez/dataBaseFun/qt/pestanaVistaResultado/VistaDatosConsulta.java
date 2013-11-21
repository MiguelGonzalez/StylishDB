package es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado;

import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado.CVistaDatosConsulta;
import java.util.List;

/**
 *
 * @author Miguel González
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
    
    public void pintarConsultaSQL(String consultaSQL) {
        textConsultaSQL.setText(consultaSQL);
    }
    
    public void pintarDatos(ResultadoEjecutarConsulta resultadoEjecutarConsulta) {
        int numFilas = resultadoEjecutarConsulta.datosFilas.size();
        int numColumnas = resultadoEjecutarConsulta.datosColumnas.size();
        
        tablaResultadoConsulta.setRowCount(numFilas);
        tablaResultadoConsulta.setColumnCount(numColumnas);
        tablaResultadoConsulta.setHorizontalHeaderLabels(
                resultadoEjecutarConsulta.nombresColumnas);
        
        anadirDatosConsulta(resultadoEjecutarConsulta.datosFilas);
    }
    
    private void anadirDatosConsulta(List<String[]> datosConsulta) {
        int numFila = 0;
        for(String[] datosFila : datosConsulta) {
            anadirDatosFila(datosFila, numFila);
            numFila++;
        }

        if (tablaResultadoConsulta.selectedItems().isEmpty()) {
            tablaResultadoConsulta.selectRow(0);
        }
        
        tablaResultadoConsulta.resizeColumnsToContents();
    }
    
    private void anadirDatosFila(String[] datosFila, int numFila) {
        int numColumna = -1;
        
        for(String datoFila : datosFila) {
            numColumna++;
            QTableWidgetItem columnaFilaWidget = new QTableWidgetItem(datoFila);
            
            tablaResultadoConsulta.setItem(numFila, numColumna, columnaFilaWidget);
        }
    }
}
