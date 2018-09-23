
import java.util.regex.*;

public class boyerInvertido {

    public static String textoOriginal = "";
    public static String textoModificado = "";
    public static String textoInverso = "";

    public String resultadoTexto(String texto, String patron_dado) {
        textoOriginal = "";
        textoInverso = textoOriginal;
        textoModificado = textoInverso;
        String regularEx = "(^" + patron_dado + ")";
        System.out.println(regularEx);
        Pattern patron = Pattern.compile(regularEx);
        Matcher encaja = patron.matcher(texto);
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
}
