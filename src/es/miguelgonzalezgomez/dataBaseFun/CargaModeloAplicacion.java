package es.miguelgonzalezgomez.dataBaseFun;

import com.google.gson.reflect.TypeToken;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GConexionesAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.configuracion.ConfiguracionAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author Miguel González
 */
public class CargaModeloAplicacion {
    private static String nombreFicheroPreferencias = "preferenciasAplicacion";
    private static Preferencias prefs;
    
    private static ConfiguracionAplicacion configuracionAplicacion;
    
    public static void cargarModeloAplicacion() {
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
        GConexionesAplicacion conexionesAplicacion = new 
                GConexionesAplicacion();
        
        for(MConexion conexion : configuracionAplicacion.conexiones) {
            conexionesAplicacion.addNuevaConexion(conexion);
        }
    }
    
    private static void cargarEditores() {
        GEditoresAplicacion editoresAplicacion = new
                GEditoresAplicacion();
        
        for(MPestanaEditor editor : configuracionAplicacion.editores) {
            editoresAplicacion.addNuevaPestanaEditor(editor);
        }
    }
}
