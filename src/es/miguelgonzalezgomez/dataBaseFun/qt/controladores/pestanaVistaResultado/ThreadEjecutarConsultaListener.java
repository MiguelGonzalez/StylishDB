package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;

/**
 *
 * @author Miguel González
 */
public interface ThreadEjecutarConsultaListener {
    public void consultaEjecutada(ResultadoEjecutarConsulta resultadoEjecutarConsulta);
    public void errorEjecutarConsulta(Exception ex);
}
