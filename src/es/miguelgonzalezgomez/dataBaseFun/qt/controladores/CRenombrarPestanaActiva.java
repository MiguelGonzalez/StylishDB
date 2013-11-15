package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.core.Qt;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalRenombrarPestanaActiva;
import es.miguelgonzalezgomez.dataBaseFun.utilidadesEstaticas.CentroCoordenadas;

/**
 *
 * @author Miguel González
 */
public class CRenombrarPestanaActiva extends CMiControladorGenerico {
    
    private MPestanaEditor mPestana;
    
    private ModalRenombrarPestanaActiva modalRenombrar;
    
    public CRenombrarPestanaActiva() {
        super();
    }
    
    public void mostrarRenombrarPestanaActiva() {
        if(pestanasAbiertas.hayPestanaActiva()) {
            modalRenombrar = new ModalRenombrarPestanaActiva(this);        
            mPestana = obtenerPestanaActiva();
            
            cargarDisenoVentanaModal();
            mostrarVentanaModal();
        }
    }
    
    private void cargarDisenoVentanaModal() {
        modalRenombrar.construirInterfaz();
        establecerDisenoInterfaz();
        posicionarVentanaModal();
        establecerNombrePestana();
    }
    
    private void mostrarVentanaModal() {
        modalRenombrar.show();
    }
    
    public MPestanaEditor obtenerPestanaActiva() {
        return pestanasAbiertas.getPestanaActiva();
    }
    
    private void establecerDisenoInterfaz() {
        modalRenombrar.setWindowFlags(Qt.WindowType.FramelessWindowHint);
        recargarEstiloModal();
    }
    
    private void posicionarVentanaModal() {
        int width = 300;
        int height = 60;
        modalRenombrar.resize(
                width, height
                );
        modalRenombrar.move(
                CentroCoordenadas.getXCentrada(width),
                CentroCoordenadas.getYCentradaArriba()
                );
    }
    
    private void establecerNombrePestana() {
        modalRenombrar.nombreEdit.setText(mPestana.nombrePestana);
        
        modalRenombrar.nombreEdit.setSelection(0,
                mPestana.nombrePestana.length());
    }
    
    protected void eventoAceptar() {
        if(noTieneErroresFormulario()) {
            mPestana.nombrePestana = modalRenombrar.nombreEdit.text();
            pestanasAbiertas.editadaPestanaEditor(mPestana);
            
            modalRenombrar.close();
        }
    }
    
    private boolean noTieneErroresFormulario() {
        if(modalRenombrar.nombreEdit.text().isEmpty()) {
            modalRenombrar.pintarErrorNombre();
            
            return false;
        }
        
        recargarEstiloModal();
        
        return true;
    }
    
    protected void recargarEstiloModal() {
        modalRenombrar.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("dialogEstilo.css")
        );
    }
}
