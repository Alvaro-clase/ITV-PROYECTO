package cliente;
import excepciones.NotExistsException;
import java.util.*;
/**
 *
 * @author Irene, Álvaro, Alejandro
 */
public class ClienteSet {
    private HashSet<Cliente> clientes;

    public ClienteSet(){
        clientes = new HashSet<>();
    }
    public ClienteSet(Cliente cliente){
        this();
        clientes.add(cliente);
    }

    /**
     * 
     * @return devuelve la lista de clientes 
     */
    public Set<Cliente> getClientes() {
        return clientes;
    }
    
    /**
     * añade un cliente a la lista de clientes
     * @param cliente 
     */
    public void addCliente(Cliente cliente){
        clientes.add(cliente);
    }
    
    /**
     * 
     * @param dni
     * @return devuelve el cliente si está en la lista y null si no lo encuentra 
     */
    public Cliente getCliente(String dni) {
        for (Cliente cliente : clientes) {
            if(cliente.getDni().equalsIgnoreCase(dni))return cliente;
        }
        return null;
    }
    
    /**
     * 
     * @return devuelve la lista de clientes que es vip
     * @throws NotExistsException 
     */
    public TreeSet<Cliente> getClientesVip() throws NotExistsException{
        TreeSet<Cliente> vips = new TreeSet<>();
        for (Cliente cliente : clientes) {
            if(cliente.isVIP())vips.add(cliente);
        }
        if(vips.isEmpty())throw new NotExistsException("ERROR, NO HAY CLIENTES VIP\n");
        return vips;
    }
    
    /**
     * borra el cliente que se solicita
     * @param cliente 
     */
    public void remove(Cliente cliente){
        clientes.remove(cliente);
    }
    
    /**
     * elimina todos los clientes que hay registrados
     */
    public void cleanClientes(){
        clientes.clear();
    }
    
    /**
     * 
     * @return devuelve la lista de clientes en String 
     */
    @Override
    public String toString() {
        return "CLIENTES=" + clientes ;
    }
}
