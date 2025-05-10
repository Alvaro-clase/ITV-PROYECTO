package menu;

import interfaces.Validable;
import java.util.ArrayList;
import opcion.*;
import util.GestorIO;
import util.Interval;

/**
 *
 * @author ciclost
 */
public class Menu implements Validable{
    GestorIO teclado = new GestorIO();
    private ArrayList<Opcion> opciones;
    private int numOpciones = 0;
    private boolean salir = false;
    
    public Menu(){
        opciones = new ArrayList<>();
    }
    
    /**
     * añade una opcion al menú
     * @param opcion 
     */
    public void añadirOpcion(Opcion opcion){
        opciones.add(opcion);
    }
    
    /**
     * muestra las opciones del menú
     */
    public void mostrar(){
        teclado.out("\n----MENÚ OPCIONES----\n");       
        for (int i = 0; i < opciones.size(); i++) {
            opciones.get(i).mostrar(i+1);
        }
    }
    
    /**
     * 
     * @return devuelve la opción elegida 
     */
    public Opcion getOpcion(){
        return opciones.get(validarOpcion() - 1);
    }
    
    /**
     * 
     * @return valida que la opción es correcta (lo cambiaremos implementando Validable) 
     */
    private int validarOpcion(){
        Interval opcion = new Interval(1,opciones.size());
        teclado.out("Dame la opción que quieres: ");      
        int usuario = Validable.opcion(teclado.inInt(), opcion);
                
        return usuario;
    }
    
    /**
     * 
     * @return devuelve el estado de salir 
     */
    public boolean getSalir(){
        return salir;
    }
    
    /**
     * cambia el estado de salir a true para terminar el programa
     */
    public void salir(){
        this.salir = true;
    }
            
            

}
