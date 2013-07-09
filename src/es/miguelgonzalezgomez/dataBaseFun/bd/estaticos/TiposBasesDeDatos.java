package es.miguelgonzalezgomez.dataBaseFun.bd.estaticos;

/**
 *
 * @author Miguel González
 */
public class TiposBasesDeDatos {
    public final static String ADABASD = "adabasd";
    public final static String CLOUDSCAPE = "Cloudscape";
    public final static String CLOUDSCAPE_RMI = "Cloudscape RMI";
    public final static String CUBRID = "cubrid";
    public final static String CUSTOM = "custom";
    public final static String DAFFODILDB_EMBEDDED = "DaffodilDB Embedded";
    public final static String DAFFODILDB_SERVER = "DaffodilDB Server";
    public final static String DB2 = "db2";
    public final static String DB2_CAE = "db2 CAE";
    public final static String DB2_UNIVERSAL = "db2 Universal";
    public final static String DERBY_EMBEDDED = "Derby embedded";
    public final static String DERBY_EMBEDDED_MEMORY = "Derby embedded memory";
    public final static String DERBY_SERVER = "Derby server";
    public final static String FIREBIRD = "firebirdsql";
    public final static String FIRSTSQL = "firstsql";
    public final static String FRONTBASE = "FrontBase";
    public final static String H2_EMBEDDED = "H2 Embedded";
    public final static String H2_SERVER = "H2 Server";
    public final static String HYPERSONIC13 = "Hypersonic SQL v1.3";
    public final static String HYPERSQL_EMBEDDED = "HyperSQL embedded";
    public final static String HYPERSQL_SERVER = "HyperSQL server";
    public final static String INFORMIX = "informix-sqli";
    public final static String INGRES = "Ingres";
    public final static String INSTANTDB314 = "InstantDB v3.14";
    public final static String INTERBASE = "Interbase";
    public final static String INTESYSTEMS = "InterSystems Cache";
    public final static String JTDS_SQLSERVER = "JTDS Sqlserver";
    public final static String JTDS_SYBASE = "JTDS Sybase";
    public final static String MIMER = "Mimer";
    public final static String MYSQL = "mysql";
    public final static String ODBC = "odbc";
    public final static String ORACLE = "oracle";
    public final static String ORACLE_OCI_8 = "oracle oci 8i";
    public final static String ORACLE_OCI_9 = "oracle oci 9i";
    public final static String PERVASIVE = "Pervasive";
    public final static String POINTBASE_SERVER = "PointBase Server";
    public final static String POINTBASE_EMBEDDED = "PointBase Embedded";
    public final static String POINTBASE_MICRO = "PointBase Micro";
    public final static String POSTGRESQL7 = "postgresql 7.0";
    public final static String SAPDB = "Sap MaxDB";
    public final static String SQL = "microsoft Driver JDBC Sqlserver 2000";
    public final static String SQLSERVER = "sqlserver Driver JDBC Sqlserver 2005";
    public final static String SYBASE5 = "Sybase 5.2";
    
    public static String[] getNombresBasesDatos() {
        return new String[]{
            ADABASD, CLOUDSCAPE, CLOUDSCAPE_RMI, CUBRID, CUSTOM, DAFFODILDB_EMBEDDED,
            DAFFODILDB_SERVER, DB2, DB2_CAE, DB2_UNIVERSAL, DERBY_EMBEDDED,
            DERBY_EMBEDDED_MEMORY, DERBY_SERVER, FIREBIRD, FIRSTSQL, FRONTBASE,
            H2_EMBEDDED, H2_SERVER, HYPERSONIC13, HYPERSQL_EMBEDDED, HYPERSQL_SERVER,
            INFORMIX, INGRES, INSTANTDB314, INTERBASE, INTESYSTEMS, JTDS_SQLSERVER, JTDS_SYBASE,
            MIMER, MYSQL, ODBC, ORACLE, ORACLE_OCI_8, ORACLE_OCI_9, PERVASIVE, POINTBASE_SERVER,
            POINTBASE_EMBEDDED, POINTBASE_MICRO, POSTGRESQL7, SAPDB, SQL, SQLSERVER,
            SYBASE5
        };
    }
}
