/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uno;

import static Enumerado.ColoresBarajaUno.AMARILLO;
import static Enumerado.ColoresBarajaUno.AZUL;
import static Enumerado.ColoresBarajaUno.ROJO;
import static Enumerado.ColoresBarajaUno.VERDE;
import gameuno.CartaUno;
import java.util.ArrayList;

/**
 *
 * @author Hugo
 */

public class Jugador implements Comparable<Jugador>{
    public String nombre;
    private int puntos;
    private ArrayList<CartaUno> mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
        this.mano = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public ArrayList<CartaUno> getMano() {
        return mano;
    }
    
    public void aumentarPuntos (int puntos){
        this.puntos+=puntos;
    }
    
    public int numCartas(){
        return this.mano.size();
    }
    
    public void mostrarMano(){
        System.out.println("Mano de "+this.nombre);
        for(int i=0;i<this.numCartas();i++){
            CartaUno carta=this.mano.get(i);
            if (carta.getPalo()==AZUL) {
                System.out.println("\033[30m"+i+". "+"\033[36m"+carta);
            }
            else if (carta.getPalo()==ROJO) {
                System.out.println("\033[30m"+i+". "+"\033[31m"+carta);
            }
            else if (carta.getPalo()==AMARILLO) {
                System.out.println("\033[30m"+i+". "+"\033[33m"+carta);
            }
            else if (carta.getPalo()==VERDE) {
                System.out.println("\033[30m"+i+". "+"\033[32m"+carta);
            }
            else{
                System.out.println("\033[30m"+i+". "+"\033[30m"+carta);
            }
            
        }
    }
    
    public CartaUno getCartaAt(int pos){
        return this.mano.get(pos);
    }
    
    public void removeCartaAt(int pos){
        this.mano.remove(pos);
    }
    
    public boolean sinCartas(){
        return this.mano.isEmpty();
    }
    
    public void setCartas(ArrayList<CartaUno> cartas){
        this.mano=cartas;
    }
    
    public int puntosMano(){
        int punto=0;
        for (CartaUno c: this.mano){
            if(c.isEspecial()){
                puntos +=c.getEfecto().getPuntos();
            }
        }
        return puntos;
    }
    
    public void agregarCartas(ArrayList<CartaUno> cartas){
        for(CartaUno carta: cartas){
            this.mano.add(carta);
        }
    }
    
    public void agregarCartas(CartaUno carta){
        this.mano.add(carta);
    }

    @Override
    public String toString() {
        return nombre + " tiene " + puntos + "puntos";
    }

    @Override
    public int compareTo(Jugador j) {
        if(j.getPuntos() > puntos){
            return 1;  
        }
        else if(j.getPuntos() == puntos){
            return 0;
        }
        else{
            return -1;     
        }
        
    }
    
    
    
}
