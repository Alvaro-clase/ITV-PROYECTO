package opcion;

import excepciones.NotExistsException;
import factura.Factura;
import itv.Taller;
import vehiculo.Vehiculo;

/**
 *
 * @author irene, alvaro, alejandro
 */
public class CalculoPagamentosRevisados extends OpcionTaller {

    

    public CalculoPagamentosRevisados(Taller taller) {
        super("Calcular y pagar vehículo revisado",taller);
        
    }

    /**
     * Calcula y cobra el precio totaln de un vehiculo
     */
    @Override
    public void ejecutar() {
       teclado.out("-- CALCULAR Y PAGA VEHÍCULO REVISADO --\n");
        try{
            Vehiculo vehiculo = taller.extraerVehiculoPago();
            double precio = vehiculo.calcularPrecio();
            teclado.out("Vehiculo con matrícula: " + vehiculo.getMatricula() + " tiene que pagar: " + precio + "\n");
            taller.agregarFactura(new Factura(vehiculo, precio));
            teclado.out("Vehiculo con matrícula: " + vehiculo.getMatricula() + " se ha añadido al historial.\n");
        }catch(NotExistsException ex){
            System.out.println(ex.getMessage());
        }
              

    }
}


