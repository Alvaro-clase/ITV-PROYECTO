package util;

public class Interval {
    private double inferior;
    private double superior;

    /**
     * Proporciona un interval amb els límits inferior i superior que li donem
     * @param inferior
     * @param superior 
     */
    public Interval(double inferior, double superior) {
        this.inferior = inferior;
        this.superior = superior;
    }

    /**
     * Proporciona un interval amb límit superior que li donem
     * @param superior 
     */
    public Interval(double superior) {
        this(0., superior);
    }

    /**
     * Proporciona un interval a partir d'un altre (copia)
     * @param interval 
     */
    public Interval(Interval interval) {
        this(interval.inferior, interval.superior);
    }

    /**
     * Proporciona un interval amb límits per defecte.
     */
    public Interval() {
        this(0., 5.);
    }

    /**
     * Clona l'interval
     * @return 
     */
    public Interval clone() {
        return new Interval(this);
    }

    /**
     * Longitud de l'interval
     * @return 
     */
    public double longitud() {
        double longitud = this.superior - this.inferior;
        return longitud;
    }

    /**
     * Mou els límits de l'interval segons el que indiquem
     * @param moviment 
     */
    public void moure(double moviment) {
       this.inferior += moviment;
       this.superior += moviment;
    }

    /**
     * Obtiene l'interval mogut segons el moviment proporcionat
     * @param moviment
     * @return 
     */
    public Interval mogut(double moviment) {
        Interval copia = this.clone();
        copia.moure(moviment);
        return copia;
    }

    /**
     * Determina si el valor está dintre de l'interval
     * @param valor
     * @return 
     */
    public boolean inclou(double valor) {
        if(valor >= this.inferior && valor <= this.superior){
            return true;
        }
        return false;
    }

    /**
     * Determina si l'interval proporcionat está inclòs en l'interval
     * @param interval
     * @return 
     */
    public boolean inclou(Interval interval) {
        if(interval.inferior >= this.inferior && interval.inferior < this.superior && interval.superior <= this.superior && interval.superior > this.inferior && interval.inferior < interval.superior){
            return true;
        }
       return false;
    }

    /**
     * Determina si dos intervals són iguals
     * @param interval
     * @return 
     */
    public boolean equals(Interval interval) {
        if(interval.inferior == this.inferior && interval.superior == this.superior)return true;
        return false;
    }

    /**
     * Obté l'interval que intersecta
     * @param interval
     * @return 
     */
    public Interval interseccio(Interval interval) {
        if(this.inclou(interval)){
            return new Interval(interval);
        } else if(interval.inclou(this)){
            return new Interval(this);
        }else if(this.inferior >= interval.inferior && this.superior >= interval.superior){
            return new Interval(this.inferior, interval.superior);
        }else if(this.inferior <= interval.inferior && this.superior <= interval.superior){
            return new Interval(interval.inferior, this.superior);
        }
        return null;
    }

    /**
     * Determina si un interval intersecta amb el nostre
     * @param interval
     * @return 
     */
    public boolean intersecta(Interval interval) {
        return this.interseccio(interval) != null;
    }

    /**
     * Transforma l'interval en el seu oposat.
     */
    public void oposar() {
        double inferior = this.superior;
        double superior = this.inferior;
        this.superior = superior * -1;
        this.inferior = inferior * -1;
    }

    /**
     * Augmenta la grandària de l'interval al doble de la seua longitud (ha de creixer igual pels dos extrems).
     */
    public void doblar() {
        double longitud = this.longitud();
        this.inferior += longitud/2;
        this.superior += longitud/2;
    }

    /**
     * Demana a l'usuari que introduïsca els límits de l'interval
     */
    public void recollir() {
        GestorIO input = new GestorIO();
        double inferiorTeclado;
        double superiorTeclado; 
        do{
        input.out("Dame el valor inferior del intervalo: ");
        inferiorTeclado = input.inDouble();
        input.out("Dame el valor superior del  intervalo: ");
        superiorTeclado = input.inDouble();
        }while(superiorTeclado < inferiorTeclado);
        this.inferior = inferiorTeclado;
        this.superior = superiorTeclado;
    }

    /** Mostra l'interval amb el format [limitInferior, limitSuperior].
     * 
     */
    public void mostrar() {
        System.out.println("(LimitInferior = "+this.inferior + ", LimitSuperior = "+ this.superior+")");
    }

    /**
     * Obté els subintervals en grandàries iguals segons els trossos proporcionats
     * @param trossos
     * @return 
     */
    public Interval[] trossejar(int trossos) {
        double longitud = this.longitud()/trossos;
        int j = 0;
        Interval[] intervalos = new Interval[trossos];
        for (double i = this.inferior; i < this.superior; i+= longitud) {
           intervalos[j++] = new Interval(i, i + longitud);
        }
        return intervalos;
    }

    public double getInferior() {
        return inferior;
    }

    public double getSuperior() {
        return superior;
    }
    
}
