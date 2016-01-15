package gui.stadio;

import java.awt.Image;

import core.elementi.Posto;
import core.elementi.Posto.Stato;
import gui.graphics.Sprite;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * la classe Poltroncina gestisce l'oggetto grafico che rappresenta il posto
 * 
 * NOTA SUI FOTOGRAMMI:
 * 	l'ordine dei fotogrammi deve combaciare con l'ordine degli stati del posto 
 *  (vedi enum Stato in Posto.java)
 */

public class Poltroncina extends Sprite{
	

	/**
	 * costruttore
	 * 
	 * @param p	posto
	 * @param frameW	larghezza ritaglio fotogramma 
	 * @param frameH	altezza ritaglio fotogramma
	 * @param img	immagine
	 */
	public Poltroncina(Posto p, int frameW, int frameH, Image img) {
		
		super(p.getX(), p.getY(), frameW, frameH, img);

		this.posto = p;
		setStato(p.getStato());
	}
	
	/**
	 * cordinate della poltroncina in Canvas space (VEDI nota su StadioCanvas.java)
	 * @param x
	 * @param y
	 */
	public void setCanvasSpacePos(float x,float y){
		posto.setX((int)x);
		posto.setY((int)y);
	}
	
	/**
	 * @return stato del posto
	 */
	public Stato getStato(){
		return this.posto.getStato();
	}
	
	/**
	 * setta lo stato del posto e aggiorna lo sprite
	 * @param s stato posto
	 */
	public void setStato(Stato s){
		this.posto.setStato(s);
		//setto come frame l'intero associato allo stato del posto (VEDI nota in alto)
		this.setFrame(s.ordinal());
	}
	
	/**
	 * @return posto
	 */
	public Posto getPosto() {
		return posto;
	}


	/**
	 * verifica in screen space se le coordinate x y si trovano dentro la poltroncina
	 * 
	 * @param x coordinata x in screen Space
	 * @param y coordinata y in screen Space
	 * @return coordinate interne/coordinate esterne (true/false)
	 */
	public boolean contains(float x,float y){
		
		float fW = boxSize*getScaleX()/2;
		float fH = boxSize*getScaleY()/2;
		
		float r1X = getX()-fW;
		float r1Y = getY()-fH;
		float r2X = getX() + fW;
		float r2Y = getY() + fH;
		
		if( r1X <= x && x <= r2X && r1Y <= y && y <= r2Y ) {
			return true;
		}
		return false;
	}
	
	/**
	 * trasforma le coordinate dello sprite da canvas space a screen space
	 * @param csX fattore di conversione x per canvas Space
	 * @param csY fattore di conversione y per canvas Space
	 */
	public void toScreenSpace(float csX,float csY){
		setPos(posto.getX()*csX,posto.getY()*csY);
		setScale(csX, csY);
	}
	
	public static int boxSize = 21; //dimensione area selezionabile
	private Posto posto;
	
}
