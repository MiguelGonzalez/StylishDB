package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CPestanaMostrarConsulta;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class PestanaMostrarResultadoConsulta extends QWidget {
    
    private QTableWidget tablaResultadoConsulta;
    private CPestanaMostrarConsulta controlador;
    
    public PestanaMostrarResultadoConsulta(CPestanaMostrarConsulta controlador) {
        this.controlador = controlador;
        tablaResultadoConsulta = new QTableWidget();
        
        establecerLayout();
    }
    
    private void establecerLayout() {
        QVBoxLayout widgetLayout = new QVBoxLayout();
        widgetLayout.addWidget(tablaResultadoConsulta);
        setLayout(widgetLayout);
    }

    public void establecerColumnas(List<String> columnas) {
        tablaResultadoConsulta.setColumnCount(columnas.size());
        tablaResultadoConsulta.setHorizontalHeaderLabels(columnas);
    }
    
    public void anadirDatosFila(List<String> datosFila) {
        int filaActual = tablaResultadoConsulta.rowCount();
        int numColumna = -1;
        
        tablaResultadoConsulta.insertRow(filaActual);
        for(String datoFila : datosFila) {
            numColumna++;
            QTableWidgetItem columnaFilaWidget = new QTableWidgetItem(datoFila);
            
            tablaResultadoConsulta.setItem(filaActual, numColumna, columnaFilaWidget);
        }
        
        if (tablaResultadoConsulta.selectedItems().isEmpty()) {
            tablaResultadoConsulta.selectRow(0);
        }

        tablaResultadoConsulta.resizeColumnsToContents();
    }
}
