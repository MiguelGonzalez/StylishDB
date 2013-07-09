package es.miguelgonzalezgomez.dataBaseFun;

import com.google.gson.reflect.TypeToken;
import es.miguelgonzalezgomez.dataBaseFun.bd.GestionadorConexionesAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.configuracion.ConfiguracionAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
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
        } catch (PreferenciasException ex) {
            ex.printStackTrace();
            cargarPropiedadesDefectoAplicacion();
        }
    }
    
    private static void cargarPropiedadesDefectoAplicacion() {
        configuracionAplicacion = new ConfiguracionAplicacion();
        configuracionAplicacion.conexiones = new ArrayList<>();
    }
    
    private static void cargarConfiguracionEnModelo() {
        GestionadorConexionesAplicacion gestionador = new 
                GestionadorConexionesAplicacion();
        for(MConexion conexion : configuracionAplicacion.conexiones) {
            gestionador.addNuevaConexion(conexion);
        }
    }
}
