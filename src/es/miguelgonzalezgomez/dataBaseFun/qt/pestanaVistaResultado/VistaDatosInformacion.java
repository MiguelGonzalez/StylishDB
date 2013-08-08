package es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado;

import com.trolltech.qt.gui.QPlainTextEdit;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado.CVistaDatosInformacion;

/**
 *
 * @author Miguel González
 */
public class VistaDatosInformacion extends QPlainTextEdit {
    
    private CVistaDatosInformacion controlador;
    private StringBuilder sbInformacion;

    public VistaDatosInformacion(CVistaDatosInformacion controlador) {
        this.controlador = controlador;
    }

    public void pintarDatosInformacion(ResultadoEjecutarConsulta resultadoConsulta) {
        sbInformacion = new StringBuilder(2000);
        
        rellenarDatosInformacion(resultadoConsulta);
        
        setPlainText(sbInformacion.toString());
        sbInformacion.delete(0, sbInformacion.length());
    }
    
    private void rellenarDatosInformacion(ResultadoEjecutarConsulta resultadoConsulta) {
        sbInformacion.append("Información de la consulta:").append("\n");
        sbInformacion.append(resultadoConsulta.consultaSQL).append("\n").append("\n");
        sbInformacion.append("Número de filas: ").
                append(resultadoConsulta.numFilas).append("\n");
        sbInformacion.append("Número de columnas: ").
                append(resultadoConsulta.numColumnas).append("\n");
        sbInformacion.append("Tiempo para establecer la conexión: ").
                append(resultadoConsulta.tiempoParaConectarContraBaseDeDatos).append("msg\n");
        sbInformacion.append("Tiempo de ejecución de la consulta: ").
                append(resultadoConsulta.tiempoEjecucionConsultaMilisegundos).append("msg\n");
        sbInformacion.append("Tiempo de carga de los datos de la consulta: ").
                append(resultadoConsulta.tiempoObtenerDatosConsulta).append("msg\n");
        sbInformacion.append("Tiempo total:").append(
                    resultadoConsulta.tiempoParaConectarContraBaseDeDatos +
                    resultadoConsulta.tiempoEjecucionConsultaMilisegundos +
                    resultadoConsulta.tiempoObtenerDatosConsulta
                ).append("msg");
    }
    
}
