package com.stylishdb.domain.controllers;

import com.stylishdb.domain.MPestana;

/**
 *
 ** @author StylishDB
 */
public interface CPestanaActivaListener {
    public void deshacer(MPestana pestana);
    public void rehacer(MPestana pestana);
    public void eliminada(MPestana pestana);
    public void ejecutarConsulta(MPestana pestana);
}
