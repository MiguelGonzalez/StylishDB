package com.stylishdb.qt.modals;

import com.trolltech.qt.gui.QComboBox;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QHBoxLayout;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QLineEdit;
import com.trolltech.qt.gui.QMessageBox;
import com.trolltech.qt.gui.QPushButton;
import com.trolltech.qt.gui.QVBoxLayout;
import com.stylishdb.db.domain.TypeManagers;
import com.stylishdb.qt.controllers.CNewConnection;

/**
 *
 ** @author StylishDB
 */
public class ModalConnection extends QDialog {
    
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
    private QPushButton cancelarEditarButton;
    private QPushButton crearEditarButton;
    
    private CNewConnection controlador;
    
    public ModalConnection(
            CNewConnection controlador) {
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
    
    public void establecerTextoGuardarCambios() {
        crearEditarButton.setText(tr("Guardar cambios"));
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
        cancelarEditarButton = new QPushButton(tr("Cancelar"));
        crearEditarButton = new QPushButton(tr("Crear"));
    }
    
    private void establecerEventosInterfaz() {
        probarConexionButton.clicked.connect(controlador, "eventoProbarConexion()");
        cancelarEditarButton.clicked.connect(controlador, "eventoCancelarCrearEditarConexion()");
        crearEditarButton.clicked.connect(controlador, "eventoCrearEditarConexion()");
        
        nombreEdit.textChanged.connect(this, "nombreCambiado()");
        gestorCombo.currentIndexChanged.connect(this, "gestorCambiado()");
        sidEdit.textChanged.connect(this, "sidCambiado()");
        ipEdit.textChanged.connect(this, "ipCambiado()");
        puertoEdit.textChanged.connect(this, "puertoCambiado()");
        usuarioEdit.textChanged.connect(this, "usuarioCambiado()");
        passwordEdit.textChanged.connect(this, "passwordCambiado()");
    }
    
    private void cargarDatosInterfaz() {
        String []tiposBD = TypeManagers.getNombresBasesDatos();
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
        accionesConexionHorizontal.addWidget(cancelarEditarButton);
        accionesConexionHorizontal.addWidget(crearEditarButton);
        
        return accionesConexionHorizontal;
    }
    
    public void pintarErrorNombre() {
        nombreLabel.setProperty("pintarError", true);
    }
    
    public void pintarErrorGestor() {
        gestorLabel.setProperty("pintarError", true);
    }
    
    public void pintarErrorSid() {
        sidLabel.setProperty("pintarError", true);
    }
    
    public void pintarErrorIp() {
        ipLabel.setProperty("pintarError", true);
    }
    
    public void pintarErrorPuerto() {
        puertoLabel.setProperty("pintarError", true);
    }
    
    public void pintarErrorUsuario() {
        usuarioLabel.setProperty("pintarError", true);
    }
    
    public void pintarErrorPassword() {
        passwordLabel.setProperty("pintarError", true);
    }
    
    private void nombreCambiado() {
        nombreLabel.setProperty("pintarError", false);
    }
    private void gestorCambiado() {
        gestorLabel.setProperty("pintarError", false);
    }
    private void sidCambiado() {
        sidLabel.setProperty("pintarError", false);
    }
    private void ipCambiado() {
        ipLabel.setProperty("pintarError", false);
    }
    private void puertoCambiado() {
        puertoLabel.setProperty("pintarError", false);
    }
    private void usuarioCambiado() {
        usuarioLabel.setProperty("pintarError", false);
    }
    private void passwordCambiado() {
        passwordLabel.setProperty("pintarError", false);
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
