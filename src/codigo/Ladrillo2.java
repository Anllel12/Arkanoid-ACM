/*
 * Autor:Angel Esquinas
 * 
 * Esto va ser Arkanoid 
 * 
 */
package codigo;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GRect;

public class Ladrillo2 extends GRect{
	public int golpes=2;
	//GImage ladrillo;

	/**
	 * 
	 * @param posX:  posicion X del ladrillo
	 * @param posY: posicion Y del ladrillo
	 * @param ancho:  ancho del ladrillo
	 * @param alto: alto del ladrillo
	 * @param color: color del ladrillo
	 * 
	 */
	public Ladrillo2(double posX, double posY, double ancho, double alto, Color color){
		super(posX, posY, ancho, alto);
		setFilled(true);
		setFillColor(color);
		//ladrillo=new GImage("Imagen/LadrilloNaranja.png");
		//ladrillo.setSize(ancho, alto);
	}

}

