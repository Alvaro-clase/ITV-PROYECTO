package opcion;

import excepciones.NotExistsException;
import interfaces.Validable;
import itv.Taller;

/**
 *
 * @author irene, álvaro, alejandro
 */
public class AltaSocio extends OpcionTaller {

    public AltaSocio(Taller taller) {
        super("Dar de alta como socio", taller);
    }

    /**
     * da de alta a un cliente como socio
     */
    @Override
    public void ejecutar() {
        teclado.out("-- DAR DE ALTA COMO SOCIO --\n");
        teclado.out("Introduce el DNI del cliente al que vas a dar de alta: ");
        String dni = Validable.withPattern(teclado.inString().trim(),"DNI");

        if (!taller.estaElDni(dni)) {
            teclado.out("\nEL DNI NO ESTÁ REGISTRADO, registra al cliente primero.");
            return;
        }

        try {
            if(taller.getClientePorDNI(dni).isSOCIO()){//IMPLEMENTACIÓN AlreadyExistsException HECHA EN EL MÉTODO ...NO ES LA MEJOR FORMA... PERO ESTÁ
                teclado.out("\nEL CLIENTE YA ES SOCIO");
                return;
            }
        } catch (NotExistsException ex) {
            System.out.println(ex);//ESTA FORMA SE QUE ES UNA RAYADA PERO ASÍ ARPOVECHO LAS OTRAS IMPLEMENTACIONES YA HECHAS
        }
        
        try {
            taller.getClientePorDNI(dni).hacerSocio();
        } catch (NotExistsException ex) {
            teclado.out("\nEL CLIENTE CON EL DNI SEÑALADO NO ESTÁ DADO DE ALTA");
            return;
        }
        teclado.out("\n----NUEVO SOCIO DNI: "+ dni+"----");

    }

}
