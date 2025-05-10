package excepciones;

/**
 *
 * @author ÁLVARO CARRIÓN ROMERO
 */
public class AlreadyExistsException extends Exception{
    private String stringExistente;
    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String stringExistente, String message) {
        super(message);
        this.stringExistente = stringExistente;
    }
    
    /**
     * 
     * @return devuelve el dato que se ha intentado introducir y ya existía 
     */
    public String getStringExistente(){
        return stringExistente;
    }

    /**
     * 
     * @return devuelve el string del mensaje 
     */
    @Override
    public String toString() {
        return "\n " +super.getMessage();
    }
    
}
