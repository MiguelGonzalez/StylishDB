package es.miguelgonzalezgomez.dataBaseFun.domain;


/**
 *
 * @author Miguel González
 */
public interface PestanaListener {
    public void textoModificado(MPestana mPestana);
    public void textoSeleccionado(MPestana mPestana);
    public void renombrada(MPestana mPestana);
}
