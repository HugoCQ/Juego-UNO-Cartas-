/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enumerado;

/**
 *
 * @author Hugo
 */
public enum EfectosBarajaUno 
{
    MASDOS(20),
    MASCUATRO(50),
    SALTO(20),
    CAMBIO_DE_SENTIDO(20),
    CAMBIOCOLOR(50);

    private int puntos;

    private EfectosBarajaUno(int puntos) 
    {
        this.puntos = puntos;
    }

    public int getPuntos() 
    {
        return puntos;
    }

}
