package itv;

import excepciones.NotExistsException;
import itv.FaseRevision;
import util.GestorIO;
import vehiculo.Vehiculo;

/**
 * 
 * @author irene, alvaro, alejandro
 */
public class Box {

    private GestorIO teclado = new GestorIO();
    private static final int NUM_FASES = 4;
    private FaseRevision[] fases;

    
    public Box() {
        fases = new FaseRevision[NUM_FASES];
        for (int i = 0; i < NUM_FASES; i++) {
            fases[i] = new FaseRevision(i);
        }
    }

    /**
     * Verifica si la primera fase del box está libre.
     * 
     * @return true si el box está libre, false en caso contrario.
     */
    public boolean estaLibre() {
        return fases[0].estaLibre();
    }

    /**
     * Asigna un vehículo a la primera fase de revisión si el box está libre.
     * 
     * @param vehiculo el vehículo a asignar.
     */
    public void asignarVehiculo(Vehiculo vehiculo){
        if (estaLibre()) {
            fases[0].asignarVehiculoFase(vehiculo);
            teclado.out("Vehículo con matrícula " + vehiculo.getMatricula() + " asignado al box.");
        } else {
            teclado.out("El box está ocupado.");
        }
    }
    
    /**
     * Copia el último vehículo que pasó por la última fase de revisión.
     * 
     * @return el vehículo en la última fase del box.
     */
    public Vehiculo copiarUltimoVehiculo() throws NotExistsException{
        if(this.fases[3].estaLibre()) throw new NotExistsException("No hay vehículos en la última fase");
        return this.fases[3].getVehiculo();
    }

    /**
     * Avanza los vehículos de las fases en un box.Si la última fase está ocupada, se elimina el vehículo antes de mover los demás.
     * @throws excepciones.NotExistsException
     */
    public void avanzarVehiculos() throws NotExistsException {
        boolean hayVehiculos = false;
        for (FaseRevision fase : fases) {
            if (!fase.estaLibre()) {
                hayVehiculos = true;
                break;
            }
        }
        if (!hayVehiculos) throw new NotExistsException("No hay vehículos en este box para avanzar.");
        
        if (!fases[fases.length - 1].estaLibre()) {
            Vehiculo ultimoVehiculoFase = fases[fases.length - 1].getVehiculo();
            teclado.out("El vehículo con matrícula " + ultimoVehiculoFase.getMatricula() + " ha superado las fases de revisión y ha abandonado el taller.\n");           
            fases[fases.length - 1].eliminarVehiculo(); //Necesito ver si aquí es donde deberíamos añadir el vehiculo a la cola de pago y que se lance la excepción de cola llena desdde aquí
        }
        for (int i = fases.length - 1; i > 0; i--) {
            if (!fases[i - 1].estaLibre()) {
                fases[i].setVehiculo(fases[i - 1].getVehiculo());
                fases[i - 1].eliminarVehiculo();
            }
        }
        if (fases[0].estaLibre()) {
            teclado.out("La primera fase ahora está libre para recibir nuevos vehículos.\n");
        }
    }

    /**
     * Muestra el estado actual de cada fase en el box.
     */
    public void mostrarEstado() {
        for (int i = 0; i < NUM_FASES; i++) {
            String estado = fases[i].estaLibre() ? "vacía" : "ocupada por " + fases[i].getVehiculo().getMatricula();
            teclado.out("\nFase " + (i+1) + " está " + estado + ".");
        }
    }

    /**
     * Valida si la matrícula de un vehículo está presente en alguna fase del box.
     * 
     * @param matricula la matrícula a validar.
     * @return true si la matrícula es válida.
     */
    public boolean matriculaValida(String matricula) {
        for (int i = 0; i < fases.length; i++) {
            if (!fases[i].matriculaValida(matricula)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica si el box está libre en la primera fase.
     * 
     * @return true si la primera fase está libre, false en caso contrario.
     */
    public boolean boxLibre() {
        return this.fases[0].estaLibre();
    }
    
    /**
     * Verifica si la última fase del box está ocupada.
     * 
     * @return true si la última fase está ocupada, false en caso contrario.
     */
    public boolean ultimaFaseOcupada(){
        return !this.fases[fases.length-1].estaLibre();
    }
}