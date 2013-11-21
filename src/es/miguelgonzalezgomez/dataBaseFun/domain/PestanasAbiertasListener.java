package es.miguelgonzalezgomez.dataBaseFun.domain;

/**
 *
 * @author Miguel González
 */
public interface PestanasAbiertasListener {
    public void pestanaActiva(MPestana mPestana);
    public void anadidaPestana(MPestana mPestana);
    public void eliminadaPestana(MPestana mPestana);
}
