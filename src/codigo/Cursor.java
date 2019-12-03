/*
 * Autor:Angel Esquinas
 * 
 * Cursor es la tabla donde choca la bola para que no caiga y no perder vidas.
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
		
		setFilled(true);//relleno el cursor
		setFillColor(color);//color del cursor
		
		setLocation(0, posY);//pongo el cursor en la coordenada Y que me pasan
	}
}
