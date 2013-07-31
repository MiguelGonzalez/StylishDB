package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CEjecutarConsultas;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class TablaResultadoConsulta extends QTableWidget {
    
    private CEjecutarConsultas controlador;
    
    public TablaResultadoConsulta(CEjecutarConsultas controlador) {
        this.controlador = controlador;
    }

    public void establecerColumnas(List<String> columnas) {
        setColumnCount(columnas.size());
        setHorizontalHeaderLabels(columnas);
    }
    
    public void anadirDatosFila(List<String> datosFila) {
        int filaActual = rowCount();
        int numColumna = -1;
        
        insertRow(filaActual);
        for(String datoFila : datosFila) {
            numColumna++;
            QTableWidgetItem columnaFilaWidget = new QTableWidgetItem(datoFila);
            
            setItem(filaActual, numColumna, columnaFilaWidget);
        }
        
        if (selectedItems().isEmpty()) {
            selectRow(0);
        }

        resizeColumnsToContents();
    }
}
