package opcion;

import util.GestorIO;

/**
 * @author irene, alvaro, alejandro
 */
public abstract class Opcion {
    private GestorIO teclado = new GestorIO();
    protected String titulo;
    
    
    public Opcion(String titulo) {
        this.titulo = titulo;
        
    }

    /**
     * muestra las opciones
     * @param numeroOpcion
     */
    public void mostrar(int numeroOpcion){
        teclado.out(numeroOpcion + ". "+ this.titulo +"\n");
    }

    
    public abstract void ejecutar();

}

