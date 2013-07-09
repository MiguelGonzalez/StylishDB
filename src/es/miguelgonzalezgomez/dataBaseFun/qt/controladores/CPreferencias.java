package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.idiomas.CargaIdioma;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalPreferencias;
import es.miguelgonzalezgomez.dataBaseFun.utilidadesEstaticas.CentroCoordenadas;

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
