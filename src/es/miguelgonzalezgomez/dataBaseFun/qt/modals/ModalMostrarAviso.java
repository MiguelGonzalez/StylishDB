package es.miguelgonzalezgomez.dataBaseFun.qt.modals;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QDialog;
import com.trolltech.qt.gui.QLabel;
import com.trolltech.qt.gui.QTextEdit;
import com.trolltech.qt.gui.QVBoxLayout;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.utilidadesEstaticas.CentroCoordenadas;

/**
 *
 * @author Miguel
 */
public class ModalMostrarAviso extends QDialog {
    
    private QLabel labelTituloMensaje;
    private QTextEdit editContenidoMensaje;
    
    private String tituloMensaje;
    private String contenidoMensaje;
    
    public static void mostrarErrorEnPantalla(String tituloError,
            String mensajeError) {
        ModalMostrarAviso modalError = new ModalMostrarAviso(tituloError,
                mensajeError);
        modalError.construirInterfaz();
        modalError.posicionarVentanaModal();
        
        modalError.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("dialogMostrarAviso.css")
        );
        
        modalError.show();
    }
    
    public ModalMostrarAviso(String tituloMensaje, String contenidoMensaje) {
        setModal(true);
        
        this.tituloMensaje = tituloMensaje;
        this.contenidoMensaje = contenidoMensaje;
    }
    
    public void construirInterfaz() {
        crearComponentesInterfaz();
        posicionarComponentesInterfaz();
        setWindowFlags(Qt.WindowType.FramelessWindowHint);
    }
    
    private void crearComponentesInterfaz() {
        labelTituloMensaje = new QLabel(tituloMensaje);
        labelTituloMensaje.setProperty("tituloMensaje", true);
        
        editContenidoMensaje = new QTextEdit(contenidoMensaje);
        editContenidoMensaje.setLineWrapColumnOrWidth(80);
        editContenidoMensaje.setLineWrapMode(QTextEdit.LineWrapMode.FixedColumnWidth);
        editContenidoMensaje.setReadOnly(true);
        editContenidoMensaje.setProperty("contenidoMensaje", true);
    }
    
    private void posicionarComponentesInterfaz() {
        QVBoxLayout ventanaLayout = new QVBoxLayout();
        ventanaLayout.setMargin(20);
        
        ventanaLayout.addWidget(labelTituloMensaje);
        ventanaLayout.addWidget(editContenidoMensaje);
        
        setLayout(ventanaLayout);
    }
    
    public void posicionarVentanaModal() {
        int width = 500;
        int height = 50;
        resize(
            width, height
        );
        move(
            CentroCoordenadas.getXCentrada(width),
            CentroCoordenadas.getYCentradaArriba()
            );
    }
}
