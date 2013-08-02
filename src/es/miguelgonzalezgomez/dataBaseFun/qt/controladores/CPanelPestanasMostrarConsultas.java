package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.gui.QTabWidget;
import es.miguelgonzalezgomez.dataBaseFun.bd.AnalizadorTextoConsulta;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.TiposBasesDeDatos.TIPO_BASE_DATOS;
import es.miguelgonzalezgomez.dataBaseFun.gestionadores.GEditoresAplicacion;
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
        TIPO_BASE_DATOS tipoBaseDatos = mPestanaEditor.mConexion.tipoDeBaseDeDatos;
        String textoConsultaLanzar = mPestanaEditor.getTextoConsultaLanzar();
        
        AnalizadorTextoConsulta analizarTextoConsulta = new AnalizadorTextoConsulta(
                tipoBaseDatos,
                textoConsultaLanzar
        );
        
        for(int i=0; i<analizarTextoConsulta.numConsultasExistentes(); i++) {
            String trozoConsultaSQL = analizarTextoConsulta.getConsulta(i);
            
            if(analizarTextoConsulta.isEjecutarQuery(
                    tipoBaseDatos,
                    trozoConsultaSQL)) {
                
                CPestanaMostrarConsulta cPestanaMostrarConsulta = new
                        CPestanaMostrarConsulta(
                                mPestanaEditor.mConexion,
                                trozoConsultaSQL);
                panelConsultas.addTab(
                        "Consulta",
                        cPestanaMostrarConsulta.getPestanaResultado()
                );
                
            }
            //ToDo: Agregar ejecutar updates
        }
    }
    
    public QTabWidget getPanelConsultas() {
        return panelConsultas;
        
    }
    
    
}
