package main;

import cliente.ClienteSet;
import itv.Taller;
import menu.Menu;
import opcion.*;


/**
 *
 * @author irene, alvaro, alejandro
 */
public class ITV {

    private final Taller taller;
    private final Menu menu;
    private ClienteSet clientes;

    public ITV() {
        this.taller = new Taller();
        this.menu = new Menu();
        menu.añadirOpcion(new RegistrarCliente(taller));
        menu.añadirOpcion(new DarDeBajaCliente(taller));
        menu.añadirOpcion(new BuscarClientesPorDni(taller));
        menu.añadirOpcion(new VerClientesPorDNI(taller));
        menu.añadirOpcion(new AltaSocio(taller));
        menu.añadirOpcion(new RegistroNuevoVehiculo(taller));
        menu.añadirOpcion(new VehiculosPendientesRevision(taller));
        menu.añadirOpcion(new ReclamacionVehiculo(taller));
        menu.añadirOpcion(new PasarDeFase(taller));
        menu.añadirOpcion(new MostrarBox(taller));
        menu.añadirOpcion(new MostrarBoxes(taller));
        menu.añadirOpcion(new CalculoPagamentosRevisados(taller));
        menu.añadirOpcion(new HistoricoFacturasVehiculo(taller));
        menu.añadirOpcion(new MostrarHistoricoFacturasPorFecha(taller));
        menu.añadirOpcion(new CalculoIngresos(taller));
        menu.añadirOpcion(new VehiculoMatriculaNueva(taller));
        menu.añadirOpcion(new ClienteDNIAlto(taller));
        menu.añadirOpcion(new MostrarVehiculosPorModelo(taller));
        menu.añadirOpcion(new InformeClientesVIP(taller));
        menu.añadirOpcion(new Salir(menu));
      
    }

    /**
     * ejecuta el programa
     *
     * @param args
     */
    public static void main(String[] args) {
        ITV programa = new ITV();
        programa.inicio();
    }

    /**
     * el programa principal
     */
    public void inicio() {
        do {
            menu.mostrar();
            menu.getOpcion().ejecutar();
        }while(!menu.getSalir());
    }
}
