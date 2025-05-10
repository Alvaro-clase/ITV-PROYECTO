package vehiculo;

import cliente.Cliente;
import util.Interval;

/**
 * @author irene, alvaro, alejandro
 */
public class Coche extends TransportePersonas {

    private static final double COSTE_PLAZA_ADICIONAL = 1.5;

    public Coche(Cliente cliente, String matricula, String modeloVehiculo) {
        super(cliente, matricula, modeloVehiculo);
    }

    /**
     * Valida la cantidad de plazas asegurando que esté dentro del rango
     * permitido.
     *
     * @return Número de plazas válido.
     */
    public int validarPlazas() {
        int plazas;
        boolean error;
        Interval limitesPlazas = new Interval(2, 7);

        do {
            error = false;
            teclado.out("Introduce las plazas del coche: ");
            plazas = teclado.inInt();
            if (!limitesPlazas.inclou(plazas)) {
                error = true;
                teclado.out("Solo puede tener entre 2 y 7 plazas.\n");
            }
        } while (error);

        return plazas;
    }

    /**
     * Obtiene el tipo de vehículo.
     *
     * @return "Coche" como tipo de vehículo.
     */
    @Override
    public String getTipo() {
        return "Coche";
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
     * 
     * @return devuelve el descuento 
     */
    @Override
    public double getDescuento() {
        return super.getDescuento();
    }

}
