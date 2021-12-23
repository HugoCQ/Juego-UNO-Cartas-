/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameuno;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Hugo
 */
public class Metodos {
    
    private Scanner sc;
    
    public static int generarNumeroEnteroAleatorio(int minimo, int maximo){
        int num = (int) (Math.random() * (minimo - (maximo +1)) + (maximo + 1));
        return num;
    }
    

    public Metodos() {
        sc = new Scanner(System.in);
        sc.useDelimiter("\n"); //Usado para nextLine()
        sc.useLocale(Locale.US); // Para double
    }
    
    public int pedirIntRango(int minimo, int maximo) {
        int num;

        //En caso de el minimo sea mas grande, se intercambia
        if (minimo > maximo) {
            int aux = minimo;
            minimo = maximo;
            maximo = aux;
        }

        do {
            try {
                System.out.println("Introduce un numero integer entre " + minimo + " y " + maximo);
                num = sc.nextInt();
            } catch (InputMismatchException ex) {
                // En caso de error, el num se marca como un numero no valido
                num = maximo + 1;
                sc.next();
            }

            if (!(num >= minimo && num <= maximo)) {
                System.out.println("Error, Introduce un numero integer entre " + minimo + " y " + maximo);
            }

        } while (!(num >= minimo && num <= maximo));

        return num;
    }

    
    public int pedirIntRango(int minimo, int maximo, String mensaje) {
        int num;

        //En caso de el minimo sea mas grande, se intercambia
        if (minimo > maximo) {
            int aux = minimo;
            minimo = maximo;
            maximo = aux;
        }

        do {
            try {
                System.out.println(mensaje);
                num = sc.nextInt();
            } catch (InputMismatchException ex) {
                // En caso de error, el num se marca como un numero no valido
                num = maximo + 1;
                sc.next();
            }

            if (!(num >= minimo && num <= maximo)) {
                System.out.println("Error, Introduce un numero integer entre " + minimo + " y " + maximo);
            }

        } while (!(num >= minimo && num <= maximo));

        return num;
    }
    
    
    
    public int pedirIntRango(int minimo, int maximo, String mensaje, String mensajeError) {
        int num;

        //En caso de el minimo sea mas grande, se intercambia
        if (minimo > maximo) {
            int aux = minimo;
            minimo = maximo;
            maximo = aux;
        }

        do {
            try {
                System.out.println(mensaje);
                num = sc.nextInt();
            } catch (InputMismatchException ex) {
                // En caso de error, el num se marca como un numero no valido
                num = maximo + 1;
                sc.next();
            }

            if (!(num >= minimo && num <= maximo)) {
                System.out.println(mensajeError);
            }

        } while (!(num >= minimo && num <= maximo));

        return num;
    }
    
    public static void metodoEspacio(){
        for (int i = 0; i < 1000; i++) {
            System.out.println("");
        }
    }
    
    
}
