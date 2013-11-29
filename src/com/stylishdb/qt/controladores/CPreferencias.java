package com.stylishdb.qt.controladores;

import com.trolltech.qt.core.Qt;
import com.stylishdb.estilos.ObtencionEstilo;
import com.stylishdb.idiomas.CargaIdioma;
import com.stylishdb.qt.modals.ModalPreferencias;
import com.stylishdb.utilidadesEstaticas.CentroCoordenadas;

/**
 *
 * @author Miguel
 */
public class CPreferencias extends CMiControladorGenerico {
    
    private ModalPreferencias modalPreferencias;
    
    public CPreferencias() {
        super();
        
        crearVentanaModal();
        
        posicionarVentanaModal();
        
        establecerEstiloVentana();
    }
    
    public void mostrarVentanaModal() {
        modalPreferencias.construirInterfaz(
                CargaIdioma.obtenerIdiomasInstalados()
                );
        
        modalPreferencias.show();
    }
    
    private void crearVentanaModal() {
        modalPreferencias = new ModalPreferencias(
                this);
        modalPreferencias.setWindowFlags(Qt.WindowType.FramelessWindowHint);
    }
    
    private void posicionarVentanaModal() {
        int width = 275;
        int height = 150;
        modalPreferencias.resize(
                width, height
                );
        modalPreferencias.move(
                CentroCoordenadas.getXCentrada(width),
                CentroCoordenadas.getYCentradaArriba()
                );
    }
    
    private void establecerEstiloVentana() {
        modalPreferencias.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("dialogEstilo.css")
        );
    }
    
    protected void eventoGuardarCambios() {
        cargarYGuardarIdiomaEstablecido();
        
        modalPreferencias.close();
    }
    
    protected void eventoCancelarCambios() {
        modalPreferencias.close();
    }
    
    private void cargarYGuardarIdiomaEstablecido() {
        int indexIdioma = modalPreferencias.idiomasCombo.currentIndex();
        if(indexIdioma != -1) {
            String idioma = modalPreferencias.idiomasCombo.itemText(indexIdioma);
            
            CargaIdioma.cargarIdioma(idioma);
            CargaIdioma.guardarPreferenciasIdioma();
        }
    }
}
