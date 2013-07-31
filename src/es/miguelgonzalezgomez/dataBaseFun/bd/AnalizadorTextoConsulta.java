package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.TiposBasesDeDatos.TIPO_BASE_DATOS;
import es.miguelgonzalezgomez.dataBaseFun.qt.restaltadoEditor.lenguajes.MySQL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class AnalizadorTextoConsulta {

    private String DELIMITADOR_CONSULTAS = ";";
    private String[] palabrasClaveComienzoConsulta;
    private String palabraClaveEjecutarConsulta = "SELECT";
    
    private String textoConsulta;
    private TIPO_BASE_DATOS tipoBaseDeDatos;
    
    private List<String> consultasSQL;
    
    public AnalizadorTextoConsulta(
            String textoConsulta,
            TIPO_BASE_DATOS tipoBaseDeDatos) {
        this.textoConsulta = textoConsulta;
        this.tipoBaseDeDatos = tipoBaseDeDatos;
        
        consultasSQL = new ArrayList<>();
        
        inicializarDatosGestor();
        trocearTextoConsulta();
    }
    
    private void inicializarDatosGestor() {
        if(tipoBaseDeDatos == TIPO_BASE_DATOS.MYSQL) {
            DELIMITADOR_CONSULTAS = MySQL.delimitador;
            palabrasClaveComienzoConsulta = MySQL.palabrasClaveComienzoConsulta;
            palabraClaveEjecutarConsulta = MySQL.palabraClaveEjecutarConsulta;
        } else if(tipoBaseDeDatos == TIPO_BASE_DATOS.ORACLE) {
            
        }
    }
    
    public boolean isEjecutarQuery(int numeroQuery) {
        return isEjecutarQuery(consultasSQL.get(numeroQuery));
    }
    
    public boolean isEjecutarQuery(String consultaSQL) {
        return consultaSQL.toLowerCase().startsWith(
                palabraClaveEjecutarConsulta.toLowerCase());
    }
    
    public int numConsultasExistentes() {
        return consultasSQL.size();
    }
    
    private void trocearTextoConsulta() {
        String textoConsultaCopia = textoConsulta;
        
        while(empiezaConsultaPalabraClave(textoConsultaCopia)) {
            int delimitadorFinal = encontrarDelimitadorFinalConsulta(textoConsultaCopia);
            
            if(delimitadorFinal != -1) {
                String sqlEncontrada = textoConsultaCopia.substring(
                        0, delimitadorFinal);
                consultasSQL.add(sqlEncontrada);
                
                textoConsultaCopia = textoConsultaCopia.substring(
                        delimitadorFinal + 1);
            } else {
                consultasSQL.add(textoConsultaCopia);
                textoConsultaCopia = "";
            }
            System.out.println(textoConsultaCopia);
        }
        
        if(textoConsultaCopia.length() > 0) {
            consultasSQL.add(textoConsultaCopia);
        }
    }
    
    private boolean empiezaConsultaPalabraClave(String textoConsulta) {
        for(String palabraClave : palabrasClaveComienzoConsulta) {
            System.out.println(palabraClave);
            if(textoConsulta.toLowerCase().startsWith(palabraClave.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    private int encontrarDelimitadorFinalConsulta(String textoConsulta) {
        return textoConsulta.indexOf(DELIMITADOR_CONSULTAS);
    }
    
}
