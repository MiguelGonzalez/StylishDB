package es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class PintadoProgresivoTabla {

    private QTableWidget table;
    private List<String[]> datosConsulta;
    
    public PintadoProgresivoTabla(QTableWidget table,
            List<String[]> datosConsulta) {
        this.table = table;
        this.datosConsulta = datosConsulta;
    }
 
    public void iniciarPintado() {
        table.blockSignals(true);
        anadirDatosConsulta(datosConsulta);
        table.blockSignals(false);
    }
    
    private void anadirDatosConsulta(final List<String[]> datosConsulta) {
        for(int numFila = 0; numFila < datosConsulta.size(); numFila++) {
            final int numFilaBucle = numFila;
            QApplication.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    String[] datosFila = datosConsulta.get(numFilaBucle);
                    anadirDatosFila(datosFila, numFilaBucle);
                }
            });
        }

        if (table.selectedItems().isEmpty()) {
            table.selectRow(0);
        }
        
        table.resizeColumnsToContents();
    }
    
    private void anadirDatosFila(String[] datosFila, int numFila) {
        int numColumna = -1;
        
        for(String datoFila : datosFila) {
            numColumna++;
            QTableWidgetItem columnaFilaWidget = new QTableWidgetItem(datoFila);
            
            table.setItem(numFila, numColumna, columnaFilaWidget);
        }
    }
    
}
