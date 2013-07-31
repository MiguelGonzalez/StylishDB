package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.PestanaEditor;

/**
 *
 * @author Miguel González
 */
public class CPestanaEditor {
    
    private GEditoresAplicacion editoresAplicacion;
    private PestanaEditor pestanaEditor;
    
    private CEditor controladorEditor;
    private CEjecutarConsultas controladorEjecutarConsultas;
    
    private MPestanaEditor mPestanaEditor;
    
    public CPestanaEditor(MPestanaEditor mPestanaEditor) {
        this.mPestanaEditor = mPestanaEditor;
        editoresAplicacion = new GEditoresAplicacion();
        
        pestanaEditor = new PestanaEditor(this);
        
        controladorEditor = new CEditor(mPestanaEditor);
        controladorEjecutarConsultas = new CEjecutarConsultas();
        
        construirInterfaz();
    }
    
    private void construirInterfaz() {
        pestanaEditor.establecerEditorTexto(
                controladorEditor.getEditorTexto()
        );
        pestanaEditor.establecerPanelConsultas(
                controladorEjecutarConsultas.getPanelConsultas()
        );
    }
    
    public PestanaEditor getPestanaEditor() {
        return pestanaEditor;
    }

    public void deshacer() {
        controladorEditor.deshacer();
    }

    public void rehacer() {
        controladorEditor.rehacer();
    }

    public void ejecutarConsulta() {
        controladorEjecutarConsultas.lanzarConsultaTexto(
            mPestanaEditor,
            controladorEditor
        );
    }

    public MPestanaEditor getModeloEditor() {
        return mPestanaEditor;
    }

    public void estaVisible() {
        editoresAplicacion.establecerPestanaActiva(
                mPestanaEditor);
    }
}
