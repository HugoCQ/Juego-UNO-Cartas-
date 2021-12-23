
package gameuno;

import java.util.ArrayList;
import LIFO.PilaDinamica;

/**
 *
 * @author Hugo
 */

public abstract class Baraja<T extends Cartas> 
{

    
    protected PilaDinamica<T> cartas;
    protected PilaDinamica<T> cartasMonton;
    protected int numCartas;
    protected int cartasPorPalo;

    public Baraja() {
        this.cartas = new PilaDinamica<>();
        this.cartasMonton = new PilaDinamica<>();
    }

    
    public abstract void crearBaraja();

    
    public void barajar() {

        int posAleatoria = 0;
        T carta;

        while (!this.cartasMonton.isEmpty()) {
            this.cartas.push(this.cartasMonton.pop());
        }

        ArrayList<T> cartasBarajar = new ArrayList<>();

        while (!this.cartas.isEmpty()) {
            cartasBarajar.add(this.cartas.pop());
        }

        for (int i = 0; i < cartasBarajar.size(); i++) {

            do {
                posAleatoria = Metodos.generarNumeroEnteroAleatorio(0, cartasBarajar.size() - 1);
                carta = cartasBarajar.get(posAleatoria);
            } while (carta == null);

            this.cartas.push(carta);
            cartasBarajar.set(posAleatoria, null);

        }

    }

    
    public T siguienteCarta(boolean monton) {

        T carta = null;

        if (!this.cartas.isEmpty()) {
            carta = cartas.pop();
            if (monton) {
                this.cartasMonton.push(carta);
            }
        }

        return carta;

    }

    
    public ArrayList<T> darCartas(int numCartas, boolean monton) {

        if (numCartas > numCartas || cartasDisponible() < numCartas) {
            return null;
        } else {

            ArrayList<T> cartasDar = new ArrayList<>();

            for (int i = 0; i < numCartas; i++) {
                cartasDar.add(siguienteCarta(monton));
            }

            return cartasDar;

        }

    }

    public int cartasDisponible() {
        return this.cartas.size();
    }

    public void cartasMonton() {

        if (cartasDisponible() == numCartas) {
            System.out.println("No se ha sacado ninguna carta");
        } else {
            System.out.println(this.cartasMonton.toString());
        }

    }

    public void mostrarBaraja() {

        if (cartasDisponible() == 0) {
            System.out.println("No hay cartas que mostrar");
        } else {
            System.out.println(this.cartas.toString());
        }

    }
    
    public void agregarCartaMonton(T carta){
        this.cartasMonton.push(carta);
    }

}
