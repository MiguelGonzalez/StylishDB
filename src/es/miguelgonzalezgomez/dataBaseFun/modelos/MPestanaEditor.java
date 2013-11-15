package es.miguelgonzalezgomez.dataBaseFun.modelos;

import java.util.UUID;

/**
 *
 * @author Miguel González
 */
public class MPestanaEditor {
    
    public UUID uuidConexion;
    public UUID uuidPestana;
    
    public String nombrePestana;
    public String contenidoTexto;
    
    public boolean hayTextoSeleccionado;
    public String textoSeleccionado;
    
    public MPestanaEditor(UUID uuidConexion, String nombrePestana) {
        this(uuidConexion);
        
        this.nombrePestana = nombrePestana;
    }
    
    public MPestanaEditor(UUID uuidConexion) {
        this.uuidConexion = uuidConexion;
        uuidPestana = UUID.randomUUID();
        
        nombrePestana = "Consultas";
        contenidoTexto = "";
    }
    
    @Override
    public MPestanaEditor clone() {
        MPestanaEditor mPestanaEditor = new MPestanaEditor(uuidConexion,
                nombrePestana);
        mPestanaEditor.uuidPestana = uuidPestana;
        mPestanaEditor.contenidoTexto = contenidoTexto;
        mPestanaEditor.hayTextoSeleccionado = hayTextoSeleccionado;
        mPestanaEditor.textoSeleccionado = textoSeleccionado;

        return mPestanaEditor;
    }
    
    @Override
    public boolean equals(Object pestanaEditor) {
        if(pestanaEditor instanceof MPestanaEditor) {
            return uuidPestana.equals(
                    ((MPestanaEditor) pestanaEditor).uuidPestana
                );
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return uuidPestana.hashCode();
    }
    
    public String getTextoConsultaLanzar() {
        if(hayTextoSeleccionado) {
            return textoSeleccionado;
        }
        return contenidoTexto;
    }
}
