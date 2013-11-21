package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.bd.AnalizadorTextoConsulta;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsultaErrorSQL;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.domain.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.domain.MPestana;
import es.miguelgonzalezgomez.dataBaseFun.domain.controladores.CPestanaActivaListener;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CMiControladorGenerico;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalMostrarAviso;
import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.TabWidgetConsultas;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class CTabWidgetConsultas extends CMiControladorGenerico
        implements CPestanaActivaListener {

    private TabWidgetConsultas panelConsultas; 
    
    public CTabWidgetConsultas() {
        super();
        
        panelConsultas = new TabWidgetConsultas(this);
        
        establecerEstilo();
        establecerListener();
    }
    
    private void establecerEstilo() {
        panelConsultas.setStyleSheet(
                ObtencionEstilo.getEstiloVentana("widgetConsultas.css")
        );
    }
    
    private void establecerListener() {
        controladorPestanaActiva.addListener(this);
    }
    
    private void ejecutarConsultas(MPestana mPestanaEditor,
            List<String> consultasEjecutar) {
        MConexion mConexion = mAplicacion.mConexionesGuardadas.
                getMConexion(mPestanaEditor.uuidConexion);
        for(String consultaEjecutar : consultasEjecutar) {
            ejecutarConsulta(
                    mConexion,
                    consultaEjecutar
            );
        }
    }
    
    private void actualizarConsultas(MPestana mPestana,
            List<String> consultasActualizar) {
        try {
            MConexion mConexion = mAplicacion.mConexionesGuardadas.
                getMConexion(mPestana.uuidConexion);
            CPestanaActualizarConsultas cPestanaActualizarConsultas = new
                    CPestanaActualizarConsultas(
                        mConexion,
                        consultasActualizar);
            
            String nombrePestana = mPestana.getNombrePestana();
            anadirNuevaPestana(
                    nombrePestana,
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
            QWidgetResultadosConsulta cPestanaMostrarConsulta = new
                    QWidgetResultadosConsulta(mConexion,consultaSQL);
            MPestana mPestana = pestanasAbiertas.getPestanaActiva();
            
            String nombrePestana = mPestana.getNombrePestana();
            anadirNuevaPestana(
                    nombrePestana,
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

    @Override
    public void deshacer(MPestana pestana) {
    }

    @Override
    public void rehacer(MPestana pestana) {
    }

    @Override
    public void eliminada(MPestana pestana) {
    }

    @Override
    public void ejecutarConsulta(MPestana mPestana) {
        MConexion mConexion = mAplicacion.mConexionesGuardadas.
                getMConexion(mPestana.uuidConexion);
        String textoConsultaLanzar = mPestana.getTextoConsultaLanzar();
        
        AnalizadorTextoConsulta analizarTextoConsulta = new AnalizadorTextoConsulta(
                mConexion.getTipoDeBaseDeDatos(), textoConsultaLanzar
        );
        
        List<String> consultasEjecutar = analizarTextoConsulta.getConsultasEjecutar();
        List<String> consultasActualizar = analizarTextoConsulta.getConsultasActualizar();
        
        if(!consultasEjecutar.isEmpty()) {
            ejecutarConsultas(mPestana, consultasEjecutar);
        }
        if(!consultasActualizar.isEmpty()) {
            actualizarConsultas(mPestana, consultasActualizar);
        }
    }
}
