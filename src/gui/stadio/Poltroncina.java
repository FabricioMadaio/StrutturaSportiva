package gui.stadio;

import java.awt.Image;

import core.elementi.Posto;
import core.elementi.Posto.Stato;
import gui.graphics.Sprite;

public class Poltroncina extends Sprite{
	

	public static int boxSize = 21; //dimensione area selezionabile
	private Posto posto;
	

	public Poltroncina(Posto p, int frameW, int frameH, Image img) {
		
		super(p.getX(), p.getY(), frameW, frameH, img);

		this.posto = p;
		setStato(p.getStato());
	}
	
	//cordinate della poltroncina in Canvas space (VEDI nota su StadioCanvas)
	public void setCanvasSpacePos(float x,float y){
		posto.setX((int)x);
		posto.setY((int)y);
	}
	
	public Stato getStato(){
		return this.posto.getStato();
	}
	
	public void setStato(Stato s){
		this.posto.setStato(s);
		this.setFrame(s.ordinal());
	}
	
	public Posto getPosto() {
		return posto;
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
		setPos(posto.getX()*csX,posto.getY()*csY);
		setScale(csX, csY);
	}
}
