/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itv;

import util.Interval;

/**,.
 *
 * @author irene
 */
public class Microbus extends TransportePersonas{
    
    public Microbus(String matricula, int cmCubicos, double costePlazasAdicionales, int incrementoPorPotencia, int plazas, int cilindros, int potencia) {
        super(matricula, cmCubicos, costePlazasAdicionales, incrementoPorPotencia, plazas, cilindros, potencia);
    }
    
     public Microbus registrarCoche(){
        int cmCubicos,plazas, cilindros, potencia;
        int incrementoPorPotencia = getIncrementoPorPotencia();
        cmCubicos = super.pedirCmCubicos();
        plazas = validarPlazas();
        cilindros = super.validarCilindros();
        potencia = super.pedirPotencia();
        return new Microbus(matricula, cmCubicos, 2, incrementoPorPotencia, plazas , cilindros, potencia);
    }
    
    @Override
    protected int validarPlazas(){
        int plazas;
        boolean error;
        limitesPlazas = new Interval(2, 20);

        do{
            error = false;
            teclado.out("Introduce las plazas del microbus: ");
            plazas = teclado.inInt();
            if(!limitesPlazas.inclou(plazas)){
                error = true;
                teclado.out("Solo puede tener entre 2 y 20 plazas.");
            }            
        }while(error);
        
        return plazas;
    }

   
}
