package interfaces;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.GestorIO;
import util.Interval;

/**
 *ME HE TOMADO LA LIBERTAD DE CREAR UNA INTERFAZ QUE SEA UTIL
 * 
 * @author ÁLVARO CARRIÓN ROMERO 
 */
public interface Validable {
    GestorIO teclado = new GestorIO();
    
    /**
     * 
     * @param elemento
     * @param tipoPatron
     * @return devuelve el String validado 
     */
    public static String withPattern(String elemento, String tipoPatron){ 
        while(!validarConPatrones(Patron.getPattern(tipoPatron.toUpperCase()) ,elemento.toUpperCase().trim())){
                teclado.out(Patron.getCaracteristica(tipoPatron));
                elemento = teclado.inString();
            }
        return elemento;
    }
    
    /**
     * 
     * @param patron
     * @param telefono
     * @return devuelve el matcher para validar por patrones 
     */
    private static boolean validarConPatrones(Pattern patron, String telefono){
        Matcher matcher = patron.matcher(telefono);
        return matcher.matches();
    }
    
    /**
     * 
     * @param respuestaCliente
     * @return devuelve la respuesta correcta y validada cuando es SI o NO 
     */
    public static String respuestaSiNo(String respuestaCliente) {
        while (!respuestaCliente.equalsIgnoreCase("SI") && !respuestaCliente.equalsIgnoreCase("NO")) {
            teclado.out("\nERROR. Lo siento está no es una respuesta válida\n");
            teclado.out("La respuesta debe ser un SI o NO: ");
            respuestaCliente = teclado.inString().trim();
        }
        return respuestaCliente;
    }
    
    /**
     * 
     * @param opcion
     * @param opciones
     * @return devuelve la opción escogida válida
     */
    public static int opcion(int opcion, Interval opciones){
        while (!opciones.inclou(opcion)) {
            teclado.out("Introduce una opción válida: ");
            opcion = teclado.inInt();
        }
        return opcion;
        
    }
      
}
