
package Inicio;

import Uno.Jugador;
import gameuno.GameUno;
import gameuno.Metodos;
import java.util.Scanner;

/**
 *
 * @author Hugo
 */
public class Menu_Principal {
    
    public static void main(String[] args){
        Metodos metodos = new Metodos();
        Scanner sc = new Scanner(System.in);
        Scanner nombre = new Scanner(System.in);

        GameUno juego = null;
        while (true){
            System.out.print("Cantidad de jugadores (2-4): ");
            int P = sc.nextInt();
            if (P==2) {
                System.out.println("--------------------------------------");
                System.out.print("Nombre del primer jugador: ");
                String A = nombre.nextLine();
                System.out.print("Nombre del segundo jugador: ");
                String B = nombre.nextLine();
                Jugador[] jugadores = {
                    new Jugador(A),
                    new Jugador(B)
                    };
                juego = new GameUno(jugadores, 100);
                        
                break;
            }
            else if (P == 3) {
                System.out.println("--------------------------------------");
                System.out.print("Nombre del primero jugador: ");
                String A = nombre.nextLine();
                System.out.print("Nombre del segundo jugador: ");
                String B = nombre.nextLine();
                System.out.print("Nombre del tercer jugador: ");
                String C = nombre.nextLine();
                Jugador[] jugadores = {
                    new Jugador(A),
                    new Jugador(B),
                    new Jugador(C)
                    };
                juego = new GameUno(jugadores, 100);
                break;
            }
            else if (P == 4){
                System.out.println("--------------------------------------");
                System.out.print("Nombre del primero jugador: ");
                String A = nombre.nextLine();
                System.out.print("Nombre del segundo jugador: ");
                String B = nombre.nextLine();
                System.out.print("Nombre del tercer jugador: ");
                String C = nombre.nextLine();
                System.out.print("Nombre del cuarto jugador: ");
                String D = nombre.nextLine();
                Jugador[] jugadores = {
                    new Jugador(A),
                    new Jugador(B),
                    new Jugador(C),
                    new Jugador(D)
                    };
                juego = new GameUno(jugadores, 100);
                break;
            }
            else{
                System.out.println("Solo numeros entre 2 y 4");
            }
        }
        
        System.out.println("--------------------------------------");
        System.out.println(" ");
       
        
        while(!juego.finJuego()){
            System.out.println("Ronda: "+juego.getRonda());
            
            while(!juego.finPartida()){
                juego.mostrarTrunoActual();
                juego.mostrarSentidoActual();
                
                juego.cartaCentro();
                
                juego.mostrarCartasJugadorActual();
                int indiceCarta = metodos.pedirIntRango(-1, juego.numCartasJugadorActual(), "ELige una carta de la mano, -1 para robar", "Solo nuemros entre -1 y "+ (juego.numCartasJugadorActual()-1));
                if (indiceCarta == -1) {
                    System.out.println("Se ha elegido robar carta");
                    juego.robarCarta();
                    juego.cambiaTurno();
                    Metodos.metodoEspacio();
                    
                }
                else{
                    if (indiceCarta >= juego.numCartasJugadorActual()) {
                        System.out.println("No puedes tirar esa carta");
                        Metodos.metodoEspacio();
                    }
                    else if (!juego.cartaCompatible(indiceCarta)){
                        System.out.println("No puedes tirar esa carta");
                        Metodos.metodoEspacio();
                    }
                    else{
                        juego.cambiaTurno();
                        Metodos.metodoEspacio();
                    }
                }
   
            }
            Metodos.metodoEspacio();
            juego.siguienteRonda();
            System.out.println("Ranking");
            juego.ranking();
            System.out.println("--------------------------------------");
            System.out.println(" ");
        }
        Jugador ganador = juego.ganadorJuego();
        System.out.println("El ganador del juego ha sido "+ganador.getNombre());
    }  
}
