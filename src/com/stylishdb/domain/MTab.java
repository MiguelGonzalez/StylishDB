package com.stylishdb.domain;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 ** @author StylishDB
 */
public class MTab {
    
    public final UUID uuidConexion;
    public final UUID uuidPestana;
    
    protected String nombrePestana;
    protected String textoEditor;
    
    protected boolean hayTextoSeleccionado;
    protected String textoSeleccionado;
    
    private transient CopyOnWriteArrayList<MTabListener> listeners;
    
    public MTab(UUID uuidConexion, String nombrePestana) {
        this(uuidConexion);
        
        this.nombrePestana = nombrePestana;
        listeners = new CopyOnWriteArrayList<>();
    }
    
    public void addPestanaListener(MTabListener listener) {
        comprobarInicializacionListeners();
        listeners.add(listener);
    }
    
    public void removePestanaListener(MTabListener listener) {
        comprobarInicializacionListeners();
        listeners.remove(listener);
    }
    
    public MTab(UUID uuidConexion) {
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
        if(pestanaEditor instanceof MTab) {
            return uuidPestana.equals(
                    ((MTab) pestanaEditor).uuidPestana
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
        for(MTabListener listener : listeners) {
            listener.renombrada(this);
        }
    }
    
    public void setTextoSeleccionado(String texto, boolean estaSeleccionado) {
        this.textoSeleccionado = texto;
        this.hayTextoSeleccionado = estaSeleccionado;
        
        comprobarInicializacionListeners();
        for(MTabListener listener : listeners) {
            listener.textoSeleccionado(this);
        }
    }
    
    public void setTextoEditor(String textoEditor) {
        if(!textoEditor.equals(this.textoEditor)) {
            this.textoEditor = textoEditor;

            comprobarInicializacionListeners();
            for(MTabListener listener : listeners) {
                listener.textoModificado(this);
            }
        }
    }
    
    public void notificarTextoFormateado() {
        comprobarInicializacionListeners();
            for(MTabListener listener : listeners) {
                listener.textoFormateado(this);
            }
    }
    
    private void comprobarInicializacionListeners() {
        if(listeners == null) {
            listeners = new CopyOnWriteArrayList();
        }
    }
}
