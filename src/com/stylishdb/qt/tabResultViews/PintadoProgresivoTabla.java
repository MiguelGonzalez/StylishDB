package com.stylishdb.qt.tabResultViews;

import com.trolltech.qt.QThread;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import java.util.List;

/**
 *
 ** @author StylishDB
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
        
        bloquearSenales();
        QThread qThread = new QThread(new Runnable() {
            @Override
            public void run() {
                for(int numFila = 0; numFila < datosConsulta.size(); numFila++) {
                    final String[] datosFila = datosConsulta.get(numFila);
                    final int numFilaFinal = numFila;
                    QApplication.invokeAndWait(new Runnable() {
                        @Override
                        public void run() {
                            anadirDatosFila(datosFila, numFilaFinal);
                        }
                    });
                }
                
                QApplication.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                       table.resizeColumnsToContents();
                       desbloquearSenales();
                    }
                });
            }
        });
        qThread.start();        
    }
    
    private void bloquearSenales() {
        table.blockSignals(true);
    }
    
    private void desbloquearSenales() {
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
