
package interfaces;

import java.util.Comparator;
import vehiculo.Vehiculo;

/**
 *
 * @author irene
 */
public class ComparatorModelo implements Comparator<Vehiculo>{

    /**
     * 
     * @param o1
     * @param o2
     * @return devuelve los vehículos comparados por modelo 
     */
    @Override
    public int compare(Vehiculo o1, Vehiculo o2) {
        return o1.getModelo().compareTo(o2.getModelo());
    }
    
}
