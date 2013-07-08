package es.miguelgonzalezgomez.dataBaseFun.qt.modals;

import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QVBoxLayout;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CPreferencias;

/**
 *
 * @author Miguel González
 */
public class ModalPreferencias extends QDialog {

    public QComboBox idiomasCombo;
    
    private QLabel idiomasLabel;
    
    private QPushButton guardarCambiosButton;
    private QPushButton cancelarCambiosButton;
    
    private CPreferencias controlador;
    
    public ModalPreferencias(
            CPreferencias controlador) {
        this.controlador = controlador;
        
        setWindowTitle(tr(tr("Preferencias")));
        setModal(true);
    }
    
    public void construirInterfaz(String[] idiomas) {
        crearComponentesInterfaz();
        establecerEventosInterfaz();
        cargarDatosInterfaz(idiomas);
        posicionarComponentesInterfaz();
    }
    
    private void crearComponentesInterfaz() {
        idiomasCombo = new QComboBox();
        
        idiomasLabel = new QLabel(tr("Selecciona el idioma"));
        
        guardarCambiosButton = new QPushButton(tr("Guardar cambios"));
        cancelarCambiosButton = new QPushButton(tr("Cancelar cambios"));
    }
    
    private void establecerEventosInterfaz() {
        guardarCambiosButton.clicked.connect(controlador, "eventoGuardarCambios()");
        cancelarCambiosButton.clicked.connect(controlador, "eventoCancelarCambios()");
    }
    
    private void cargarDatosInterfaz(String []idiomas) {
        for(String idioma : idiomas) {
            idiomasCombo.addItem(idioma);
        }
    }
    
    private void posicionarComponentesInterfaz() {
        QVBoxLayout ventanaLayout = new QVBoxLayout();
        ventanaLayout.setMargin(10);
        
        ventanaLayout.addLayout(
                getLayoutDatosPreferencias()
        );
        
        ventanaLayout.addLayout(
                getLayoutAccionesPreferencias()
        );
        
        setLayout(ventanaLayout);
    }
    
    private QGridLayout getLayoutDatosPreferencias() {
        QGridLayout datosPreferencias = new QGridLayout();
        
        datosPreferencias.addWidget(idiomasLabel, 0, 0);
        datosPreferencias.addWidget(idiomasCombo, 0, 1);
        
        return datosPreferencias;
    }
    
    private QHBoxLayout getLayoutAccionesPreferencias() {
        QHBoxLayout accionePreferenciasHorizontal = new QHBoxLayout();
        
        accionePreferenciasHorizontal.addWidget(guardarCambiosButton);
        accionePreferenciasHorizontal.addWidget(cancelarCambiosButton);
        
        return accionePreferenciasHorizontal;
    }
}
