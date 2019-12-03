/*
 * Autor:Angel Esquinas
 * 
 * Ladrillo con estos se forman algunos mapas al chocar la bola dos veces con ellos se destruyen.
 * 
 */
package codigo;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GRect;

public class Ladrillo2 extends GRect{
	public int golpes=2;//veces que tienes que chocar con el para destruirlo

	/**
	 * 
	 * @param posX:  posicion X del ladrillo 2
	 * @param posY: posicion Y del ladrillo 2
	 * @param ancho:  ancho del ladrillo 2
	 * @param alto: alto del ladrillo 2
	 * @param color: color del ladrillo 2
	 * 
	 */
	public Ladrillo2(double posX, double posY, double ancho, double alto, Color color){
		super(posX, posY, ancho, alto);
		
		setFilled(true);//relleno del ladrillo
		setFillColor(color);//color del ladrillo
	}

}

