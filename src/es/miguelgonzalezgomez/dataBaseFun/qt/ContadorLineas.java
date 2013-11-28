package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.gui.QPalette;
import com.trolltech.qt.gui.QPlainTextEdit;
import com.trolltech.qt.gui.QTextCursor;
import com.trolltech.qt.gui.QTextOption;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CEditor;

/**
 *
 * @author Miguel González
 */
public class ContadorLineas extends QPlainTextEdit {

    private CEditor controlador;
    int ultimaLinea;
    
    public ContadorLineas(CEditor controlador) {
        this.controlador = controlador;
        establecerPrimeraLinea();
        establecerEstilos();
    }
    
    private void establecerEstilos() {
        setForegroundRole(QPalette.ColorRole.Light);
        setEnabled(false);
        setWordWrapMode(QTextOption.WrapMode.NoWrap);
    }
    
    private void establecerPrimeraLinea() {
        setPlainText("1");
        ultimaLinea = 1;
    }
    
    public synchronized void pintarContadorLineas(int numeroLineas) {
        if(numeroLineas > ultimaLinea) {
            StringBuilder sb = new StringBuilder();
            for(int i=ultimaLinea + 1; i<=numeroLineas; i++) {
                if(i != ultimaLinea + 1) {
                    sb.append("\n");
                }
                sb.append(Integer.toString(i));
            }
            appendPlainText(sb.toString());
        } else {
            QTextCursor curs = textCursor();
            curs.beginEditBlock();
            curs.movePosition(QTextCursor.MoveOperation.End,
                    QTextCursor.MoveMode.MoveAnchor);
            
            for(int i=numeroLineas; i < ultimaLinea; i++) {
                curs.movePosition(QTextCursor.MoveOperation.StartOfLine,
                        QTextCursor.MoveMode.KeepAnchor);
                curs.movePosition(QTextCursor.MoveOperation.PreviousCharacter,
                        QTextCursor.MoveMode.KeepAnchor);
            }
            
            curs.removeSelectedText();
            curs.movePosition(QTextCursor.MoveOperation.End,
                    QTextCursor.MoveMode.MoveAnchor);
            curs.endEditBlock();
        }
        ultimaLinea = numeroLineas;
    }
    
    public void setPosicionScrollBar(int pos) {
        verticalScrollBar().setValue(pos);
    }

    public synchronized void lineasSeleccionadas(int[] lineasSeleccionadas) {
        QTextCursor curs = textCursor();
        curs.setPosition(0);
         
        curs.movePosition(QTextCursor.MoveOperation.Down,
                QTextCursor.MoveMode.MoveAnchor, lineasSeleccionadas[0] - 1);
        curs.movePosition(QTextCursor.MoveOperation.Down,
                    QTextCursor.MoveMode.KeepAnchor,
                    lineasSeleccionadas.length - 1);
        curs.movePosition(QTextCursor.MoveOperation.EndOfLine,
                QTextCursor.MoveMode.KeepAnchor);
        curs.movePosition(QTextCursor.MoveOperation.NextCharacter,
                QTextCursor.MoveMode.KeepAnchor);
        
        
        setTextCursor(curs);
    }
}
