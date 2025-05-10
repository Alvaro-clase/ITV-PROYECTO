package opcion;

import excepciones.NotExistsException;
import itv.Taller;

/**
 *
 * @author irene, álvaro, alejandro
 */
public class MostrarHistoricoFacturasPorFecha extends OpcionTaller {

    public MostrarHistoricoFacturasPorFecha(Taller taller) {
        super("Mostrar histórico facturas por fecha", taller);
    }

    /**
     * muestra todas las facturas organizadas por fecha
     */
    @Override
    public void ejecutar() {
        teclado.out("-- MOSTRAR HISTÓRICO DE LAS FACTURAS POR FECHA --\n");
        try {
            taller.mostrarFacturas();
        } catch (NotExistsException ex) {
            teclado.out(ex.getMessage());
        }
    }
    
}

