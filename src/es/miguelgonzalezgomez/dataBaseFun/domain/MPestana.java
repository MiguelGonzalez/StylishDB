package es.miguelgonzalezgomez.dataBaseFun.domain;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Miguel González
 */
public class MPestana {
    
    public final UUID uuidConexion;
    public final UUID uuidPestana;
    
    protected String nombrePestana;
    protected String textoEditor;
    
    protected boolean hayTextoSeleccionado;
    protected String textoSeleccionado;
    
    private transient CopyOnWriteArrayList<PestanaListener> listeners;
    
    public MPestana(UUID uuidConexion, String nombrePestana) {
        this(uuidConexion);
        
        this.nombrePestana = nombrePestana;
        listeners = new CopyOnWriteArrayList<>();
    }
    
    public void addPestanaListener(PestanaListener listener) {
        comprobarInicializacionListeners();
        listeners.add(listener);
    }
    
    public void removePestanaListener(PestanaListener listener) {
        comprobarInicializacionListeners();
        listeners.remove(listener);
    }
    
    public MPestana(UUID uuidConexion) {
        this.uuidConexion = uuidConexion;
        uuidPestana = UUID.randomUUID();
        
        nombrePestana = "Consultas";
        textoEditor = "";
    }
    
    @Override
    public String toString() {
        return nombrePestana;
    }
    
    @Override
    public boolean equals(Object pestanaEditor) {
        if(pestanaEditor instanceof MPestana) {
            return uuidPestana.equals(
                    ((MPestana) pestanaEditor).uuidPestana
                );
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return uuidPestana.hashCode();
    }
    
    public String getNombrePestana() {
        return nombrePestana;
    }
    
    public String getTextoEditorSinIndentar() {
        StringBuilder sb = new StringBuilder();
        String lineas[] = textoEditor.split("\n");
        for(String linea : lineas) {
            sb.append(linea.trim()).append("\n");
        }
        return sb.toString();
    }
    
    public String getTextoEditor() {
        return textoEditor;
    }
    
    public String getTextoConsultaLanzar() {
        if(hayTextoSeleccionado) {
            return textoSeleccionado;
        }
        return textoEditor;
    }
    
    public void setNombrePestana(String nombrePestana) {
        this.nombrePestana = nombrePestana;
        
        comprobarInicializacionListeners();
        for(PestanaListener listener : listeners) {
            listener.renombrada(this);
        }
    }
    
    public void setTextoSeleccionado(String texto, boolean estaSeleccionado) {
        this.textoSeleccionado = texto;
        this.hayTextoSeleccionado = estaSeleccionado;
        
        comprobarInicializacionListeners();
        for(PestanaListener listener : listeners) {
            listener.textoSeleccionado(this);
        }
    }
    
    public void setTextoEditor(String textoEditor) {
        if(!textoEditor.equals(this.textoEditor)) {
            this.textoEditor = textoEditor;

            comprobarInicializacionListeners();
            for(PestanaListener listener : listeners) {
                listener.textoModificado(this);
            }
        }
    }
    
    public void notificarTextoFormateado() {
        comprobarInicializacionListeners();
            for(PestanaListener listener : listeners) {
                listener.textoFormateado(this);
            }
    }
    
    private void comprobarInicializacionListeners() {
        if(listeners == null) {
            listeners = new CopyOnWriteArrayList();
        }
    }
}
