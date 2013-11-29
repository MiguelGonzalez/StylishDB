package com.stylishdb.qt.controladores.pestanaVistaResultado;

import com.stylishdb.bd.domain.ResultadoEjecutarConsulta;

/**
 *
 ** @author StylishDB
 */
public interface ThreadEjecutarConsultaListener {
    public void consultaEjecutada(ResultadoEjecutarConsulta resultadoEjecutarConsulta);
    public void errorEjecutarConsulta(Exception ex);
}
