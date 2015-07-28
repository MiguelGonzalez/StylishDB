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
public class ExecutesTablePaint {

    private final QTableWidget table;
    private final List<String[]> datosConsulta;
    private boolean pintando;
    
    public ExecutesTablePaint(QTableWidget table,
            List<String[]> datosConsulta) {
        this.table = table;
        this.datosConsulta = datosConsulta;
    }
    
    public void iniciarPintado() {
        pintando = true;
        
        bloquearSenales();
        QThread qThread = new QThread(new Runnable() {
            @Override
            public void run() {
                for(int numFila = 0; numFila < datosConsulta.size() && pintando; numFila++) {
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
                        if(pintando) {
                            table.resizeColumnsToContents();
                            desbloquearSenales();
                        }
                    }
                });
            }
        });
        qThread.start();        
    }
    
    public synchronized void pararPintado() {
        pintando = false;
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
            
            if(pintando) {
                table.setItem(numFila, numColumna, columnaFilaWidget);
            }
        }
    }

}
