package es.miguelgonzalezgomez.dataBaseFun.configuracion;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class ConfiguracionAplicacion extends IConfiguracion {
    public List<MConexion> conexiones;
    public List<MPestanaEditor> editores;
}
