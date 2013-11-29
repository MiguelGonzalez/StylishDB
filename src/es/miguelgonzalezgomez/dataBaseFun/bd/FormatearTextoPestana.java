package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.bd.formatter.Formmater;
import es.miguelgonzalezgomez.dataBaseFun.domain.MPestana;

/**
 *
 * @author Miguel González
 */
public class FormatearTextoPestana {

    public void pestana(MPestana pestanaActiva) {
        String textoSQL = pestanaActiva.getTextoEditor();
        
        Formmater formatter = new Formmater();
        textoSQL = formatter.format(textoSQL);
        
        pestanaActiva.setTextoEditor(textoSQL);
        pestanaActiva.notificarTextoFormateado();
    }
}
