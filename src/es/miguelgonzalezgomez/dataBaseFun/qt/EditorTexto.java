package es.miguelgonzalezgomez.dataBaseFun.qt;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.core.Qt.KeyboardModifiers;
import com.trolltech.qt.gui.QKeyEvent;
import com.trolltech.qt.gui.QPlainTextEdit;
import com.trolltech.qt.gui.QTextCursor;
import es.miguelgonzalezgomez.dataBaseFun.domain.MPestana;
import es.miguelgonzalezgomez.dataBaseFun.domain.holders.IndentacionHolder;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CEditor;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author Miguel González
 */
public class EditorTexto extends QPlainTextEdit {
    
    private final int NUM_SPACES_INDENT = 4;
    private UUID uuid;
    private CEditor controlador;
    
    public EditorTexto(CEditor controlador) {
        super();
        
        this.controlador = controlador;
        uuid = UUID.randomUUID();
        setLineWrapMode(LineWrapMode.NoWrap);
        setTabStopWidth(40);
        
        establecerEventos();
    }
    
    @Override
    public boolean equals(Object object) {
        if(object == null) {
            return false;
        }
        if(!(object instanceof EditorTexto)) {
            return false;
        }
        EditorTexto editorTextoComparar = (EditorTexto) object;
        return uuid.equals(editorTextoComparar.uuid);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.uuid);
        return hash;
    }
    
    public void establecerTexto(String texto) {
        texto = texto.replaceAll("\t", getTabIndent());
        setPlainText(texto);
    }
    
    private void establecerEventos() {
        textChanged.connect(controlador, "eventoTextoCambiado()");
        selectionChanged.connect(controlador, "eventoSeleccionCambiado()");
    }
   
    public MPestana getModeloEditor() {
        return controlador.getModeloEditor();
    }
    
    @Override
    protected void keyPressEvent(QKeyEvent event) {
        if(suprimirRehacerNativo(event)) {
            return;
        }
        
        if(esCambiarSiguientePestana(event)) {
            controlador.cambiarSiguientePestana();
            return;
        }
        
        if(esCambiarAnteriorPestana(event)) {
            controlador.cambiarAnteriorPestana();
            return;
        }
        
        if(esTabPestana(event)) {
            insertarTab();
            
            return;
        }
        
        super.keyPressEvent(event);
    }
    
    private boolean suprimirRehacerNativo(QKeyEvent e) {
        KeyboardModifiers modifiers = e.modifiers();
        if(
                modifiers.isSet(Qt.KeyboardModifier.ControlModifier) &&
                modifiers.isSet(Qt.KeyboardModifier.ShiftModifier)) {
            if(e.key() == Qt.Key.Key_Z.value()) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean esCambiarSiguientePestana(QKeyEvent e) {
        KeyboardModifiers modifiers = e.modifiers();
        if(
                modifiers.isSet(Qt.KeyboardModifier.ControlModifier)) {
            if(e.key() == Qt.Key.Key_Tab.value()) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean esCambiarAnteriorPestana(QKeyEvent e) {
        KeyboardModifiers modifiers = e.modifiers();
        if(
                modifiers.isSet(Qt.KeyboardModifier.ControlModifier) &&
                modifiers.isSet(Qt.KeyboardModifier.ShiftModifier)) {
            if(e.key() == Qt.Key.Key_Backtab.value()) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean esTabPestana(QKeyEvent e) {
        if(e.key() == Qt.Key.Key_Tab.value()) {
            return true;
        }
        return false;
    }
    
    private void insertarTab() {
        if(textCursor().hasSelection()) {
            inserTabSelection();
        } else {
            insertTabSimple();
        }
    }
    
    private void insertTabSimple() {
        int caracteresPrevios = textCursor().position() -
                textCursor().block().position();
        int numSpacesAppend = NUM_SPACES_INDENT - caracteresPrevios % NUM_SPACES_INDENT;
        String tab = getTabIndentParcial(numSpacesAppend);
        insertPlainText(tab);
    }
    
    private void inserTabSelection() {
        IndentacionHolder indentacionHolder = new IndentacionHolder();
        indentacionHolder.curs = textCursor();
        
        obtenemosFilasIndentar(indentacionHolder);
        indentamosFilas(indentacionHolder);
        seleccionamosTodasFilasIndentadas(indentacionHolder);
    }
    
    private void obtenemosFilasIndentar(IndentacionHolder indentacionHolder) {
        QTextCursor curs = indentacionHolder.curs;
        
        int posInicial =  curs.anchor();
        int posFinal = curs.position();

        if(posInicial > posFinal) {
            int hold = posInicial;
            posInicial = posFinal;
            posFinal = hold;
        }
        indentacionHolder.posInicial = posInicial;
        indentacionHolder.posFinal = posFinal;

        curs.setPosition(posInicial, QTextCursor.MoveMode.MoveAnchor);
        indentacionHolder.bloqueInical = curs.block().blockNumber();

        curs.setPosition(posFinal, QTextCursor.MoveMode.MoveAnchor);
        indentacionHolder.bloqueFinal = curs.block().blockNumber();
    }
    
    private void indentamosFilas(IndentacionHolder indentacionHolder) {
        QTextCursor curs = indentacionHolder.curs;
        
        curs.setPosition(indentacionHolder.posInicial,
                QTextCursor.MoveMode.MoveAnchor);
        curs.beginEditBlock();

        String tabIndent = getTabIndent();
        for(int i = 0; i <= (indentacionHolder.bloqueFinal - 
                indentacionHolder.bloqueInical); ++i) {
            curs.movePosition(QTextCursor.MoveOperation.StartOfBlock,
                    QTextCursor.MoveMode.MoveAnchor);

            curs.insertText(tabIndent);

            curs.movePosition(QTextCursor.MoveOperation.NextBlock,
                    QTextCursor.MoveMode.MoveAnchor);
        }

        curs.endEditBlock();
    }
    
    private void seleccionamosTodasFilasIndentadas(
            IndentacionHolder indentacionHolder) {
        QTextCursor curs = indentacionHolder.curs;
        
        curs.setPosition(indentacionHolder.posInicial,
                QTextCursor.MoveMode.MoveAnchor);
        curs.movePosition(QTextCursor.MoveOperation.StartOfBlock, 
                QTextCursor.MoveMode.MoveAnchor);
        
        while(curs.block().blockNumber() < indentacionHolder.bloqueFinal) {
            curs.movePosition(QTextCursor.MoveOperation.NextBlock,
                    QTextCursor.MoveMode.KeepAnchor);
        }

        curs.movePosition(QTextCursor.MoveOperation.EndOfBlock,
                QTextCursor.MoveMode.KeepAnchor);

        setTextCursor(curs);
    }
    
    public String getTabIndent() {
        String tabIndent = "";
        for(int i=0; i<NUM_SPACES_INDENT; i++) {
            tabIndent += " ";
        }
        return tabIndent;
    }
    
    public String getTabIndentParcial(int caracteresPrevios) {
        int numSpacesAppend = NUM_SPACES_INDENT - caracteresPrevios % NUM_SPACES_INDENT;
        String indentTab = "";
        for(int i=0; i<numSpacesAppend; i++) {
            indentTab += " ";
        }
        return indentTab;
    }

    public void establecerEditorVisible() {
        controlador.establecerEditorVisible();
    }
}
