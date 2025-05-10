package excepciones;
/**
 * 
 * @author Alejandro
 */
public class NotExistsException extends Exception{
     public NotExistsException() {
        super("Error: El elemento solicitado no existe.\n");
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
