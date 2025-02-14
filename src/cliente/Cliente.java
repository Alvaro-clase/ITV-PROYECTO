
package cliente;

import java.util.Objects;
import java.util.UUID;
import vehiculo.Vehiculo;

/**
 *
 * @author Irene, Álvaro, Alejandro
 */
public class Cliente {
    private  final String DNI;
    private String nombre;
    private  String telefono;
    private  boolean vip;
    private  boolean socio; 
    private int contSiendoSocio;
    private int contVecesAqui;
    private int cochesTiene;
    
    public Cliente(String dni,String nombre,String telefono, boolean VIP) {
        this.DNI = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.vip = VIP;
        this.contVecesAqui = 0;
        this.contSiendoSocio = 0;
    }
    
    public double descuentoPrimeraVez(){
        if(this.getContVecesAqui() == 0){
            return 0.7; //Es un 30% de descuento
        }
        return 1;
    }
    
    public double descuentoSocio(){
        if(this.contSiendoSocio() == 0){
            contSiendoSocio++;
            return 0.97;
        } //ARREGLAR ESTA PTA MRDA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return 1;
    }
    
    public double descuentoVip(){
        if(isVIP()){
            return 0;
        }
        return 1;
    }
    
    public String getDni() {
        return DNI;
    }

    public String getTelefono() {
        return telefono;
    }

    public boolean isVIP() {
        return vip;
    }

    public boolean isSOCIO() {
        return socio;
    }
    
    public void hacerSocio(){
        socio = true;
    }

    public int getContVecesAqui() {
        return contVecesAqui;
    }
    public int contSiendoSocio(){
        return contSiendoSocio;
    }
    
    
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
    
    @Override
    public String toString() {
        return "Cliente{" + "DNI=" + DNI + ", telefono=" + telefono + ", VIP=" + vip + ", SOCIO=" + socio + ", contVecesAqui=" + contVecesAqui + '}';
    }
    
}