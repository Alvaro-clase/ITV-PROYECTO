
package opcion;

import menu.Menu;
import util.GestorIO;

/**
 *
 * @author irene
 */

public class Salir extends Opcion{
    GestorIO teclado = new GestorIO();
    Menu menu;
    public Salir(Menu menu) {
        super("Salir");
        this.menu = menu;
    }

    /**
     * pone fin al programa
     */
    @Override
    public void ejecutar() {
        teclado.out("\n--FIN DEL PROGRAMA--");
        menu.salir();
    }
    
}
