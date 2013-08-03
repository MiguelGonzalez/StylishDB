package es.miguelgonzalezgomez.dataBaseFun.bd.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel González
 */
public class ResultadoEjecutarConsulta {
    
    public int numColumnas;
    public List<Integer> tipoColumnas;
    public List<String> nombresColumnas;
    public List<String[]> datosFila;
    
    public ResultadoEjecutarConsulta() {
        tipoColumnas = new ArrayList<>();
        nombresColumnas = new ArrayList<>();
        datosFila = new ArrayList<>();
    }
    
}
