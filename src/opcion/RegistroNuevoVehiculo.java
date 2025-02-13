package opcion;


import cliente.Cliente;
import excepciones.NotExistsException;
import itv.Cola;
import itv.Taller;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.GestorIO;
import util.Interval;
import vehiculo.Camion;
import vehiculo.Coche;
import vehiculo.Furgoneta;
import vehiculo.MicroBus;
import vehiculo.Vehiculo;

/**
 *
 * @author irene, alvaro, alejandro
 */
public class RegistroNuevoVehiculo extends OpcionTaller {

    
    Interval OPCIONES_COCHE = new Interval(1, 4);

    public RegistroNuevoVehiculo(Taller taller) {
        super("Registrar nuevo vehículo", taller);
    }

    /**
     * Registra un nuevo vehiculo
     */
    @Override
    public void ejecutar() {
        Vehiculo vehiculo = null;
        String dniCliente;
        String matricula;
        boolean esValida;
        Pattern patron = Pattern.compile("^[0-9]{4}[A-Z]{3}$");
        
        teclado.out("Introduce el DNI del cliente:");
        dniCliente = validarDNI(teclado.inString().toUpperCase().trim());
        
        //meter excepción notexists
        Cliente cliente;
        try {
            cliente = taller.getClientePorDNI(dniCliente);
        } catch (NotExistsException ex) {
            teclado.out("El cliente con el DNI " + dniCliente +" no ha sido dado de alta");
            return;
        }
        
        
        
        teclado.out("Selecciona el tipo de vehículo: \n");
        teclado.out("1. Coche\n");
        teclado.out("2. Microbús\n");
        teclado.out("3. Furgoneta\n");
        teclado.out("4. Camión\n");
        teclado.out("Selecciona una opción: ");
        int opcion = validarOpcion(teclado.inInt());

        do {
            teclado.out("Introduce la matrícula del coche (formato 1234ABC): ");
            matricula = teclado.inString().toUpperCase().trim();

            Matcher matcher = patron.matcher(matricula);
            esValida = (matcher.matches() && taller.matriculaValida(matricula));

            if (!esValida) {
                teclado.out("Matrícula inválida.");
            }
        } while (!esValida);
        
        teclado.out("Introduce el modelo del vehículo: ");
        String modelo = teclado.inString();

        switch (opcion) {
            
            case 1:
                vehiculo = new Coche(cliente,matricula, modelo); //He usado constructores con estos dos atributos porque son finales y necesito inicializar los tipos de vehiculo para usar el polimorfismo
                break;
            case 2:
                vehiculo = new MicroBus(cliente, matricula, modelo);
                break;
            case 3:
                vehiculo = new Furgoneta(cliente, matricula, modelo);
                break;
            case 4:
                vehiculo = new Camion(cliente,matricula, modelo);
                break;
            
        }
        
        
        taller.registrarVehiculo(vehiculo);
        taller.insertarVehiculo(vehiculo);
        teclado.out("Vehiculo " + vehiculo.getMatricula() + " registrado\n");
        
    }
    private String validarDNI(String dni){
        while(!validarConPatrones(Pattern.compile("^[0-9]{8}[A-Z]$"),dni)){
                teclado.out("Error, el DNI debe ser 8 números y una letra.\n");
                dni = teclado.inString().toUpperCase().trim();
            }
        return dni;
    }
    private boolean validarConPatrones(Pattern patron, String telefono){
        Matcher matcher = patron.matcher(telefono);
        return matcher.matches();
    }
    /**
     * valida que el usuario haya introducido una opción válida
     * @param opcion
     * @return opción valida
     */
    @Override
    public int validarOpcion(int opcion) {
        while (!OPCIONES_COCHE.inclou(opcion)) {
            teclado.out("Introduce una opción válida: ");
            opcion = teclado.inInt();
        }
        return opcion;
    }

}