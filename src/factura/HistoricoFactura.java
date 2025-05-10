package factura;

import static interfaces.Validable.teclado;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import vehiculo.Vehiculo;

/**
 * @author irene, alvaro, alejandro
 */
public class HistoricoFactura {
    private Map<String, TreeSet<Factura>> historico;

    public HistoricoFactura() {
        historico = new HashMap<>();
    }
    
    /**
     * agrega una nueva factura al historial
     * @param factura 
     */
    public void agregarFactura(Factura factura){
        TreeSet<Factura> facturasCliente = historico.get(factura.getVehiculo().getMatricula());
        if (facturasCliente == null){
            facturasCliente = new TreeSet<>();
            historico.put(factura.getVehiculo().getMatricula(), facturasCliente);
        }
        facturasCliente.add(factura);
    }
    
    /**
     * 
     * @param matricula
     * @return devuelve las facturas del vehículo que se solicita 
     */
    public TreeSet<Factura> obtenerFacturasPorVehiculo(String matricula){
        TreeSet<Factura> facturasCliente = historico.get(matricula);
        if (facturasCliente == null){
            facturasCliente = new TreeSet<>();
        }
        return facturasCliente;
    }
    
    /**
     * muestra todas las facturas
     */
    public void mostrarFacturas(){
        for (String matricula : historico.keySet()) {
            this.mostrarFacturas(matricula);
        }
    }
    
    /**
     * muestra las facturas de un vehículo en concreto
     * @param matricula 
     */
    public void mostrarFacturas(String matricula) {
        System.out.println("-- FACTURAS DEL VEHICULO --\n");
        for (Factura factura : historico.get(matricula)){
            System.out.println(factura);
        }
    }
    
    /**
     * calcula el total de la suma de todas las facturas
     * @return 
     */
    public double calculoFacturas(){
        double resultado= 0.;
        for (String matricula : historico.keySet()) {
            for (Factura factura : historico.get(matricula)) {
                resultado += factura.getPrecioPagado();
            }
        }
        return resultado;
    }
    
    /**
     * 
     * @return comprueba si está vacía la lista de facturas (el historial) 
     */
    public boolean isEmpty(){
        return historico.isEmpty();
    }
    
    /**
     * 
     * @param matricula
     * @return devuelve si el vehículo que se solicita tiene alguna factura guardada en el historial
     */
    public boolean isVehiculoIn(String matricula){
        return historico.containsKey(matricula);
        
    }
    
}
