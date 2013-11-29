package com.stylishdb.qt.controllers;

import com.trolltech.qt.core.Qt;
import com.stylishdb.style.GetStyle;
import com.stylishdb.domain.MTab;
import com.stylishdb.qt.modals.ModalRenameTab;
import com.stylishdb.utilities.CoordenatesWindow;

/**
 *
 ** @author StylishDB
 */
public class CModalRenameTab extends Controller {
    
    private MTab mPestana;
    
    private ModalRenameTab modalRenombrar;
    
    public CModalRenameTab() {
        super();
    }
    
    public void mostrarRenombrarPestanaActiva() {
        if(pestanasAbiertas.hayPestanaActiva()) {
            modalRenombrar = new ModalRenameTab(this);        
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
    
    public MTab obtenerPestanaActiva() {
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
                CoordenatesWindow.getXCentrada(width),
                CoordenatesWindow.getYCentradaArriba()
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
                GetStyle.getEstiloVentana("dialogEstilo.css")
        );
    }
}
