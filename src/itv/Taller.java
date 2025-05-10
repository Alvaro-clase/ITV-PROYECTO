package itv;

import cliente.Cliente;
import cliente.ClienteSet;
import excepciones.NotExistsException;
import vehiculo.Vehiculo;
import excepciones.AlreadyExistsException;
import excepciones.FullQueueException;
import factura.Factura;
import java.util.List;
import java.util.Set;
import factura.HistoricoFactura;
import interfaces.ComparatorModelo;
import java.util.ArrayList;
import java.util.TreeSet;
import util.GestorIO;
import util.Interval;


/**
 * @author irene, alvaro, alejandro
 *
 *
 */
public class Taller {

    private GestorIO teclado = new GestorIO();
    private GenericQueue<Box> boxes;
    private GenericQueue<Vehiculo> colaPrincipal;
    private GenericQueue<Vehiculo> colaDePago;
    private double ingresosTotales;
    public final Interval NUMERO_BOXES = new Interval(1, 6);
    private ClienteSet clientes;
    private HistoricoFactura historicoFacturas;
    private static int contadorID = 0;

    public Taller()  {
        boxes = new GenericQueue<>((int)NUMERO_BOXES.getSuperior());
        
        try {
            for (int i = 0; i < NUMERO_BOXES.getSuperior(); i++) {
            boxes.enqueue(new Box());
            }
        } catch (FullQueueException ex) {
            teclado.out("No se han podido añadir todos los elementos");
        }
        
        
        colaPrincipal = new GenericQueue<>(20);
        colaDePago = new GenericQueue<>(4);       
        ingresosTotales = 0;
        clientes = new ClienteSet();
        historicoFacturas = new HistoricoFactura();
    }
    
    
    
    /**
     * HACE EL CÁLCULO DE LAS FACTURAS ACUMULADAS 
     * @return RESULTADO
     * @throws NotExistsException 
     */
    public double calculoFacturas() throws NotExistsException{
        if(historicoFacturas.isEmpty())throw new NotExistsException("ERROR: Aun no hay facturas");
        return historicoFacturas.calculoFacturas();
    }
    
    /**
     * aumenta el contador de facturas y agrega la factura al histórico
     * @param factura 
     */
    public void agregarFactura(Factura factura) {
        factura.setID(contadorID);
        contadorID++;
        historicoFacturas.agregarFactura(factura);
    }

    /**
     * muestra el histórico de facturas
     * @throws NotExistsException 
     */
    public void mostrarFacturas() throws NotExistsException {
        if(historicoFacturas.isEmpty())throw new NotExistsException("ERROR: Aun no hay facturas registradas.");
        historicoFacturas.mostrarFacturas();
    }
    
    /**
     * muestra las facturas de un vehículo en concreto
     * @param matricula
     * @throws NotExistsException 
     */
    public void mostrarFacturas(String matricula) throws NotExistsException{
        if(historicoFacturas.isEmpty())throw new NotExistsException("ERROR: Aun no hay facturas registradas.");
        if(historicoFacturas.isVehiculoIn(matricula)){
            System.out.println(historicoFacturas.obtenerFacturasPorVehiculo(matricula));
        }else{
            teclado.out("El vehículo no tiene ninguna factura asociada.");
        }
    }

    /**
     * Busca a un cliente dentro del array de clientes
     *
     * @param dni
     * @return cliente (NULL SI NO ESTÁ)
     * @throws NotExistsException
     * @throws NullPointerException
     */
    public Cliente getClientePorDNI(String dni) throws NotExistsException, NullPointerException{
        try{
            estaEsteDNI(dni);//ESTE MÉTODO LLAMA AL DE ABAJO Y SI SALTA LA EXCEPTION HACE EL FOR
            throw new NotExistsException("ERROR: El cliente con el DNI: " + dni + " no existe.");//SI NO ESTÁ LANZA ESTA EXCEPTION
        }catch(AlreadyExistsException ex){
            return clientes.getCliente(dni);
        }
    }
    
    /**
     * elimina el cliente
     * @param cliente 
     */
    public void removeCliente(Cliente cliente){
        clientes.remove(cliente);
    }

    /**
     * Verifica si está el dni
     *
     * @param dni
     * @return dni
     * @throws excepciones.AlreadyExistsException
     */
    public String estaEsteDNI(String dni)throws AlreadyExistsException {
        if(clientes.getCliente(dni)!=null)throw new AlreadyExistsException(dni,"ERROR. El DNI que quiere insertar está registrado.");
        return dni;
    }

