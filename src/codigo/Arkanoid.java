/*
 * Autor:Angel Esquinas
 * 
 * Arkanoid juego de ladrillos con una bola que los destruye, la cual rebota en un corsor(tabla).
 * 
 */
package codigo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Arkanoid extends GraphicsProgram{
	
	static int ANCHO_LADRILLO=35;
	static int ALTO_LADRILLO=15;

	static int ANCHO_PANTALLA=700;
	static int ALTO_PANTALLA=600;

	static int ANCHO_NIVEL=200;
	static int ALTO_NIVEL=30;

	int punto=0;
	int vida=3;
	int nivel=1;
	int puntosTotal=0;
	int numNivel=1;

	Font letras;

	boolean juego=false;
	boolean auto=false;

	Bola bola=new Bola(10, Color.orange);
	Cursor cursor=new Cursor(400, 60, 10, Color.gray);
	Puntos puntos=new Puntos("Puntos: 0", ANCHO_PANTALLA-200, ALTO_PANTALLA-100);
	Nivel niveles=new Nivel("Nivel 1", ANCHO_PANTALLA-400, ALTO_PANTALLA-100);
	Vidas vidas=new Vidas("Vidas: 3", ANCHO_PANTALLA-625, ALTO_PANTALLA-100);
	PuntosFinal puntosFinal=new PuntosFinal("Puntos totales: ", ANCHO_PANTALLA-540, ALTO_PANTALLA-150);

	GLabel letraPiramide=new GLabel("Piramide", ANCHO_PANTALLA-450,  ALTO_PANTALLA-280);
	GLabel letraMediaPiramide=new GLabel("Media Piramide", ANCHO_PANTALLA-510,  ALTO_PANTALLA-205);
	GLabel letraMuro=new GLabel("Muro", ANCHO_PANTALLA-415,  ALTO_PANTALLA-130);
	GLabel modoAuto=new GLabel("Modo Auto", ANCHO_PANTALLA-480,  ALTO_PANTALLA-55);

	GImage fin=new GImage("Imagen/gameOver.png");
	GImage fondo=new GImage("Imagen/ArkanoidFondo.png");
	GImage logo=new GImage("Imagen/ArkanoidLogo.png");

	RandomGenerator colorRandom=new RandomGenerator();//da un color random a los ladrillos

	public void init(){	
		
		setSize(ANCHO_PANTALLA, ALTO_PANTALLA+37);
		
		fondo.setSize(700, 600);
		add(fondo);
		
		add(logo, ANCHO_PANTALLA-650, 50);
		logo.scale(0.75, 0.75);
		
		addMouseListeners();
		
		inicio();//añado la pantalla de inicio
	}

	public void run(){//movimiento de la pelota y el rebote	
		
		removeAll();
		
		setSize(ANCHO_PANTALLA, ALTO_PANTALLA);
		
		fondo.setSize(700, 600);
		add(fondo);
		
		addMouseListeners();
		
		add(bola, ANCHO_PANTALLA/2, ALTO_PANTALLA/2);
		add(cursor, ANCHO_PANTALLA/2, 400);
		add(puntos);
		add(vidas);
		
		for(int i=numNivel; i<4; i++){
			if(!juego){
				
				if(i==1){//primer mapa
					pantalla1();
				}
				if(i==2){//segundo mapa
					pantalla2();
				}		
				if(i==3){//tercer mapa
					pantalla3();
				}
			}
		}
	}
	
	private void creaPiramide(){//creo la  primera pantalla
		
		int numLadrillos=14;
		int desplazamientoInicial=(getWidth()-numLadrillos*ANCHO_LADRILLO)/2;
		
		add(niveles);//añado el nivel
		
		for(int j=0; j<numLadrillos; j++){
			for(int i=j; i<numLadrillos; i++){
				
				int posicionX=desplazamientoInicial+ANCHO_LADRILLO*i-ANCHO_LADRILLO/2*j;
				int posicionY=ALTO_LADRILLO*j+ALTO_LADRILLO;
				
				Ladrillo ladrillo=new Ladrillo(posicionX, posicionY, ANCHO_LADRILLO, ALTO_LADRILLO, colorRandom.nextColor());//creo el ladrillo	
				add(ladrillo);
				
				pause(10);//hago que se cree ladrillo a ladrillo para que quede bonito
			}
		}
	}

	private void mediaPiramide(){//creo media piramide 
		
		int numLadrillos = 18;
		int desplazamientoInicial=(getWidth()-numLadrillos*ANCHO_LADRILLO)/2;
		
		add(niveles);//añado el nivel
		nivel++;//sumo el nivel
		niveles.actualiza(this);//actualizo el nivel
		
		for (int i = numLadrillos; i > 0; i--) {
			for(int j = i; j < numLadrillos; j++) {
				
				Ladrillo ladrillo = new Ladrillo(desplazamientoInicial+ANCHO_LADRILLO * (i - j) + ANCHO_LADRILLO * j - ANCHO_LADRILLO, ALTO_LADRILLO * j + ALTO_LADRILLO, ANCHO_LADRILLO, ALTO_LADRILLO, colorRandom.nextColor());
				add(ladrillo);
				
				pause(10);//hago que se cree ladrillo a ladrillo para que quede bonito
			}
		}
		nivel=1;//igualo nivel a 1 para que funcione en el siguiente nivel
	}

	private void creaMuro(){
		int numLadrillos=10;
		int desplazamientoInicial=(getWidth()-numLadrillos*ANCHO_LADRILLO)/2;
		
		add(niveles);//añado el nivel
		nivel++;//sumo el nivel
		nivel++;//sumo el nivel
		niveles.actualiza(this);//actualizo el nivel
		
		for(int j=0; j<numLadrillos; j++){
			for(int i=0; i<numLadrillos; i++){
				
				int posicionX=desplazamientoInicial+ANCHO_LADRILLO*i;
				int posicionY=ALTO_LADRILLO*j+ALTO_LADRILLO;
				
				if(j==0 || j==9 || i==0 || i==9){//hago que el mapa 3 tenga un reborde de ladrillos de 2 "vidas"
					
					Ladrillo2 ladrillo2=new Ladrillo2(posicionX, posicionY, ANCHO_LADRILLO, ALTO_LADRILLO, Color.green);//creo el ladrillo	
					add(ladrillo2);
				}
				else{
					
					Ladrillo ladrillo=new Ladrillo(posicionX, posicionY, ANCHO_LADRILLO, ALTO_LADRILLO, colorRandom.nextColor());//creo el ladrillo	
					add(ladrillo);
				}
				
				pause(10);//hago que se cree ladrillo a ladrillo para que quede bonito
			}
		}
	}

	private void pantalla1(){
		
		creaPiramide();//creo el mapa
		
		waitForClick();
		
		while(!juego && punto!=105){
			
			pause(5);
			
			bola.checkRebote(this);

			if(vida<1){//si no tiene vidas fin del juego
				
				juego=true;
				
				gameOver();
			}
			if(auto){//si esta el auto activado el cursor sigue a la bola automaticamente
				
				cursor.setLocation(bola.getX()-20, 400);
			}
		}
		punto=0;//reseteo los puntos para el nuevo mapa
	}

	private void pantalla2(){
		
		mediaPiramide();//creo el mapa
		
		bola.velocidadX=-1;
		bola.velocidadY=-1;
		
		bola.setLocation(ANCHO_PANTALLA/2, ALTO_PANTALLA/2);//recoloco la bola
		
		waitForClick();
		
		while(!juego && punto!=153){
			
			pause(5);
			
			bola.checkRebote(this);

			if(vida<1){//si no tiene vidas fin del juego
				
				juego=true;
				
				gameOver();
			}
			if(auto){//si esta el auto activado el cursor sigue a la bola automaticamente
				
				cursor.setLocation(bola.getX()-20, 400);
			}
		}
		punto=0;//reseteo los puntos para el nuevo mapa
	}

	private void pantalla3(){
		
		creaMuro();//creo el mapa
		
		bola.velocidadX=-1;
		bola.velocidadY=-1;
		
		bola.setLocation(ANCHO_PANTALLA/2, ALTO_PANTALLA/2);//recoloco la bola
		
		waitForClick();
		
		while(!juego){
			
			pause(5);
			
			bola.checkRebote(this);
			
			if(punto==100 || punto==258 || punto==358 || vida<1){//si no tiene vidas fin del juego o si ha llega el tope de puntos
				
				juego=true;
				
				gameOver();
			}
			if(auto){//si esta el auto activado el cursor sigue a la bola automaticamente
				
				cursor.setLocation(bola.getX()-20, 400);
			}
		}
	}

	private void inicio(){//pantalla de inicio
		try {

			letras= Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("iomanoid.ttf"));//cambio la fuente de la letra
		} catch (Exception e) {
		}
		
		// cambio el color de la fuente
		letraPiramide.setColor(Color.green);
		letraMediaPiramide.setColor(Color.green);
		letraMuro.setColor(Color.green);
		modoAuto.setColor(Color.green);

		// cambio el alto y el ancho de la fuente.
		letraPiramide.setFont(letras.deriveFont(50, 50));
		letraMediaPiramide.setFont(letras.deriveFont(50, 50));
		letraMuro.setFont(letras.deriveFont(50, 50));
		modoAuto.setFont(letras.deriveFont(50, 50));

		// cambio la posicion de la fuente.		
		add(letraPiramide);
		add(letraMediaPiramide);
		add(letraMuro);
		add(modoAuto);
		
		waitForClick();
	}
	
	private void gameOver(){//al quedarte sin vidas elimina todo y aparece la imagen de GameOver junto a los puntos totales obetenidos
		
		removeAll();
		
		setSize(ANCHO_PANTALLA, ALTO_PANTALLA);
		
		add(fondo);
		
		add(fin, -150, ALTO_PANTALLA-500);
		fin.scale(2, 2);
		
		add(puntosFinal);
		puntosFinal.actualiza(this);//actualizo los puntos totales

	}

	public void mouseMoved(MouseEvent evento){//mueve el cursor siguiendo la posicion del raton
		
		cursor.setLocation(evento.getX(), cursor.getY());
	}

	public void mouseClicked(MouseEvent evento){
		
		int posicionX=evento.getX();//coordenada X donde se ha producido el click
		int posicionY=evento.getY();//coordenada Y donde se ha producio el click

		if (getElementAt(posicionX, posicionY)==letraPiramide){//si es la letra "piramide" entra en el mapa 1
			numNivel=1;
		}
		if (getElementAt(posicionX, posicionY)==letraMediaPiramide){//si es la letra "media piramide" entra en el mapa 2
			numNivel=2;
		}
		if (getElementAt(posicionX, posicionY)==letraMuro){//si es la letra "muro" entra en el mapa 3
			numNivel=3;
		}
		if (getElementAt(posicionX, posicionY)==modoAuto){//si es la letra "modo auto" cambia el auto
			auto=true;
		}
	}
}

















