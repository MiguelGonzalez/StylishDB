package com.stylishdb.db.domain;

import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class ResultadoActualizarConsultas {
    public List<String> consultasSQLActualizar;
    public List<Integer> numeroFilasAfectadas;
    
    public Long tiempoParaConectarContraBaseDeDatos;
    public Long tiempoActualizacionConsultas;
}
