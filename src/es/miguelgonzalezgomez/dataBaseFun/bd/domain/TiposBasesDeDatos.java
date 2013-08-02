package es.miguelgonzalezgomez.dataBaseFun.bd.domain;

import es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.lenguajes.DatosBaseDatos;
import es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.lenguajes.MySQL;
import es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.lenguajes.ORACLE;

/**
 *
 * @author Miguel González
 */
public class TiposBasesDeDatos {
    public static enum TIPO_BASE_DATOS {
        MYSQL,
        ORACLE;

        private DatosBaseDatos datosBaseDatos = null;
        
        public DatosBaseDatos getDatosBaseDatos() {
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
            }
        }
    };
    
    public static String[] getNombresBasesDatos() {
        return new String[]{
            TIPO_BASE_DATOS.MYSQL.toString(),
            TIPO_BASE_DATOS.ORACLE.toString()
        };
    }
    
    public static TIPO_BASE_DATOS devolverTipoBaseDatos(String nombreBaseDatos) {
        if(nombreBaseDatos.equals(TIPO_BASE_DATOS.MYSQL.toString())) {
            return TIPO_BASE_DATOS.MYSQL;
        }
        if(nombreBaseDatos.equals(TIPO_BASE_DATOS.ORACLE.toString())) {
            return TIPO_BASE_DATOS.ORACLE;
        }
        
        return null;
    }
}
