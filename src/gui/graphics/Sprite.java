package gui.graphics;

import java.awt.Graphics;
import java.awt.Image;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * Classe Sprite:
 * 	definisce un oggetto grafico disegnato come una immagine ritagliata, spostando il ritaglio (frame)
 *  è possibile animare l'oggetto.
 */
public class Sprite{
	
	/**
	 * costruttore sprite
	 * 
	 * @param xPos posizione in coordinate assolute
	 * @param yPos posizione in coordinate assolute
	 * @param frameW larghezza ritaglio immagine
	 * @param frameH altezza ritaglio immagine
	 * @param img riferimento immagine
	 */
	public Sprite(float xPos,float yPos,int frameW,int frameH,Image img){
			
		this.img = img;
		
		x = xPos;
		y = yPos;
		
		direction = -1; //l'immagine non viene specchiata
		visible = true; //lo sprite è visible
		
		//setto il fattore di scala a 1
		this.sx = 1;	
		this.sy = 1;
		
		//calcolo il numero di frames
		this.framesRow = (frameW==0)?1:img.getWidth(null)/frameW;
		
		this.frameW = (frameW==0)?img.getWidth(null):frameW;
		this.frameH = (frameH==0)?img.getHeight(null):frameH;
		
		frame = 0;
	}
	
	/**
	 * @return fattore di scala x
	 */
	public float getScaleX() {
		return sx;
	}

	/**
	 * @return fattore di scala x
	 */
	public float getScaleY() {
		return sy;
	}

	/**
	 * @return larghezza ritaglio
	 */
	public int getFrameW() {
		return frameW;
	}

	/**
	 * @return altezza ritaglio
	 */
	public int getFrameH() {
		return frameH;
	}

	/**
	 * @param right direzione dello sprite (se vale 1 risulta specchiato, -1 normale)
	 */
	public void setDirection(boolean right){
		if(right==true)
			direction = -1;
		else
			direction = 1;
	}
	
	/**
	 * @return x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return y
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * @return verifica se lo sprite è specchiato
	 */
	public boolean getDirection(){
		if(direction>0) return true;
		else return false;
	}
	
	/**
	 * @param x x
	 * @param y y
	 */
	public void setPos(float x,float y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @param f indice ritaglio animazione
	 */
	public void setFrame(int f){
		frame = f;
	}
		
	/**
	 * setta il fattore di scala
	 * 
	 * @param sx scala x
	 * @param sy scala y
	 */
	public void setScale(float sx,float sy){
		this.sx = sx;
		this.sy = sy;
	}
	
	/**
	 * @return sprite visibile
	 */
	public boolean isVisible(){
		return visible;
	}
	
	/**
	 * @param v visibilità
	 */
	public void setVisible(boolean v){
		visible = v;
	}
	
	/**
	 * disegna lo sprite
	 * @param g oggetto Graphics
	 */
	public void draw(Graphics g)
    {
		
		//se non è visibile non lo disegna
		if(!visible) return;
		
		/*
		 * calcolo le coordinate di riferimento del ritaglio tenendo conto del fattore di scala
		 * e se lo sprite è specchiato
		 * 
		 * X = x + scalaX*larghezzaFrame/2 * specchiato
		 * Y = y - scalay*altezzaFrame/2
		 */
		
		//int X = (int)(x + sx*frameW/2*direction);
		//int Y = (int)(y - sy*frameH/2);
		
		float X = x + (sx*frameW/2)*direction;
		float Y = y - (sy*frameH/2);
		
		/*
		 * calcolo l'incremento delle coordinate rispetto al valore del frame (per le animazioni)
		 * 
		 * frame%framesRow = numero di celle orizzontali per il frame corrente
		 * frame/framesRow = numero di celle verticali per il frame corrente
		 */
		int i = (frame%framesRow)*frameW;
		int j = (frame/framesRow)*frameH;
	
		float scaleH = sy;	//fattore di scala y
		float scaleW = (direction>0)?-sx:sx; //fattore di scala x (tiene conto dello specchio)

		//disegna immagine ritagliata (vedi definizione g.drawImage)
		g.drawImage(img, Math.round(X), Math.round(Y), Math.round(frameW*scaleW+X),Math.round(frameH*scaleH+Y),i, j, i+frameW, j+frameH, null);
		
    }
	



	private float x;
	private float y;
	
	private int framesRow;
	private int frame;
	private int frameW;
	private int frameH;
	private float sx,sy;
		
	private int direction;
	private boolean visible;
	
	private Image img;
}
