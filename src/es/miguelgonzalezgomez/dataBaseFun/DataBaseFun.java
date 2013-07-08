package es.miguelgonzalezgomez.dataBaseFun;

import com.trolltech.qt.core.QTextCodec;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CVentanaPrincipal;
import com.trolltech.qt.gui.QApplication;
import es.miguelgonzalezgomez.dataBaseFun.idiomas.CargaIdioma;

/**
 *
 * @author Miguel González
 */
public class DataBaseFun {

    private static CVentanaPrincipal controladorVentanaPrincipal;
    
    public static void main(String args[]) {
        QApplication.initialize(args);
        QTextCodec.setCodecForCStrings(QTextCodec.codecForName("UTF-8"));
        QTextCodec.setCodecForLocale(QTextCodec.codecForName("UTF-8"));

        CargaIdioma.cargarIdiomaDefecto();
        
        controladorVentanaPrincipal = new
                CVentanaPrincipal();
        
        QApplication.exec();
    }
    
    public static void salirAplicacion() {
        controladorVentanaPrincipal.salirAplicacion();
    }
}
