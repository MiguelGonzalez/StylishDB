package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.idiomas.CargaIdioma;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalPreferencias;

/**
 *
 * @author Miguel
 */
public class CPreferencias {
    
    private ModalPreferencias modalPreferencias;
    
    public CPreferencias() {
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
    }
    
    private void posicionarVentanaModal() {
        modalPreferencias.resize(
                250, 150
                );
        modalPreferencias.move(
                100,100
                );
    }
    
    private void establecerEstiloVentana() {
        modalPreferencias.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("preferencias.css")
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
