package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.bd.AnalizadorTextoConsulta;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsultaErrorSQL;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalMostrarAviso;
import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.PanelPestanasMostrarConsultas;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class CPanelPestanasMostrarConsultas {

    private CPanelPestanasMostrarConsultasEscuchaCambios escuchaCambiosConsultas;
    private GEditoresAplicacion editoresAplicacion;
    private PanelPestanasMostrarConsultas panelConsultas;
    
    public CPanelPestanasMostrarConsultas() {
        panelConsultas = new PanelPestanasMostrarConsultas(this);
        escuchaCambiosConsultas = new CPanelPestanasMostrarConsultasEscuchaCambios(this);
        editoresAplicacion = new GEditoresAplicacion();
        
        inicializarEscuchaCambios();
    }
    
    private void inicializarEscuchaCambios() {
        editoresAplicacion.addPestanasEditorListener(escuchaCambiosConsultas);
    }

    public void lanzarConsultaTexto(MPestanaEditor mPestanaEditor) {
        MConexion mConexion = mPestanaEditor.mConexion;
        String textoConsultaLanzar = mPestanaEditor.getTextoConsultaLanzar();
        
        AnalizadorTextoConsulta analizarTextoConsulta = new AnalizadorTextoConsulta(
                mConexion.tipoDeBaseDeDatos, textoConsultaLanzar
        );
        
        List<String> consultasEjecutar = analizarTextoConsulta.getConsultasEjecutar();
        List<String> consultasActualizar = analizarTextoConsulta.getConsultasActualizar();
        
        if(!consultasEjecutar.isEmpty()) {
            ejecutarConsultas(mPestanaEditor, consultasEjecutar);
        }
        if(!consultasActualizar.isEmpty()) {
            actualizarConsultas(mPestanaEditor, consultasActualizar);
        }
    }
    
    private void ejecutarConsultas(MPestanaEditor mPestanaEditor,
            List<String> consultasEjecutar) {
        for(String consultaEjecutar : consultasEjecutar) {
            ejecutarConsulta(
                    mPestanaEditor.mConexion,
                    consultaEjecutar
            );
        }
    }
    
    private void actualizarConsultas(MPestanaEditor mPestanaEditor,
            List<String> consultasActualizar) {
        try {
            CPestanaActualizarConsultas cPestanaActualizarConsultas = new
                    CPestanaActualizarConsultas(
                        mPestanaEditor.mConexion,
                        consultasActualizar);
            MPestanaEditor pestana = editoresAplicacion.getMPestanaActiva();
            
            anadirNuevaPestana(
                    pestana.nombrePestana,
                    cPestanaActualizarConsultas.getPestanaResultado()
            );
        } catch (ManejadorConsultaErrorSQL ex) {
            ModalMostrarAviso.mostrarErrorEnPantalla(
                "Error al actualizar la consulta",
                ex.getMessage()
            );
        }
    }
    
    private void ejecutarConsulta(MConexion mConexion, String consultaSQL) {
        try {
            CPestanaMostrarConsulta cPestanaMostrarConsulta = new
                    CPestanaMostrarConsulta(mConexion,consultaSQL);
            MPestanaEditor pestana = editoresAplicacion.getMPestanaActiva();
            
            anadirNuevaPestana(
                    pestana.nombrePestana,
                    cPestanaMostrarConsulta.getPestanaResultado()
            );
        } catch (ManejadorConsultaErrorSQL ex) {
            ModalMostrarAviso.mostrarErrorEnPantalla(
                "Error al ejecutar la consulta",
                ex.getMessage()
            );
        }
    }
    
    public QTabWidget getPanelConsultas() {
        return panelConsultas;
        
    }
    
    private void anadirNuevaPestana(String nombrePestana, QWidget widget) {
        panelConsultas.addTab(nombrePestana, widget);
        int nPestanas = panelConsultas.count();
        if(nPestanas > 0) {
            panelConsultas.setCurrentIndex(nPestanas - 1);
        }
    }
    
    protected void cerrarPestana(int indexPestana) {
        panelConsultas.removeTab(indexPestana);
    }
}
