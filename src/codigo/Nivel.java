/*
 * Autor:Angel Esquinas
 * 
 * Nivel indica en el mapa que estas.
 * 
 */
package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;

public class Nivel extends GLabel{
	
	Font letras;
	
	/**
	 * 
	 * @param str: palabra que sale en la pantalla
	 * @param xPos: posicion X
	 * @param yPos: posicion Y
	 * 
	 */
	public Nivel(String str, int xPos, int yPos) {
		super(str, xPos, yPos);
		try {
			 
            letras= Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("iomanoid.ttf"));//cambio la fuente de las letras
        } catch (Exception e) {
        }
        
        setColor(Color.green);// cambio el color de la fuente
       
        setFont(letras.deriveFont(30, 30)); // cambio el alto y el ancho de la fuente
	}

	public void actualiza(Arkanoid arkanoid) {//actualiza marcador segun los puntos que vayas consiguiendo
		arkanoid.niveles.setLabel("Nivel " + arkanoid.nivel);
	}
}
