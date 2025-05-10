package excepciones;

/**
 *
 * @author irene
 */
public class FullQueueException extends Exception{
    private Object generaError;
    private Object ultimoCola;

    public FullQueueException(Object generaError, Object ultimoCola, String message) {
        super(message);
        this.generaError = generaError;
        this.ultimoCola = ultimoCola;
    }

    public FullQueueException(Object generaError, Object ultimoCola) {
        this.generaError = generaError;
        this.ultimoCola = ultimoCola;
    }


    /**
     * 
     * @return devuelve el dato que genera el error  
     */
    public Object getGeneraError() {
        return generaError;
    }

    /**
     * 
     * @return devuelve el dato que está al final de la cola y es el límite 
     */
    public Object getUltimoCola() {
        return ultimoCola;
    }
    
    /**
     * 
     * @return devuelve el mensaje de error en String
     */
    @Override
    public String toString() {
        return "FullQueueException{\nEL ELEMENTO: "+ getGeneraError().toString() + ", NO PUEDE SER AÑADIDO A LA COLA.\nDEBE SALIR EL ELEMENTO: "+getUltimoCola().toString()+ "}\n";
    }

    
}
