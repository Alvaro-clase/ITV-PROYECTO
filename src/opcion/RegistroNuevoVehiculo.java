
package opcion;


import cliente.Cliente;
import excepciones.*;
import interfaces.Validable;
import itv.Taller;
import java.util.regex.Pattern;
import util.Interval;
import vehiculo.*;

/**
 *
 * @author irene, alvaro, alejandro
 */
public class RegistroNuevoVehiculo extends OpcionTaller implements Validable{

    
    Interval OPCIONES_COCHE = new Interval(1, 4);

    public RegistroNuevoVehiculo(Taller taller) {
        super("Registrar nuevo vehículo", taller);
    }

    /**
     * Registra un nuevo vehiculo
     */
    @Override
    public void ejecutar() {
        teclado.out("-- REGISTRAR NUEVO VEHÍCULO --\n");
        Vehiculo vehiculo = null;
        String dniCliente = null;
        String matricula;
        boolean error;
        Pattern patron = Pattern.compile("^[0-9]{4}[A-Z]{3}$");
        
        teclado.out("\nIntroduce el DNI del cliente:");
        dniCliente = Validable.withPattern(teclado.inString().toUpperCase().trim(), "dni");
        
        //meter excepción notexists
        Cliente cliente;
        try {
            cliente = taller.getClientePorDNI(dniCliente);
            cliente.unVehiculoMas();
        } catch (NotExistsException ex) {
            teclado.out(ex.getMessage());
            return;
        }
        
        teclado.out("Selecciona el tipo de vehículo: \n");
        teclado.out("1. Coche\n");
        teclado.out("2. Microbús\n");
        teclado.out("3. Furgoneta\n");
        teclado.out("4. Camión\n");
        teclado.out("Selecciona una opción: ");
        int opcion = Validable.opcion(teclado.inInt(), OPCIONES_COCHE);
        
        do{
            error = false;
            teclado.out("Introduce la matrícula del coche (formato 1234ABC): ");
            matricula = Validable.withPattern(teclado.inString(),"MATRICULA");
            
            try {
                taller.validarMatricula(matricula);
            } catch (AlreadyExistsException ex) {
                System.out.println(ex.getMessage());
                error = true;
            }
            
        }while(error);
        
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
        try{
            taller.insertarVehiculo(vehiculo);
            teclado.out("\n----Vehiculo " + vehiculo.getMatricula() + " registrado----\n");
        }catch(FullQueueException ex){
            System.out.println(ex.getMessage());
            System.out.println(ex);
        }
    }

    

}