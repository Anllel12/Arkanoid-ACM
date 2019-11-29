/*
 * Autor:Angel Esquinas
 * 
 * Esto va ser la primera parte del Arkanoid 
 * Sirve para crear un cursor que sera la nave del juego Arkanoid
 * 
 */
package codigo;

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GRect;

public class Cursor extends GRect{
	/**
	 * 
	 * @param posY: posicion Y del cursor la X siempre sera 0
	 * @param ancho: ancho del cursor
	 * @param alto: alto del cursor
	 * @param color: color del cursor
	 * 
	 */
	public Cursor(int posY, double ancho, double alto, Color color){
		super(ancho, alto);
		setFilled(true);
		setFillColor(color);
		setLocation(0, posY);//pongo el cursor en la coordenada Y que me pasan
	}
}
