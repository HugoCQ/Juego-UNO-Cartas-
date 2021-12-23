/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameuno;

public abstract class Cartas<T> {

    //Atributos
    protected int numero;
    protected T palo;

    public int getNumero() {
        return numero;
    }

    public T getPalo() {
        return palo;
    }

    //Constructor
    public Cartas(int numero, T palo) {
        this.numero = numero;
        this.palo = palo;
    }

    public Cartas() {
    }

}