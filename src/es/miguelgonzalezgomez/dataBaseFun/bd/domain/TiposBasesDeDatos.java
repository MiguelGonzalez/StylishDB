package es.miguelgonzalezgomez.dataBaseFun.bd.domain;

import es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.lenguajes.MySQL;

/**
 *
 * @author Miguel González
 */
public class TiposBasesDeDatos {
    public static enum TIPO_BASE_DATOS {
        MYSQL(1,
                "MySQL","com.mysql.jdbc.Driver",
                "3306"),
        ORACLE(2,
                "Oracle","oracle.jdbc.driver.OracleDriver",
                "PUERTO_ORACLE");
        
        private int identificadorUnico;
        private String nombrePresentable;
        private String claseDriver;
        private String puertoDriver;

        private TIPO_BASE_DATOS(
                int identificadorUnico,
                String nombrePresentable,
                String claseDriver,
                String puertoDriver) {
            this.identificadorUnico = identificadorUnico;
            this.nombrePresentable = nombrePresentable;
            this.claseDriver = claseDriver;
            this.puertoDriver = puertoDriver;
        }
        
        public String[] getPalabrasReservadas() {
            return MySQL.palabrasClavesReservadas;
        }
        
        public String[] getFuncionesReservadas() {
            return MySQL.funcionesReservadas;
        }
        
        public String[] getFuncionesEspecialesReservadas() {
            return MySQL.funcionesEspecialesReservadas;
        }
        
        public String getClaseDriver() {
            return claseDriver;
        }
        
        public String getPuertoDriver() {
            return puertoDriver;
        }
        
        @Override
        public String toString() {
            return nombrePresentable;
        }
        
        public boolean equals(TIPO_BASE_DATOS tipoBaseDatos) {
            return tipoBaseDatos.identificadorUnico == identificadorUnico;
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
