package es.miguelgonzalezgomez.dataBaseFun.gestionadores;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class GConexionesAplicacion {
    
    private MAplicacion aplicacion;
    
    public GConexionesAplicacion() {
        aplicacion = MAplicacion.getInstance();
    }
    
    public List<MConexion> getConexiones() {
        return aplicacion.mConexionesGuardadas.getConexionesGuardadas();
    }
    
    public MConexion getMConexionNombre(String nombreConexion) {
        return aplicacion.mConexionesGuardadas.getMConexionNombre(nombreConexion);
    }
    
    public void borrarConexion(MConexion mConexion) {
        aplicacion.mConexionesGuardadas.removeConexion(mConexion);
    }
    
    public void addNuevaConexion(MConexion mConexion) {
        aplicacion.mConexionesGuardadas.addNuevaConexion(mConexion);
    }
    
    public boolean existeNombreConexion(MConexion mConexion) {
        List<MConexion> conexiones = aplicacion.mConexionesGuardadas.
                getConexionesGuardadas();
        
        for(MConexion conexion : conexiones) {
            if(
                    !mConexion.equals(conexion) &&
                    conexion.nombre.equals(mConexion.nombre)
                    ) {
                return true;
            }
        }
        
        return false;
    }

    public void editadaConexion(MConexion mConexionVieja, MConexion mConexionNueva) {
        aplicacion.mConexionesGuardadas.editadaConexion(
                mConexionVieja,
                mConexionNueva);
    }
}
