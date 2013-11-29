package com.stylishdb.db;

import com.trolltech.qt.core.QRegExp;
import com.stylishdb.db.domain.TiposBasesDeDatos.TIPO_BASE_DATOS;
import com.stylishdb.db.managers.DatosBaseDatos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class AnalizadorTextoConsulta {

    private TIPO_BASE_DATOS tipoBaseDatos;
    private String textoConsultaLanzar;
    
    private List<String> consultasSQL;
    
    private QRegExp commentStartExpression;
    private QRegExp commentEndExpression;
    private QRegExp patronDobleLinea;
    
    public AnalizadorTextoConsulta(
            TIPO_BASE_DATOS tipoBaseDatos,
            String textoConsultaLanzar) {
        this.tipoBaseDatos = tipoBaseDatos;
        
        commentStartExpression = new QRegExp("/\\*");
        commentEndExpression = new QRegExp("\\*/");
        patronDobleLinea = new QRegExp("--[^\n]*");

        consultasSQL = new ArrayList<>();

        this.textoConsultaLanzar = getConsultaFormateada(textoConsultaLanzar);
        
        trocearTextoConsulta();
    }
    
    public boolean isEjecutarQuery(int numeroQuery) {
        return isEjecutarQuery(
                consultasSQL.get(numeroQuery - 1)
        );
    }
    
    public boolean isEjecutarQuery(String consultaSQL) {
        DatosBaseDatos datosBaseDatos = tipoBaseDatos.getDatosBaseDatos();
        String[] palabrasClaveEjecutarConsulta = datosBaseDatos.
                getPalabrasClaveEjecutarConsulta();
        
        for(String palabraEjecutarConsulta : palabrasClaveEjecutarConsulta) {
            if(consultaSQL.trim().toLowerCase().startsWith(
                    palabraEjecutarConsulta.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    public int numConsultasExistentes() {
        return consultasSQL.size();
    }
    
    public String getConsulta(int numeroQuery) {
        return consultasSQL.get(
                numeroQuery - 1
        );
    }
    
    private void trocearTextoConsulta() {
        TroceadorTextoConsulta troceadorConsulta = new TroceadorTextoConsulta(
                tipoBaseDatos.getDatosBaseDatos(),
                textoConsultaLanzar
        );
        
        String consultasTroceadas[] = troceadorConsulta.getConsultasTroceadas();
        consultasSQL.addAll(Arrays.asList(consultasTroceadas));
    }

    private String getConsultaFormateada(String consultaSQL) {
        consultaSQL = quitarComentarios(consultaSQL);
        
        consultaSQL = consultaSQL.replaceAll("\\r", " ").
                replaceAll("\\n", " ").
                replaceAll("\\t", " ").
                replaceAll("\\r\\n", " ").
                replaceAll("" + (char)8233, " ");
        consultaSQL = consultaSQL.trim();
        
        return consultaSQL;
    }
    
    private String quitarComentarios(String consultaSQL) {
        DatosBaseDatos datosBaseDatos = tipoBaseDatos.getDatosBaseDatos();
        
        if(datosBaseDatos.tieneComentarioDeBloque()) {
            consultaSQL = quitarComentariosBloques(consultaSQL);
        }
        
        if(datosBaseDatos.tieneComentarioDeLinea()) {
            consultaSQL = quitarComentariosLinea(consultaSQL);
        }
        
        return consultaSQL;
    }
    
    private String quitarComentariosBloques(String consultaSQL) {
        StringBuilder strTextoLimpio = new StringBuilder(consultaSQL);
        int startIndex = commentStartExpression.indexIn(consultaSQL);

        while (startIndex >= 0) {
            int endIndex = commentEndExpression.indexIn(
                    strTextoLimpio.toString()
            );
            int commentLength;
            if (endIndex == -1) {
                commentLength = strTextoLimpio.length() - startIndex;
            } else {
                commentLength = endIndex - startIndex + commentEndExpression.matchedLength();
            }
            strTextoLimpio.delete(startIndex, startIndex + commentLength);
            
            startIndex = commentStartExpression.indexIn(
                    strTextoLimpio.toString()
            );
        }
        
        return strTextoLimpio.toString();
    }
    
    private String quitarComentariosLinea(String consultaSQL) {
        StringBuilder strTextoLimpio = new StringBuilder(consultaSQL);
        
        int index = patronDobleLinea.indexIn(
                strTextoLimpio.toString()
        );
        
        while (index >= 0) {
            int length = patronDobleLinea.matchedLength();
            strTextoLimpio.delete(index, index + length);
            
            index = patronDobleLinea.indexIn(
                    strTextoLimpio.toString()
            );
        }

        return strTextoLimpio.toString();
    }

    public List<String> getConsultasEjecutar() {
        List<String> consultasEjecutar = new ArrayList<>();
        
        for(String consultaSQL : consultasSQL) {
            if(isEjecutarQuery(consultaSQL)) {
                consultasEjecutar.add(consultaSQL);
            }
        }
        
        return consultasEjecutar;
    }

    public List<String> getConsultasActualizar() {
        List<String> consultasActualizar = new ArrayList<>();
        
        for(String consultaSQL : consultasSQL) {
            if(!isEjecutarQuery(consultaSQL)) {
                consultasActualizar.add(consultaSQL);
            }
        }
        
        return consultasActualizar;
    }
}
