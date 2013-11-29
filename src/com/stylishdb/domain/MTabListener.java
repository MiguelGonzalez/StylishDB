package com.stylishdb.domain;


/**
 *
 ** @author StylishDB
 */
public interface MTabListener {
    public void textoModificado(MTab mPestana);
    public void textoSeleccionado(MTab mPestana);
    public void renombrada(MTab mPestana);
    public void textoFormateado(MTab mPestana);
}
