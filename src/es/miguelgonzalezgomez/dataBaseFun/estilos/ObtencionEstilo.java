package es.miguelgonzalezgomez.dataBaseFun.estilos;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Miguel González
 */
public class ObtencionEstilo {

    public static String getEstiloVentana(String nameVentana) {
        InputStream is = ObtencionEstilo.class.getResourceAsStream(nameVentana);

        return leerTextoInputStream(is);
    }

    private static synchronized String leerTextoInputStream(InputStream is) {
        char[] buffer = new char[512];
        StringBuilder out = new StringBuilder();
        Reader in = null;
        try {
            in = new InputStreamReader(is, "UTF-8");
            int rsz;
            while((rsz = in.read(buffer, 0, buffer.length)) > 0) {
                out.append(buffer, 0, rsz);
            }
        } catch (UnsupportedEncodingException ex) {
            escribirErrorEncodingException(ex);
        } catch (IOException ex) {
            escribirErrorIOException(ex);
        } catch(NullPointerException ex) {
            escribirErrorNullPointerException(ex);
        } finally {
            cerrarReader(in);
        }

        return out.toString();
    }

    private static void cerrarReader(Reader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException ex) {
            escribirErrorIOException(ex);
        }
    }

    private static void escribirErrorIOException(IOException ex) {
        ex.printStackTrace();
    }

    private static void escribirErrorEncodingException(UnsupportedEncodingException ex) {
        ex.printStackTrace();
    }

    private static void escribirErrorNullPointerException(NullPointerException ex) {
        ex.printStackTrace();
    }
}
