/*
 * Autor:Angel Esquinas
 * 
 * Esto va ser Arkanoid 
 * 
 */
package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;

public class Puntos extends GLabel{
	
	Font letras;
	
	/**
	 * 
	 * @param str: palabra que sale en la pantalla
	 * @param x: posicion X
	 * @param y: posicion Y
	 * 
	 */
	
	public Puntos(String str, int x, int y) {
		super(str, x, y);
		try {
			 
            letras= Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("iomanoid.ttf"));
        } catch (Exception e) {
        }
        // cambio el color de la fuente
        setColor(Color.green);
 
        // cambio el alto y el ancho de la fuente.
        setFont(letras.deriveFont(30, 30));
	}

	public void actualiza(Arkanoid arkanoid) {//actualiza marcador segun los puntos que vayas consiguiendo
		arkanoid.puntos.setLabel("Puntos: " + arkanoid.punto);
	}

}
