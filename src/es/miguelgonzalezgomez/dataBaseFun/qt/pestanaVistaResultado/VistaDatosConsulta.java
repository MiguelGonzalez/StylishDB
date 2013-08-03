package es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado;

import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QVBoxLayout;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado.CVistaDatosConsulta;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class VistaDatosConsulta extends QWidget {

    private QTableWidget tablaResultadoConsulta;
    
    private CVistaDatosConsulta controlador;
    
    public VistaDatosConsulta(CVistaDatosConsulta controlador) {
        this.controlador = controlador;
        
        crearComponentesInterfaz();
        
        posicionarComponentesInterfaz();
    }
    
    private void crearComponentesInterfaz() {
        tablaResultadoConsulta = new QTableWidget();
    }
    
    
    private void posicionarComponentesInterfaz() {
        QVBoxLayout ventanaLayout = new QVBoxLayout();
        
        ventanaLayout.addLayout(
                getLayoutDatosConsulta()
        );
        
        setLayout(ventanaLayout);
    }
    
    private QGridLayout getLayoutDatosConsulta() {
        QGridLayout datosGridConsulta = new QGridLayout();
        
        datosGridConsulta.addWidget(tablaResultadoConsulta, 0, 0);
        
        return datosGridConsulta;
    }
    
    public void establecerColumnas(List<String> columnas) {
        tablaResultadoConsulta.setColumnCount(columnas.size());
        tablaResultadoConsulta.setHorizontalHeaderLabels(columnas);
    }
    
    public void anadirDatosConsulta(List<String[]> datosConsulta) {
        for(String[] datosFila : datosConsulta) {
            anadirDatosFila(datosFila);
        }
        
        if (tablaResultadoConsulta.selectedItems().isEmpty()) {
            tablaResultadoConsulta.selectRow(0);
        }
        
        tablaResultadoConsulta.resizeColumnsToContents();
    }
    
    private void anadirDatosFila(String[] datosFila) {
        int filaActual = tablaResultadoConsulta.rowCount();
        int numColumna = -1;
        
        tablaResultadoConsulta.insertRow(filaActual);
        for(String datoFila : datosFila) {
            numColumna++;
            QTableWidgetItem columnaFilaWidget = new QTableWidgetItem(datoFila);
            
            tablaResultadoConsulta.setItem(filaActual, numColumna, columnaFilaWidget);
        }
    }
}
