package com.stylishdb.bd.domain;

import static com.stylishdb.bd.domain.TiposBasesDeDatos.*;

/**
 *
 ** @author StylishDB
 */
public class ObtenerUrlConexion {

    public static synchronized String getUrlConexion(
            TIPO_BASE_DATOS tipoBaseDeDatos,
            String ip,
            String puerto,
            String sid) {
        if(TIPO_BASE_DATOS.MYSQL.equals(tipoBaseDeDatos)) {
            return "jdbc:mysql://" + ip.trim() + ":" + puerto.trim() + "/" + sid.trim();
        } else if(TIPO_BASE_DATOS.ORACLE.equals(tipoBaseDeDatos)) {
            return "jdbc:oracle:thin:@" + ip.trim() + ":" + puerto.trim() + ":" + sid.trim();
        }
        
        return "";
    }
    
}
