package es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado;

import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.CMiControladorGenerico;
import es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado.VistaDatosConsulta;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class CVistaDatosConsulta extends CMiControladorGenerico {
    
    private VistaDatosConsulta vistaDatos;
    private ResultadoEjecutarConsulta resultado;
    
    public CVistaDatosConsulta(ResultadoEjecutarConsulta resultado) {
        super();
        this.resultado = resultado;
        
        int numColumnas = resultado.datosColumnas.size();
        int numFilas = resultado.datosFilas.size();
        
        vistaDatos = new VistaDatosConsulta(this, numFilas, numColumnas);
    }
    
    public VistaDatosConsulta getVistaDatosConsulta() {
        return vistaDatos;
    }

    public void pintarDatosConsulta() {
        pintarConsultaSQL(resultado.consultaSQL);
        
        establecerColumnas(resultado.nombresColumnas);

        anadirDatosConsulta(resultado.datosFilas);
    }
    
    private void establecerColumnas(List<String> columnas) {
        vistaDatos.establecerColumnas(columnas);
    }

    private void pintarConsultaSQL(String consultaSQL) {
        vistaDatos.pintarConsultaSQL(consultaSQL);
    }
    
    private void anadirDatosConsulta(List<String[]> datosConsulta) {
        vistaDatos.anadirDatosConsulta(datosConsulta);
    }
}
