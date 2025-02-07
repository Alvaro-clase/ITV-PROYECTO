package itv;

import cliente.Cliente;
import java.util.Arrays;
import util.GestorIO;
import util.Interval;
import vehiculo.*;
import java.util.regex.*;
import opcion.CalculoIngresos;

/**
 * @author irene, alvaro, alejandro
 * 
 * 
 */
public class Taller {

    private GestorIO teclado = new GestorIO();
    private Box[] boxes;
    private Cola colaPrincipal;
    private Cola colaDePago;
    private String[] matriculasEnTaller;
    private double ingresosTotales;
    public final Interval NUMERO_BOXES = new Interval(1, 6);
    private  Cliente[] clientes;
    

   
    public Taller() {
        boxes = new Box[6];
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new Box();
        }
        colaPrincipal = new Cola();
        colaDePago = new Cola();
        matriculasEnTaller = new String[0];
        ingresosTotales = 0;
        clientes = new Cliente[0];
    }
    
    /**
     * Busca a un cliente dentro del array de clientes
     * @param dni
     * @return cliente (NULL SI NO ESTÁ)
     */
    public Cliente getClientePorDNI(String dni){
        if(estaEsteDNI(dni)){
            for(Cliente c: clientes){
                if(c.getDni().equals(dni))return c;
            }
        }
        return null;
    }
    /**
     * Verifica si está el dni
     * @param DNI
     * @return boolean
     */
    public boolean estaEsteDNI(String dni){
        for (int i = 0; i < clientes.length; i++) {
            if(clientes[i].getDni().equalsIgnoreCase(dni)){
                return true;
            }
        }
        return false;
    } 
    /**
     * Añade un Cliente al array de los clientes
     * 
     * @param cliente 
     */
    public void añadirCliente(Cliente cliente){
        Cliente[] clientesNew = Arrays.copyOf(clientes, clientes.length + 1);
        clientesNew[clientesNew.length - 1] = cliente;
        clientes = clientesNew;
    }
    
    
    public boolean estaElDni(String dni){
        for (int i = 0; i < clientes.length; i++) {
            if(clientes[i].getDni().equals(dni))return true;
        }
        return false;
    }
    /**
     * Registra un vehículo en el sistema.
     * 
     * @param vehiculo el vehículo a registrar.
     */
    public void registrarVehiculo(Vehiculo vehiculo) {
        vehiculo.insertarCilindros();
        vehiculo.insertarCc();
        vehiculo.registrarVehiculo();
    }

    /**
     * Verifica si hay vehículos en la cola de pago.
     * 
     * @return true si hay vehículos para pagar, false en caso contrario.
     */
    public boolean hayVehiculosParaPagar() {
        return !this.colaDePago.estaVacia();
    }

    /**
     * Extrae un vehículo de la cola de pago.
     * 
     * @return el vehículo extraído.
     */
    public Vehiculo extraerVehiculoPago() {
        return this.colaDePago.extraerVehiculo();
    }

    /**
     * Extrae un vehículo de la cola principal.
     * 
     * @return el vehículo extraído.
     */
    public Vehiculo extraerVehiculoCola() {
        return this.colaPrincipal.extraerVehiculo();
    }

    /**
     * Si la matrícula ya está registrada en el taller NO será válida.
     * 
     * @param matricula la matrícula a validar.
     * @return true si la matrícula es válida.
     */
    public boolean matriculaValida(String matricula) {
        if (colaPrincipal.matriculaValida(matricula)) {
            for (Box box : boxes) {
                if (!box.matriculaValida(matricula)) {
                    return false;
                }
            }
          
        }else{
            return false;
        }
        return true;
    }

    /**
     * Avanza los vehículos en un box específico.
     * 
     * @param numeroBox el número del box.
     */
    public void avanzarVehiculos(int numeroBox) {
        boxes[numeroBox - 1].avanzarVehiculos();
    }

    /**
     * Verifica si la cola principal está vacía.
     * 
     * @return true si la cola está vacía, false en caso contrario.
     */
    public boolean colaEstaVacia() {
        return this.colaPrincipal.estaVacia();
    }

    /**
     * Verifica si hay boxes vacíos.
     * 
     * @return true si hay algún box libre, false en caso contrario.
     */
    public boolean boxesVacios() {
        for (Box box : boxes) {
            if (box.boxLibre()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Inserta un vehículo en la cola principal.
     * 
     * @param vehiculo el vehículo a insertar.
     */
    public void insertarVehiculo(Vehiculo vehiculo) {
        this.colaPrincipal.insertarVehiculo(vehiculo);
    }

    /**
     * Asigna un vehículo a un box específico.
     * 
     * @param numeroBox el número del box.
     * @param vehiculo el vehículo a asignar.
     */
    public void asignarVehiculoBox(int numeroBox, Vehiculo vehiculo) {
        boxes[numeroBox - 1].asignarVehiculo(vehiculo);
    }

    /**
     * Muestra el estado de un box específico.
     * 
     * @param box el número del box.
     */
    public void mostrarBox(int box) {
        this.boxes[box - 1].mostrarEstado();
    }

    /**
     * Muestra el estado de todos los boxes.
     */
    public void mostrarBoxes() {
        int i = 1;
        for (Box box : boxes) {
            teclado.out("\nBOX " + i++ + "\n");
            box.mostrarEstado();
            teclado.out("\n");
        }
    }
    
    /**
     * Extrae el último vehículo que pasó por un box específico.
     * 
     * @param numeroBox el número del box.
     * @return el vehículo extraído.
     */
    public Vehiculo extraerVehiculoBox(int numeroBox) {
        return boxes[numeroBox - 1].copiarUltimoVehiculo();
    }
    
    /**
     * Verifica si la última fase de un box está ocupada.
     * 
     * @param numeroBox el número del box.
     * @return true si la última fase está ocupada, false en caso contrario.
     */
    public boolean ultimaFaseOcupada(int numeroBox) {
        return boxes[numeroBox - 1].ultimaFaseOcupada();
    }
    
    /**
     * Mueve un vehículo a la cola de pago.
     * 
     * @param vehiculo el vehículo a mover.
     */
    public void meterColaPago(Vehiculo vehiculo) {
        this.colaDePago.insertarVehiculo(vehiculo);
    }
}