package com.stylishdb.qt.tabResultViews.controllers;

import com.stylishdb.db.domain.ResultadoEjecutarConsulta;

/**
 *
 ** @author StylishDB
 */
public interface ThreadEjecutarConsultaListener {
    public void consultaEjecutada(ResultadoEjecutarConsulta resultadoEjecutarConsulta);
    public void errorEjecutarConsulta(Exception ex);
}
