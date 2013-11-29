package com.stylishdb;

import com.google.gson.reflect.TypeToken;
import com.stylishdb.configuration.ConfiguracionAplicacion;
import com.stylishdb.domain.MAplicacion;
import com.stylishdb.domain.MConexion;
import com.stylishdb.domain.MPestana;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author StylishDB
 */
public class CargaModeloAplicacion {
    private static String nombreFicheroPreferencias = "preferenciasAplicacion";
    private static Preferencias prefs;
    private static MAplicacion mAplicacion;
    
    private static ConfiguracionAplicacion configuracionAplicacion;
    
    public static void cargarModeloAplicacion() {
        mAplicacion = MAplicacion.getInstance();
        prefs = Preferencias.getInstance();
        
        cargaConfiguracionAplicacion();
        
        cargarConfiguracionEnModelo();
    }
    
    private static void cargaConfiguracionAplicacion() {
        Type fooType = new TypeToken<ConfiguracionAplicacion>() {}.getType();
        try {
            configuracionAplicacion = prefs.getConfiguracion(
                    fooType,
                    nombreFicheroPreferencias);
            
            comprobarConstruidosObjetos();
        } catch (PreferenciasException ex) {
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
        configuracionAplicacion = new ConfiguracionAplicacion();
        configuracionAplicacion.conexiones = new ArrayList<>();
        configuracionAplicacion.editores = new ArrayList<>();
    }
    
    private static void cargarConfiguracionEnModelo() {
        cargarConexiones();
        cargarEditores();
    }
    
    private static void cargarConexiones() {
        for(MConexion conexion : configuracionAplicacion.conexiones) {
            mAplicacion.mConexionesGuardadas.addNuevaConexion(conexion);
        }
    }
    
    private static void cargarEditores() {
        for(MPestana editor : configuracionAplicacion.editores) {
            mAplicacion.mPestanasEditorAbiertas.addPestana(editor);
        }
    }
}
