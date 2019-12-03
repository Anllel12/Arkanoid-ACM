/*
 * Autor:Angel Esquinas
 * 
 * Puntos Final son los puntos totales que has obtenido en todos los mapas
 * 
 */

package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;

public class PuntosFinal extends GLabel{
	
	Font letras;
	
	/**
	 * 
	 * @param str: palabra que sale en la pantalla
	 * @param x: posicion X
	 * @param y: posicion Y
	 * 
	 */
	public PuntosFinal(String str, int x, int y) {
		super(str, x, y);
		try {
			 
            letras= Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("iomanoid.ttf"));//cambio la fuente de las letras
        } catch (Exception e) {
        }
        
        setColor(Color.white);// cambio el color de la fuente
 
        setFont(letras.deriveFont(50, 50));// cambio el alto y el ancho de la fuente
	}

	public void actualiza(Arkanoid arkanoid) {//actualiza marcador segun los puntos que vayas consiguiendo
		arkanoid.puntosFinal.setLabel("Puntos totales: " + arkanoid.puntosTotal);
	}

}