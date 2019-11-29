/*
 * Autor:Angel Esquinas
 * 
 * Esto va ser Arkanoid 
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

	GLabel letraPiramide=new GLabel("Piramide", ANCHO_PANTALLA-450,  ALTO_PANTALLA-280);
	GLabel letraMediaPiramide=new GLabel("Media Piramide", ANCHO_PANTALLA-510,  ALTO_PANTALLA-205);
	GLabel letraMuro=new GLabel("Muro", ANCHO_PANTALLA-415,  ALTO_PANTALLA-130);
	GLabel modoAuto=new GLabel("Modo Auto", ANCHO_PANTALLA-480,  ALTO_PANTALLA-55);

	GImage fin=new GImage("Imagen/gameOver.png");
	GImage fondo=new GImage("Imagen/ArkanoidFondo.png");
	GImage logo=new GImage("Imagen/ArkanoidLogo.png");

	RandomGenerator colorRandom=new RandomGenerator();

	public void init(){	
		setSize(ANCHO_PANTALLA, ALTO_PANTALLA+37);
		fondo.setSize(685, 600);
		add(fondo);
		add(logo, ANCHO_PANTALLA-650, 50);
		logo.scale(0.75, 0.75);
		addMouseListeners();
		inicio();
	}

	public void run(){//movimiento de la pelota y el rebote	
		removeAll();
		setSize(ANCHO_PANTALLA, ALTO_PANTALLA);
		fondo.setSize(685, 600);
		add(fondo);
		addMouseListeners();
		add(bola, ANCHO_PANTALLA/2, ALTO_PANTALLA/2);
		add(cursor, ANCHO_PANTALLA/2, 400);
		add(puntos);
		add(vidas);
		for(int i=numNivel; i<4; i++){
			if(!juego){
				if(i==1){
					pantalla1();
				}
				if(i==2){
					pantalla2();
				}		
				if(i==3){
					pantalla3();
				}
			}
		}

	}

	private void gameOver(){//al quedarte sin vidas elimina todo y aparece la imagen de GameOver
		removeAll();
		setSize(ANCHO_PANTALLA, ALTO_PANTALLA);
		add(fondo);
		add(fin, -150, ALTO_PANTALLA-500);
		fin.scale(2, 2);

	}

	private void creaPiramide(){//creo la  primera pantalla
		int numLadrillos=14;
		int desplazamientoInicial=(getWidth()-numLadrillos*ANCHO_LADRILLO)/2;
		add(niveles);//añado abajo el numero de nivel
		niveles.actualiza(this);
		for(int j=0; j<numLadrillos; j++){
			for(int i=j; i<numLadrillos; i++){
				int posicionX=desplazamientoInicial+ANCHO_LADRILLO*i-ANCHO_LADRILLO/2*j;
				int posicionY=ALTO_LADRILLO*j+ALTO_LADRILLO;
				Ladrillo ladrillo=new Ladrillo(posicionX, posicionY, ANCHO_LADRILLO, ALTO_LADRILLO, colorRandom.nextColor());//creo el ladrillo	
				add(ladrillo);
				pause(10);//hago que se cree ladrillo a ladrillo para que quede bonito
				//add(miLadrillo.ladrillo, posicionX, posicionY);//añado la imagen
			}

		}
	}

	private void mediaPiramide(){//creo media piramide 
		int numLadrillos = 18;
		int desplazamientoInicial=(getWidth()-numLadrillos*ANCHO_LADRILLO)/2;
		add(niveles);//añado abajo el numero de nivel
		nivel++;//sumo el nivel
		niveles.actualiza(this);
		for (int i = numLadrillos; i > 0; i--) {
			for(int j = i; j < numLadrillos; j++) {
				Ladrillo ladrillo = new Ladrillo(desplazamientoInicial+ANCHO_LADRILLO * (i - j) + ANCHO_LADRILLO * j - ANCHO_LADRILLO, ALTO_LADRILLO * j + ALTO_LADRILLO, ANCHO_LADRILLO, ALTO_LADRILLO, colorRandom.nextColor());
				add(ladrillo);
				pause(10);//hago que se cree ladrillo a ladrillo para que quede bonito
			}
		}
	}

	private void creaMuro(){
		int numLadrillos=10;
		int desplazamientoInicial=(getWidth()-numLadrillos*ANCHO_LADRILLO)/2;
		add(niveles);//añado abajo el numero de nivel
		nivel++;
		nivel++;
		niveles.actualiza(this);
		for(int j=0; j<numLadrillos; j++){
			for(int i=0; i<numLadrillos; i++){
				int posicionX=desplazamientoInicial+ANCHO_LADRILLO*i;
				int posicionY=ALTO_LADRILLO*j+ALTO_LADRILLO;
				if(j==0 || j==9 || i==0 || i==9){
					Ladrillo2 ladrillo2=new Ladrillo2(posicionX, posicionY, ANCHO_LADRILLO, ALTO_LADRILLO, Color.green);//creo el ladrillo	
					add(ladrillo2);
				}
				else{
					Ladrillo ladrillo=new Ladrillo(posicionX, posicionY, ANCHO_LADRILLO, ALTO_LADRILLO, colorRandom.nextColor());//creo el ladrillo	
					add(ladrillo);
				}
				pause(10);//hago que se cree ladrillo a ladrillo para que quede bonito
				//add(miLadrillo.ladrillo, posicionX, posicionY);//añado la imagen
			}
		}
	}

	private void pantalla1(){
		creaPiramide();
		waitForClick();
		while(!juego && punto!=105){
			pause(5);
			bola.checkRebote(this);
			//cursor.setLocation(bola.getX(), cursor.getY());
			if(vida<1){
				juego=true;
				gameOver();
			}
			if(auto){
				cursor.setLocation(bola.getX()-20, 400);
			}
		}
		punto+=puntosTotal;//guardo todos los puntos para que al final del juego salga el total
		punto=0;//reseteo los puntos para el nuevo mapa
		nivel=0;
	}

	private void pantalla2(){
		mediaPiramide();
		bola.velocidadX=-1;
		bola.velocidadY=-1;
		bola.setLocation(ANCHO_PANTALLA/2, ALTO_PANTALLA/2);
		waitForClick();
		while(!juego && punto!=153){
			pause(5);
			bola.checkRebote(this);
			//cursor.setLocation(bola.getX(), cursor.getY());
			if(vida<1){
				juego=true;
				gameOver();
			}
			if(auto){
				cursor.setLocation(bola.getX()-20, 400);
			}
		}
		punto+=puntosTotal;//guardo todos los puntos para que al final del juego salga el total
		punto=0;//reseteo los puntos para el nuevo mapa
		nivel=0;
	}

	private void pantalla3(){
		creaMuro();
		bola.velocidadX=-1;
		bola.velocidadY=-1;
		bola.setLocation(ANCHO_PANTALLA/2, ALTO_PANTALLA/2);
		waitForClick();
		while(!juego){
			pause(5);
			bola.checkRebote(this);
			//cursor.setLocation(bola.getX(), cursor.getY());	
			if(punto==100 || punto==258 || punto==358 || vida<1){
				juego=true;
				gameOver();
			}
			if(auto){
				cursor.setLocation(bola.getX()-20, 400);
			}
		}
		punto+=puntosTotal;//guardo todos los puntos para que al final del juego salga el total
		nivel=0;
	}

	private void inicio(){
		try {

			letras= Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("iomanoid.ttf"));
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

	public void mouseMoved(MouseEvent evento){//mueve el cursor siguiendo la posicion del raton
		cursor.setLocation(evento.getX(), cursor.getY());
	}

	public void mouseClicked(MouseEvent evento){
		int posicionX=evento.getX();//coordenada X donde se ha producido el click
		int posicionY=evento.getY();//coordenada Y donde se ha producio el click

		if (getElementAt(posicionX, posicionY)==letraPiramide){
			numNivel=1;
		}
		if (getElementAt(posicionX, posicionY)==letraMediaPiramide){
			numNivel=2;
		}
		if (getElementAt(posicionX, posicionY)==letraMuro){
			numNivel=3;
		}
		if (getElementAt(posicionX, posicionY)==modoAuto){
			auto=true;
		}
	}
}

















