package vehiculo;

import cliente.Cliente;
import util.Interval;

/**
 * 
 * @author irene, alvaro, alejandro
 */
public class Camion extends TransporteCarga {
    private static final int FIJO_ADICIONAL = 40;


    
    public Camion(Cliente cliente, String matricula, String modeloVehiculo) {
        super(cliente, matricula, modeloVehiculo);
    }
    
    /**
     * Calcula el precio total de la revisión del camión.
     * 
     * @return Precio total de la revisión.
     */
    @Override
    public double calcularPrecio() {
        double precioBase = super.calcularPrecio();
        double incrementoPorcentaje = precioBase * 0.2;
        double incrementoPotencia = (this.getPotencia() > 2000) ? 20 : 0;
        return precioBase + FIJO_ADICIONAL + incrementoPorcentaje + incrementoPotencia;
    }

    /**
     * Obtiene el tipo de vehículo.
     * 
     * @return "Camion" como tipo de vehículo.
     */
    @Override
    public String getTipo() {
        return "Camion";
    }

    /**
     * Calcula el pago total de la revisión.
     * 
     * @return Pago total a realizar.
     */
    @Override
    public double pagoTotal() {
        return this.calcularPrecio();
    }
    
    /**
     * Valida la cantidad de cilindros del camión asegurando que esté dentro del rango permitido.
     * 
     * @return Número de cilindros válido.
     */
    @Override
    public int validarCilindros() {
        int cilindros;
        boolean error;
        Interval limite = new Interval(8, 16);
        do {
            error = false;
            teclado.out("Introduce los cilindros: ");
            cilindros = teclado.inInt();
            if (!limite.inclou(cilindros)) {
                error = true;
                teclado.out("Solo puede tener entre 8 y 16 cilindros.\n");
            }
        } while (error);

        return cilindros;
    }

    /**
     * 
     * @return devuelve el descuento 
     */
    @Override
    public double getDescuento() {
        if(cliente.esSuPrimeraVez())return cliente.descuentoPrimeraVez();
        if(cliente.isSOCIO())return cliente.descuentoSocio();
        return 1.;
    }

}