    /**
     * Añade un Cliente al array de los clientes
     *
     * @param cliente
     */
    public void añadirCliente(Cliente cliente){
        clientes.addCliente(cliente);
    }


    /**
     * 
     * @param dni
     * @return devuelve si está el dni registrado
     */
    public boolean estaElDni(String dni) {
        if(clientes.getCliente(dni)!=null)return true;
        return false;
    }
    /**
     * Registra un vehículo en el sistema.
     *
     * @param vehiculo el vehículo a registrar.
     */
    public void registrarVehiculo(Vehiculo vehiculo) {
        vehiculo.insertarCilindros();
        vehiculo.insertarCc();
        vehiculo.registrarVehiculo();
    }

    /**
     * Verifica si hay vehículos en la cola de pago.
     *
     * @return true si hay vehículos para pagar, false en caso contrario.
     */
    public boolean hayVehiculosParaPagar() {
        return !this.colaDePago.isEmpty();
    }

    /**
     * Extrae un vehículo de la cola de pago.
     *
     * @return el vehículo extraído.
     */
    public Vehiculo extraerVehiculoPago() throws NotExistsException {
        if(this.colaDePago.isEmpty())
            throw new NotExistsException("ERROR: No hay vehículos en la cola de pagos.");
        return this.colaDePago.dequeue();
    }

    /**
     * Extrae un vehículo de la cola principal.
     *
     * @return el vehículo extraído.
     */
    public Vehiculo extraerVehiculoCola() throws NotExistsException {
        if(this.colaPrincipal.isEmpty())
            throw new NotExistsException("ERROR: No hay vehículos en la cola principal.");
        return this.colaPrincipal.peek();
    }

    /**
     * 
     * @return elimina el sigueinte vehiculo en la cola
     * @throws NotExistsException 
     */
    public Vehiculo eliminarVehiculoCola() throws NotExistsException {
        if(this.colaPrincipal.isEmpty())
            throw new NotExistsException("ERROR: No hay vehículos en la cola principal.");
        return this.colaPrincipal.dequeue();
    }
    /**
     * Si la matrícula ya está registrada en el taller NO será válida.
     * @param matricula la matrícula a validar.
     * @return true si la matrícula es válida.
     */
    public void validarMatricula(String matricula) throws AlreadyExistsException {
        if (GenericQueue.matriculaValida(matricula,colaPrincipal)) {
            for (Box box : boxes.getCola()) {
                if (!box.matriculaValida(matricula)) {
                    throw new AlreadyExistsException("ERROR: La matricula ya está registrada");
                }
            }  
            return;
        }
        throw new AlreadyExistsException("ERROR: La matricula ya está registrada"); 
    }

    /**
     * Avanza los vehículos en un box específico.
     *
     * @param numeroBox el número del box.
     */
    public void avanzarVehiculos(int numeroBox) {
        boxes.getCola().get(numeroBox-1).avanzarVehiculos();
    }

    /**
     * Verifica si la cola principal está vacía.
     *
     * @return true si la cola está vacía, false en caso contrario.
     */
    public boolean colaEstaVacia() {
        return this.colaPrincipal.isEmpty();
    }

