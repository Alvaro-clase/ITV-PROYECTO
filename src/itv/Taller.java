package itv;

import java.util.Arrays;
import util.GestorIO;
import util.Interval;

/**
 * @author Irene Payá, Álvaro Carrión, Alejando Soler
 */
public class Taller {

    GestorIO teclado = new GestorIO();
    private Box[] boxes;
    private Cola colaPrincipal;
    private String[] matriculasCochesEnTaller;//incluido ahora por mi

    public Taller() {
        boxes = new Box[6];
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new Box();
        }
        colaPrincipal = new Cola();
        matriculasCochesEnTaller = new String[1];//incluido ahora por mi
    }

    /**
     * Método para comprobar que los boxes están vacios
     *
     * @return
     */
    public boolean boxesVacios() {
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (boxes[i].boxLibre()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Método para comprobar que la matrícula no se encuentra en ninguno de los
     * boxes.
     *
     * @param matricula
     * @return (boolean)
     */
    public boolean comprobarMatriculaBoxes(String matricula) {
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i].comprobarMatriculaFases(matricula)) {
                return true;
            }
        }
        return false;
    }

    public Vehiculo registrarVehiculo() {
        int opcion;
        Interval opciones = new Interval(1,4);
        teclado.out("¿Qué vehículo vas a registrar?");
        teclado.out("1. Coche");
        teclado.out("2. Microbus");
        teclado.out("3. Camión");
        teclado.out("4. Furgoneta");
        opcion = validarOpcion(opciones);
        
    }

    public static void main(String[] args) {
        Taller programa = new Taller();
        programa.inicio();
    }

    public void inicio() {
        int opcion = 0;
        Interval opcionesMenu = new Interval(1, 6);

        teclado.out("\n--- TALLER ITV ---\n");
        teclado.out("1. Alta y recepción de vehículos\n");
        teclado.out("2. Reclamar vehículo para entrar en el box\n");
        teclado.out("3. Mover todos los vehículos de fase dentro de un box\n");
        teclado.out("4. Información del estado de un box concreto\n");
        teclado.out("5. Información general de todos los boxes\n");
        teclado.out("6. Salir del programa\n\n");

        programaITV:
        while (opcion != 6) {

            opcion = validarOpcion(opcionesMenu);

            switch (opcion) {
                case 1:
                    boolean matriculaRepetida = true;
                    Vehiculo vehiculo1 = null;

                    while (matriculaRepetida) {
                        vehiculo1 = registrarVehiculo();
                        matriculaRepetida = false;

                        for (String matricula : matriculasCochesEnTaller) {
                            if (vehiculo1.getMatricula().equals(matricula)) {
                                teclado.out("Error: Esta matrícula ya pertenece a un vehículo dentro del taller.\n");
                                matriculaRepetida = true;
                                break;
                            }
                        }
                    }
                    colaPrincipal.insertarVehiculo(vehiculo1);

                    matriculasCochesEnTaller = Arrays.copyOf(matriculasCochesEnTaller, matriculasCochesEnTaller.length + 1);
                    matriculasCochesEnTaller[matriculasCochesEnTaller.length - 1] = vehiculo1.getMatricula();

                    colaPrincipal.mostrarCola();
                    inicio();
                    break;
                case 2:
                    if (this.colaPrincipal.estaVacia()) {
                        teclado.out("La cola está vacía.\n");
                    } else {
                        int contadorBoxesOcupados = 0;
                        for (int i = 0; i < boxes.length; i++) {
                            if (!boxes[i].getPrimeraFase().estaLibre()) {
                                contadorBoxesOcupados++;
                                if (contadorBoxesOcupados == 6) {
                                    teclado.out("El vehículo no puede entrar en ningun box, ya que todas las primeras fases se encuentran ocupadas.\n");
                                    inicio();
                                }
                            }
                        }
                        Vehiculo v = colaPrincipal.extraerVehiculo();
                        for (int i = 0; i < boxes.length; i++) {
                            if (boxes[i].getFaseRevision()[0].estaLibre()) {
                                boxes[i].getFaseRevision()[0].asignarVehiculoFase(v);
                                break;
                            }

                        }
                        teclado.out("El vehículo: Matrícula = " + v.getMatricula() + " ha accedido al box correctamente.\n");
                    }
                    inicio();
                    break;
                case 3:
                    int boxSeleccionado = 0;
                    teclado.out("Introduce el box en el que quieras mover de fase a todos sus vehículos: ");
                    boxSeleccionado = teclado.inInt();
                    boxes[boxSeleccionado - 1].avanzarVehiculos();
                    teclado.out("Los vehículos del box " + (boxSeleccionado) + " Han avanzado a la siguiente fase.");

                    inicio();
                    break;
                case 4:
                    int numeroBox = 0;
                    teclado.out("Dime un número de box para consultar su estado (1 - 6): ");
                    numeroBox = teclado.inInt();
                    while (numeroBox > 1 || numeroBox < boxes.length) {
                        teclado.out("Número de box inválido. Debe estar entre 1 y 6.\n");
                        teclado.out("Volver a insertar: ");
                        numeroBox = teclado.inInt();
                        teclado.out("\n");
                    }
                    if (this.boxes[numeroBox - 1] == null) {
                        teclado.out("El box se encuentra vacío.\n");
                    } else {
                        teclado.out("Estado del box " + numeroBox + ":\n");
                        this.boxes[numeroBox - 1].mostrarEstado();
                    }
                    inicio();
                    break;
                case 5:
                    teclado.out("--Estado de todos los box--\n");
                    for (int i = 0; i < boxes.length; i++) {
                        teclado.out("Box " + (i + 1) + ":\n");
                        boxes[i].mostrarEstado();
                        teclado.out("\n");
                    }
                    inicio();
                    break;
                case 6:
                    teclado.out("Fin del programa.\n");
                    break programaITV;
            }

        }

    }

    
    private Vehiculo opcionRegistro(int opcion){
        
        switch(opcion){
            case 1:
                Coche coche;
                return coche.registrarCoche();
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
    private int validarOpcion(Interval opciones) {
        int opcion;

        teclado.out("Selecciona una opción: ");
        opcion = teclado.inInt();
        while (!opciones.inclou(opcion)) {
            teclado.out("Error: Debes introducir un valor entre 1 y 6\n");
            opcion = teclado.inInt();

        }
        return opcion;
    }
}
