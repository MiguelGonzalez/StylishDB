package es.miguelgonzalezgomez.dataBaseFun.bd;

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
    
    public AnalizadorTextoConsulta(TIPO_BASE_DATOS tipoBaseDatos, String textoConsultaLanzar) {
        this.tipoBaseDatos = tipoBaseDatos;
        this.textoConsultaLanzar = getConsultaFormateada(textoConsultaLanzar);
        
        consultasSQL = new ArrayList<>();
        
        trocearTextoConsulta();
    }
    
    public boolean isEjecutarQuery(TIPO_BASE_DATOS tipoBaseDeDatos,
            int numeroQuery) {
        return isEjecutarQuery(
                tipoBaseDeDatos,
                consultasSQL.get(numeroQuery - 1)
        );
    }
    
    public boolean isEjecutarQuery(TIPO_BASE_DATOS tipoBaseDeDatos,
            String consultaSQL) {
        DatosBaseDatos datosBaseDatos = tipoBaseDeDatos.getDatosBaseDatos();
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
            if(textoConsulta.charAt(i) == '"') {
                abiertoComentarioDoble = !abiertoComentarioDoble;
            } else if(textoConsulta.charAt(i) == '\'') {
                abiertoComentarioSimple = !abiertoComentarioSimple;
            } else if(textoConsulta.charAt(i) == delimitadorConsulta ) {
                if(!abiertoComentarioSimple && !abiertoComentarioDoble) {
                    return i;
                }
            }
        }
        return -1;
    }

    public String getConsulta(int numConsultaLanzando) {
        return consultasSQL.get(numConsultaLanzando);
    }
    
    private String getConsultaFormateada(String consultaSQL) {
        consultaSQL = consultaSQL.replaceAll("\\r", "").
                replaceAll("\\n", " ").
                replaceAll("\\r\\n", " ").
                replaceAll("" + (char)8233, "");
        consultaSQL = consultaSQL.trim();
        
        return consultaSQL;
    }
}
