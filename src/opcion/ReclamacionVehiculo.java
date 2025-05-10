/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package opcion;

import excepciones.AlreadyExistsException;
import excepciones.NotExistsException;
import itv.Taller;

/**
 *
 * @author irene, alvaro, alejandro
 */
public class ReclamacionVehiculo extends OpcionTaller {
   
    public ReclamacionVehiculo(Taller taller) {
        super("Reclamar vehículo", taller);
    }
    
    /**
     * Reclama un vehiculo para introducirlo en un box.
     */
    @Override
    public void ejecutar(){
        teclado.out("-- RECLAMAR VEHÍCULO --\n");
        if(this.taller.colaEstaVacia()){
           teclado.out("NO HAY VEHÍCULOS EN LA COLA PRINCIPAL\n");
           
        }else if(!taller.boxesVacios()){
           teclado.out("LOS BOXES ESTÁN LLENOS\n");
        }else{
            teclado.out("Indica el box donde quieres introducir el vehiculo: ");
            int opcion = teclado.inInt();            
           while(!taller.NUMERO_BOXES.inclou(opcion)){
               teclado.out("Este box no existe (1-6): ");
               opcion = teclado.inInt();
           }
            try {
                taller.asignarVehiculoBox(opcion, taller.extraerVehiculoCola());
                taller.eliminarVehiculoCola();
            } catch (NotExistsException ex) {
                teclado.out("NO HAY VEHÍCULOS\n");
            } catch (AlreadyExistsException ex) {
                teclado.out(ex.getMessage());
            }
            
        }
    }
   
   
}
