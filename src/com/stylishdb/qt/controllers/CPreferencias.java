package com.stylishdb.qt.controllers;

import com.trolltech.qt.core.Qt;
import com.stylishdb.style.GetStyle;
import com.stylishdb.languages.LoadLanguage;
import com.stylishdb.qt.modals.ModalPreferences;
import com.stylishdb.utilities.CoordenatesWindow;

/**
 *
 * @author Miguel
 */
public class CPreferencias extends Controller {
    
    private ModalPreferences modalPreferencias;
    
    public CPreferencias() {
        super();
        
        crearVentanaModal();
        
        posicionarVentanaModal();
        
        establecerEstiloVentana();
    }
    
    public void mostrarVentanaModal() {
        modalPreferencias.construirInterfaz(
                LoadLanguage.obtenerIdiomasInstalados()
                );
        
        modalPreferencias.show();
    }
    
    private void crearVentanaModal() {
        modalPreferencias = new ModalPreferences(
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
                CoordenatesWindow.getXCentrada(width),
                CoordenatesWindow.getYCentradaArriba()
                );
    }
    
    private void establecerEstiloVentana() {
        modalPreferencias.setStyleSheet(
                GetStyle.getEstiloVentana("dialogEstilo.css")
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
            
            LoadLanguage.cargarIdioma(idioma);
            LoadLanguage.guardarPreferenciasIdioma();
        }
    }
}
