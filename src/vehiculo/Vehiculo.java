package vehiculo;

import cliente.Cliente;
import java.util.Objects;
import util.GestorIO;

/**
 * @author irene, alvaro, alejandro
 */
public abstract class Vehiculo implements Comparable<Vehiculo>{
    GestorIO teclado = new GestorIO();
    protected int cilindros;
    protected double CC;
    private final String matricula;
    private final String modeloVehiculo;
    private boolean estadoVehiculo;
    public static final int PRECIO_BASE = 15;
    protected final Cliente cliente;
    private boolean opcDescuentoVIP;

 
     public Vehiculo(Cliente cliente, String matricula, String modeloVehiculo){
        this.matricula = matricula;
        this.modeloVehiculo = modeloVehiculo;
        this.cliente = cliente;
    }
     
     /**
      * 
      * @return devuelve si el cliente es VIP o no
      */
    public boolean clienteIsVIP(){
       return cliente.isVIP();
    }
    
    /**
     * 
     * @return devuelve si el cliente es socio o no 
     */
    public boolean clienteIsSOCIO(){
        return cliente.isSOCIO();
    }
    /**
     * Verifica si el vehículo tiene una matrícula específica.
     * 
     * @param matricula Matrícula a verificar.
     * @return true si la matrícula coincide.
     */
    public boolean tieneEstaMatricula(String matricula) {
        return this.matricula.equalsIgnoreCase(matricula);
    }

    /**
     * Marca el vehículo como terminado en su revisión.
     */
    public void vehiculoTerminado() {
        teclado.out("El vehículo con la matrícula " + this.matricula + " ha completado la revisión.\n");
    }

    /**
     * Obtiene la matrícula del vehículo.
     * 
     * @return Matrícula del vehículo.
     */
    public String getMatricula() {
        return this.matricula;
    }

    /**
     * Obtiene el modelo del vehículo.
     * 
     * @return Modelo del vehículo.
     */
    public String getModelo() {
        return this.modeloVehiculo;
    }
    
    /**
     * Obtiene la potencia del vehículo.
     * 
     * @return Potencia en centímetros cúbicos.
     */
    public double getPotencia() {
        return this.CC;
    }
    
    /**
     * Obtiene el número de cilindros del vehículo.
     * 
     * @return Número de cilindros.
     */
    public int getCilindros() {
        return this.cilindros;
    }

    /**
     * Obtiene el estado del vehículo en la revisión.
     * 
     * @return true si el vehículo ha pasado la revisión, false en caso contrario.
     */
    public boolean getEstado() {
        return this.estadoVehiculo;
    }
    
    /**
     * Solicita e inserta la potencia del vehículo.
     */
    public void insertarCc(){
        int cc;
        do {
            teclado.out("Introduce la potencia que tiene el vehículo: ");
            cc = teclado.inInt();
        } while (cc < 0);
        this.CC = cc;
    }
    
    /**
     * Solicita e inserta el número de cilindros del vehículo.
     */
    public void insertarCilindros(){
        this.cilindros = this.validarCilindros();
    }
    
    /**
     * Cambia el estado del vehículo en la revisión.
     * 
     * @param nuevoEstado Nuevo estado del vehículo.
     */
    public void setEstado(boolean nuevoEstado) {
        this.estadoVehiculo = nuevoEstado;
    }
    
    /**
     * Muestra toda la información del vehículo.
     */
    public void mostrarTodo(){
        teclado.out("-------Datos del vehículo----------\n");
        teclado.out("Matrícula: "+this.matricula+"\n");
        teclado.out("Tipo de vehículo: "+this.getTipo()+"\n");
        teclado.out("Centímetros cúbicos: "+this.CC+"\n");
        teclado.out("Cantidad cilindros: "+this.cilindros+"\n");
        teclado.out("Modelo: "+this.modeloVehiculo+"\n");
        teclado.out("El vehículo ha pasado la revisión: "+this.estadoVehiculo+"\n");
    }

    /**
     * Obtiene el tipo de vehículo.
     * 
     * @return Tipo de vehículo.
     */
    public abstract String getTipo();
    
    /**
     * Calcula el precio total de la revisión del vehículo.
     * 
     * @return Precio total de la revisión.
     */
    public abstract double calcularPrecio();
    
    /**
     * Obtiene el pago total de la revisión.
     * 
     * @return Monto total a pagar.
     */
    public abstract double pagoTotal();
    
    /**
     * Valida el número de cilindros del vehículo.
     * 
     * @return Número de cilindros válido.
     */
    public abstract int validarCilindros();
    
    /**
     * Registra el vehículo en el sistema.
     */
    public abstract void registrarVehiculo();
    
    /**
     * 
     * @return devuelve el descuento 
     */
    public abstract double getDescuento();

    /**
     * 
     * @param o
     * @return compara los vehículos por matricula como en la vida real, primero según las letras y si son iguales por los números
     */
    @Override
    public int compareTo(Vehiculo o) {
        String mat1 = this.getMatricula().substring(3);
        String mat2 = o.getMatricula().substring(3);
        String num1 = this.getMatricula().substring(0, 4);
        String num2 = o.getMatricula().substring(0, 4);
        
        if(mat1.equalsIgnoreCase(mat2)){
            return num1.compareTo(num2);
        }
        return mat1.compareTo(mat2);
       
    }

    /**
     * 
     * @param obj
     * @return compara los vehículos por matricula 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        return Objects.equals(this.matricula, other.matricula);
    }

    /**
     * 
     * @return devuelve la matrícula del vehículo
     */
    @Override
    public String toString() {
        return " matricula:" + matricula;
    }

    
    
}