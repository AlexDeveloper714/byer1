
import java.io.*;
import java.util.regex.*;

public class boyerInvertido {

    public static String textoOriginal = "";
    public static String textoModificado = "";
    public static String textoInverso = "";
    public static int Lineas;
    public static int cantidadApariciones=0;

    public String resultadoTexto(String texto, String patron_dado) throws FileNotFoundException {
        textoOriginal = "";
        textoInverso = textoOriginal;
        textoModificado = textoInverso;
        cantidadApariciones=0;
        leerLineas(texto);
        textoOriginal=obtenerDatos(texto);
        //"^[aA]bc.*"
        String regularEx = "(^"+patron_dado+".*)";
        System.out.println(regularEx);
        System.out.println(textoOriginal);
        Pattern patron = Pattern.compile(regularEx);
        Matcher encaja = patron.matcher(textoOriginal);
        System.out.println("lookingAt(): "+encaja.lookingAt());
        System.out.println("matches(): "+encaja.matches());
        if (encaja.lookingAt()) {
            revisarInversos(textoOriginal);
            cantidadApariciones=contarCaracteres(patron_dado, textoOriginal);
            return "Cumples con los requisitos: "
                    + "cantidad apariciones: " + cantidadApariciones;
        } else {
            return "No cumples con los requisitos";
        }
    }

    public String revisarInversos(String original) {
        
        for (int i = original.length()-1; i >=0 ; i--) {
            textoInverso += original.charAt(i);
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
