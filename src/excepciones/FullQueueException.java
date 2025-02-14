
package excepciones;

import itv.Cola;
import vehiculo.Vehiculo;

/**
 *
 * @author irene
 */
public class FullQueueException extends Exception{
    private Vehiculo vehiculo;
    private Cola cola;
    
    
    public FullQueueException(Vehiculo vehiculo, Cola cola){
        super("La cola está llena");
        this.vehiculo = vehiculo;
        this.cola = cola;
    }
    
    public FullQueueException(String frase, Vehiculo vehiculo, Cola cola){
        super(frase);
        this.vehiculo = vehiculo;
        this.cola = cola;
    }

   
    public String getMatriculaError(){
        return vehiculo.getMatricula();
    }
    
    
    public String getMatriculaCola(){
        return cola.getPrimerVehiculo().getMatricula();
    }

    @Override
    public String toString() {
        return "FullQueueException{ El vehiculo con matrícula "+ getMatriculaError() + ", no puede ser añadido a la cola.\nDebe salir el vehículo con la matrícula "+getMatriculaCola()+  '}';
    }
    
    
    
    
}
