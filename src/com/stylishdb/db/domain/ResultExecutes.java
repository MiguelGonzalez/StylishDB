package com.stylishdb.db.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class ResultExecutes {
    
    public String consultaSQL;
    public int numColumnas;
    public long tiempoEjecucionConsultaMilisegundos;
    public long tiempoObtenerDatosConsulta;
    public long tiempoParaConectarContraBaseDeDatos;
    public int numFilas;
    public List<ColumnDatas> datosColumnas;
    public List<String> nombresColumnas;
    public List<String[]> datosFilas;
    
    public ResultExecutes() {
        datosColumnas = new ArrayList<>();
        nombresColumnas = new ArrayList<>();
        datosFilas = new ArrayList<>();
    }
    
    public String[] nombreDatosColumnaMostrar = {
        "Etiqueta",
        "Nombre",
        "Tabla",
        "Tipo",
        "Precisión",
        "Decimales",
        "Solo lectura",
        "Auto incremental",
        "Admite nulos"
    };
}
