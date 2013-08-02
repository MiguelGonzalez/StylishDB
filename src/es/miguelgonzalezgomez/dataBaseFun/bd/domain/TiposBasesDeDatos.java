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
        MYSQL(1),
        ORACLE(2);

        private int identificadorUnico;
        private DatosBaseDatos datosBaseDatos = null;
        
        private TIPO_BASE_DATOS(
                int identificadorUnico) {
            this.identificadorUnico = identificadorUnico;
            inicializarDatosBaseDatos();
        }
        
        
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
            if(datosBaseDatos == null) {
                switch(identificadorUnico) {
                    case 1:
                        datosBaseDatos = new MySQL();
                        break;
                    case 2:
                        datosBaseDatos = new ORACLE();
                        break;
                }
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
        if(nombreBaseDatos.equals(TIPO_BASE_DATOS.MYSQL)) {
            return TIPO_BASE_DATOS.MYSQL;
        }
        if(nombreBaseDatos.equals(TIPO_BASE_DATOS.ORACLE)) {
            return TIPO_BASE_DATOS.ORACLE;
        }
        
        return null;
    }
}
