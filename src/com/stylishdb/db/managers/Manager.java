package com.stylishdb.db.managers;

import com.stylishdb.db.domain.TypeManagers.TIPO_BASE_DATOS;

/**
 *
 ** @author StylishDB
 */
public interface Manager {
    public String[] getPalabrasReservadas();
    public String[] getFuncionesReservadas();
    public String[] getFuncionesEspecialesReservadas();
    public String getClaseDriver();
    public String getPuertoDriver();
    public boolean equals(TIPO_BASE_DATOS tipoBaseDatos);
    public boolean tieneComentarioDeLinea();
    public boolean tieneComentarioDeBloque();
    @Override
    public String toString();
    
    public String[][] getPalabrasClaveDelimitadoresConsulta();
    public String[] getPalabrasClaveEjecutarConsulta();
}
