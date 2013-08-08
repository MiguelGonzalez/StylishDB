package es.miguelgonzalezgomez.dataBaseFun.qt.pestanaVistaResultado;

import com.trolltech.qt.gui.QFont;
import com.trolltech.qt.gui.QPlainTextEdit;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.DatosColumna;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.ResultadoEjecutarConsulta;
import es.miguelgonzalezgomez.dataBaseFun.qt.controladores.pestanaVistaResultado.CVistaDatosTextoPlano;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class VistaDatosTextoPlano extends QPlainTextEdit {

    private CVistaDatosTextoPlano controlador;
    private final int MAX_ANCHO_COLUMNA = 150;
    private int[] anchoColumnas;
    
    private StringBuilder sbTextoPintar;
    
    public VistaDatosTextoPlano(CVistaDatosTextoPlano controlador) {
        this.controlador = controlador;
        
        construirWidget();
    }

    public void pintarDatosConsulta(ResultadoEjecutarConsulta resultado) {
        sbTextoPintar = new StringBuilder();
        rellenarAnchosColumnas(resultado.datosColumnas);
        rellenarDatosColumna(resultado.datosColumnas);
        rellenarDatosFilas(resultado.datosFilas);
        
        setPlainText(sbTextoPintar.toString());
    }
    
    private void construirWidget() {
        setLineWrapMode(LineWrapMode.NoWrap);
        QFont fuenteTextoPlano = new QFont();
        fuenteTextoPlano.setFixedPitch(true);
        fuenteTextoPlano.setFamily("monospacedmonospaced");
        setFont(fuenteTextoPlano);
    }
    
    private void rellenarDatosColumna(List<DatosColumna> datosColumnas) {
        StringBuilder sbDatosColumna = new StringBuilder();
        StringBuilder sbLineasIntermitentes = new StringBuilder();
        int numColumna = 0;
        for(DatosColumna datosColumna : datosColumnas) {
            int anchoCol = anchoColumnas[numColumna];
            String datoColumna = rellenarConCaracter(
                    datosColumna.columnName,anchoCol," ");
            String lineasIntermitentesColumna = rellenarConCaracter(
                    "",anchoCol,"-");
            
            sbDatosColumna.append(datoColumna).append("  ");
            sbLineasIntermitentes.append(lineasIntermitentesColumna).append("  ");
            
            numColumna++;
        }
        sbTextoPintar.append(sbDatosColumna).append("\n");
        sbTextoPintar.append(sbLineasIntermitentes).append("\n");
    }
    
    private void rellenarDatosFilas(List<String[]> datosFilas) {
        for(String[] datosFila : datosFilas) {
            rellenarDatosFila(datosFila);
        }
    }
    
    private void rellenarDatosFila(String[] datosFila) {
        StringBuilder sbDatosFila = new StringBuilder();
        int numColumna = 0;
        for(String datoFila : datosFila) {
            sbDatosFila.append(
                    rellenarConCaracter(
                            datoFila,
                            anchoColumnas[numColumna],
                            " "
                    )
            ).append("  ");
            
            numColumna++;
        }
        sbTextoPintar.append(sbDatosFila).append("\n");
    }
    
    private void rellenarAnchosColumnas(List<DatosColumna> datosColumnas) {
        anchoColumnas = new int[datosColumnas.size()];
        int numColumna = 0;
        for(DatosColumna datosColumna : datosColumnas) {
            anchoColumnas[numColumna] = getAnchoColumna(datosColumna);
            numColumna ++;
        }
    }
    
    private int getAnchoColumna(DatosColumna datosColumna) {
        int anchoDefectoTipoDato = getAnchoDefectoTipoDato(datosColumna.columnType);
        
        if(anchoDefectoTipoDato != -1) {
            return anchoDefectoTipoDato;
        }
        return buscarAnchoColumna(datosColumna);
    }
    
    private int getAnchoDefectoTipoDato(int columnType) {
        switch(columnType) {
            case java.sql.Types.DATE: 
            case java.sql.Types.TIMESTAMP:
                return 20;
        }
        
        return -1;
    }
    
    private String rellenarConCaracter(
            String cadena,
            int longitudMaxima,
            String caracter) {
        StringBuilder sbCadena = new StringBuilder(cadena);
        while(sbCadena.length() < longitudMaxima) {
            sbCadena.append(caracter);
        }
        return sbCadena.toString();
    }

    private int buscarAnchoColumna(DatosColumna datosColumna) {
        int precision = datosColumna.precision;
        int anchoNombre = datosColumna.columnName.length();
        int anchuraMinima = precision < anchoNombre ? anchoNombre : precision;
        
        return anchuraMinima < MAX_ANCHO_COLUMNA ? anchuraMinima :
                    MAX_ANCHO_COLUMNA;
    }
}
