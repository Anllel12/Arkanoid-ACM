/*
 * Autor:Angel Esquinas
 * 
 * Esto va ser la primera parte del Arkanoid 
 * Sirve para crear una bola que sera la bola del juego Arkanoid
 * 
 */
package codigo;

import java.awt.Color;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.util.RandomGenerator;

public class Bola extends GOval{
	double velocidadX=-1;//velocidad a la que se mueve la bola en el eje X
	double velocidadY=-1;//velocidad a la que se mueve la bola en el eje Y

	RandomGenerator ladrilloRandom=new RandomGenerator();

	public Bola(double width, double height) {
		super(width, height);
	}
	/**
	 * 
	 * @param ancho: el ancho es el mismo que el alto ya que es redonda
	 * @param color: color de la bola
	 * 
	 */
	public Bola(double ancho, Color color){
		super(ancho,ancho);
		setFilled(true);
		setFillColor(color);
	}
	private void mueveBola(){//mueve la bola
		move(velocidadX, velocidadY);
	}
	public void checkRebote(Arkanoid arkanoid){//cuando llega a una pared rebota
		mueveBola();
		if(checkRebote(getX(), getY(), arkanoid)){//diferentes puntos de la pelota
			if(checkRebote(getX()+getWidth(), getY(), arkanoid)){
				if(checkRebote(getX(), getY()+getHeight(), arkanoid)){
					if(checkRebote(getX()+getWidth(), getY()+getHeight(), arkanoid)){
						if(checkRebote(getX()+getWidth()*0.25, getY(), arkanoid)){
							if(checkRebote(getX(), getY()+getHeight()*0.25, arkanoid)){
								if(checkRebote(getX()+getWidth()*0.25, getY()+getHeight(), arkanoid)){
									if(checkRebote(getX()+getWidth(), getY()+getHeight()*0.25, arkanoid)){
										if(checkRebote(getX()+getWidth()*0.5, getY(), arkanoid)){
											if(checkRebote(getX(), getY()+getHeight()*0.5, arkanoid)){
												if(checkRebote(getX()+getWidth()*0.5, getY()+getHeight(), arkanoid)){
													if(checkRebote(getX()+getWidth(), getY()+getHeight()*0.5, arkanoid)){
														if(checkRebote(getX()+getWidth()*0.75, getY(), arkanoid)){
															if(checkRebote(getX(), getY()+getHeight()*0.75, arkanoid)){
																if(checkRebote(getX()+getWidth()*0.75, getY()+getHeight(), arkanoid)){
																	if(checkRebote(getX()+getWidth(), getY()+getHeight()*0.75, arkanoid)){
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if(getX()>arkanoid.getWidth()-10||getX()<0){//rebota con la pared		
			velocidadX*=-1;
		}
		if(getY()>arkanoid.getHeight()-10||getY()<0){
			velocidadY*=-1;
		}
	}
	public boolean checkRebote(double posX, double posY , Arkanoid arkanoid){
		boolean noChoca=true;
		GObject auxiliar=arkanoid.getElementAt(posX, posY);
		if(getY()>425){//reseteo de la bola de lugar
			velocidadX=-1;
			velocidadY=-1;
			setLocation(arkanoid.ANCHO_PANTALLA/2, arkanoid.ALTO_PANTALLA/2);
			arkanoid.vida--;//elimino una vida
			arkanoid.vidas.actualiza(arkanoid);//actualizo las vidas
			arkanoid.waitForClick();
		}

		if(auxiliar instanceof Cursor){//si entra aqui es porque el objeto que está en posX, posY es de tipo cursor
			if(!arkanoid.auto){
				if (getX() > auxiliar.getX() + auxiliar.getWidth()*0.8) {//el cursor esta dividido en 5 partes y cada parte da un efecto diferente

					velocidadX=1.5;
				}
				else if (getX() > auxiliar.getX() + auxiliar.getWidth()*0.6) {

					velocidadX=1;

				}
				else if (getX() > auxiliar.getX() + auxiliar.getWidth()*0.4) {

					velocidadX=0;
				}
				else if (getX() > auxiliar.getX() + auxiliar.getWidth()*0.2) {

					velocidadX=-1;
				}
				else {

					velocidadX=-1.5;
				}
			}
			velocidadY*=-1;
			noChoca=false;
		}
		
		if(auxiliar instanceof Ladrillo){//si entra aqui es porque el objeto que está en posX, posY es de tipo ladrillo
			if(!arkanoid.auto){
				velocidadX*=-1;
			}
			velocidadY*=-1;
			setFillColor(ladrilloRandom.nextColor());//cambia del color la bola al chocar con un ladrillo
			noChoca=false;
			//((Ladrillo) auxiliar).setFillColor(Color.BLACK);
			arkanoid.remove(auxiliar);//quita el ladrillo
			arkanoid.punto++;//sumo un punto
			arkanoid.puntos.actualiza(arkanoid);//actualizo los puntos
			//arkanoid.remove(((Ladrillo) auxiliar).ladrillo);//quita la  imagen						
		}
		if(auxiliar instanceof Ladrillo2){//si entra aqui es porque el objeto que está en posX, posY es de tipo ladrillo
			velocidadX*=-1;
			velocidadY*=-1;
			setFillColor(ladrilloRandom.nextColor());//cambia del color la bola al chocar con un ladrillo
			((Ladrillo2) auxiliar).golpes--;
			noChoca=false;
			//((Ladrillo) auxiliar).setFillColor(Color.BLACK);
			if(((Ladrillo2) auxiliar).golpes==0){
				arkanoid.remove(auxiliar);//quita el ladrillo
				arkanoid.punto++;//sumo un punto
				arkanoid.puntos.actualiza(arkanoid);//actualizo los puntos
				//arkanoid.remove(((Ladrillo) auxiliar).ladrillo);//quita la  imagen	
			}

		}
		return noChoca;
	}
}
