package es.miguelgonzalezgomez.dataBaseFun.bd.domain;

import java.util.List;

/**
 *
 * @author Miguel González
 */
public class ResultadoActualizarConsultas {
    public List<String> consultasSQLActualizar;
    public List<Integer> numeroFilasAfectadas;
    
    public Long tiempoParaConectarContraBaseDeDatos;
    public Long tiempoActualizacionConsultas;
}
