package itv;

import util.GestorIO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alejandro Soler Cruz, Álvaro Carrión Romero e Irene Payá Hernández
 */
public abstract class Vehiculo {
    GestorIO teclado = new GestorIO();
    private int cilindros;
    private int potencia;
    private final int REVISION_CILINDROS = 15;
    
    protected Vehiculo(int cilindros, int potencia){
        this.cilindros = cilindros;
        this.potencia = potencia;
    }
    
    public int pedirPotencia(){
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
    
    
    
    public abstract double calcularPrecio();
    
    public abstract double calcularPrecioBase();
    
    public abstract double calcularAdicional();
    
    
  
}
