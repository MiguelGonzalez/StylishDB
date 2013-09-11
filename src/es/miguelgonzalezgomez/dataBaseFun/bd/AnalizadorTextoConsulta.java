package es.miguelgonzalezgomez.dataBaseFun.bd;

import com.trolltech.qt.core.QRegExp;
import es.miguelgonzalezgomez.dataBaseFun.bd.domain.TiposBasesDeDatos.TIPO_BASE_DATOS;
import es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.lenguajes.DatosBaseDatos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel González
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
    
    public String getConsulta(int numConsultaLanzando) {
        return consultasSQL.get(numConsultaLanzando);
    }
    
    private void trocearTextoConsulta() {
        String textoConsultaCopia = textoConsultaLanzar;
        DatosBaseDatos datosBaseDatos = tipoBaseDatos.getDatosBaseDatos();
        
        String[] palabrasClaveComienzoConsulta = 
                datosBaseDatos.getPalabrasClaveComienzoConsulta();
        char delimitadorConsulta = datosBaseDatos.getDelimitadorConsulta();
        
        while(empiezaConsultaPalabraClave(palabrasClaveComienzoConsulta,
                textoConsultaCopia)) {
            int delimitadorFinal = encontrarDelimitadorFinalConsulta(
                    delimitadorConsulta,
                    textoConsultaCopia);
            
            if(delimitadorFinal != -1) {
                String sqlEncontrada = textoConsultaCopia.substring(
                        0, delimitadorFinal);
                consultasSQL.add(sqlEncontrada);
                
                textoConsultaCopia = textoConsultaCopia.substring(
                        delimitadorFinal + 1).trim();
            } else {
                consultasSQL.add(textoConsultaCopia.trim());
                textoConsultaCopia = "";
            }
        }
        
        if(textoConsultaCopia.length() > 0) {
            consultasSQL.add(textoConsultaCopia);
        }
    }
    
    private boolean empiezaConsultaPalabraClave(String[] palabrasClaveComienzoConsulta,
            String textoConsulta) {
        for(String palabraClave : palabrasClaveComienzoConsulta) {
            if(textoConsulta.toLowerCase().startsWith(palabraClave.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    private int encontrarDelimitadorFinalConsulta(char delimitadorConsulta
            ,String textoConsulta) {
        boolean abiertoComentarioSimple = false;
        boolean abiertoComentarioDoble = false;
        //ToDo: Mirar si el comentario está escapado o no
        for(int i=0; i<textoConsulta.length(); i++) {
            if(!abiertoComentarioSimple && textoConsulta.charAt(i) == '"') {
                abiertoComentarioDoble = !abiertoComentarioDoble;
            } else if(!abiertoComentarioDoble && textoConsulta.charAt(i) == '\'') {
                abiertoComentarioSimple = !abiertoComentarioSimple;
            } else if(textoConsulta.charAt(i) == delimitadorConsulta ) {
                if(!abiertoComentarioSimple && !abiertoComentarioDoble) {
                    return i;
                }
            }
        }
        return -1;
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
            System.out.println(length);
            strTextoLimpio.delete(index, index + length);
            
            index = patronDobleLinea.indexIn(
                    strTextoLimpio.toString()
            );
        }

        return strTextoLimpio.toString();
    }
}
