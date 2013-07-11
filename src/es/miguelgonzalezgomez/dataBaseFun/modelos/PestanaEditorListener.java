package es.miguelgonzalezgomez.dataBaseFun.modelos;

/**
 *
 * @author Miguel González
 */
public interface PestanaEditorListener {

    public void modificadaPestanaEditor(MPestanaEditor pestanaEditorVieja,
            MPestanaEditor pestanaEditorViejaNueva);
    public void eliminadaPestanaEditor(PestanaEditorListener pestanaEditorListener);
    public void nuevaPestanaEditor(MPestanaEditor pestanaEditor);

    public void rehacerPestana(MPestanaEditor pestanaEditor);
    public void deshacerPestana(MPestanaEditor pestanaEditor);
}
