package es.miguelgonzalezgomez.dataBaseFun.modelos;

/**
 *
 * @author Miguel González
 */
public interface PestanaEditorListener {

    public void modificadaPestanaEditor(MPestanaEditor pestanaEditorEditada);
    public void eliminadaPestanaEditor(PestanaEditorListener pestanaEditorListener);
    public void nuevaPestanaEditor(MPestanaEditor pestanaEditor);

    public void atajoTeclado(MPestanaEditor pestanaEditor, int atajo);
}
