package com.stylishdb.languages;

import com.google.gson.reflect.TypeToken;
import com.trolltech.qt.core.QTranslator;
import com.trolltech.qt.gui.QApplication;
import com.stylishdb.configuration.LanguageConfiguration;
import com.stylishdb.Preferences;
import com.stylishdb.PreferencesException;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Type;

/**
 *
 ** @author StylishDB
 */
public class LoadLanguage {
    
    private static Preferences preferencias;
    private static LanguageConfiguration configuracionIdioma;
    
    private static String idiomaActual;
    
    static {
        preferencias = Preferences.getInstance();
        cargarPreferenciasIdioma();
    }
    
    public static String[] obtenerIdiomasInstalados() {
        String rutaIdiomasInstalados = preferencias.
                getPathToFolderPreferencias("idiomas");
        File carpetaIdiomas = new File(rutaIdiomasInstalados);
        
        return obtenerIdiomasDirectorio(carpetaIdiomas);
    }

    private static String[] obtenerIdiomasDirectorio(File directorio) {
        File[] fichIdiomas = obtenerFicherosIdiomasDirectorio(directorio);
        String []idiomas = new String[fichIdiomas.length];
        int numIdioma = 0;
        for(File fichIdioma : fichIdiomas) {
            idiomas[numIdioma++] = fichIdioma.getName();
        }
        
        return idiomas;
    }
    
    private static File[] obtenerFicherosIdiomasDirectorio(File directorio) {
        return directorio.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".qm");
            }
        });
    }
    
    public static void cargarIdioma(String idioma) {
        QTranslator traductor = new QTranslator();
        
        String rutaIdiomasInstalados = preferencias.
                getPathToFolderPreferencias("idiomas");
        
        if(traductor.load(rutaIdiomasInstalados + idioma)) {
            idiomaActual = idioma;
            
            QApplication.instance().installTranslator(traductor);
        }
    }
    
    public static void cargarIdiomaDefecto() {
        cargarIdioma(configuracionIdioma.idioma);
    }
    
    public static void guardarPreferenciasIdioma() {
        configuracionIdioma.idioma = idiomaActual;
        
        Type fooType = new TypeToken<LanguageConfiguration>() {}.getType();
        preferencias.guardarConfiguracion(
                fooType,
                configuracionIdioma,
                "confIdioma");
    }
    
    private static void cargarPreferenciasIdioma() {
        try {
            Type fooType = new TypeToken<LanguageConfiguration>() {}.getType();
            configuracionIdioma = preferencias.getConfiguracion(
                    fooType,
                    "confIdioma");
        } catch (PreferencesException ex) {
            cargarConfiguracionPreferenciasIdiomaDefecto();
       
        }
    }
    
    private static void cargarConfiguracionPreferenciasIdiomaDefecto() {
        configuracionIdioma = new LanguageConfiguration();
        
        // ToDo: Idiomas por locale, así se carga el Locale del S.O.
        String []idiomas = obtenerIdiomasInstalados();
        if(idiomas.length > 0) {
            configuracionIdioma.idioma = idiomas[0];
        }
    }

}
