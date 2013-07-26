package es.miguelgonzalezgomez.dataBaseFun.modelos;

import java.util.UUID;

/**
 *
 * @author Miguel González
 */
public class MPestanaEditor {
    
    public MConexion mConexion;
    public UUID uuidPestana;
    
    public String nombrePestana;
    public String contenidoTexto;
    
    public boolean hayTextoSeleccionado;
    public String textoSeleccionado;
    
    public MPestanaEditor(MConexion mConexion) {
        this.mConexion = mConexion;
        
        nombrePestana = mConexion.nombre;
        
        contenidoTexto = "";
        uuidPestana = UUID.randomUUID();
    }
    
    @Override
    public MPestanaEditor clone() {
        MPestanaEditor mPestanaEditor = new MPestanaEditor(mConexion);
        mPestanaEditor.contenidoTexto = contenidoTexto;
        mPestanaEditor.nombrePestana = nombrePestana;
        mPestanaEditor.uuidPestana = uuidPestana;
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
}
