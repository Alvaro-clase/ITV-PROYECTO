package interfaces;

import java.util.regex.Pattern;

/**
 *
 * @author ÁLVARO CARRIÓN ROMERO
 */
public enum Patron {
    DNI(Pattern.compile("^[0-9]{8}[A-Z]$"),"8 numeros y una letra"), 
    TELEFONO(Pattern.compile("^[0-9]{9}$"),"9 numeros"),
    MATRICULA(Pattern.compile("^[0-9]{4}[A-Z]{3}$"),"4 numeros y 3 letras");

    private final Pattern patron;
    private final String definicion;

    Patron(Pattern tipo, String definicion) {
        this.patron = tipo;
        this.definicion=definicion;
    }

    /**
     * 
     * @return te devuelve el tipo de patron 
     */
    public Pattern tipo() {
        return patron;
    }
    
    /**
     * 
     * @return te devuelve que reglas debe seguir el patron (de forma entendible) 
     */
    public String definicion(){
        return definicion;
    }
    
    /**
     * 
     * @param patron
     * @return devuelve el patrón solicitado si lo encuentra en las opciones 
     */
    public static Pattern getPattern(String patron){
        for (Patron value : values()) {
            if(value.name().equalsIgnoreCase(patron)){
                return value.patron;
            }
        }
        throw new IllegalArgumentException("NO SE ENCONTRÓ EL PATRÓN PARA: " + patron + "\n");
    }

    //RECOGO LA EXPRESIÓN REGULAR Y DEVUELVO EL CONTENDIO DEL ENUM 
    //PARA QUE SE PUEDA SER ESCALABLE FÁCILMENTE SIN TENER QUE DUPLICAR Y SABERSE LAS EXPRESIONES REGULARES
    /**
     * 
     * @param patron
     * @return devuelve la definicion del patrón  
     */
    public static String getCaracteristica(String patron) {
        for (Patron value : values()) {
            if (value.name().equalsIgnoreCase(patron)) {
                return caracteristica(value.definicion());
            }
        }
        return null;
    }

    /**
     * 
     * @param definicionPatron
     * @return devuelve el mensaje de error 
     */
    private static String caracteristica(String definicionPatron) {
        return "ERROR. DEBE SEGUIR UN PATRÓN IGUAL A ÉSTE: ("+definicionPatron+")\n";
    }
    
    
    
}
