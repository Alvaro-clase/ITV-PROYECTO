
package opcion;

import interfaces.Validable;
import itv.Taller;
import util.Interval;

/**
 *
 * @author irene, alvaro, alejandro
 */
public abstract class OpcionTaller extends Opcion implements Validable {

    protected Taller taller ;
    private Interval OPCIONES = new Interval(1, 8);
    
    public OpcionTaller(String titulo, Taller taller) {
        super(titulo);
        this.taller = taller;
    }

    /**
     * valida que el usuario introduzca una opción válida
     * @param opcion
     * @return opción válida
     */
    public int validarOpcion(int opcion) {
        while (!OPCIONES.inclou(opcion)) {
            teclado.out("Introduce una opción válida: ");
            opcion = teclado.inInt();
        }
        return opcion;
    }

}