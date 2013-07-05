package es.miguelgonzalezgomez.DataBaseFun.qt.modals;

import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QVBoxLayout;
import es.miguelgonzalezgomez.DataBaseFun.Controladores.CNuevaConexion;

/**
 *
 * @author Miguel González
 */
public class ModalNuevaConexion extends QDialog {
    
    private QLineEdit nombreEdit;
    private QComboBox gestorCombo;
    private QLineEdit sidEdit;
    private QLineEdit ipEdit;
    private QLineEdit puertoEdit;
    private QLineEdit usuarioEdit;
    private QLineEdit passwordEdit;
    
    private QLabel nombreLabel;
    private QLabel gestorLabel;
    private QLabel sidLabel;
    private QLabel ipLabel;
    private QLabel puertoLabel;
    private QLabel usuarioLabel;
    private QLabel passwordLabel;
    
    private QPushButton probarConexionButton;
    private QPushButton cancelarButton;
    private QPushButton crearButton;
    
    private CNuevaConexion controlador;
    
    public ModalNuevaConexion(String tituloVentana,
            CNuevaConexion controlador) {
        this.controlador = controlador;
        
        setWindowTitle(tr(tituloVentana));
        setModal(true);
    }
    
    public void construirInterfaz() {
        crearComponentesInterfaz();
        establecerEventosInterfaz();
        establecerBuddiesInterfaz();
        cargarDatosInterfaz();
        posicionarComponentesInterfaz();
    }
    
    private void crearComponentesInterfaz() {
        nombreEdit = new QLineEdit();
        gestorCombo = new QComboBox();
        sidEdit = new QLineEdit();
        ipEdit = new QLineEdit();
        puertoEdit = new QLineEdit();
        usuarioEdit = new QLineEdit();
        
        nombreLabel = new QLabel(tr("Nombre"));
        gestorLabel = new QLabel(tr("Gestor BBDD"));
        sidLabel = new QLabel(tr("SID"));
        ipLabel = new QLabel(tr("Ip"));
        puertoLabel = new QLabel("Puerto");
        usuarioLabel = new QLabel("Usuario");
        passwordLabel = new QLabel("Contraseña");
        
        probarConexionButton = new QPushButton(tr("Probar conexión"));        
        cancelarButton = new QPushButton(tr("Cancelar"));
        crearButton = new QPushButton(tr("Crear"));
    }
    
    public void establecerBuddiesInterfaz() {
        passwordEdit = new QLineEdit();
        nombreLabel.setBuddy(nombreEdit);
        gestorLabel.setBuddy(gestorCombo);
        sidLabel.setBuddy(sidEdit);
        ipLabel.setBuddy(ipEdit);
        puertoLabel.setBuddy(puertoEdit);
        usuarioLabel.setBuddy(usuarioEdit);
        passwordLabel.setBuddy(passwordEdit);
    }
    
    public void establecerEventosInterfaz() {
        probarConexionButton.clicked.connect(controlador, "eventoProbarConexion()");
        cancelarButton.clicked.connect(controlador, "eventoCancelarCrearConexion()");
        probarConexionButton.clicked.connect(controlador, "eventoCrearConexion()");
    }
    
    public void cargarDatosInterfaz() {
        gestorCombo.addItem(tr("MySQL"));
        gestorCombo.addItem(tr("Oracle"));
        gestorCombo.addItem(tr("SQL"));
    }
    
    public void posicionarComponentesInterfaz() {
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
        QGridLayout datosConexionGrid = new QGridLayout();
        
        datosConexionGrid.addWidget(nombreLabel, 0, 0);
        datosConexionGrid.addWidget(nombreEdit, 0, 1);
        
        datosConexionGrid.addWidget(gestorLabel, 1, 0);
        datosConexionGrid.addWidget(gestorCombo, 1, 1);
        
        datosConexionGrid.addWidget(sidLabel, 2, 0);
        datosConexionGrid.addWidget(sidEdit, 2, 1);
        
        datosConexionGrid.addWidget(ipLabel, 3, 0);
        datosConexionGrid.addWidget(ipEdit, 3, 1);
        
        datosConexionGrid.addWidget(puertoLabel, 4, 0);
        datosConexionGrid.addWidget(puertoEdit, 4, 1);
        
        datosConexionGrid.addWidget(usuarioLabel, 5, 0);
        datosConexionGrid.addWidget(usuarioEdit, 5, 1);
        
        datosConexionGrid.addWidget(passwordLabel, 6, 0);
        datosConexionGrid.addWidget(passwordEdit, 6, 1);
        
        return datosConexionGrid;
    }
    
    private QHBoxLayout getLayoutAccionesConexion() {
        QHBoxLayout accionesConexionHorizontal = new QHBoxLayout();
        
        accionesConexionHorizontal.addWidget(probarConexionButton);
        accionesConexionHorizontal.addWidget(cancelarButton);
        accionesConexionHorizontal.addWidget(crearButton);
        
        return accionesConexionHorizontal;
    }
    
}
