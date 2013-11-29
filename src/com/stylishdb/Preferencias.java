package com.stylishdb;

import com.google.gson.Gson;
import com.stylishdb.configuration.IConfiguracion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 ** @author StylishDB
 */
public class Preferencias {
    
    private static Preferencias INSTANCE = null;
    private Gson gson;
    
    private Properties propertiesProyecto;
    
    private Preferencias() {
        gson = new Gson();
        
        cargarPropertiesProyecto();
        
        crearSiNoExisteCarpetaPreferencias();
    }
    
    public static Preferencias getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Preferencias();
        }
        
        return INSTANCE;
    }
    
    public <T> T getConfiguracion(Type fooType, String nombreConfiguracion)
            throws PreferenciasException {
        String jsonFile = getJsonFile(nombreConfiguracion);
        
        if(jsonFile.isEmpty()) {
            throw new PreferenciasException("No existe el fichero de propiedades");
        }
        return gson.fromJson(
                    jsonFile,
                    fooType);
    }
    
    public void guardarConfiguracion(
                Type fooType,
                IConfiguracion configuracion,
                String nombreConfiguracion) {
        String jsonData = gson.toJson(configuracion, fooType);
        
        File fichJson = new File(getAbsolutePathToFile(nombreConfiguracion));
        
        escribirTextoEnFichero(jsonData, fichJson);
    }
    
    public String getPathToHomePreferencias() {
        String userHome = System.getProperty("user.home")
                .replace("\\", "/");
        if(!userHome.endsWith("/")) {
            userHome += "/";
        }
        String pathRelativeToHome = propertiesProyecto.getProperty(
                "NOMBRE_CARPETA_HOME",
                ".DataBaseFun").replace("\\", "/");
        if(!pathRelativeToHome.endsWith("/")) {
            pathRelativeToHome += "/";
        }
        return userHome + pathRelativeToHome;
    }
    
    public String getPathToFolderPreferencias(String nombreCarpeta) {
        nombreCarpeta = nombreCarpeta.replace("\\", "/");
        if(!nombreCarpeta.endsWith("/")) {
            nombreCarpeta += "/";
        }
        String pathToFolder = getPathToHomePreferencias() + nombreCarpeta;
        File folder = new File(pathToFolder);
        if(!folder.exists()) {
            folder.mkdirs();
        }
        return getPathToHomePreferencias() + nombreCarpeta;
    }
    
    private void cargarPropertiesProyecto() {
        propertiesProyecto = new Properties();
        try {
            propertiesProyecto.load(Preferencias.class.
                    getResourceAsStream("proyecto.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            cargarPropertiesDefecto();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            cargarPropertiesDefecto();
        }
    }
    
    private void cargarPropertiesDefecto() {
        propertiesProyecto.setProperty(
                "NOMBRE_CARPETA_HOME",
                ".DataBaseFun");
    }
    
    private String getJsonFile(String nameFile) {
        File fichJson = new File(getAbsolutePathToFile(nameFile));
        String json = "";
        if(fichJson.exists() && fichJson.isFile()) {
            try {
                FileReader fr = new FileReader(fichJson);
                json = getStringFileReader(fr);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return json;
    }
    
    private String getAbsolutePathToFile(String nameFile) {
        return getPathToHomePreferencias() + nameFile;
    }
    
    private String getStringFileReader(FileReader fr) {
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = getStringBuilderFromBufferedReader(br);

        return sb.toString();
    }
    
    private StringBuilder getStringBuilderFromBufferedReader(BufferedReader br) {
        StringBuilder sb = new StringBuilder();
        
        try {
            String linea;
            while((linea = br.readLine()) != null) {
                sb.append(linea);
            }
        } catch (IOException ex) {
            Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarBufferedReader(br);
        }
        
        return sb;
    }
    
    private void cerrarBufferedReader(BufferedReader br) {
        if(br != null) {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void escribirTextoEnFichero(String texto, File fich) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(fich);
            
            escribirTextoEnFileWriter(texto, fw);
        } catch (IOException ex) {
            Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarFileWriter(fw);
        }
    }
    
    private void escribirTextoEnFileWriter(String texto, FileWriter fw) {
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write(texto);
        } catch (IOException ex) {
            Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarBufferedWriter(bw);
        }
    }
    
    private void cerrarBufferedWriter(BufferedWriter bw) {
        try {
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cerrarFileWriter(FileWriter fw) {
        if(fw != null) {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    private void crearSiNoExisteCarpetaPreferencias() {
        File fichPreferencias = new File(
                getPathToHomePreferencias()
        );
        fichPreferencias.mkdirs();
    }
}
