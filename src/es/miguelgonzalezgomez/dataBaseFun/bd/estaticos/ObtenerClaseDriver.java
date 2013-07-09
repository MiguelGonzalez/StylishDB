package es.miguelgonzalezgomez.dataBaseFun.bd.estaticos;

import static es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.ClasesDrivers.*;
import static es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.TiposBasesDeDatos.*;

/**
 *
 * @author Miguel González
 */
public class ObtenerClaseDriver {
    public static synchronized String obtenerClaseDriver(String gestor) {
        switch (gestor) {
            case ADABASD:
                return CLASS_ADABASD;
            case CLOUDSCAPE:
                return CLASS_CLOUDSCAPE;
            case CLOUDSCAPE_RMI:
                return CLASS_CLOUDSCAPE_RMI;
            case CUBRID:
                return CLASS_CUBRID;
            case DAFFODILDB_EMBEDDED:
                return CLASS_DAFFODILDB_EMBEDDED;
            case DAFFODILDB_SERVER:
                return CLASS_DAFFODILDB_SERVER;
            case DB2:
                return CLASS_DB2;
            case DB2_CAE:
                return CLASS_DB2_CAE;
            case DB2_UNIVERSAL:
                return CLASS_DB2_UNIVERSAL;
            case DERBY_SERVER:
                return CLASS_DERBY_SERVER;
            case DERBY_EMBEDDED_MEMORY:
                return CLASS_DERBY_EMBEDDED;
            case DERBY_EMBEDDED:
                return CLASS_DERBY_EMBEDDED;
            case FIREBIRD:
                return CLASS_FIREBIRD;
            case FIRSTSQL:
                return CLASS_FIRSTSQL;
            case FRONTBASE:
                return CLASS_FRONTBASE;
            case H2_EMBEDDED:
                return CLASS_H2;
            case H2_SERVER:
                return CLASS_H2;
            case HYPERSONIC13:
                return CLASS_HYPERSONIC13;
            case HYPERSQL_EMBEDDED:
                return CLASS_HYPERSQL;
            case HYPERSQL_SERVER:
                return CLASS_HYPERSQL;
            case INFORMIX:
                return CLASS_INFORMIX;
            case INGRES:
                return CLASS_INGRES;
            case INSTANTDB314:
                return CLASS_INSTANTDB314;
            case INTERBASE:
                return CLASS_INTERBASE;
            case INTESYSTEMS:
                return CLASS_INTESYSTEMS;
            case JTDS_SQLSERVER:
                return CLASS_JTDS;
            case JTDS_SYBASE:
                return CLASS_JTDS;
            case MIMER:
                return CLASS_MIMER;
            case MYSQL:
                return CLASS_MYSQL;
            case ODBC:
                return CLASS_ODBC;
            case ORACLE:
                return CLASS_ORACLE;
            case ORACLE_OCI_8:
                return CLASS_ORACLE;
            case ORACLE_OCI_9:
                return CLASS_ORACLE;
            case PERVASIVE:
                return CLASS_PERVASIVE;
            case POINTBASE_SERVER:
                return CLASS_POINTBASE;
            case POINTBASE_EMBEDDED:
                return CLASS_POINTBASE;
            case POINTBASE_MICRO:
                return CLASS_POINTBASE_MICRO;
            case POSTGRESQL7:
                return CLASS_MYSQL;
            case SAPDB:
                return CLASS_SAPDB;
            case SQLSERVER:
                return CLASS_SQLSERVER2005;
            case SQL:
                return CLASS_SQLSERVER;
            case SYBASE5:
                return CLASS_SYBASE5;
        }
        
        return "";
    }
}
