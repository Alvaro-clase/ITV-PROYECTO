package opcion;

import cliente.Cliente;
import excepciones.NotExistsException;
import itv.Taller;
import java.util.TreeSet;

/**
 *
 * @author irene
 */
public class VerClientesPorDNI extends OpcionTaller{

    public VerClientesPorDNI(Taller taller) {
        super("Ver clientes ordenados por DNI", taller);
    }

    /**
     * muestra los clientes en orden por DNI
     */
    @Override
    public void ejecutar() {
        teclado.out("-- VER CLIENTES ORDENADOS POR DNI --\n");
        try {
            TreeSet<Cliente> clientes = new TreeSet<>(taller.listaClientes());
            for (Cliente cliente : clientes) {
                teclado.out(cliente.toString());
            }
        } catch (NotExistsException ex) {
            teclado.out(ex.getMessage());
        }
            
        
    }
    
}
