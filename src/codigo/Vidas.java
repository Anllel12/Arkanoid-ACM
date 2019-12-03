/*
 * Autor:Angel Esquinas
 * 
 * Vidas son las vidas que tienes al caer tu bola pierdes una vida.
 * 
 */
package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;

public class Vidas extends GLabel{
	Font letras;
	
	/**
	 * 
	 * @param str: palabra que sale en la pantalla 
	 * @param x: posicion X
	 * @param y: posicion Y
	 * 
	 */
	public Vidas(String str, double x, double y) {
		super(str, x, y);
		try {
			 
            letras= Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("iomanoid.ttf"));//cambio la fuente de las letras
        } catch (Exception e) {
        }
        
        setColor(Color.green);// cambio el color de la fuente
        
        setFont(letras.deriveFont(30, 30));// cambio el alto y el ancho de la fuente
	}

	public void actualiza(Arkanoid arkanoid) {//actualiza marcador segun los puntos que vayas consiguiendo
		arkanoid.vidas.setLabel("Vidas: " + arkanoid.vida);
	}
}
