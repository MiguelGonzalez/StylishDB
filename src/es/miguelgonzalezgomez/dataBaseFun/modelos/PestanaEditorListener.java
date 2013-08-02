package es.miguelgonzalezgomez.dataBaseFun.modelos;

/**
 *
 * @author Miguel González
 */
public interface PestanaEditorListener {

    public void modificadaPestanaEditor(MPestanaEditor pestanaEditorEditada, int tipoEvento);
    public void eliminadaPestanaEditor(MPestanaEditor pestanaEditorEliminada);
    public void nuevaPestanaEditor(MPestanaEditor pestanaEditor);

    public void atajoTeclado(MPestanaEditor pestanaEditor, int tipoEvento);

    public void ejecutarConsultaPestanaEditor(MPestanaEditor pestanaEditor);

    public void cambiarSiguientePestana();

    public void cambiarAnteriorPestana();
}
