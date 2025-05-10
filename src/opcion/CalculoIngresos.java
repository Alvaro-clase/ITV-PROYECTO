package opcion;

import excepciones.NotExistsException;
import itv.Taller;

/**
 *
 * @author irene, alvaro, alejandro
 */

public class CalculoIngresos extends OpcionTaller{
    
    public CalculoIngresos(Taller taller) {
        super("Calculo ingresos totales",taller);    
    }

    @Override
    /**
     * calcula los ingresos totales
     */
    public void ejecutar() {
        teclado.out("-- CÁLCULO DE INGRESOS TOTALES --\n");
        try {
            System.out.println("Los ingresos totales han sido un total de: "+taller.calculoFacturas()+"\n");
        } catch (NotExistsException ex) {
            System.out.println(ex.getMessage());
        }
    }
}