package es.miguelgonzalezgomez.dataBaseFun.qt.modals;

import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QVBoxLayout;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CNuevoEditor;

/**
 *
 * @author Miguel González
 */
public class ModalCrearNuevoEditor extends QDialog {
    
    private QLabel conexionLabel;
    
    public QComboBox conexionCombo;
    
    private QPushButton crearButton;
    private QPushButton cancelarButton;
    
    private CNuevoEditor controlador;
    
    public ModalCrearNuevoEditor(CNuevoEditor controlador) {
        this.controlador = controlador;
        
        setWindowTitle(tr(tr("Nuevo editor")));
        setModal(true);      
    }
    
    public void construirYLanzarInterfaz() {
        crearComponentesInterfaz();
        establecerEventosInterfaz();
        posicionarComponentesInterfaz();
    }
    
    private void crearComponentesInterfaz() {
        conexionLabel = new QLabel(tr("Conexión"));
        conexionCombo = new QComboBox();
        
        cancelarButton = new QPushButton(tr("Cancelar"));
        crearButton = new QPushButton(tr("Crear editor"));
    }
    
    private void establecerEventosInterfaz() {
        cancelarButton.clicked.connect(controlador, "eventoCancelar()");
        crearButton.clicked.connect(controlador, "eventoCrearEditor()");
    }
    
    private void posicionarComponentesInterfaz() {
        QVBoxLayout ventanaLayout = new QVBoxLayout();
        ventanaLayout.setMargin(10);
        
        ventanaLayout.addLayout(
                getLayoutConexion()
        );
        
        ventanaLayout.addLayout(
                getLayoutAccionesConexion()
        );
        
        setLayout(ventanaLayout);
    }
    
    private QGridLayout getLayoutConexion() {
        QGridLayout datosConexionGrid = new QGridLayout();
        
        datosConexionGrid.addWidget(conexionLabel, 0, 0);
        datosConexionGrid.addWidget(conexionCombo, 0, 1);
        
        return datosConexionGrid;
    }
    
    private QHBoxLayout getLayoutAccionesConexion() {
        QHBoxLayout accionesConexionHorizontal = new QHBoxLayout();
        
        accionesConexionHorizontal.addWidget(cancelarButton);
        accionesConexionHorizontal.addWidget(crearButton);
        
        return accionesConexionHorizontal;
    }
    
}
