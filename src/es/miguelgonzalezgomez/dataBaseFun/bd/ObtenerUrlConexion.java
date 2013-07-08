package es.miguelgonzalezgomez.dataBaseFun.bd;

import static es.miguelgonzalezgomez.dataBaseFun.bd.TiposBasesDeDatos.*;

/**
 *
 * @author Miguel González
 */
public class ObtenerUrlConexion {

    public static synchronized String getUrlConexion(
            String gestor,
            String ip,
            String puerto,
            String sid) {
        switch (gestor) {
            case ADABASD:
                return "jdbc:adabasd://" + ip.trim() + ":" + puerto.trim() + "/" + sid.trim();
            case CLOUDSCAPE:
                return "jdbc:cloudscape:" + sid.trim();
            case CLOUDSCAPE_RMI:
                return "jdbc:rmi://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") + "/jdbc:cloudscape:" + sid.trim();
            case CUBRID:
                return "jdbc:cubrid:" + ip.trim() + ":" + ((puerto.trim().length() > 0)?puerto.trim():"") + ":" + ((sid.trim().length() > 0)?sid.trim():"") + ":::";
            case DAFFODILDB_EMBEDDED:
                return "jdbc:daffodilDB_embedded:" + sid.trim();
            case DAFFODILDB_SERVER:
                return "jdbc:daffodilDB://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") + "/" + sid.trim();
            case DB2:
                return "jdbc:db2://" + ip.trim() + ":" + puerto.trim() + "/" + sid.trim();
            case DB2_CAE:
                return "jdbc:DB2:" + sid.trim();
            case DB2_UNIVERSAL:
                return "jdbc:db2j:net://" + ip.trim() + ":" + puerto.trim() + "/" + sid.trim();
            case DERBY_SERVER:
                return "jdbc:derby://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") + "/" + sid.trim();
            case DERBY_EMBEDDED_MEMORY:
                return "jdbc:derby:memory:" + sid.trim();
            case DERBY_EMBEDDED:
                return "jdbc:derby:" + sid.trim();
            case FIREBIRD:
                return "jdbc:firebirdsql:" + ip.trim() + "/" + puerto.trim() + ":" + sid.trim();
            case FIRSTSQL:
                return "jdbc:dbcp://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"");
            case FRONTBASE:
                return "jdbc:frontbase://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") + ((sid.trim().length() > 0)?"/" + sid.trim():"");
            case H2_EMBEDDED:
                return "jdbc:h2:" + sid.trim();
            case H2_SERVER:
                return "jdbc:h2:tcp://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") + ((sid.trim().length() > 0)?"/" + sid.trim():"");
            case HYPERSONIC13:
                return "jdbc:HypersonicSQL:" + sid.trim();
            case HYPERSQL_EMBEDDED:
                return "jdbc:hsqldb:" + sid.trim();
            case HYPERSQL_SERVER:
                return "jdbc:hsqldb:hsql://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") + ((sid.trim().length() > 0)?"/" + sid.trim():"");
            case INGRES:
                return "jdbc:ingres://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") + ((sid.trim().length() > 0)?"/" + sid.trim():"");
            case INSTANTDB314:
                return "jdbc:idb:" + sid.trim();
            case INTERBASE:
                return "jdbc:interbase://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") + "/" + sid.trim();
            case INTESYSTEMS:
                return "jdbc:Cache://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") + ((sid.trim().length() > 0)?"/" + sid.trim():"");
            case JTDS_SQLSERVER:
                return "jdbc:jtds:sqlserver://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") +
                        ((sid.trim().length() > 0)?"/" + sid.trim():"");
            case JTDS_SYBASE:
                return "jdbc:jtds:sybase://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") +
                        ((sid.trim().length() > 0)?"/" + sid.trim():"");
            case MIMER:
                return "jdbc:mimer://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") + ((sid.trim().length() > 0)?"/" + sid.trim():"");
            case MYSQL:
                return "jdbc:mysql://" + ip.trim() + ":" + puerto.trim() + "/" + sid.trim();
            case ODBC:
                return "jdbc:odbc:" + sid.trim();
            case ORACLE:
                return "jdbc:oracle:thin:@" + ip.trim() + ":" + puerto.trim() + ":" + sid.trim();
            case ORACLE_OCI_8:
                return "jdbc:oracle:oci8:@" + sid.trim();
            case ORACLE_OCI_9:
                return "jdbc:oracle:oci:@" + sid.trim();
            case PERVASIVE:
                return "jdbc:pervasive://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") + ((sid.trim().length() > 0)?"/" + sid.trim():"");
            case POINTBASE_SERVER:
                return "jdbc:pointbase:server://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") + "/" + sid.trim();
            case POINTBASE_EMBEDDED:
                return "jdbc:pointbase:embedded:" + sid.trim();
            case POINTBASE_MICRO:
                return "jdbc:pointbase:micro:" + sid.trim();
            case POSTGRESQL7:
                return "jdbc:postgresql://" + ip.trim() + ":" + puerto.trim() + "/" + sid.trim();
            case SAPDB:
                return "jdbc:sapdb://" + ip.trim() + ((puerto.trim().length() > 0)?":" + puerto.trim():"") +
                        ((sid.trim().length() > 0)?"/" + sid.trim():"");
            case SQLSERVER:
                return "jdbc:sqlserver://" + ip.trim() + ":" + puerto.trim();
            case SQL:
                return "jdbc:microsoft:sqlserver://" + ip.trim() + ":" + puerto.trim();
            case SYBASE5:
                return "jdbc:sybase:Tds:" + ip.trim() + ":" + puerto.trim();
            case CUSTOM:
                return "custom:" + sid.trim() + "???" + ip.trim();
        }
        return "";
    }
    
}
