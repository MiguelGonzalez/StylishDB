package es.miguelgonzalezgomez.dataBaseFun.qt.controladores;

import com.trolltech.qt.core.Qt;
import es.miguelgonzalezgomez.dataBaseFun.bd.ManejadorConsulta;
import es.miguelgonzalezgomez.dataBaseFun.modelos.MPestanaEditor;
import es.miguelgonzalezgomez.dataBaseFun.qt.modals.ModalResultadoConsulta;
import es.miguelgonzalezgomez.dataBaseFun.utilidadesEstaticas.CentroCoordenadas;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class CEjecutarConsulta {

    private ModalResultadoConsulta vistaResultadoConsulta;
    private MPestanaEditor mPestanaEditor;
    private ManejadorConsulta manejadorConsulta;
    
    public CEjecutarConsulta() {
        vistaResultadoConsulta = new ModalResultadoConsulta(this);
    }
    
    void lanzarConsulta(MPestanaEditor pestanaEditor) {
        this.mPestanaEditor = pestanaEditor;
        
        manejadorConsulta = new ManejadorConsulta(
                pestanaEditor.mConexion);
        
        manejadorConsulta.ejecutarConsulta(getConsultaFormateada());
        
        pintarResultadoConsulta();
        
        mostrarResultadosConsulta();
    }
    
    private void pintarResultadoConsulta() {
        vistaResultadoConsulta.construirInterfaz();
        
        List<String> columnas = manejadorConsulta.getNombresColumnas();
        vistaResultadoConsulta.establecerColumnas(columnas);
        
        while(manejadorConsulta.haySiguienteFila()) {
            List<String> datosFila = manejadorConsulta.getFila();
            
            vistaResultadoConsulta.anadirDatosFila(datosFila);
        }
        
        manejadorConsulta.cerrarConsultaSQL();
    }
    
    private void mostrarResultadosConsulta() {
        colocarVentanaModal();
        
        vistaResultadoConsulta.setWindowFlags(Qt.WindowType.FramelessWindowHint);
        vistaResultadoConsulta.show();
    }
    
    private void colocarVentanaModal() {
        int width = 600; 
        vistaResultadoConsulta.setFixedWidth(width);
        vistaResultadoConsulta.move(
                CentroCoordenadas.getXCentrada(width),
                CentroCoordenadas.getYCentradaArriba()
                );
    }
    
    private String getConsultaFormateada() {
        String consultaSQL;
        
        if(mPestanaEditor.hayTextoSeleccionado) {
            consultaSQL = mPestanaEditor.textoSeleccionado;
        } else {
            consultaSQL = mPestanaEditor.contenidoTexto;
        }
        
        consultaSQL= consultaSQL.replaceAll("\\r", "").
                replaceAll("\\n", " ").
                replaceAll("\\r\\n", " ").
                replaceAll("" + (char)8233, "");
        consultaSQL = consultaSQL.trim();
        
        return consultaSQL;
    }
    
}
