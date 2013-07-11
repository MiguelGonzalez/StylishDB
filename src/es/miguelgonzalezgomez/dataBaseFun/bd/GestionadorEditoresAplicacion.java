package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.modelos.MAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class GestionadorEditoresAplicacion {
    
    private MAplicacion aplicacion;
    
    public GestionadorEditoresAplicacion() {
        aplicacion = MAplicacion.getInstance();
    }
    
    public List<MPestanaEditor> getPestanasEditores() {
        return aplicacion.mPestanasEditorAbiertas.getPestanasEditoresAbiertas();
    }
    
    public void borrarPestanaEditor(MPestanaEditor mPestanaEditor) {
        aplicacion.mPestanasEditorAbiertas.removePestanaEditor(mPestanaEditor);
    }
    
    public void addNuevaConexion(MPestanaEditor mPestanaEditor) {
        aplicacion.mPestanasEditorAbiertas.addNuevaPestanaEditor(mPestanaEditor);
    }
    
    public void editadaConexion(MPestanaEditor mPestanaEditorVieja,
            MPestanaEditor mPestanaEditorNueva) {
        aplicacion.mPestanasEditorAbiertas.editedPestanaEditor(
                mPestanaEditorVieja,
                mPestanaEditorNueva);
    }
}
