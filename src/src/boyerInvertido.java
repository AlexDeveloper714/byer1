
import java.io.*;
import java.util.regex.*;

public class boyerInvertido {

    public static String textoOriginal = "";
    public static String textoModificado = "";
    public static String textoInverso = "";
    public static int Lineas;

    public String resultadoTexto(String texto, String patron_dado) throws FileNotFoundException {
        textoOriginal = "";
        textoInverso = textoOriginal;
        textoModificado = textoInverso;
        leerLineas(texto);
        textoOriginal=obtenerDatos(texto);
        String regularEx = "(^a).*";
        System.out.println(regularEx);
        System.out.println(textoOriginal);
        Pattern patron = Pattern.compile(regularEx);
        Matcher encaja = patron.matcher(textoOriginal);
        System.out.println("lookingAt(): "+encaja.lookingAt());
        System.out.println("matches(): "+encaja.matches());
        if (encaja.matches()) {
            textoOriginal = texto;
            revisarInversos(textoOriginal);
            contarCaracteres(patron_dado, textoOriginal);
            return "Cumples con los requisitos: " + texto;
        } else {
            return "No cumples con los requisitos";
        }
    }

    public String revisarInversos(String original) {
        for (int i = 0; i < original.length(); i++) {
            textoInverso += original.charAt(original.length() - i);
        }
        System.out.println(textoInverso);
        textoModificado = textoInverso;
        return "";
    }

    public static int contarCaracteres(String patron, String texto) {
        int cantidad = 0;
        if (texto.contains(patron)) {
            textoModificado = texto.replaceAll(patron, "-");
            for (int i = 0; i < textoModificado.length(); i++) {
                if (textoModificado.charAt(i) == '-') {
                    cantidad++;
                }
            }
        }
        System.out.println(cantidad);
        return cantidad;
    }
    
    
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

}
