
package opcion;

import excepciones.NotExistsException;
import interfaces.Validable;
import itv.Taller;

/**
 *
 * @author ciclost
 */
public class BuscarClientesPorDni extends OpcionTaller{

    public BuscarClientesPorDni(Taller taller) {
        super("Buscar cliente por dni", taller);
    }
    
    /**
     * busca al cliente por dni y lo muestra
     */
    @Override
    public void ejecutar() {
        teclado.out("-- BUSCAR CLIENTE POR DNI --\n");
        try {
            teclado.out("\nInserte el DNI del Cliente: ");
            System.out.println(taller.getClientePorDNI(Validable.withPattern(teclado.inString(), "DNI")));
        } catch (NotExistsException ex) {
            System.out.println(ex);
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }
    }

}
