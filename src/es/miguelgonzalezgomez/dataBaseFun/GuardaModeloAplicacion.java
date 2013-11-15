package es.miguelgonzalezgomez.dataBaseFun;

import com.google.gson.reflect.TypeToken;
import es.miguelgonzalezgomez.dataBaseFun.configuracion.ConfiguracionAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MAplicacion;
import java.lang.reflect.Type;

/**
 *
 * @author Miguel González
 */
public class GuardaModeloAplicacion {
    private static String nombreFicheroPreferencias = "preferenciasAplicacion";
    private static Preferencias prefs;
    
    private static ConfiguracionAplicacion configuracionAplicacion;
    private static MAplicacion mAplicacion;
    
    static {
        configuracionAplicacion = new ConfiguracionAplicacion();
    }
    
    public static void guardarModeloAplicacion() {
        mAplicacion = MAplicacion.getInstance();
        prefs = Preferencias.getInstance();
        
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
                getPestanasEditoresAbiertas();
    }
    
    private static void guardarEstadoEnFicheroJson() {
        Type fooType = new TypeToken<ConfiguracionAplicacion>() {}.getType();
        prefs.guardarConfiguracion(fooType,
                configuracionAplicacion,
                nombreFicheroPreferencias);
    }
}
