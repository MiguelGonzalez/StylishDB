package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import com.trolltech.qt.gui.QWidget;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsulta;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsultaErrorSQL;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsultaNoHayConexion;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.domain.MConexion;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CMiControladorGenerico;
import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.PestanaMostrarResultadoConsulta;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalMostrarAviso;

/**
 *
 * @author Miguel González
 */
public class CPestanaMostrarConsulta extends CMiControladorGenerico {
    
    private ManejadorConsulta manejadorConsulta;
    private PestanaMostrarResultadoConsulta pestanaResultado;
    
    private CVistaDatosConsulta controladorVistaDatosConsulta;
    private CVistaDatosTabla controladorVistaDatosTabla;
    private CVistaDatosTextoPlano controladorVistaDatosTextoPlano;
    private CVistaDatosInformacion controladorVistaDatosInformacion;
    
    private String consultaSQL;
    private MConexion mConexion;
    
    public CPestanaMostrarConsulta(
            MConexion mConexion,
            String consultaSQL) throws ManejadorConsultaErrorSQL {
        super();
        
        this.consultaSQL = consultaSQL;
        this.mConexion = mConexion;
        
        crearControladoresYComponentes();
        ejecutarPasosLanzarQuery();
    }
    
    private void crearControladoresYComponentes() {
        manejadorConsulta = new ManejadorConsulta(
                mConexion,
                consultaSQL
        );
        
        controladorVistaDatosConsulta = new CVistaDatosConsulta();
        controladorVistaDatosTabla = new CVistaDatosTabla();
        controladorVistaDatosTextoPlano = new CVistaDatosTextoPlano();
        controladorVistaDatosInformacion = new CVistaDatosInformacion();
        
        pestanaResultado = new PestanaMostrarResultadoConsulta(this);
        
        pestanaResultado.pintarVistaDatosConsulta(
                controladorVistaDatosConsulta.getVistaDatosConsulta()
        );
        pestanaResultado.pintarVistaDatosTextoPlano(
                controladorVistaDatosTextoPlano.getVistaDatosTextoPlano()
        );
        pestanaResultado.pintarVistaDatosTabla(
                controladorVistaDatosTabla.getVistaDatosTabla()
        );
        pestanaResultado.pintarVistaDatosInformacion(
                controladorVistaDatosInformacion.getVistaDatosInformacion()
        );
    }
    
    private void ejecutarPasosLanzarQuery() throws ManejadorConsultaErrorSQL {
        if(conectarContraBaseDeDatos()) {
            ejecutarConsulta();
            pintarRespuestaConsulta();
        }
    }
    
    public QWidget getPestanaResultado() {
        return pestanaResultado;
    }
    
    private boolean conectarContraBaseDeDatos() {
        try {
            manejadorConsulta.conectarContraBaseDeDatos();
            return true;
        } catch (ManejadorConsultaNoHayConexion ex) {
            mostrarErrorEnPantalla(
                    "Connection exception",
                    ex.getMessage()
            );
        }
        return false;
    }
    
    private void ejecutarConsulta() throws ManejadorConsultaErrorSQL {
        manejadorConsulta.ejecutarConsulta();
    }
    
    private void pintarRespuestaConsulta() {
        ResultadoEjecutarConsulta resultado = manejadorConsulta.
                getDatosConsultaEjecutada();
        controladorVistaDatosConsulta.pintarDatosConsulta(resultado);
        controladorVistaDatosTextoPlano.pintarDatosConsulta(resultado);
        controladorVistaDatosTabla.pintarDatosTabla(resultado);
        controladorVistaDatosInformacion.pintarDatosInformacion(resultado);
    }

    
    private void mostrarErrorEnPantalla(String tituloError, String mensajeError) {
        ModalMostrarAviso.mostrarErrorEnPantalla(tituloError,
                mensajeError);
    }
    
}
