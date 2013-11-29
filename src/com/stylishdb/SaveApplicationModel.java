package com.stylishdb;

import com.google.gson.reflect.TypeToken;
import com.stylishdb.configuration.AppConfiguration;
import com.stylishdb.domain.MApplication;
import java.lang.reflect.Type;

/**
 *
 * @author StylishDB
 */
public class SaveApplicationModel {
    private static String nombreFicheroPreferencias = "preferenciasAplicacion";
    private static Preferences prefs;
    
    private static AppConfiguration configuracionAplicacion;
    private static MApplication mAplicacion;
    
    static {
        configuracionAplicacion = new AppConfiguration();
    }
    
    public static void guardarModeloAplicacion() {
        mAplicacion = MApplication.getInstance();
        prefs = Preferences.getInstance();
        
        guardarEstadoAplicacion();
        guardarEstadoEnFicheroJson();
    }
    
    private static void guardarEstadoAplicacion() {
        cargarEnConfiguracionConexiones();
        cargarEnConfiguracionEditores();
    }
    
    private static void cargarEnConfiguracionConexiones() {
        configuracionAplicacion.conexiones = mAplicacion.mConexionesGuardadas.
                getConexionesGuardadas();
    }
    
    private static void cargarEnConfiguracionEditores() {
        configuracionAplicacion.editores = mAplicacion.mPestanasEditorAbiertas.
                getPestanasAbiertas();
    }
    
    private static void guardarEstadoEnFicheroJson() {
        Type fooType = new TypeToken<AppConfiguration>() {}.getType();
        prefs.guardarConfiguracion(fooType,
                configuracionAplicacion,
                nombreFicheroPreferencias);
    }
}
