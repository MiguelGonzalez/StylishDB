package es.miguelgonzalezgomez.dataBaseFun;

import com.google.gson.reflect.TypeToken;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GConexionesAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.configuracion.ConfiguracionAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GEditoresAplicacion;
import java.lang.reflect.Type;

/**
 *
 * @author Miguel González
 */
public class GuardaModeloAplicacion {
    private static String nombreFicheroPreferencias = "preferenciasAplicacion";
    private static Preferencias prefs;
    
    private static ConfiguracionAplicacion configuracionAplicacion;
    
    static {
        configuracionAplicacion = new ConfiguracionAplicacion();
    }
    
    public static void guardarModeloAplicacion() {
        prefs = Preferencias.getInstance();
        
        guardarEstadoAplicacion();
        guardarEstadoEnFicheroJson();
    }
    
    private static void guardarEstadoAplicacion() {
        cargarEnConfiguracionConexiones();
        cargarEnConfiguracionEditores();
    }
    
    private static void cargarEnConfiguracionConexiones() {
        GConexionesAplicacion conexionesAplicacion = new 
                GConexionesAplicacion();
        configuracionAplicacion.conexiones = conexionesAplicacion.getConexiones();
    }
    
    private static void cargarEnConfiguracionEditores() {
        GEditoresAplicacion editoresAplicacion = new
                GEditoresAplicacion();
        
        configuracionAplicacion.editores = editoresAplicacion.
                getPestanasEditores();
    }
    
    private static void guardarEstadoEnFicheroJson() {
        Type fooType = new TypeToken<ConfiguracionAplicacion>() {}.getType();
        prefs.guardarConfiguracion(fooType,
                configuracionAplicacion,
                nombreFicheroPreferencias);
    }
}
