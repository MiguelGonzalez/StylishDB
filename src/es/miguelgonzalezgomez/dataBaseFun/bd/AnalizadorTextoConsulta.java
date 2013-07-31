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

    private String textoConsulta;
    private DatosBaseDatos datosBaseDatos;
    
    private List<String> consultasSQL;
    
    public AnalizadorTextoConsulta(
            String textoConsulta,
            TIPO_BASE_DATOS tipoBaseDeDatos) {
        
        this.textoConsulta = textoConsulta;
        this.datosBaseDatos = tipoBaseDeDatos.getDatosBaseDatos();
        
        
        consultasSQL = new ArrayList<>();

        trocearTextoConsulta();
    }
    

    
    public boolean isEjecutarQuery(int numeroQuery) {
        return isEjecutarQuery(consultasSQL.get(numeroQuery));
    }
    
    public boolean isEjecutarQuery(String consultaSQL) {
        for(String palabraEjecutarConsulta :
                datosBaseDatos.getPalabrasClaveEjecutarConsulta()) {
            if(consultaSQL.toLowerCase().equals(
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
        for(String palabraClave : datosBaseDatos.getPalabrasClaveComienzoConsulta()) {
            System.out.println(palabraClave);
            if(textoConsulta.toLowerCase().startsWith(palabraClave.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    private int encontrarDelimitadorFinalConsulta(String textoConsulta) {
        return textoConsulta.indexOf(datosBaseDatos.getDelimitadorConsulta());
    }
    
}
