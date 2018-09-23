
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BoyerMoore {

    public static int Lineas = 0;
    public static int ConteoPalabras = 0;
    public static String textoCopia = "";
    public static String textoOriginal = "";
    public static String salidaTexto = "";

    public void reiniciarVariables() {
        Lineas = 0;
        ConteoPalabras = 0;
        textoCopia = "";
        textoOriginal = textoCopia;
        salidaTexto = textoCopia;
    }

    public void empezarCodigo(String ruta, String patron) throws FileNotFoundException {
        leerLineas(ruta);
        if (Lineas > 0) {
            textoOriginal = obtenerDatos(ruta);
            pruebaMooyer(textoOriginal, patron, 0);
            //
        }
    }

    public static void pruebaMooyer(String texto, String patron, int indice) {
        char[] textoConvertido = texto.toCharArray();
        char[] patronConvertido = patron.toCharArray();
        int resultado = bm(textoConvertido, patronConvertido);
        String textoCopia = "";
        if (resultado == indice) {
            salidaTexto = "Pasado el test..."
                    + "\nPatron: \n" + patron
                    + "\nIndice: \n" + 0
                    + "\nPrimer resultado indice: " + resultado
                    + "\nCantidad caracteres: " + contarCaracteres(patron, texto);
        } else {
            salidaTexto = "Fallido el test..."
                    + "\nPatron: \n" + patron
                    + "\nIndice: \n" + 0
                    + "\nPrimer resultado indice: " + resultado
                    + "\nCantidad caracteres: " + contarCaracteres(patron, texto);
        }

    }

    //MakeD1
    public static int[] makeD1(char[] pat) {
        int[] table = new int[255];
        int conteo = 0;
        for (int i = 0; i < 255; i++) {
            table[i] = pat.length;
        }
        for (int i = 0; i < pat.length - 1; i++) {
            table[pat[i]] = pat.length - 1 - i;
        }
        return table;
    }

    public static boolean esPrefijo(char[] patron, int pos) {
        int suffixlen = patron.length - pos;
        for (int i = 0; i < suffixlen; i++) {
            if (patron[i] != patron[pos + i]) {
                return false;
            }
        }
        return true;
    }

    public static int longitud_sufijo(char[] patron, int pos) {
        int i;
        for (i = 0; ((patron[pos - i] == patron[patron.length - 1 - i])
                && (i < pos)); i++) {
        }
        return i;
    }

    //MakeD2 Pendiente
    public static int[] makeD2(char[] pat) {
        int[] delta2 = new int[pat.length];
        int p;
        int last_prefix_index = pat.length - 1;
        for (p = pat.length - 1; p >= 0; p--) {
            if (esPrefijo(pat, p + 1)) {
                last_prefix_index = p + 1;
            }
            delta2[p] = last_prefix_index + (pat.length - 1 - p);
        }
        for (p = 0; p < pat.length - 1; p++) {
            int slen = longitud_sufijo(pat, p);
            if (pat[p - slen] != pat[pat.length - 1 - slen]) {
                delta2[pat.length - 1 - slen] = pat.length - 1 - p + slen;
            }
        }
        return delta2;
    }

    public static int bm(char[] string, char[] pat) {
        int[] d1 = makeD1(pat);
        int[] d2 = makeD2(pat);
        String d1_i = "", d2_j = "";
        for (int i = 0; i < d1.length; i++) {
            d1_i += d1[i] + " ";
        }
        for (int i = 0; i < d2.length; i++) {
            d2_j += d2[i] + " ";
        }
        int i = pat.length - 1;
        while (i < string.length) {
            int j = pat.length - 1;
            while (j >= 0 && (string[i] == pat[j])) {
                i--;
                j--;
            }
            if (j < 0) {
                return (i + 1);
            }
            i += Math.max(d1[string[i]], d2[j]);
        }
        return -1;
    }
//OK

    public static void leerLineas(String ruta) throws FileNotFoundException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        archivo = new File(ruta);
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
        try {
            String linea;
            while ((linea = br.readLine()) != null) {
                int longitud = 0;
                if (longitud < linea.length()) {
                    longitud = linea.length();
                }
                Lineas++;
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//OK

    public static String obtenerDatos(String ruta) throws FileNotFoundException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        archivo = new File(ruta);
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
        String linea;
        String texto = "";
        try {

            int cantidadLineas = 0;
            while ((linea = br.readLine()) != null) {
                if (cantidadLineas < Lineas) {
                    texto += linea + "\n";
                }
                cantidadLineas++;
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texto;
    }
//OK

    public static int contarCaracteres(String patron, String texto) {
        int cantidad = 0;
        if (texto.contains(patron)) {
            textoCopia = texto.replaceAll(patron, "-");
            for (int i = 0; i < textoCopia.length(); i++) {
                if (textoCopia.charAt(i) == '-') {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }
}
