package com.stylishdb.db;

import com.stylishdb.db.domain.DatosColumna;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 ** @author StylishDB
 */
public class ManejadorDatoConsulta {
    
    private ResultSet rsQuery;

    ManejadorDatoConsulta(ResultSet rsQuery) {
        this.rsQuery = rsQuery;
    }
    
    public String getDatoColumna(int i, int columnType, DatosColumna datosColumna)
            throws ManejadorConsultaErrorSQL
    {
        return getDatoColumnaParseado(i, columnType, datosColumna.scale);
    }
    
    private String getDatoColumnaParseado(
            int numColumna,
            int tipoColumna,
            int numDecimales
    )
            throws ManejadorConsultaErrorSQL {
        try {
            switch(tipoColumna) {
                case java.sql.Types.BINARY: 
                    return "BINARY";
                case java.sql.Types.BIT: 
                    return "BIT";
                case java.sql.Types.DATALINK: 
                    return "DATALINK";
                case java.sql.Types.DISTINCT: 
                    return "DISTINCT";
                case java.sql.Types.JAVA_OBJECT: 
                    return "JAVA_OBJECT";    
                case java.sql.Types.NULL: 
                    return "NULL";
                case java.sql.Types.OTHER: 
                    return "OTHER";
                case java.sql.Types.ROWID: 
                    return "ROWID";
                case java.sql.Types.SQLXML: 
                    return "SQLXML";
                case java.sql.Types.STRUCT: 
                    return "STRUCT";
                case java.sql.Types.VARBINARY: 
                    return "VARBINARY";
                case java.sql.Types.INTEGER: 
                case java.sql.Types.SMALLINT: 
                case java.sql.Types.TINYINT: 
                    return getIntValue(numColumna);
                case java.sql.Types.BIGINT:
                    return getLongValue(numColumna);
                case java.sql.Types.FLOAT: 
                case java.sql.Types.NUMERIC: 
                    return getFloatValue(numColumna, numDecimales);
                case java.sql.Types.DECIMAL: 
                case java.sql.Types.DOUBLE: 
                case java.sql.Types.REAL: 
                    return getDoubleValue(numColumna);
                case java.sql.Types.REF: 
                    return getRefValue(numColumna);
                case java.sql.Types.CHAR: 
                case java.sql.Types.LONGNVARCHAR:    
                case java.sql.Types.LONGVARBINARY: 
                case java.sql.Types.LONGVARCHAR: 
                case java.sql.Types.VARCHAR:
                    return getStringValue(numColumna);
                case java.sql.Types.NVARCHAR: 
                case java.sql.Types.NCHAR: 
                    return getNStringValue(numColumna);
                case java.sql.Types.DATE: 
                    return getDateValue(numColumna);
                case java.sql.Types.TIME: 
                    return getTimeValue(numColumna);
                case java.sql.Types.TIMESTAMP: 
                    return getTimeStampValue(numColumna);
                case java.sql.Types.NCLOB: 
                    return getNClobValue(numColumna);
                case java.sql.Types.ARRAY:
                    return getArrayValue(numColumna);
                case java.sql.Types.BLOB: 
                    return getBlobValue(numColumna);
                case java.sql.Types.BOOLEAN:
                    return getBooleanValue(numColumna);
                case java.sql.Types.CLOB: 
                    return getClobValue(numColumna);                
            }
        } catch (SQLException ex) {
            throw new ManejadorConsultaErrorSQL(ex);
        }
        return "";
    }
    
    private String getBooleanValue(int numColumna) throws SQLException {
        Boolean booleanV = rsQuery.getBoolean(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        }
        return Boolean.toString(booleanV);
    }
    
    private String getClobValue(int numColumna) throws SQLException {
        Clob clob = rsQuery.getClob(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        }
        
        InputStream in = clob.getAsciiStream();
        Reader read = new InputStreamReader(in, Charset.forName("UTF-8"));
        StringWriter write = new StringWriter();

        try {
            int c;
            while ((c = read.read()) != -1)
            {
                write.write(c);
            }
        } catch (IOException ex) {
            Logger.getLogger(ManejadorDatoConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
        write.flush();
        return write.toString();
    }
    
    private String getBlobValue(int numColumna) throws SQLException {
        Blob blob = rsQuery.getBlob(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        }

        return blob.toString();
    }
    
    private String getArrayValue(int numColumna) throws SQLException {
        Array array = rsQuery.getArray(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        }
        Object object = array.getArray();
        if(object == null) {
            return "NULL";
        } else {
            return object.toString();
        }
    }
    
    private String getNClobValue(int numColumna) throws SQLException {
        NClob nClob = rsQuery.getNClob(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        }
        return nClob.toString();
    }
    
    private String getTimeStampValue(int numColumna) throws SQLException {
        Timestamp timestamp = rsQuery.getTimestamp(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        }
        return timestamp.toString();
    }
    
    private String getTimeValue(int numColumna) throws SQLException {
        Time time = rsQuery.getTime(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        }
        return time.toString();
    }
    
    private String getDateValue(int numColumna) throws SQLException {
        Date date = rsQuery.getDate(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        }
        return date.toString();
    }
    
    private String getNStringValue(int numColumna) throws SQLException {
        String texto = rsQuery.getNString(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        }
        return texto;
    }
    
    private String getStringValue(int numColumna) throws SQLException {
        String texto = rsQuery.getString(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        }
        return texto;
    }
    
    private String getIntValue(int numColumna) throws SQLException {
        int numero = rsQuery.getInt(numColumna); 
        if(rsQuery.wasNull()) {
            return "NULL";
        }
        return Integer.toString(numero);
    }
    
    private String getLongValue(int numColumna) throws SQLException {
        Long numero = rsQuery.getLong(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        }
        return Long.toString(numero);
    }
    
    private String getFloatValue(int numColumna, int numDecimales)
            throws SQLException {
        Float numero = rsQuery.getFloat(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        }
        if(numDecimales == 0) {
            return Integer.toString(numero.intValue());
        } else {
            return Float.toString(rsQuery.getFloat(numColumna));
        }
    }
    
    private String getDoubleValue(int numColumna) throws SQLException {
        Double numero = rsQuery.getDouble(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        }
        return Double.toString(numero);
    }
    
    private String getRefValue(int numColumna) throws SQLException {
        Ref ref = rsQuery.getRef(numColumna);
        if(rsQuery.wasNull()) {
            return "NULL";
        } else {
            Object object = ref.getObject();
            if(object == null) {
                return "NULL";
            } else {
                return object.toString();
            }
        }
    }
}
