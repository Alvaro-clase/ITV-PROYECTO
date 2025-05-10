
package opcion;

import itv.Taller;

/**
 *
 * @author irene, alvaro, alejandro
 */
public class MostrarBoxes extends OpcionTaller {

    
    public MostrarBoxes(Taller taller){
        super("Mostrar estado de todos los boxes", taller);
    }

    /**
     * muestra el estado de todos los boxes
     */
    @Override
    public void ejecutar(){
        teclado.out("-- MOSTRAR ESTADO DE TODOS LOS BOXES --\n");
        taller.mostrarBoxes();
    }
   

}
