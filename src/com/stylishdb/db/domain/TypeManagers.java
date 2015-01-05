package com.stylishdb.db.domain;

import com.stylishdb.db.managers.Manager;
import com.stylishdb.db.managers.MySQL;
import com.stylishdb.db.managers.ORACLE;
import com.stylishdb.db.managers.SQL_SERVER;

/**
 *
 ** @author StylishDB
 */
public class TypeManagers {
    public static enum TIPO_BASE_DATOS {
        MYSQL,
        ORACLE,
        SQL_SERVER;

        private Manager datosBaseDatos = null;
        
        public Manager getDatosBaseDatos() {
            inicializarDatosBaseDatos();
            return datosBaseDatos;
        }
        
        @Override
        public String toString() {
            inicializarDatosBaseDatos();
            return datosBaseDatos == null?"":datosBaseDatos.toString();
        }
        
        private void inicializarDatosBaseDatos() {
            if(this == TIPO_BASE_DATOS.MYSQL) {
                datosBaseDatos = new MySQL();
            } else if (this == TIPO_BASE_DATOS.ORACLE) {
                datosBaseDatos = new ORACLE();
            } else if (this == TIPO_BASE_DATOS.SQL_SERVER) {
                datosBaseDatos = new SQL_SERVER();
            }
        }
    };
    
    public static String[] getNombresBasesDatos() {
        return new String[]{
            TIPO_BASE_DATOS.MYSQL.toString(),
            TIPO_BASE_DATOS.ORACLE.toString(),
            TIPO_BASE_DATOS.SQL_SERVER.toString()
        };
    }
    
    public static TIPO_BASE_DATOS devolverTipoBaseDatos(String nombreBaseDatos) {
        if(nombreBaseDatos.equals(TIPO_BASE_DATOS.MYSQL.toString())) {
            return TIPO_BASE_DATOS.MYSQL;
        }
        if(nombreBaseDatos.equals(TIPO_BASE_DATOS.ORACLE.toString())) {
            return TIPO_BASE_DATOS.ORACLE;
        }
        if(nombreBaseDatos.equals(TIPO_BASE_DATOS.SQL_SERVER.toString())) {
            return TIPO_BASE_DATOS.SQL_SERVER;
        }
        
        return null;
    }
}
