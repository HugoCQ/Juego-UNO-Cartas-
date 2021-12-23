/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameuno;

import Enumerado.ColoresBarajaUno;
import static Enumerado.ColoresBarajaUno.AMARILLO;
import static Enumerado.ColoresBarajaUno.AZUL;
import static Enumerado.ColoresBarajaUno.ROJO;
import static Enumerado.ColoresBarajaUno.VERDE;
import Uno.Jugador;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Hugo
 */

public class GameUno {

    private int ronda;
    private BarajaUno baraja;
    private int turno;
    private Jugador[] jugadores;
    private int putnosLimites;

    public GameUno(Jugador[] jugadores, int putnosLimites) {
        this.jugadores = jugadores;
        this.putnosLimites = putnosLimites;
        this.ronda=1;
        this.turnoInicial();
        this.repartirCartas();
        
        if (this.baraja.getUltimaCarta().isEspecial()) {
            this.aplicarEfectoCarta(true);
        }
    }
    
    public void turnoInicial(){
        this.turno = Metodos.generarNumeroEnteroAleatorio(0, this.jugadores.length - 1);
    }
    
    public void repartirCartas(){
        this.baraja=new BarajaUno();
        for (Jugador j: this.jugadores){
            j.setCartas(this.baraja.darCartas(7, false));
        }
    }
    
    public int getRonda(){
        return this.ronda;
    }
    
    public Jugador jugadorActual(){
        return this.jugadores[this.turno];
    }
    
    public void mostrarTrunoActual(){
        System.out.println("Es el turno del jugador: "+this.jugadorActual().getNombre());
        
    }
    
    public void mostrarCartasJugadorActual(){
        this.jugadorActual().mostrarMano();
    }
    
    public int numCartasJugadorActual(){
        return this.jugadorActual().numCartas();
    }
    
    public void mostrarSentidoActual(){
        if(this.baraja.isSentido()){
            System.out.println("Sentido actual: A favor de las manecillas del reloj");
        }
        else{
            System.out.println("Sentido actual: En contra de las manecillas del reloj");
        }
    }
    
    public void cambiaTurno(){
        if(this.baraja.isSentido() && turno == this.jugadores.length - 1){
            turno=0;
        }
        else if (!this.baraja.isSentido() && turno == 0){
            this.turno=this.jugadores.length - 1;
        }
        
        else{
            if(this.baraja.isSentido()){
                turno++;
            }
            else{
                turno--;
            }
        }
    }
    
    public Jugador ganadorPartida(){
        Jugador j=null;
        
        for(int i=0;i<this.jugadores.length;i++)
            if(this.jugadores[i].sinCartas()){
                j=this.jugadores[i];
            }
        
        return j;
    }
    
    public Jugador ganadorJuego(){
        Jugador j=null;
        int mayorPuntacion=0;
        for(int i=0; i<this.jugadores.length;i++){
            if(this.jugadores[i].getPuntos()>=mayorPuntacion){
                mayorPuntacion=this.jugadores[i].getPuntos();
                j=this.jugadores[i];
            }
        }
        return j;
    }
    
    public boolean finJuego(){
        return this.ganadorJuego().getPuntos() >= this.putnosLimites;
    }
    
    public boolean finPartida(){
        return this.ganadorPartida()!=null;
    }
    
    public int calcularPuntos(){
        int puntos = 0;
        
        for (int i = 0; i < this.jugadores.length; i++) {
            puntos += this.jugadores[i].puntosMano(); 
        }
        
        return puntos;
    }
    
    public void siguienteRonda(){
        this.ronda++;
        int puntos = this.calcularPuntos();
        
        Jugador ganador = this.ganadorPartida();
        ganador.aumentarPuntos(puntos);
        System.out.println("Ha ganado "+ ganador.getNombre()+ " "+puntos+" puntos");
        this.repartirCartas();
    }
    
    public void ranking(){
        ArrayList<Jugador> jugadoresClon=new ArrayList<>();
        for (Jugador j: jugadores) {
            jugadoresClon.add(j);  
        }
        
        Collections.sort(jugadoresClon);
        
        for (Jugador j: jugadores) {
            System.out.println(j);
        }
        
    }
    
    public boolean cartaCompatible(int posCarta){
        CartaUno cartaJ = this.jugadorActual().getCartaAt(posCarta);
        CartaUno cartaM = this.baraja.getUltimaCarta();
        
        if (cartaJ.compatible(cartaM) || baraja.getColorActual() == cartaJ.getPalo() ) {
            this.baraja.setUltimaCarta(cartaJ);
            this.baraja.agregarCartaMonton(cartaJ);
            this.jugadorActual().removeCartaAt(posCarta);
            
            if (cartaJ.isEspecial()) {
                this.aplicarEfectoCarta(false);
            }
            else{
                this.baraja.actualizarColor();
            }
            return true;
        }
        else{
            return false;
        }
    }
    
