package es.miguelgonzalezgomez.dataBaseFun.bd.estaticos.lenguajes;

import es.miguelgonzalezgomez.dataBaseFun.bd.domain.TiposBasesDeDatos.TIPO_BASE_DATOS;

/**
 *
 * @author Miguel González
 */
public interface DatosBaseDatos {
    public String[] getPalabrasReservadas();
    
    public String[] getFuncionesReservadas();

    public String[] getFuncionesEspecialesReservadas();

    public String getClaseDriver();

    public String getPuertoDriver();

    public boolean equals(TIPO_BASE_DATOS tipoBaseDatos);
}
