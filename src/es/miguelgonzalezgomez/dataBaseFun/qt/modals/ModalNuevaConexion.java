package es.miguelgonzalezgomez.dataBaseFun.qt.modals;

import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QVBoxLayout;
import es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.TiposBasesDeDatos;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CNuevaConexion;

/**
 *
 * @author Miguel González
 */
public class ModalNuevaConexion extends QDialog {
    
    public QLineEdit nombreEdit;
    public QComboBox gestorCombo;
    public QLineEdit sidEdit;
    public QLineEdit ipEdit;
    public QLineEdit puertoEdit;
    public QLineEdit usuarioEdit;
    public QLineEdit passwordEdit;
    
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
    
    public ModalNuevaConexion(
            CNuevaConexion controlador) {
        this.controlador = controlador;
        
        setWindowTitle(tr(tr("Nueva conexión")));
        setModal(true);
    }
    
    public void construirInterfaz() {
        crearComponentesInterfaz();
        establecerEventosInterfaz();
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
        passwordEdit = new QLineEdit();
        
        nombreLabel = new QLabel(tr("Nombre"));
        gestorLabel = new QLabel(tr("Gestor BBDD"));
        sidLabel = new QLabel(tr("SID"));
        ipLabel = new QLabel(tr("Ip"));
        puertoLabel = new QLabel(tr("Puerto"));
        usuarioLabel = new QLabel(tr("Usuario"));
        passwordLabel = new QLabel(tr("Contraseña"));
        
        probarConexionButton = new QPushButton(tr("Probar conexión"));        
        cancelarButton = new QPushButton(tr("Cancelar"));
        crearButton = new QPushButton(tr("Crear"));
    }
    
    private void establecerEventosInterfaz() {
        probarConexionButton.clicked.connect(controlador, "eventoProbarConexion()");
        cancelarButton.clicked.connect(controlador, "eventoCancelarCrearConexion()");
        crearButton.clicked.connect(controlador, "eventoCrearConexion()");
        
        nombreEdit.textChanged.connect(this, "nombreCambiado()");
        gestorCombo.currentIndexChanged.connect(this, "gestorCambiado()");
        sidEdit.textChanged.connect(this, "sidCambiado()");
        ipEdit.textChanged.connect(this, "ipCambiado()");
        puertoEdit.textChanged.connect(this, "puertoCambiado()");
        usuarioEdit.textChanged.connect(this, "usuarioCambiado()");
        passwordEdit.textChanged.connect(this, "passwordCambiado()");
    }
    
    private void nombreCambiado() {
        nombreEdit.setStyleSheet("background-color: transparent");
    }
    private void gestorCambiado() {
        gestorCombo.setStyleSheet("background-color: transparent");
    }
    private void sidCambiado() {
        sidEdit.setStyleSheet("background-color: transparent");
    }
    private void ipCambiado() {
        ipEdit.setStyleSheet("background-color: transparent");
    }
    private void puertoCambiado() {
        puertoEdit.setStyleSheet("background-color: transparent");
    }
    private void usuarioCambiado() {
        usuarioEdit.setStyleSheet("background-color: transparent");
    }
    private void passwordCambiado() {
        passwordEdit.setStyleSheet("background-color: transparent");
    }
    
    private void cargarDatosInterfaz() {
        String []tiposBD = TiposBasesDeDatos.getNombresBasesDatos();
        for(String tipoBD : tiposBD) {
            gestorCombo.addItem(tipoBD);
        }
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
    
    private String styleSheetError = "background: #FA8072;";
    public void pintarErrorNombre() {
        nombreEdit.setStyleSheet(styleSheetError);
    }
    
    public void pintarErrorGestor() {
        gestorCombo.setStyleSheet(styleSheetError);
    }
    
    public void pintarErrorSid() {
        sidEdit.setStyleSheet(styleSheetError);
    }
    
    public void pintarErrorIp() {
        ipEdit.setStyleSheet(styleSheetError);
    }
    
    public void pintarErrorPuerto() {
        puertoEdit.setStyleSheet(styleSheetError);
    }
    
    public void pintarErrorUsuario() {
        usuarioEdit.setStyleSheet(styleSheetError);
    }
    
    public void pintarErrorPassword() {
        passwordEdit.setStyleSheet(styleSheetError);
    }
    
    public void mostrarAvisoConexionEstablecida() {
        QMessageBox.information(this,
                tr("Test conexión"),
                tr("Conexión establecida"));
    }
    
    public void mostrarAvisoNoSePudoEstablecerConexion() {
        QMessageBox.warning(this,
                tr("Test conexión"),
                tr("No se pudo establecer la conexión"));
    }

    public void mostrarAvisoNombreConexionDuplicado() {
        QMessageBox.critical(this, tr("Error al crear la conexión"),
                  tr("Ya existe una conexión creada con el mismo nombre"));
    }
    
}
