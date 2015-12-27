package gui.stadio;

import java.awt.Image;
import java.awt.Rectangle;

import gui.graphics.Sprite;

public class Poltroncina extends Sprite{
	
	//cordinate della poltroncina in Canvas space (VEDI nota su StadioCanvas)
	private float canvasSpaceX;
	private float canvasSpaceY;
	
	public static int boxSize = 21; //dimensione area selezionabile
	
	private Stato stato;
	
	public enum Stato{PRENOTATO,VENDUTO,DISPONIBILE,NON_DISPONIBILE};

	public Poltroncina(int xPos, int yPos, int frameW, int frameH, Image img) {
		
		super(xPos, yPos, frameW, frameH, img);

		setCanvasSpacePos(xPos,yPos);
		setStato(Stato.DISPONIBILE);
	}
	
	public void setCanvasSpacePos(float x,float y){
		canvasSpaceX = x;
		canvasSpaceY = y;
	}
	
	public void setStato(Stato s){
		this.setFrame(s.ordinal());
	}

	//verifica in screen space
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
	
	//imposta lo sprite per disegnarlo in screen space
	public void toScreenSpace(float csX,float csY){
		setPos(canvasSpaceX*csX,canvasSpaceY*csY);
		setScale(csX, csY);
	}
}