    public Jugador siguienteJugador(){
        if (this.baraja.isSentido()){
            if (this.turno == this.jugadores.length - 1){
                return this.jugadores[0];
            }
            else{
                return this.jugadores[this.turno + 1];
            }
        }
        else{
            if (this.turno == 0) {
                return this.jugadores[this.jugadores.length - 1];
            }
            else{
                return this.jugadores[this.turno - 1];
            }
        }
    }
    
    public void elegirColor(){
        ColoresBarajaUno[] colores = ColoresBarajaUno.values();
        
        Metodos metodos = new Metodos();
        
        for (int i = 0; i < colores.length - 1; i++) {
            if (colores[i].name()=="AZUL") {
            System.out.println("\033[36m"+i+". "+colores[i].name());
            }
            else if (colores[i].name()=="ROJO") {
                System.out.println("\033[31m"+i+". "+colores[i].name());
            }
            else if (colores[i].name()=="AMARILLO") {
                System.out.println("\033[33m"+i+". "+colores[i].name());
            }
            else if (colores[i].name()=="VERDE") {
                System.out.println("\033[32m"+i+". "+colores[i].name());
            }
            else{
                System.out.println("\033[30m"+i+". "+colores[i].name());
            }

        }
        int posColor = metodos.pedirIntRango(0, colores.length - 2, "\033[30m"+"Seleccione un color:" );
        
        this.baraja.setColorActual(colores[posColor]);
        
    }
    
    public void pasarTurno(){
        this.cambiaTurno();
    }
    
    public void cartaCentro(){
        System.out.print("\033[30mCarta en el centro: ");
        if (this.baraja.getColorActual()==AZUL) {
            System.out.println("\033[36m"+this.baraja.getUltimaCarta() + " ("+this.baraja.getColorActual()+") ");
        }
        else if (this.baraja.getColorActual()==ROJO) {
            System.out.println("\033[31m"+this.baraja.getUltimaCarta() + " ("+this.baraja.getColorActual()+") ");
        }
        else if (this.baraja.getColorActual()==AMARILLO) {
            System.out.println("\033[33m"+this.baraja.getUltimaCarta() + " ("+this.baraja.getColorActual()+") ");
        }
        else if (this.baraja.getColorActual()==VERDE) {
            System.out.println("\033[32m"+this.baraja.getUltimaCarta() + " ("+this.baraja.getColorActual()+") ");
        }
        else{
            System.out.println("\033[30m"+this.baraja.getUltimaCarta() + " ("+this.baraja.getColorActual()+") ");
        }
        
        
    }
    
    public void robarCarta(){
        CartaUno carta = this.baraja.siguienteCarta(false);
        if (carta != null) {
            this.jugadorActual().agregarCartas(carta);
        }
        else{
            this.baraja.barajar();
            carta = this.baraja.siguienteCarta(false);
            if (carta != null) {
                this.jugadorActual().agregarCartas(carta);
            }
            else{
                System.out.println("No hay mas cartas que robar");
            }
        }
    }
    
    public void aplicarEfectoCarta(boolean inicio){
        CartaUno cartaM = this.baraja.getUltimaCarta();
        Jugador siguienteJ = null;
        ArrayList<CartaUno> cartas;
        switch (cartaM.getEfecto()){
            case MASDOS: 
                if (inicio) {
                    siguienteJ = this.jugadorActual();
                }
                else{
                    siguienteJ = this.siguienteJugador();
                }
                cartas = this.baraja.darCartas(2, false);
                if (cartas==null) {
                    this.baraja.barajar();
                    cartas = this.baraja.darCartas(2, false);
                }
                siguienteJ.agregarCartas(cartas);
                System.out.println("+2 carta para el jugador "+ siguienteJ.getNombre());
                this.pasarTurno();
                this.baraja.actualizarColor();
                
                break;
            case MASCUATRO:
                if (inicio) {
                    siguienteJ = this.jugadorActual();
                }
                else{
                    siguienteJ = this.siguienteJugador();
                }
                cartas = this.baraja.darCartas(4, false);
                if (cartas==null) {
                    this.baraja.barajar();
                    cartas = this.baraja.darCartas(4, false);
                }
                siguienteJ.agregarCartas(cartas);
                Metodos.metodoEspacio();
                System.out.println("+4 carta para el jugador "+ siguienteJ.getNombre());
                this.elegirColor();
                System.out.println("Color cambiado");
                this.pasarTurno();
                
                
                break;
                
            case SALTO:
                this.pasarTurno();
                System.out.println("Se salta al siguiente jugador");
                this.baraja.actualizarColor();
                break;
                
            case CAMBIO_DE_SENTIDO:
                this.baraja.cambiarSentido();
                System.out.println("Se cambio el sentido");
                this.baraja.actualizarColor();
                break;
                
            case CAMBIOCOLOR:
                this.elegirColor();
                System.out.println("Color cambiado");
                break;
        }
    }
    
    
}
