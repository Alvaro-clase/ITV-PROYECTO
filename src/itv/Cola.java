package itv;

import excepciones.FullQueueException;
import vehiculo.Vehiculo;
import util.GestorIO;
import java.util.Arrays;

/**
 * 
 * @author irene, alvaro, alejandro
 */
public class Cola {
    private Vehiculo[] vehiculos;
    private int limiteCola;
   
    public Cola(int limiteCola) {
        this.vehiculos = new Vehiculo[0];
        this.limiteCola = limiteCola;
    }

    /**
     * Inserta un vehículo al final de la cola.
     * 
     * @param vehiculo el vehículo a insertar.
     * @throws excepciones.FullQueueException
     */
    public void insertarVehiculo(Vehiculo vehiculo) throws FullQueueException {
        if(vehiculos.length == limiteCola) throw new FullQueueException(vehiculo, this);
        Vehiculo[] nuevaCola = Arrays.copyOf(this.vehiculos, this.vehiculos.length + 1);
        nuevaCola[nuevaCola.length - 1] = vehiculo;
        this.vehiculos = nuevaCola;
    }

    public Vehiculo getPrimerVehiculo(){
        return vehiculos[0];
    }
    /**
     * Extrae y devuelve el primer vehículo de la cola.
     * 
     * @return el vehículo extraído o null si la cola está vacía.
     */
    public Vehiculo extraerVehiculo() {
        if (estaVacia()) {
            return null;
        }
        Vehiculo vehiculo = this.vehiculos[0];
        this.vehiculos = Arrays.copyOfRange(this.vehiculos, 1, this.vehiculos.length);
        return vehiculo;
    }

    /**
     * Si la matrícula ya está registrada en la cola de vehiculos devolvera false porque no es válida
     * 
     * @param matricula la matrícula a validar.
     * @return true si la matrícula es valida.
     */
    public boolean matriculaValida(String matricula) {
        for (int i = 0; i < this.vehiculos.length; i++) {
            if (this.vehiculos[i].tieneEstaMatricula(matricula)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica si la cola está vacía.
     * 
     * @return true si no hay vehículos en la cola, false en caso contrario.
     */
    public boolean estaVacia() {
        return this.vehiculos.length == 0;
    }
    
    /**
     * Obtiene el array de vehículos en la cola.
     * 
     * @return un array de vehículos.
     */
    public Vehiculo[] getVehiculos() {
        return this.vehiculos;
    }

    /**
     * Muestra el estado de la cola de vehículos en el taller.
     */
    public void mostrarCola() {
        GestorIO teclado = new GestorIO();
        teclado.out("\n-- Cola de Vehículos --\n");
        for (int i = 0; i < this.vehiculos.length; i++) {
            teclado.out("\nPosición " + (i + 1) + ":\n");
            this.vehiculos[i].mostrarTodo();
            teclado.out("\n");
        }
        teclado.out("\n");
    }
}
