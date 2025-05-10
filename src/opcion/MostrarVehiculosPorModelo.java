
package opcion;

import excepciones.NotExistsException;
import interfaces.ComparatorModelo;
import itv.Taller;
import java.util.TreeSet;
import vehiculo.Vehiculo;

/**
 *
 * @author irene
 */
public class MostrarVehiculosPorModelo extends OpcionTaller{

    public MostrarVehiculosPorModelo(Taller taller) {
        super("Mostrar vehiculos ordenados por modelo", taller);
    }

    /**
     * muestra todos los vehículos de la cola ordenados por modelo
     */
    @Override
    public void ejecutar() {
        TreeSet<Vehiculo> vehiculos = new TreeSet<>(new ComparatorModelo());
        teclado.out("-- MOSTRAR VEHÍCULOS ORDENADOS POR MODELO --\n");
        try {
            vehiculos.addAll(taller.getVehiculos());
            for (Vehiculo vehiculo : vehiculos) {
                vehiculo.mostrarTodo();
            }
        } catch (NotExistsException ex) {
            teclado.out(ex.getMessage());
                  
        }
    }
    
}
