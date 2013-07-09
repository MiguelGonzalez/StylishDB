package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class GestionadorConexionesAplicacion {
    
    private MAplicacion aplicacion;
    
    public GestionadorConexionesAplicacion() {
        aplicacion = MAplicacion.getInstance();
    }
    
    public List<MConexion> getConexiones() {
        return aplicacion.mConexionesGuardadas.getConexionesGuardadas();
    }
    
    public void borrarConexion(MConexion mConexion) {
        aplicacion.mConexionesGuardadas.removeConexion(mConexion);
    }
    
    public void addNuevaConexion(MConexion mConexion) {
        aplicacion.mConexionesGuardadas.addNuevaConexion(mConexion);
    }
    
    public boolean existeNombreConexion(String nombreConexion) {
        List<MConexion> conexiones = aplicacion.mConexionesGuardadas.
                getConexionesGuardadas();
        
        for(MConexion conexion : conexiones) {
            if(conexion.nombre.equals(nombreConexion)) {
                return true;
            }
        }
        
        return false;
    }
}
