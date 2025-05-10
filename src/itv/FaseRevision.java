package itv;

import util.GestorIO;
import util.Interval;
import vehiculo.Vehiculo;

/**
 * @author irene, alvaro, alejandro
 */
public class FaseRevision {
    private GestorIO teclado = new GestorIO();
    private Interval LIMITE_POSICION = new Interval(1,4);
    private static final String[] NOMBRES_FASES = {
        "Revisión inicial de elementos de seguridad",
        "Revisión del sistema eléctrico",
        "Revisión de emisión de humos",
        "Revisión de frenos y dirección"
    }; 
    private String nombreFase;
    private Vehiculo vehiculo;

   
    public FaseRevision(int posicion) {
        this.nombreFase = NOMBRES_FASES[posicion];
        this.vehiculo = null;
    }

    /**
     * Asigna un vehículo a la fase de revisión.
     * 
     * @param vehiculo el vehículo a asignar.
     */
    public void asignarVehiculoFase(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }

    /**
     * Obtiene el nombre de la fase de revisión.
     * 
     * @return el nombre de la fase.
     */
    public String getNombreFase() {
        return this.nombreFase;
    }

    /**
     * Elimina el vehículo asignado a la fase.
     */
    public Vehiculo eliminarVehiculo() {
        Vehiculo vehiculo = this.vehiculo;
        this.vehiculo = null;
        return vehiculo;
    }

    /**
     * Verifica si la fase de revisión está libre.
     * 
     * @return true si la fase está libre, false en caso contrario.
     */
    public boolean estaLibre() {
        return this.vehiculo == null;
    }

    /**
     * Verifica si la fase tiene un vehículo asignado.
     * 
     * @return true si hay un vehículo en la fase, false en caso contrario.
     */
    public boolean tieneVehiculo() {
        return this.vehiculo != null;
    }

    /**
     * Obtiene la matrícula del vehículo asignado a la fase.
     * 
     * @return la matrícula del vehículo o "Sin vehículo" si no hay vehículo asignado.
     */
    public String getMatriculaVehiculo() {
        return (vehiculo != null) ? vehiculo.getMatricula() : "SIN VEHICULO\n";
    }

    /**
     * Obtiene el vehículo asignado a la fase.
     * 
     * @return el vehículo asignado.
     */
    public Vehiculo getVehiculo() {
        return this.vehiculo;
    }

    /**
     * Valida si la matrícula del vehículo ya está en una fase.
     * 
     * @param matricula la matrícula a validar.
     * @return true si la matrícula es válida.
     */
    public boolean matriculaValida(String matricula) {
        if(this.vehiculo == null)return true;
        return !this.vehiculo.getMatricula().equalsIgnoreCase(matricula);
    }
    
    /**
     * Asigna un vehículo a la fase.
     * 
     * @param vehiculo el vehículo a asignar.
     */
    public void setVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }
    
    /**
     * Valida si la posición dada está dentro del rango permitido.
     * 
     * @param posicion la posición a validar.
     * @return la posición válida.
     */
    public int validarPosicion(int posicion){
        while(!LIMITE_POSICION.inclou(posicion)){
            teclado.out("Introduce una posición correcta: ");
            posicion = teclado.inInt();
        }
        return posicion;
    }
}