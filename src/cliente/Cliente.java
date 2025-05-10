package cliente;

import java.util.Objects;

/**
 *
 * @author Irene, Álvaro, Alejandro
 */
public class Cliente implements Comparable<Cliente> {

    private final String DNI;
    private String nombre;
    private String telefono;
    private boolean vip = false;
    private boolean socio = false;
    private int contSiendoSocio;
    private int contVecesAqui;
    private int vehiculosTiene = 0;
    private double descuentoSocio = 0.97;
    private final double INCREMENTO_SOCIO_DESCUENTO = 0.01;

    public Cliente(String dni, String nombre, String telefono, boolean VIP) {
        this.DNI = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.vip = VIP;
        this.contVecesAqui = 0;
        this.contSiendoSocio = 0;
    }

                
    /**
     * 
     * @return cantidad de veces que ha estado aquí
     */   
    public boolean esSuPrimeraVez() {
        return contVecesAqui == 0;
    }

    /**
     * 
     * @return descuento primera vez
     */
    public double descuentoPrimeraVez() {
        contVecesAqui++;
        return 0.7; //Es un 30% de descuento

    }

    /**
     * 
     * @return descuento socio
     */
    public double descuentoSocio() {
        if (this.contSiendoSocio() == 0) {
            contSiendoSocio++;
            return descuentoSocio;
        }
        contSiendoSocio++;
        if (this.contSiendoSocio() % 2 == 0 && descuentoSocio != 0.90) {
            descuentoSocio -= INCREMENTO_SOCIO_DESCUENTO;
        }
        return descuentoSocio;
    }

    /**
     * 
     * @return devuelve el dni del cliente
     */
    public String getDni() {
        return DNI;
    }
    
    /**
     * 
     * @return devuelve el nombre del cliente
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * 
     * @return devuelve el teléfono del cliente 
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * 
     * @return devuelve si el cliente es vip o no 
     */
    public boolean isVIP() {
        return vip;
    }

    /**
     * 
     * @return devuelve si es socio o no
     */
    public boolean isSOCIO() {
        return socio;
    }

    /**
     * hace socio al cliente
     */
    public void hacerSocio() {
        socio = true;
    }

    /**
     * 
     * @return devuelve las veces que ha venido siendo socio 
     */
    public int contSiendoSocio() {
        return contSiendoSocio;
    }
    
    /**
     * lleva la cuenta de cuantos vehículos tiene el cliente
     */
    public void unVehiculoMas(){
        vehiculosTiene++;
    }

    /**
     *
     * @param obj
     * @return si son iguales o no
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        return Objects.equals(this.DNI, other.DNI);
    }

    /**
     * 
     * @return los datos del cliente en String
     */
    @Override
    public String toString() {
        return "CLIENTE("+nombre+"){\n"
                + " DNI=" + DNI + "\n"
                + " TELEFONO=" + telefono + "\n"
                + " VIP=" + vip + "\n"
                + " SOCIO=" + socio + "\n"
                + " VECES AQUÍ=" + (contVecesAqui==0?" NUNCA":contVecesAqui) + "}\n";
    }
    
   

    /**
     * 
     * @param t
     * @return compara el cliente por dni
     */
    @Override
    public int compareTo(Cliente t) {
        return this.DNI.compareTo(t.getDni());
    }

    

   
}
