package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado.CPestanaMostrarConsulta;
import com.trolltech.qt.gui.QTabWidget;
import es.miguelgonzalezgomez.dataBaseFun.bd.AnalizadorTextoConsulta;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GEditoresAplicacion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.PanelPestanasMostrarConsultas;

/**
 *
 * @author Miguel González
 */
public class CPanelPestanasMostrarConsultas {

    private CEjecutarConsultasEscuchaCambios escuchaCambiosConsultas;
    private GEditoresAplicacion editoresAplicacion;
    private PanelPestanasMostrarConsultas panelConsultas;
    
    public CPanelPestanasMostrarConsultas() {
        panelConsultas = new PanelPestanasMostrarConsultas(this);
        escuchaCambiosConsultas = new CEjecutarConsultasEscuchaCambios(this);
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
        
        int numeroConsultasAnalizadas = analizarTextoConsulta.numConsultasExistentes();
        for(int i=0; i<numeroConsultasAnalizadas; i++) {
            String trozoConsultaSQL = analizarTextoConsulta.getConsulta(i);
            
            lanzarConsulta(
                    mPestanaEditor.mConexion,
                    analizarTextoConsulta,
                    trozoConsultaSQL
            );
        }
    }
    
    private void lanzarConsulta(
            MConexion mConexion,
            AnalizadorTextoConsulta analizarTextoConsulta,
            String consultaSQL) {
        
        
        if(analizarTextoConsulta.isEjecutarQuery(consultaSQL)) {
            lanzarEjecutarQuery(
                    mConexion, consultaSQL
            );
        } else {
            lanzarUpdateQuery(
                    mConexion, consultaSQL
            );
        }
    }
    
    private void lanzarEjecutarQuery(MConexion mConexion, String consultaSQL) {
        CPestanaMostrarConsulta cPestanaMostrarConsulta = new
                CPestanaMostrarConsulta(mConexion,consultaSQL);
        MPestanaEditor pestana = editoresAplicacion.getMPestanaActiva();
        
        panelConsultas.addTab(
                pestana.nombrePestana,
                cPestanaMostrarConsulta.getPestanaResultado()
        );
    }
    
    private void lanzarUpdateQuery(MConexion mConexion, String consultaSQL) {
        //ToDo: Hacer Upadte query
    }
    
    public QTabWidget getPanelConsultas() {
        return panelConsultas;
        
    }
    
    protected void cerrarPestana(int indexPestana) {
        panelConsultas.removeTab(indexPestana);
    }
}
