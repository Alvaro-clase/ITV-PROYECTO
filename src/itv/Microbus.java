/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itv;

/**,.
 *
 * @author irene
 */
public class Microbus extends TransportePersonas{
    
    public Microbus(int cmCubicos, double costePlazasAdicionales, int incrementoPorPotencia, int plazas, int cilindros, int potencia) {
        super(cmCubicos, costePlazasAdicionales, incrementoPorPotencia, plazas, cilindros, potencia);
    }
    
     public Microbus registrarCoche(){
        int cmCubicos,plazas, cilindros, potencia;
        int incrementoPorPotencia = getIncrementoPorPotencia();
        cmCubicos = super.pedirCmCubicos();
        plazas = validarPlazas();
        cilindros = super.validarCilindros();
        potencia = super.pedirPotencia();
        return new Microbus(cmCubicos, 2, incrementoPorPotencia, plazas , cilindros, potencia);
    }
    
    private int validarPlazas(){
        int plazas;
        boolean error;
        do{
            error = false;
            teclado.out("Introduce las plazas del microbus: ");
            plazas = teclado.inInt();
            if(plazas < 2 || plazas > 20){
                error = true;
                teclado.out("Solo puede tener entre 2 y 20 plazas.");
            }            
        }while(error);
        
        return plazas;
    }

   
}
