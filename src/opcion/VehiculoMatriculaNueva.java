/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opcion;

import excepciones.NotExistsException;
import itv.Taller;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.GestorIO;
import vehiculo.Vehiculo;

/**
 *
 * @author irene
 */
public class VehiculoMatriculaNueva extends OpcionTaller {
    
    public VehiculoMatriculaNueva(Taller taller) {
        super("Mostrar vehiculo con la matrícula más nueva", taller);
    }
    
    /**
     * muestra el vehículo con la matricula más nueva
     */
    @Override
    public void ejecutar() {
        GestorIO teclado = new GestorIO();
        teclado.out("-- VEHÍCULO CON LA MATRÍCULA MÁS NUEVA --\n");
        Vehiculo vehiculo;
        try {
            vehiculo = Taller.encontrarMaximo(taller.getVehiculos());
            teclado.out(vehiculo.getMatricula()+"\n");
        } catch (NotExistsException ex) {
            teclado.out(ex.toString());
        }
        
    }
    
}


