package com.stylishdb.domain;


/**
 *
 ** @author StylishDB
 */
public interface PestanaListener {
    public void textoModificado(MPestana mPestana);
    public void textoSeleccionado(MPestana mPestana);
    public void renombrada(MPestana mPestana);
    public void textoFormateado(MPestana mPestana);
}