    /**
     * Verifica si hay boxes vacíos.
     *
     * @return true si hay algún box libre, false en caso contrario.
     */
    public boolean boxesVacios() {
        for (Box box : boxes.getCola()) {
            if (box.boxLibre()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Inserta un vehículo en la cola principal.
     *
     * @param vehiculo el vehículo a insertar.
     */
    public void insertarVehiculo(Vehiculo vehiculo) throws FullQueueException {
        this.colaPrincipal.enqueue(vehiculo);
    }

    /**
     * Asigna un vehículo a un box específico.
     *
     * @param numeroBox el número del box.
     * @param vehiculo el vehículo a asignar.
     */
    public void asignarVehiculoBox(int numeroBox, Vehiculo vehiculo) throws AlreadyExistsException {
        boxes.getCola().get(numeroBox-1).asignarVehiculo(vehiculo);
    }

    /**
     * Muestra el estado de un box específico.
     *
     * @param box el número del box.
     */
    public void mostrarBox(int box) {
        boxes.getCola().get(box-1).mostrarEstado();
    }

    /**
     * Muestra el estado de todos los boxes.
     */
    public void mostrarBoxes() {
        int i = 1;
        for (Box box : boxes.getCola()) {
            teclado.out("\nBOX " + i++ + "\n");
            box.mostrarEstado();
            teclado.out("\n");
        }
    }

    /**
     * Extrae el último vehículo que pasó por un box específico.
     *
     * @param numeroBox el número del box.
     * @return el vehículo extraído.
     */
    public Vehiculo extraerVehiculoBox(int numeroBox) throws NotExistsException {
        return boxes.getCola().get(numeroBox-1).copiarUltimoVehiculo();
    }

    /**
     * Verifica si la última fase de un box está ocupada.
     *
     * @param numeroBox el número del box.
     * @return true si la última fase está ocupada, false en caso contrario.
     */
    public boolean ultimaFaseOcupada(int numeroBox) {
        return boxes.getCola().get(numeroBox-1).ultimaFaseOcupada();
    }

    /**
     * Mueve un vehículo a la cola de pago.
     *
     * @param vehiculo el vehículo a mover.
     */
    public void meterColaPago(Vehiculo vehiculo)throws FullQueueException {
        this.colaDePago.enqueue(vehiculo);
    }
    /**
     * COMPRUEBA SI EXISTE EL NOMBRE, SI NO EXISTE LO RETORNA SI EXISTE LANZA UNA EXCEPCION
     * @param nombre
     * @return EL NOMBRE
     * @throws AlreadyExistsException 
     */
    public String esteNombreExiste(String nombre) throws AlreadyExistsException{
        for(Cliente c: clientes.getClientes()){
            if(c.getNombre().equalsIgnoreCase(nombre))throw new AlreadyExistsException(nombre, "QUE CASUALIDAD!! Ya existe este nombre!");
        }
        return nombre;
    }
    /**
     * COMPRUEBA SI EXISTE EL TELEFONO, SI NO EXISTE LO RETORNA Y SI EXISTE LANZA UNA EXCEPCION
     * @param telefono
     * @return EL TELEFONO
     * @throws AlreadyExistsException 
     */
    public String esteTelefonoExiste(String telefono) throws AlreadyExistsException{
        for(Cliente c: clientes.getClientes()){
            if(c.getTelefono().equals(telefono))throw new AlreadyExistsException(telefono,"ESTE TELÉFONO YA EXISTE."+telefono+"\n");
        }
        return telefono;
    }
    

    /**
     * 
     * @return devuelve la lista de clientes (devolverá una nueva para evitar problemas de modificación de datos)
     * @throws NotExistsException 
     */
    public List<Cliente> listaClientes() throws NotExistsException{
        if(this.clientes.getClientes().isEmpty())throw new NotExistsException("NO HAY CLIENTES REGISTRADOS\n");
        return new ArrayList<>(this.clientes.getClientes()); //devuelvo uno nuevo para mantener la lista original intacta (por seguridad)
    }
    
    /**
     * 
     * @return devuelve la lista de vehiculos (pero una nueva para evitar problemas con la modificación de datos)
     * @throws NotExistsException 
     */
    public List<Vehiculo> getVehiculos() throws NotExistsException{
        if(this.colaPrincipal.isEmpty())throw new NotExistsException("NO HAY VEHICULOS EN LA COLA\n");
        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.addAll(this.colaPrincipal.getCola());
        return vehiculos;
    }
    
    /**
     * 
     * @return devuelve los vehículos ordenados por modelo (una nueva lista para evitar problemas de modificación de datos)
     * @throws NotExistsException 
     */
    public Set<Vehiculo> listaVehiculosOrdenadosModelo() throws NotExistsException{
        Set<Vehiculo> vehiculosOrdenados = new TreeSet<>(new ComparatorModelo()); //uso el comparator para ordenar por matricula pero de forma real, revisando primero las letras y luego los números (de mat más nueva a más antigua)
        vehiculosOrdenados.addAll(this.getVehiculos());
        return vehiculosOrdenados;
    }

    /**
     * 
     * @return devuelve la lista de clientes vip
     * @throws NotExistsException 
     */
    public TreeSet<Cliente> getClientesVip() throws NotExistsException{
        return clientes.getClientesVip();
    }
    
    /**
     * 
     * @param <E>
     * @param lista
     * @return devuelve el elemento máximo de cualquier lista que implemente comparable
     * @throws NotExistsException 
     */
    public static <E extends Comparable<E>> E encontrarMaximo(List<E> lista) throws NotExistsException{
        if(lista.isEmpty())throw new NotExistsException("ERROR. ESTÁ VACÍA\n");

        E maximo = lista.get(0);
        for (E e : lista) {
            if(e.compareTo(maximo)>0) maximo=e;
        }
        return maximo;       
    }

}
