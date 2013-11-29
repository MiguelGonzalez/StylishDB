package com.stylishdb.db;

import com.stylishdb.domain.MApplication;
import com.stylishdb.domain.MConnection;
import com.stylishdb.domain.AllMConnections;
import com.stylishdb.domain.MTab;
import java.util.ArrayList;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class FormatterTextSQL {

    private Formatter formatter;

    public FormatterTextSQL() {
        formatter = new Formatter();
    }
    
    public void pestana(MTab pestanaActiva) {
        List<String> consultasSQL = consultasSQL(pestanaActiva);
        List<String> consultasSQLFormateadas =
                formatearConsultas(consultasSQL);

        StringBuilder sbConsultasFormateadas = new StringBuilder();
        for(String consulta : consultasSQLFormateadas) {
            sbConsultasFormateadas.
                    append(consulta).
                    append(";").
                    append("\n").
                    append("\n");
        }
        
        pestanaActiva.setTextoEditor(sbConsultasFormateadas.toString());
        pestanaActiva.notificarTextoFormateado();
    }
    
    private List<String> consultasSQL(MTab pestana) {
        MApplication mAplicacion = MApplication.getInstance();
        
        AllMConnections conexionesGuardadas = mAplicacion.mConexionesGuardadas;
        MConnection conexion = conexionesGuardadas.getMConexion(pestana.uuidConexion);
        
        ParserTextSQL analizador = new ParserTextSQL(
                conexion.getTipoDeBaseDeDatos(),
                pestana.getTextoEditorSinIndentar()
        );
        int numConsultas = analizador.numConsultasExistentes();
        
        
        List<String> consultas = new ArrayList<>();
        for(int i=1; i <= numConsultas; i++) {
            consultas.add(
                    analizador.getConsulta(i)
            );
        }
        return consultas;
    }
    
    private List<String> formatearConsultas(List<String> consultas) {
        List<String> formateadas = new ArrayList<>();
        
        for(String consulta : consultas) {
            formateadas.add(formatearConsulta(consulta));
        }
        return formateadas;
    }
    
    private String formatearConsulta(String consulta) {
        String formateada = formatter.format(consulta);
        
        return formateada;
    }
}
