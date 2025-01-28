package itv;

import itv.Cola;
import util.GestorIO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.Interval;

/**
 * @author Alejandro Soler Cruz, Álvaro Carrión Romero e Irene Payá Hernández
 */
public abstract class Vehiculo {

    GestorIO teclado = new GestorIO();
    private final String MATRICULA;
    private int cilindros;
    private int potencia;
    private final int REVISION_CILINDROS = 15;
    protected Interval limite;

    protected Vehiculo(String matricula, int cilindros, int potencia) {
        this.MATRICULA = matricula;
        this.cilindros = cilindros;
        this.potencia = potencia;
    }

    
    protected String validarMatricula() {
        String matricula;
        boolean error;
        do {
            error = false;
            teclado.out("Introduce la matrícula (formato: 4444AAA): ");
            matricula = teclado.inString();
            if (!matricula.matches("\\d{4}[A-Z]{3}") && validarmMatriculasVehiculosCola(matricula)) error = true;
                
            
        } while (error);
    }

    public int pedirPotencia() {
        teclado.out("Introduce la potencia: ");
        return teclado.inInt();
    }

    public int getCilindros() {
        return cilindros;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getREVISION_CILINDROS() {
        return REVISION_CILINDROS;
    }
    
    public String getMatricula(){
        return MATRICULA;
    }

    public void mostrarTodo(){
        
        teclado.out("DATOS DEL VEHÍCULO");
        teclado.out("------------------");
        teclado.out("Matrícula: "+ this.MATRICULA.toString());
        teclado.out("Cilindros: "+ this.cilindros);
        teclado.out("Potencia: "+ this.potencia +"CCC");
    }
    protected abstract int validarCilindros();

    public abstract double calcularPrecio();

    public abstract double calcularPrecioBase();

    public abstract double calcularAdicional();

}
