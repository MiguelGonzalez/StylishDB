package com.stylishdb.bd;

import com.stylishdb.bd.formatter.Formmater;
import com.stylishdb.domain.MAplicacion;
import com.stylishdb.domain.MConexion;
import com.stylishdb.domain.MConexionesGuardadas;
import com.stylishdb.domain.MPestana;
import java.util.ArrayList;
import java.util.List;

/**
 *
 ** @author StylishDB
 */
public class FormatearTextoPestana {

    private Formmater formatter;

    public FormatearTextoPestana() {
        formatter = new Formmater();
    }
    
    public void pestana(MPestana pestanaActiva) {
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
    
    private List<String> consultasSQL(MPestana pestana) {
        MAplicacion mAplicacion = MAplicacion.getInstance();
        
        MConexionesGuardadas conexionesGuardadas = mAplicacion.mConexionesGuardadas;
        MConexion conexion = conexionesGuardadas.getMConexion(pestana.uuidConexion);
        
        AnalizadorTextoConsulta analizador = new AnalizadorTextoConsulta(
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
