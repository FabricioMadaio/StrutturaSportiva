package gui.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import gui.graphics.PhysicBody;
import gui.graphics.Animation;
import gui.graphics.Executable;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * Classe Sprite:
 * 	definisce un oggetto grafico disegnato come una immagine ritagliata, spostando il ritaglio (frame)
 *  è possibile animare l'oggetto.
 *  Le animazioni possono essere gestite in maniera automatica dalla classe Animation e invocando il 
 *  metodo next per passare al ritaglio successivo.
 *  La classe dispone dei metodi ereditati da physicBody per gestire il movimento dello sprite seguendo
 *  una simulazione fisica.
 */
public class Sprite extends PhysicBody{
	
	/**
	 * costruttore sprite
	 * 
	 * @param xPos posizione in coordinate assolute
	 * @param yPos posizione in coordinate assolute
	 * @param frameW larghezza ritaglio immagine
	 * @param frameH altezza ritaglio immagine
	 * @param img riferimento immagine
	 */
	public Sprite(int xPos,int yPos,int frameW,int frameH,Image img){
			
		this.img = img;
		
		x = (int)xPos;
		y = (int)yPos;
		
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
		this.currentAnim = null;
		
		this.accumulator = 0;	//azzero il tempo trascorso dall'ultima animazione
		
		animations = new ArrayList<Animation>(); 
	
		//inizializzo la fisica
		super.Init(x,y,frameW,frameH,1.0f);
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
		
		int X = (int)x + ((int)(sx*frameW))/2*direction;
		int Y = (int)y - ((int)(sy*frameH))/2;
		
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
		g.drawImage(img, (int)X, (int)Y, (int)(frameW*scaleW+X),(int)(frameH*scaleH+Y),i, j, i+frameW, j+frameH, null);
		
    }
	
	/**
	 * setta il frame e annulla l'animazione corrente
	 * @param f
	 */
	public void setStaticAnimation(int f) {
		// TODO Auto-generated method stub
		currentAnim = null;
		frame = f;
	}
	
	/**
	 * setta l'animazione con nome name
	 * @param name
	 */
	public void setAnimation(String name){
		
		if(currentAnim!= null && currentAnim.getName().equals(name)) return;
		
		//cerco l'animazione name, faccio il reset dell'animazione corrente e setto
		//quella trovata come quella corrente
		for(Animation a : animations){
			if(a.getName().equals(name)){
				a.reset();
				currentAnim = a;
				return;
			}
		}
		
	}
	
	/**
	 * aggiungo una nuova animazione
	 * 
	 * @param string nome animazione
	 * @param is	array di frames
	 * @param speed	velocità animazione
	 * @param duration	durata animazione (secondi)
	 */
	public void addAnimation(String string, int[] is,float speed,float duration) {

		this.addAnimation(string,is,speed,duration,null);
	}

	/**
	 * aggiungo una animazione con listener alla fine del ciclo
	 * 
	 * @param string nome animazione
	 * @param is	array di frames
	 * @param speed	velocità animazione
	 * @param duration	durata animazione (secondi)
	 * @param func listener fine ciclo
	 */
	public void addAnimation(String string, int[] is,float speed,float duration,Executable func) {

		Animation a = new Animation(string,is,speed,duration,func);
		animations.add(a);
	}
	
	/**
	 * gestisce l'animazione automatica
	 * @param dt step della simulazione (tempo in secondi)
	 */
	public void animate(float dt){
		
		if(currentAnim==null) return;
		
		accumulator+=dt; //aggiorno il tempo trascorso dalla precedente animazione
		
		//se il tempo trascorso supera la durata, scatta il fotogramma seguente
		if(accumulator> currentAnim.getDuration()){
			currentAnim.next();
			frame = currentAnim.getFrame();
			accumulator = 0;
		}
		
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
	
	private ArrayList<Animation> animations;
	private Animation currentAnim;
	private float accumulator;
}
