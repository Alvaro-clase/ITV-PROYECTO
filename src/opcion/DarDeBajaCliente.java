package opcion;
import cliente.Cliente;
import excepciones.NotExistsException;
import interfaces.Validable;
import itv.Taller;
import util.GestorIO;

/**
 *
 * @author ciclost
 */
public class DarDeBajaCliente extends OpcionTaller{
    GestorIO teclado = new GestorIO();
    public DarDeBajaCliente(Taller taller) {
        super("Dar de baja al cliente", taller);
    }

    /**
     * da de baja a un cliente
     */
    @Override
    public void ejecutar() {
        teclado.out("-- DAR DE BAJA AL CLIENTE --\n");
        try {
            
            teclado.out("\n-Inserta el DNI del cliente que quieres dar de baja: ");
            Cliente cliente = taller.getClientePorDNI(Validable.withPattern(teclado.inString().trim(),"DNI"));
            taller.removeCliente(cliente); //AQUÍ HACERLO CON EL REMOVE DE CLIENTE SET
            teclado.out(cliente+"\n---- DADO DE BAJA CORRECTAMENTE ----\n");
        } catch (NotExistsException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
