package es.miguelgonzalezgomez.dataBaseFun.configuracion;

import es.miguelgonzalezgomez.dataBaseFun.domain.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.domain.MPestana;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class ConfiguracionAplicacion extends IConfiguracion {
    public List<MConexion> conexiones;
    public List<MPestana> editores;
}
