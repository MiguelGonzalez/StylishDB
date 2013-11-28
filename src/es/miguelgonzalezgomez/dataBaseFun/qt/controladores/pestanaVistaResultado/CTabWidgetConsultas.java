package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QTabWidget;
import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.bd.AnalizadorTextoConsulta;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsultaErrorSQL;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.estilos.ObtencionEstilo;
import es.miguelgonzalezgomez.dataBaseFun.domain.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.domain.MPestana;
import es.miguelgonzalezgomez.dataBaseFun.domain.controladores.CPestanaActivaListener;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CMiControladorGenerico;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalMostrarAviso;
import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.TabWidgetConsultas;
import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.WidgetResultadosConsulta;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class CTabWidgetConsultas extends CMiControladorGenerico
        implements CPestanaActivaListener, ThreadEjecutarConsultaListener {

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
        panelConsultas.setContentsMargins(0, 0, 0, 0);
    }
    
    private void establecerListener() {
        controladorPestanaActiva.addListener(this);
    }
    
    private void ejecutarConsultas(MPestana mPestana,
            List<String> consultasEjecutar) {
        MConexion mConexion = conexionesGuardadas.getMConexion(
                mPestana.uuidConexion);
                
        for(String consultaEjecutar : consultasEjecutar) {
            ejecutarConsulta(mConexion, consultaEjecutar);
        }
    }
    
    private void ejecutarConsulta(MConexion mConexion, String consultaEjecutar) {
        ThreadEjecutarConsulta thread = new ThreadEjecutarConsulta(
            mConexion,
            consultaEjecutar
        );
        thread.addListener(this);
        thread.start();
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
        WidgetResultadosConsulta tab = (WidgetResultadosConsulta) panelConsultas.
                widget(indexPestana);
        
        tab.liberarControlador();
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

    @Override
    public void consultaEjecutada(final ResultadoEjecutarConsulta resultadoEjecutarConsulta) {
        QApplication.invokeLater(new Runnable() {
            @Override
            public void run() {
                CWidgetResultadosConsulta cPestanaMostrarConsulta = new
                        CWidgetResultadosConsulta();
                MPestana mPestana = pestanasAbiertas.getPestanaActiva();

                String nombrePestana = mPestana.getNombrePestana();
                anadirNuevaPestana(
                        nombrePestana,
                        cPestanaMostrarConsulta.getPestanaResultado()
                );
                
                cPestanaMostrarConsulta.pintarResultados(resultadoEjecutarConsulta);
            }
        });
    }

    @Override
    public void errorEjecutarConsulta(final Exception ex) {
        QApplication.invokeLater(new Runnable() {
            @Override
            public void run() {
                ModalMostrarAviso.mostrarErrorEnPantalla(
                    "Error al ejecutar la consulta",
                    ex.getMessage()
                );
            }
        });
    }
}
