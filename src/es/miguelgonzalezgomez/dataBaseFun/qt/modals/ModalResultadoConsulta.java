package es.miguelgonzalezgomez.dataBaseFun.qt.modals;

import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QVBoxLayout;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CEjecutarConsulta;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class ModalResultadoConsulta extends QDialog {
    
    private QTableWidget tablaResultados;
    private CEjecutarConsulta controlador;
    
    public ModalResultadoConsulta(CEjecutarConsulta controlador) {
        this.controlador = controlador;
        
        setWindowTitle(tr(tr("Resultado consulta")));
        setModal(true);
    }
    
    public void construirInterfaz() {
        crearComponentesInterfaz();
        
        posicionarComponentesInterfaz();
    }

    private void crearComponentesInterfaz() {
        tablaResultados = new QTableWidget();
    }
    
    private void posicionarComponentesInterfaz() {
        QVBoxLayout ventanaLayout = new QVBoxLayout();
        ventanaLayout.setMargin(10);
        
        ventanaLayout.addLayout(
                getLayoutTabla()
        );
        
        setLayout(ventanaLayout);
    }
    
    private QGridLayout getLayoutTabla() {
        QGridLayout datosConsutlaSQL = new QGridLayout();
        
        datosConsutlaSQL.addWidget(tablaResultados, 0, 0);
        
        return datosConsutlaSQL;
    }
    
    public void establecerColumnas(List<String> columnas) {
        tablaResultados.setColumnCount(columnas.size());
        tablaResultados.setHorizontalHeaderLabels(columnas);
    }
    
    public void anadirDatosFila(List<String> datosFila) {
        int filaActual = tablaResultados.rowCount();
        int numColumna = -1;
        
        tablaResultados.insertRow(filaActual);
        for(String datoFila : datosFila) {
            numColumna++;
            QTableWidgetItem columnaFilaWidget = new QTableWidgetItem(datoFila);
            
            tablaResultados.setItem(filaActual, numColumna, columnaFilaWidget);
        }
        
        if (tablaResultados.selectedItems().isEmpty()) {
            tablaResultados.selectRow(0);
        }

        tablaResultados.resizeColumnsToContents();
    }
    
}
