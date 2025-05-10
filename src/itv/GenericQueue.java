package itv;

import excepciones.FullQueueException;
import java.util.ArrayList;
import vehiculo.Vehiculo;
import util.GestorIO;
import java.util.List;

/**
 * 
 * @author irene, alvaro, alejandro
 */
public class GenericQueue<T> {
    private ArrayList<T> cola;
    private int limiteCola;
   
    public GenericQueue(int limiteCola) {
        this.cola = new ArrayList<T>();
        this.limiteCola = limiteCola;
    }

    /**
     * Inserta un elemento al final de la cola.
     * 
     * @param elemento a insertar.
     */
    public void enqueue(T elemento)throws FullQueueException {
        if(cola.size() == limiteCola)throw new FullQueueException(elemento, peek());
        this.cola.add(elemento);
    }

    /**
     * 
     * @return devuelve el primer elemento de la cola
     */
    public T peek(){
        return this.cola.getFirst();
    }
    
    /**
     * 
     * @return devuelve el último elemento de la cola 
     */
    public T getUltimo(){
        return this.cola.getLast();
    }
    /**
     * Extrae y devuelve el primer elemento de la cola.
     * 
     * @return el elemento extraído o null si la cola está vacía.
     */
    public T dequeue()throws NullPointerException {
        return this.cola.removeFirst();
    }

    /**
     * valida si el elemento ya esta en la cola
     * 
     * @param elemento 
     * @return true si existe.
     */
    public boolean isPresent(T elemento) {
        for (T t : cola) {
            if(t.equals(elemento))return true;
        }
        return false;
     
    }

    /**
     * Verifica si la cola está vacía.
     * 
     * @return true si la cola está vacía.
     */
    public boolean isEmpty() {
        return this.cola.isEmpty();
    }
    
    /**
     * Obtiene el array de vehículos en la cola.
     * 
     * @return un array de vehículos.
     */
    public List<T> getCola() {
        return this.cola;
    }
    
    /**
     * 
     * @param <E>
     * @param matricula
     * @param vehiculos
     * @return devuelve si la matrícula es válida o no 
     */
    public static <E extends Vehiculo> boolean matriculaValida(String matricula, GenericQueue<E> vehiculos){
        for (E e : vehiculos.cola) {
            if(e.getMatricula().equalsIgnoreCase(matricula)) return false;
        }
        return true;
    }

    /**
     * Muestra el estado de la cola de vehículos en el taller.
     */
    public void mostrarCola() {
        GestorIO teclado = new GestorIO();
        teclado.out("\n-- COLA --\n");
        int i=0;
        for (T elemento : cola) {
            teclado.out("\nPosición " + ++i + ":\n");
            elemento.toString();
            teclado.out("\n");
        }       
    }
}
