package com.stylishdb.bd.estaticos.lenguajes;

import com.stylishdb.bd.domain.TiposBasesDeDatos;

/**
 *
 ** @author StylishDB
 */
public class MySQL implements DatosBaseDatos {
    
    public static String nombrePresentable = "MySQL";
    public static String claseDriver = "com.mysql.jdbc.Driver";
    public static String puertoDriver = "3306";
    
    public static String[][] palabrasClaveDelimitadoresConsulta = {
        {"SELECT",";"},
        {"SHOW",";"},
        {"UPDATE",";"},
        {"INSERT",";"},
        {"DELETE",";"},
        {"DROP",";"},
        {"TRUNCATE",";"}
    };

    public static String[] palabrasClaveEjecutarConsulta = {
        "SELECT",
        "SHOW"
    };
        
    public static String[] palabrasClavesReservadas = {
        "ADD","ALL","ALTER","ANALYZE","AND","AS","ASC","ASENSITIVE",
        "BEFORE","BETWEEN","BIGINT","BINARY","BLOB","BOTH","BY","CALL",
        "CASCADE","CASE","CHANGE","CHARACTER","CHECK","COLLATE",
        "COLUMN","CONDITION","CONSTRAINT","CONTINUE","CREATE",
        "CROSS","CURRENT_DATE","CURRENT_TIME","CURRENT_TIMESTAMP",
        "CURSOR","DATABASES","DAY_HOUR",
        "DAY_MICROSECOND","DAY_MINUTE","DAY_SECOND","DEC","DECIMAL",
        "DECLARE","DEFAULT","DELAYED","DELETE","DESC","DESCRIBE",
        "DETERMINISTIC","DISTINCT","DISTINCTROW","DOUBLE","DROP",
        "DUAL","EACH","ELSE","ELSEIF","ENCLOSED","ESCAPED","EXISTS",
        "EXIT","EXPLAIN","FALSE","FETCH","FLOAT","FLOAT4","FLOAT8",
        "FOR","FORCE","FOREIGN","FROM","FULLTEXT","GRANT","GROUP",
        "HAVING","HIGH_PRIORITY","HOUR_MICROSECOND","HOUR_MINUTE",
        "HOUR_SECOND","IF","IGNORE","IN","INDEX","INFILE","INNER",
        "INOUT","INSENSITIVE","INSERT","INT","INT1","INT2","INT3",
        "INT4","INT8","INTEGER","INTERVAL","INTO","IS","ITERATE",
        "JOIN","KEY","KEYS","KILL","LEADING","LEAVE",
        "LIMIT","LINES","LOAD","LOCALTIME","LOCALTIMESTAMP","LOCK",
        "LONG","LONGBLOB","LONGTEXT","LOOP","LOW_PRIORITY",
        "MEDIUMBLOB","MEDIUMINT","MEDIUMTEXT","MIDDLEINT",
        "MINUTE_MICROSECOND","MINUTE_SECOND","MODIFIES",
        "NATURAL","NOT","NO_WRITE_TO_BINLOG","NULL","NUMERIC","ON",
        "OPTIMIZE","OPTION","OPTIONALLY","OR","ORDER","OUT","OUTER",
        "OUTFILE","PRECISION","PRIMARY","PROCEDURE","PURGE","READ",
        "READS","REAL","REFERENCES","RELEASE","RENAME",
        "REQUIRE","RESTRICT","RETURN","REVOKE",
        "SECOND_MICROSECOND",
        "SELECT","SENSITIVE","SEPARATOR","SET","SHOW","SMALLINT","SONAME",
        "SPATIAL","SPECIFIC","SQL","SQLEXCEPTION","SQLSTATE","SQLWARNING",
        "SQL_BIG_RESULT","SQL_CALC_FOUND_ROWS","SQL_SMALL_RESULT","SSL",
        "STARTING","STRAIGHT_JOIN","TABLE","TERMINATED","THEN","TINYBLOB",
        "TINYINT","TINYTEXT","TO","TRAILING","TRIGGER","TRUE","UNDO",
        "UNION","UNIQUE","UNLOCK","UNSIGNED","UPDATE","USAGE","USE",
        "USING","UTC_DATE","UTC_TIME","UTC_TIMESTAMP","VALUES","VARBINARY",
        "VARCHAR","VARCHARACTER","VARYING","WHEN","WHERE","WHILE","WITH",
        "WRITE","XOR","YEAR_MONTH","ZEROFILL",
        "GET_LOCK","INET_ATON","INET_NTOA","IS_FREE_LOCK","IS_USED_LOCK",
        "MASTER_POS_WAIT","NAME_CONST","RELEASE_LOCK","SLEEP"
    };
    
