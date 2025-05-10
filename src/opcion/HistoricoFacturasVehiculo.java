
package opcion;

import excepciones.NotExistsException;
import interfaces.Validable;
import itv.Taller;

/**
 *
 * @author álvaro, irene, alejandro
 */
public class HistoricoFacturasVehiculo extends OpcionTaller {

    public HistoricoFacturasVehiculo(Taller taller) {
        super("Consultar histórico de revisiones por vehículo", taller);
    }

    /**
     * muestra el histórico de facturas de un vehículo en concreto
     */
    @Override
    public void ejecutar() {
        teclado.out("-- CONSULTAR HISTÓRICO DE REVISIONES POR VEHÍCULO --\n");
        try {
            teclado.out("Inserta la matrícula del vehículo: ");
            String matricula = Validable.withPattern(teclado.inString(), "MATRICULA");
            taller.mostrarFacturas(matricula);
        } catch (NotExistsException ex) {
            System.out.println(ex);
        }
    }
    
}
