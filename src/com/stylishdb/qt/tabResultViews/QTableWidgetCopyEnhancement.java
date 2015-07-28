package com.stylishdb.qt.tabResultViews;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QAbstractItemView;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QClipboard;
import com.trolltech.qt.gui.QKeyEvent;
import com.trolltech.qt.gui.QTableWidget;
import com.trolltech.qt.gui.QTableWidgetItem;
import com.trolltech.qt.gui.QTableWidgetSelectionRange;
import com.trolltech.qt.gui.QWidget;
import java.util.List;

/**
 * Copyright © 2014 by Telecor
 *
 * All Rights Reserved
 */
public class QTableWidgetCopyEnhancement extends QTableWidget {
    
    private static int SPACE_FILL_RIGHT_COLUMN = 5;

    public QTableWidgetCopyEnhancement() {
        setSelectionMode(QAbstractItemView.SelectionMode.ContiguousSelection);
    }

    public QTableWidgetCopyEnhancement(QWidget parent) {
        super(parent);
        
        setSelectionMode(QAbstractItemView.SelectionMode.ContiguousSelection);
    }

    public QTableWidgetCopyEnhancement(int rows, int columns) {
        super(rows, columns);
        
        setSelectionMode(QAbstractItemView.SelectionMode.ContiguousSelection);
    }

    public QTableWidgetCopyEnhancement(int rows, int columns, QWidget parent) {
        super(rows, columns, parent);
        
        setSelectionMode(QAbstractItemView.SelectionMode.ContiguousSelection);
    }
            
    @Override
    protected void keyPressEvent(QKeyEvent event) {        
        if(esCopiar(event)) {
            copiarDatosTabla();
            
            return;
        }
        
        super.keyPressEvent(event);
    }

    private boolean esCopiar(QKeyEvent e) {
        Qt.KeyboardModifiers modifiers = e.modifiers();
        if(
                modifiers.isSet(Qt.KeyboardModifier.ControlModifier)) {
            if(e.key() == Qt.Key.Key_C.value()) {
                return true;
            }
        }
        
        return false;
    }

    private void copiarDatosTabla() {                         
        QClipboard clipboard = QApplication.clipboard();
        
        if(selectedRanges().size() == 1) {
            String datosTabla = obtenerTextoCeldasSeleccionadas();
            
            clipboard.setText(datosTabla);
        } else {
            clipboard.setText("No se han podido obtener los datos de la tabla");
        }
    }
    
    private String obtenerTextoCeldasSeleccionadas() {
        StringBuilder sbCopy = new StringBuilder();
        String newLineSeparator = System.getProperty("line.separator");
        
        QTableWidgetSelectionRange selectionRange = selectedRanges().get(0);
        List<QTableWidgetItem> selectedItems = selectedItems();

        int colStart = selectionRange.leftColumn();
        int rowStart = selectionRange.topRow();
        int numRows = selectionRange.rowCount();
        int numCols = selectionRange.columnCount();

        int[] anchosColumnas = new int[numCols];

        for(int numCol=0; numCol<numCols; numCol++) {
            anchosColumnas[numCol] = obtenerAnchoColumna(
                    selectedItems,
                    colStart + numCol,
                    rowStart,
                    numRows);
        }

        for(int numRow=0; numRow<numRows; numRow++) {
            for(int numCol=0; numCol<numCols; numCol++) {
                QTableWidgetItem item = obtenerSelectedItem(
                        selectedItems,
                        colStart + numCol,
                        rowStart + numRow);

                String data = rellenarConCaracter(
                        item.text(),
                        anchosColumnas[numCol], " ");

                sbCopy.append(data);
            }

            if(noEsUltimaFila(numRow, numRows)) {
                sbCopy.append(newLineSeparator);
            }
        }
        
        return sbCopy.toString();
    }
    
    private boolean noEsUltimaFila(int numRow, int numRows) {
        return numRow + 1 != numRows;
    }

    private int obtenerAnchoColumna(
            List<QTableWidgetItem> selectedItems,
            int numCol,
            int rowStart,
            int numRows) {
        
        int width = 10;
        int widthItem;
        
        for(int i=0; i<numRows; i++) {
            QTableWidgetItem item = obtenerSelectedItem(
                    selectedItems,
                    numCol,
                    i + rowStart);
            widthItem = item.text().length() + SPACE_FILL_RIGHT_COLUMN;
            
            if(width < widthItem) {
                width = widthItem;
            }
        }
        
        return width;
    }

    private QTableWidgetItem obtenerSelectedItem(
            List<QTableWidgetItem> selectedItems,
            int numCol,
            int numRow) {
        
        for(QTableWidgetItem item : selectedItems) {
            if(item.row() == numRow && item.column()== numCol) {
                return item;
            }
        }
        
        return null;
    }
    
    private String rellenarConCaracter(
            String cadena,
            int longitudMaxima,
            String caracter) {
        StringBuilder sbCadena = new StringBuilder(cadena);
        while(sbCadena.length() < longitudMaxima) {
            sbCadena.append(caracter);
        }
        return sbCadena.toString();
    }
}