    public static String[] funcionesReservadas = {
        "ASCII","BIN","BIT_LENGTH","CHAR_LENGTH","CHAR","CHARACTER_LENGTH",
        "CONCAT_WS","CONCAT","ELT","EXPORT_SET","FIELD","FIND_IN_SET","FORMAT",
        "HEX","INSTR","LCASE","LEFT","LENGTH","LIKE","LOAD_FILE","LOCATE",
        "LOWER","LPAD","LTRIM","MAKE_SET","MATCH","MID","OCT","OCTET_LENGTH",
        "ORD","POSITION","QUOTE","REGEXP","REPEAT","REPLACE","REVERSE","RIGHT",
        "RLIKE","RPAD","RTRIM","SOUNDEX","SOUNDS LIKE","SPACE","STRCMP","SUBSTR",
        "SUBSTRING_INDEX","SUBSTRING","TRIM","UCASE","UNHEX","UPPER",
        "NULLIF", "IFNULL","ABS","ACOS","ASIN","ATAN2","ATAN","CEIL","CEILING",
        "CONV","COS","COT","CRC32","DEGRESS","DIV","EXP","FLOOR","LN","LOG10",
        "LOG2","LOG","MOD","PI","POW","POWER","RADIANS","RAND","ROUND","SIGN",
        "SIN","SQRT","TAN","TRUNCATE","CONVERT",
        "ADDDATE","ADDTIME","CONVERT_TZ","CURDATE","CURTIME","DATE_ADD",
        "DATE_FORMAT","DATE_SUB","DATE","DATEDIFF","DAY","DAYNAME","DAYOFMONTH",
        "DAYOFWEEK","DAYOFYEAR","EXTRACT","FROM_DAYS","FROM_UNIXTIME",
        "GET_FORMAT","HOUR","LAST_DAY","MAKEDATE","MAKETIME","MICROSECOND",
        "MINUTE","MONTH","MONTHNAME","NOW","PERIOD_ADD","PERIOD_DIFF",
        "QUARTER","SEC_TO_TIME","SECOND","STR_TO_DATE","SUBDATE","SUBTIME",
        "SYSDATE","TIME_FORMAT","TIME_TO_SEC","TIME","TIMEDIFF","TIMESTAMP",
        "TIMESTAMPADD","TIMESTAMPDIFF","TO_DAYS","UNIX_TIMESTAMP","WEEK",
        "WEEKDAY","WEEKOFYEAR","YEAR","YEARWEEK","CAST",
        "GROUP_BY","UUID","MAX","MIN","AVG","BIT_AND","BIT_OR","BIT_XOR",
        "COUNT","GROUP_CONCAT","STD","STDDEV_POP","STDDEV_SAMP","VAR_POP",
        "VAR_SAMP","VARIANCE"
    };
    
    public static String[] funcionesEspecialesReservadas = {
        "AES_DECRYPT","AES_ENCRYPT","COMPRESS","DECODE","DES_DECRYPT",
        "DES_ENCRYPT","ENCODE","ENCRYPT","MD5","OLD_PASSWORD","PASSWORD",
        "SHA1","SHA","UNCOMPRESS","UNCOMPRESSED_LENGTH",
        "BENCHMARK","CHARSET","COERCIBILITY","COLLATION","CONNECTION_ID",
        "CURRENT_USER","DATABASE","FOUND_ROWS","LAST_INSERT_ID","ROW_COUNT",
        "SCHEMA","SCHEMAS","SESSION_USER","SYSTEM_USER","USER","VERSION"
    };

    @Override
    public String[] getPalabrasReservadas() {
        return palabrasClavesReservadas;
    }

    @Override
    public String[] getFuncionesReservadas() {
        return funcionesReservadas;
    }

    @Override
    public String[] getFuncionesEspecialesReservadas() {
        return funcionesEspecialesReservadas;
    }

    @Override
    public String getClaseDriver() {
        return claseDriver;
    }

    @Override
    public String getPuertoDriver() {
        return puertoDriver;
    }

    @Override
    public boolean equals(TiposBasesDeDatos.TIPO_BASE_DATOS tipoBaseDatos) {
        return tipoBaseDatos.equals(TiposBasesDeDatos.TIPO_BASE_DATOS.MYSQL);
    }
    
    @Override
    public String toString() {
        return nombrePresentable;
    }

    @Override
    public boolean tieneComentarioDeLinea() {
        return true;
    }

    @Override
    public boolean tieneComentarioDeBloque() {
        return true;
    }
    
    @Override
    public String[] getPalabrasClaveEjecutarConsulta() {
        return palabrasClaveEjecutarConsulta;
    }

    @Override
    public String[][] getPalabrasClaveDelimitadoresConsulta() {
        return palabrasClaveDelimitadoresConsulta;
    }
}
