package com.stylishdb.domain.controllers;

import com.stylishdb.domain.MTab;

/**
 *
 ** @author StylishDB
 */
public interface CFocusTabListener {
    public void deshacer(MTab pestana);
    public void rehacer(MTab pestana);
    public void eliminada(MTab pestana);
    public void ejecutarConsulta(MTab pestana);
}
