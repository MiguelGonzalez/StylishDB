package es.miguelgonzalezgomez.dataBaseFun;

import com.google.gson.reflect.TypeToken;
import es.miguelgonzalezgomez.dataBaseFun.bd.GestionadorConexionesAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.configuracion.ConfiguracionAplicacion;
import java.lang.reflect.Type;

/**
 *
 * @author Miguel González
 */
public class GuardaModeloAplicacion {
    private static String nombreFicheroPreferencias = "preferenciasAplicacion";
    private static Preferencias prefs;
    
    private static ConfiguracionAplicacion configuracionAplicacion;
    
    public static void guardarModeloAplicacion() {
        prefs = Preferencias.getInstance();
        
        guardarEstadoAplicacion();
        guardarEstadoEnFicheroJson();
    }
    
    private static void guardarEstadoAplicacion() {
        GestionadorConexionesAplicacion gestionador = new 
                GestionadorConexionesAplicacion();
        configuracionAplicacion = new ConfiguracionAplicacion();
        configuracionAplicacion.conexiones = gestionador.getConexiones();
    }
    
    private static void guardarEstadoEnFicheroJson() {
        Type fooType = new TypeToken<ConfiguracionAplicacion>() {}.getType();
        prefs.guardarConfiguracion(fooType,
                configuracionAplicacion,
                nombreFicheroPreferencias);
    }
}
