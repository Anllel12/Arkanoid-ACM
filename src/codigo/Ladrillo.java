/*
 * Autor:Angel Esquinas
 * 
 * Ladrillo con estos se forman los mapas al chocar la bola con ellos se destruyen.
 * 
 */
package codigo;

import java.awt.Color;

import acm.graphics.GRect;

public class Ladrillo extends GRect{

	/**
	 * 
	 * @param posX:  posicion X del ladrillo
	 * @param posY: posicion Y del ladrillo
	 * @param ancho:  ancho del ladrillo
	 * @param alto: alto del ladrillo
	 * @param color: color del ladrillo
	 * 
	 */
	public Ladrillo(double posX, double posY, double ancho, double alto, Color color){
		super(posX, posY, ancho, alto);
		
		setFilled(true);//relleno del ladrillo
		setFillColor(color);//color del ladrillo
	}

}
