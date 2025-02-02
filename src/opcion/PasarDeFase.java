/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opcion;

import itv.Taller;
import util.GestorIO;
import util.Interval;

/**
 *
 * @author acarr
 */
public class PasarDeFase extends OpcionTaller {
    
    private Interval limiteBoxes = new Interval(1, 6);

    public PasarDeFase(Taller taller) {
        super("Pasar vehículo de fase", taller);
    }
    
    public void ejecutar() {
        
        teclado.out("Ingrese el número de box donde desea pasar los vehículos de fase: ");
        int numeroBox = teclado.inInt();
        while (!limiteBoxes.inclou(numeroBox)) {
            teclado.out("Introduce un número de box válido (1-6): ");
            numeroBox = teclado.inInt();
        }
        if (taller.ultimaFaseOcupada(numeroBox)) {
            taller.meterColaPago(taller.extraerVehiculoBox(numeroBox)); 
        }
        taller.avanzarVehiculos(numeroBox);
        teclado.out("\nHan pasado de fase\n");
    }
}
