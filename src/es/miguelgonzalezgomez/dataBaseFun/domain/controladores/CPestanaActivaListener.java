package es.miguelgonzalezgomez.dataBaseFun.domain.controladores;

import es.miguelgonzalezgomez.dataBaseFun.domain.MPestana;

/**
 *
 * @author Miguel González
 */
public interface CPestanaActivaListener {
    public void deshacer(MPestana pestana);
    public void rehacer(MPestana pestana);
    public void eliminada(MPestana pestana);
    public void ejecutarConsulta(MPestana pestana);
}
