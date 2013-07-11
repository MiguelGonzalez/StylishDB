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
        mPestanaEditor.uuidPestana = uuidPestana;
        
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
        int hashCode = super.hashCode();
        hashCode += contenidoTexto.hashCode();
        hashCode += uuidPestana.hashCode();
        hashCode += mConexion.hashCode();
        return hashCode;
    }
    
}
