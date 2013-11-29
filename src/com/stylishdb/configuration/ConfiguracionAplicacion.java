package com.stylishdb.configuration;

import com.stylishdb.domain.MConexion;
import com.stylishdb.domain.MPestana;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class ConfiguracionAplicacion extends IConfiguracion {
    public List<MConexion> conexiones;
    public List<MPestana> editores;
}
