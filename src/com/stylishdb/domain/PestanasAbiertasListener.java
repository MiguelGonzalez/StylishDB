package com.stylishdb.domain;

/**
 *
 ** @author StylishDB
 */
public interface PestanasAbiertasListener {
    public void pestanaActiva(MPestana mPestana);
    public void anadidaPestana(MPestana mPestana);
    public void eliminadaPestana(MPestana mPestana);
}
