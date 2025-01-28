/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itv;

import util.Interval;

/**
 *
 * @author irene
 */
public abstract class TransportePersonas extends Vehiculo {

    private final int PLAZAS_GRATUITAS = 3;
    private int cmCubicos;
    private double costePlazasAdicionales;
    private int incrementoPorPotencia = 10;
    private int plazas;
    private final Interval LIMITES_CILINDROS = new Interval(2,6);
    protected Interval limitesPlazas;
    
    protected TransportePersonas(String matricula, int cmCubicos, double costePlazasAdicionales, int incrementoPorPotencia, int plazas, int cilindros, int potencia) {
        
        super(matricula,cilindros, potencia);
        this.cmCubicos = cmCubicos;
        this.costePlazasAdicionales = costePlazasAdicionales;
        this.incrementoPorPotencia = incrementoPorPotencia;
        this.plazas = plazas;
    }

    public int getCmCubicos() {
        return cmCubicos;
    }

    public double getCostePlazasAdicionales() {
        return costePlazasAdicionales;
    }

    public int getPlazas() {
        return plazas;
    }

    
    protected int validarCilindros() {
        int cilindros;
        boolean error;
        limite = new Interval(2,6);
        do {
            error = false;
            teclado.out("Introduce los cilindros: ");
            cilindros = teclado.inInt();
            if (!limite.inclou(cilindros)) {
                error = true;
                teclado.out("Solo puede tener entre 2 y 6 cilindros.");
            }
        } while (error);

        return cilindros;
    }

    protected int pedirCmCubicos() {
        teclado.out("Introduce los cmCubicos: ");
        return teclado.inInt();
    }

    public int getIncrementoPorPotencia() {
        return this.incrementoPorPotencia;
    }

    public double calcularAdicional() {
        double extra = 0;
        if (this.tienePlazasExtra()) {
            extra = this.plazas - this.PLAZAS_GRATUITAS;
            extra *= this.costePlazasAdicionales;
        }
        if (this.tienePotenciaExtra()) {
            extra += 10;
        }
        return extra;
    }

    public boolean tienePotenciaExtra() {
        return this.getPotencia() > 1200;
    }

    public boolean tienePlazasExtra() {
        return this.getPlazas() > this.PLAZAS_GRATUITAS;
    }

    @Override
    public void mostrarTodo(){
        
    }
    protected abstract int validarPlazas();

    @Override
    public double calcularPrecio() {
        return this.calcularPrecioBase() + this.calcularAdicional();
    }

    @Override
    public double calcularPrecioBase() {
        return this.getCilindros() * this.getREVISION_CILINDROS();
    }
    

}
