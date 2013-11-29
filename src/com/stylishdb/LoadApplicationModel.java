package com.stylishdb;

import com.google.gson.reflect.TypeToken;
import com.stylishdb.configuration.AppConfiguration;
import com.stylishdb.domain.MApplication;
import com.stylishdb.domain.MConnection;
import com.stylishdb.domain.MTab;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author StylishDB
 */
public class LoadApplicationModel {
    private static String nombreFicheroPreferencias = "preferenciasAplicacion";
    private static Preferences prefs;
    private static MApplication mAplicacion;
    
    private static AppConfiguration configuracionAplicacion;
    
    public static void cargarModeloAplicacion() {
        mAplicacion = MApplication.getInstance();
        prefs = Preferences.getInstance();
        
        cargaConfiguracionAplicacion();
        
        cargarConfiguracionEnModelo();
    }
    
    private static void cargaConfiguracionAplicacion() {
        Type fooType = new TypeToken<AppConfiguration>() {}.getType();
        try {
            configuracionAplicacion = prefs.getConfiguracion(
                    fooType,
                    nombreFicheroPreferencias);
            
            comprobarConstruidosObjetos();
        } catch (PreferencesException ex) {
            ex.printStackTrace();
            cargarPropiedadesDefectoAplicacion();
        }
    }
    
    private static void comprobarConstruidosObjetos() {
        if(configuracionAplicacion.conexiones == null) {
            configuracionAplicacion.conexiones = new ArrayList<>();
        }
        if(configuracionAplicacion.editores == null) {
            configuracionAplicacion.editores = new ArrayList<>();
        }
    }
    
    private static void cargarPropiedadesDefectoAplicacion() {
        configuracionAplicacion = new AppConfiguration();
        configuracionAplicacion.conexiones = new ArrayList<>();
        configuracionAplicacion.editores = new ArrayList<>();
    }
    
    private static void cargarConfiguracionEnModelo() {
        cargarConexiones();
        cargarEditores();
    }
    
    private static void cargarConexiones() {
        for(MConnection conexion : configuracionAplicacion.conexiones) {
            mAplicacion.mConexionesGuardadas.addNuevaConexion(conexion);
        }
    }
    
    private static void cargarEditores() {
        for(MTab editor : configuracionAplicacion.editores) {
            mAplicacion.mPestanasEditorAbiertas.addPestana(editor);
        }
    }
}
