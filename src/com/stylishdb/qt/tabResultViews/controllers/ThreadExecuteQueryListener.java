package com.stylishdb.qt.tabResultViews.controllers;

import com.stylishdb.db.domain.ResultExecutes;

/**
 *
 ** @author StylishDB
 */
public interface ThreadExecuteQueryListener {
    public void consultaEjecutada(ResultExecutes resultadoEjecutarConsulta);
    public void errorEjecutarConsulta(Exception ex);
}
