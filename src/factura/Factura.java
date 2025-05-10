package factura;

import cliente.Cliente;
import java.time.LocalDateTime;
import java.util.Objects;
import vehiculo.Vehiculo;

/**
 * @author irene, alvaro, alejandro
 */
public class Factura implements Comparable<Factura>{
    private Vehiculo vehiculo;
    private double precioPagado;
    private LocalDateTime fecha;
    private int ID;
    private boolean VIP;
    private boolean socio;

    public Factura(Vehiculo vehiculo, Double precio) {
        this.vehiculo = vehiculo;
        this.precioPagado = precio;
        this.fecha = LocalDateTime.now();
        this.VIP = vehiculo.clienteIsVIP();
        this.socio = vehiculo.clienteIsSOCIO();
        this.ID = 0;
    }

    /**
     * 
     * @return devuelve la ID de la factura 
     */
    public int getID() {
        return ID;
    }

    /**
     * inserta la ID de la factura
     * @param ID 
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * 
     * @return devuelve el precio que pagó el vehículo 
     */
    public double getPrecioPagado() {
        return precioPagado;
    }

    /**
     * 
     * @return devuelve el vehículo de ésta factura 
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * 
     * @return devuelve la fecha de la factura 
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * 
     * @return devuelve si el cliente era vip en el momento en el que se hizo la factura 
     */
    public boolean isVIP() {
        return VIP;
    }

    /**
     * 
     * @return devuelve si el cliente era socio en el momento en el que se hizo la factura
     */
    public boolean isSocio() {
        return socio;
    }
    
    /**
     * 
     * @return devuelve en string los datos de la factura 
     */
    @Override
    public String toString() {
        String clienteVip;
        if (this.VIP){
            clienteVip = "Si";
        }
        else {
            clienteVip = "No";
        }
        
        String clienteSocio;
        if (this.socio){
            clienteSocio = "Si";
        }
        else {
            clienteSocio = "No";
        }
        
        
        return "--FACTURA DEL VEHICULO--\n"
                + "ID Factura = " + ID + "\n"
                + "Matrícula de vehiculo = " + vehiculo.getMatricula() + "\n"
                + "Fecha de pago = " + fecha + "\n"
                + "Cliente VIP = " + clienteVip + "\n"
                + "Tarjeta de socio = " + clienteSocio
                + "Coste de revisión = " + precioPagado + "€\n";
    }


    /**
     * muestra el String... (este método sobra)
     */
    public void mostrar() {
        System.out.println(this.toString());
    }
    
    /**
     * 
     * @param obj
     * @return devuelve si los objetos que se están comparando son iguales 
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
        final Factura other = (Factura) obj;
        if (Double.doubleToLongBits(this.precioPagado) != Double.doubleToLongBits(other.precioPagado)) {
            return false;
        }
        if (this.VIP != other.VIP) {
            return false;
        }
        if (this.socio != other.socio) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return Objects.equals(this.vehiculo, other.vehiculo);
    }

    /**
     * 
     * @param o
     * @return dcompara las facturas por fecha 
     */
    @Override
    public int compareTo(Factura o) {
        return this.getFecha().compareTo(o.getFecha());
    }
    
}