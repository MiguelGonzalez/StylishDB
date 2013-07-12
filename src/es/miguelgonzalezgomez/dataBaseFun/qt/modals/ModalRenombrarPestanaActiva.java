package es.miguelgonzalezgomez.dataBaseFun.qt.modals;

import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QVBoxLayout;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CRenombrarPestanaActiva;

/**
 *
 * @author Miguel González
 */
public class ModalRenombrarPestanaActiva extends QDialog {
    
    public QLineEdit nombreEdit;
    
    private QLabel nombreLabel;
    
    private QPushButton cancelarButton;
    private QPushButton aceptarButton;
    
    private CRenombrarPestanaActiva controlador;

    public ModalRenombrarPestanaActiva(CRenombrarPestanaActiva controlador) {
        this.controlador = controlador;
        
        setWindowTitle(tr(tr("Renombrar pestaña")));
        setModal(true);
    }
    
    public void construirInterfaz() {
        crearComponentesInterfaz();
        establecerEventosInterfaz();
        posicionarComponentesInterfaz();
    }
    
    private void crearComponentesInterfaz() {
        nombreEdit = new QLineEdit();
        nombreLabel = new QLabel(tr("Nombre"));
        
        cancelarButton = new QPushButton(tr("Cancelar"));
        aceptarButton = new QPushButton(tr("Aceptar"));
    }
    
    private void establecerEventosInterfaz() {
        cancelarButton.clicked.connect(controlador, "eventoCancelar()");
        aceptarButton.clicked.connect(controlador, "eventoAceptar()");
        
        nombreEdit.textChanged.connect(this, "nombreCambiado()");
    }
    
    
    private void posicionarComponentesInterfaz() {
        QVBoxLayout ventanaLayout = new QVBoxLayout();
        ventanaLayout.setMargin(10);
        
        ventanaLayout.addLayout(
                getLayoutDatosConexion()
        );
        
        ventanaLayout.addLayout(
                getLayoutAccionesConexion()
        );
        
        setLayout(ventanaLayout);
    }
    
    private QGridLayout getLayoutDatosConexion() {
        QGridLayout datoNombrePestana = new QGridLayout();
     
        datoNombrePestana.addWidget(nombreLabel, 0, 0);
        datoNombrePestana.addWidget(nombreEdit, 0, 1);
        
        return datoNombrePestana;
    }
    
    private QHBoxLayout getLayoutAccionesConexion() {
        QHBoxLayout accionCambioNombrePestana = new QHBoxLayout();
     
        accionCambioNombrePestana.addWidget(cancelarButton);
        accionCambioNombrePestana.addWidget(aceptarButton);
        
        return accionCambioNombrePestana;
    }
    
    public void pintarErrorNombre() {
        nombreLabel.setProperty("pintarError", true);
    }
    
    private void nombreCambiado() {
        nombreLabel.setProperty("pintarError", false);
    }
    
    
    
}
