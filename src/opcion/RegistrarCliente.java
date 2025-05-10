package opcion;

import cliente.Cliente;
import excepciones.AlreadyExistsException;
import excepciones.NotExistsException;
import interfaces.Validable;
import itv.Taller;
import java.util.regex.Matcher;

/**
 *
 * @author ciclost
 */
public class RegistrarCliente extends OpcionTaller {

    public RegistrarCliente(Taller taller) {
        super("Resgistrar cliente", taller);
    }

    /**
     * registra a un nuevo cliente
     */
    @Override
    public void ejecutar() {
        boolean error = false;
        String dni = null;
        String nombre = null;
        String telefono = null;
        boolean vip = false;
        Matcher matcher;
        
        //INTRODUCCION DEL DNI
        teclado.out("\n--- REGISTRAR CLIENTE ---");
        teclado.out("\nIntroduce el DNI: ");
        try {
            dni = taller.estaEsteDNI(Validable.withPattern(teclado.inString(),"DNI"));
        } catch (AlreadyExistsException ex) {
            System.out.println(ex);//HAGO UN PRINT DE LA EXCEPCIÓN Y LUEGO UTILIZO EL ATRIBUTO QUE SE HA DADO
            try {
                Cliente clienteExistente = taller.getClientePorDNI(ex.getStringExistente());//DEMUESTRO QUE EL CLIENTE ESTA EN EL TALLER YA REGISTRADO
                System.out.println("\n--Este es el cliente ya registrado.--");              //PARA QUE NO SE VUELVA A COMETER EL ERROR.
                System.out.println(clienteExistente);
            } catch (NotExistsException ex1) {
                System.out.println(ex1);
            } catch (NullPointerException ex1) {
                System.out.println(ex1);
            }
            return;
        }
        //INTRODUCCIÓN DEL NOMBRE
        do {
            error = false;
            teclado.out("\nIntroduce el nombre: ");
            try {
                nombre = esteNombreExiste(teclado.inString().toUpperCase().trim());//ESTO ES ALGO LIOSO PERO LE PREGUNTO A TALLER SI YA EXISTE EL NOMBRE CON UN MÉTODO QUE ME HE CREADO AQUÍ
            } catch (AlreadyExistsException ex) {                                  //PARA QUE SI YA EXISTE ESTE NOMBRE LE PREGUNTE SI ES CORRECTO O SE HA EQUIVOCADO
                System.out.println(ex);
                System.out.println("¿Tan conocido es " + ex.getStringExistente() + " como nombre?");
                System.out.println("¿Quieres guardar entonces este nombre?(SI / NO)");
                if (Validable.respuestaSiNo(teclado.inString().trim()).equalsIgnoreCase("SI")) {//AQUÍ PREGUNTO SI ES EL NOMBRE EXISTENTE EL QUE QUIERE GUARDAR COMO VÁLIDO
                    nombre = ex.getStringExistente();
                } else {
                    error = true;
                }
            } finally {
                teclado.out("Ok. Sigamos.");
            }
        } while (error);
        //INTRODUCCIÓN DEL NÚMERO DEL TELÉFONO
        do {
            error = false;
            teclado.out("\nIntroduce el número de teléfono: ");
            telefono = Validable.withPattern(teclado.inString(), "TELEFONO");
            try {
                telefono = esteTelefonoExiste(telefono);
            } catch (AlreadyExistsException ex) {
                System.out.println(ex);
                error = true;
                System.out.println("Debes insertar otro teléfono");
            }
            
        }while(error);  
        
        //SI UN CLIENTE QUIERE SER VIP 
        teclado.out("\n¿El cliente quiere ser VIP? (Si / No):  ");
        String respuesta = Validable.respuestaSiNo(teclado.inString());
        if (respuesta.equalsIgnoreCase("SI")) {
            vip = true;
        }
        teclado.out("\n---EL CLIENTE -"+nombre+"- HA SIDO REGISTRADO--\n");
        taller.añadirCliente(new Cliente(dni, nombre, telefono, vip));//INSERTAMOS EL CLIENTE
    }

    /**
     * 
     * @param nombre
     * @return devuelve el error
     * @throws AlreadyExistsException 
     */
    private String esteNombreExiste(String nombre) throws AlreadyExistsException {
        return taller.esteNombreExiste(nombre);
    }
    
    /**
     * 
     * @param telefono
     * @return devuelve el error
     * @throws AlreadyExistsException 
     */
    private String esteTelefonoExiste(String telefono) throws AlreadyExistsException{
        return taller.esteTelefonoExiste(telefono);
    }

    

}
