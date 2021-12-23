/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameuno;

import Enumerado.ColoresBarajaUno;
import Enumerado.EfectosBarajaUno;

/**
 *
 * @author Hugo
 */

public class CartaUno extends Cartas<ColoresBarajaUno> 
{

    private EfectosBarajaUno efecto;

    public CartaUno(int numero, ColoresBarajaUno color) 
    {
        super(numero, color);
    }

    public CartaUno() 
    {
    }

    public CartaUno(ColoresBarajaUno color, EfectosBarajaUno efecto) 
    {
        super(-1, color);
        this.efecto = efecto;
    }

    public EfectosBarajaUno getEfecto() 
    {
        return efecto;
    }

    public boolean isEspecial() 
    {
        return this.efecto != null;
    }
    
    public boolean compatible(CartaUno c)
    {
        return this.getPalo() == ColoresBarajaUno.NEGRO
                || this.getPalo() == c.getPalo()
                || (this.getNumero() == c.getNumero() && !this.isEspecial() && !c.isEspecial())
                || (this.isEspecial() && c.isEspecial() && this.efecto == c.efecto);
        
    }

    @Override
    public String toString() 
    {

        String estado = "";

        if (this.isEspecial()) 
        {
            switch (this.efecto) 
            {
                case MASDOS:
                    estado = "+2 " + palo;
                    break;
                case MASCUATRO:
                    estado = "+4 ";
                    break;
                case SALTO:
                    estado = "Salto turno " + palo;
                    break;
                case CAMBIO_DE_SENTIDO:
                    estado = "Cambio de sentido " + palo;
                    break;
                case CAMBIOCOLOR:

                    estado = "Cambio de color";
                    break;
            }
        } 
        else 
        {
            estado = numero + " " + palo;
        }

        return estado;
    }

}