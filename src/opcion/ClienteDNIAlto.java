
package opcion;

import cliente.Cliente;
import excepciones.NotExistsException;
import itv.Taller;
import util.GestorIO;

/**
 *
 * @author irene
 */
public class ClienteDNIAlto extends OpcionTaller{

    public ClienteDNIAlto(Taller taller) {
        super("Cliente con el DNI más alto", taller);
    }

    /**
     * busca el dni más alto de los que están registrados y lo muestra
     */
    @Override
    public void ejecutar() {
        GestorIO teclado = new GestorIO();
        Cliente cliente;
        teclado.out("-- CLIENTE CON EL DNI MÁS ALTO --\n");
        try {
            cliente = Taller.encontrarMaximo(taller.listaClientes());
            teclado.out("EL DNI MÁS ALTO ES: "+ cliente.getDni()+ "\n");
        } catch (NotExistsException ex) {
            teclado.out(ex.getMessage());
        }
             
        
    }
    
}
