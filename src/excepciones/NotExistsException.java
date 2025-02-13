
package excepciones;

public class NotExistsException extends Exception{
    
    public NotExistsException() {
        super("Error: El elemento solicitado no existe.");
    }
    
    public NotExistsException(String mensaje){
        super(mensaje);
    }
    
    public NotExistsException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    
    public NotExistsException(Throwable causa) {
        super(causa);
    }
}
