/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itv;

import util.GestorIO;

/**
 *
 * @author irene
 */
public class Coche extends TransportePersonas{
    GestorIO teclado = new GestorIO();
    protected Coche(int cmCubicos, double costePlazasAdicionales, int incrementoPorPotencia, int plazas, int cilindros, int potencia) {
        super(cmCubicos, costePlazasAdicionales, incrementoPorPotencia, plazas, cilindros, potencia);
    }
    
    public Coche registrarCoche(){
        int cmCubicos,plazas, cilindros, potencia;
        int incrementoPorPotencia = 10;
        double costePlazasAdicionales = 15;
        cmCubicos = super.pedirCmCubicos();
        plazas = validarPlazas();
        cilindros = super.validarCilindros();
        potencia = super.pedirPotencia();
        return new Coche(cmCubicos, 1.5, incrementoPorPotencia, plazas , cilindros, potencia);
    }
    
    private int validarPlazas(){
        int plazas;
        boolean error;
        do{
            error = false;
            teclado.out("Introduce las plazas del coche: ");
            plazas = teclado.inInt();
            if(plazas < 2 || plazas > 7){
                error = true;
                teclado.out("Solo puede tener entre 2 y 7 plazas.");
            }            
        }while(error);
        
        return plazas;
    }

   
}
