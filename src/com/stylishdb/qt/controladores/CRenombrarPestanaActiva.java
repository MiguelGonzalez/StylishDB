package com.stylishdb.qt.controladores;

import com.trolltech.qt.core.Qt;
import com.stylishdb.estilos.ObtencionEstilo;
import com.stylishdb.domain.MPestana;
import com.stylishdb.qt.modals.ModalRenombrarPestanaActiva;
import com.stylishdb.utilidadesEstaticas.CentroCoordenadas;

/**
 *
 ** @author StylishDB
 */
public class CRenombrarPestanaActiva extends CMiControladorGenerico {
    
    private MPestana mPestana;
    
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
    
    public MPestana obtenerPestanaActiva() {
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
        String nombrePestana = mPestana.getNombrePestana();
        modalRenombrar.nombreEdit.setText(nombrePestana);
        
        modalRenombrar.nombreEdit.setSelection(0,
                nombrePestana.length());
    }
    
    protected void eventoAceptar() {
        if(noTieneErroresFormulario()) {
            String nombrePestana = modalRenombrar.nombreEdit.text();
            mPestana.setNombrePestana(nombrePestana);
            
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
