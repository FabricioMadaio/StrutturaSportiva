package gui.stadio;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import core.elementi.Posto;
import gui.graphics.Sprite;


/**
 * @author Fabri
 *	NOTA SULLA TERMINOLOGIA
 *	Canvas Space: spazio della canvas (coordinate assolute indipendenti dallo schermo)
 *  Screen Space: spazio dello schermo (coordinate proporzionali alle dimensioni del JPanel)
 */
public class StadioCanvas extends JPanel{
	
	private Sprite background;
	private Poltroncina selezione;
	
	private Sprite glowBorder;
	
	private float canvasSx,canvasSy;
	
	private Image poltroncinaImg;
	private ArrayList<Poltroncina> poltroncine;
	private ArrayList<Posto> posti;
	
	private StadioListener stadioListener;
	
	private editMode modalita;
	public enum editMode{AGGIUNGI,MODIFICA,SELEZIONA};
	
	public StadioCanvas(){
		
		//fattori di conversione tra Canvas space e Screen space
		canvasSx = 1;
		canvasSy = 1;
		
		File backgroundFile = new File("res/Stadio1.png");
    	File poltroncinaFile = new File("res/Sprite.png");
    	File glowBorderFile = new File("res/glowBorder.png");
    	
    	Image backgroundImg;
    	Image glowBorderImg;
    	
    	poltroncine = new ArrayList<Poltroncina>();
    	selezione = null;
    	
    	modalita = editMode.SELEZIONA;
 
		try {
			backgroundImg = ImageIO.read(backgroundFile);
			poltroncinaImg = ImageIO.read(poltroncinaFile);
			glowBorderImg = ImageIO.read(glowBorderFile);
			
			background = new Sprite(0,0,0,0,backgroundImg);
			glowBorder = new Sprite(0,0,0,0,glowBorderImg);
			
			this.addMouseListener(new MouseListener());
			this.addMouseMotionListener(new MouseMotionListener());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
	}
	
	public void addStadioListener(StadioListener st){
		stadioListener = st;
	}
	
	public void setPosti(ArrayList<Posto> posti) {
		
		this.posti = posti;
		
		if(poltroncine!=null)
			poltroncine.clear();
		
		poltroncine = new ArrayList<Poltroncina>();
		
		for(Posto p:posti){
			Poltroncina pol = new Poltroncina(p,50,50,poltroncinaImg);
			poltroncine.add(pol);
		}
		
		resetSelezione();
	}
	
	public editMode getModalita() {
		return modalita;
	}

	public void setModalita(editMode modalita) {
		this.modalita = modalita;
		resetSelezione();
		repaint();
	}

	class MouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			
			if(modalita==editMode.MODIFICA || modalita==editMode.SELEZIONA){
				resetSelezione();
				for(int i=0;i<poltroncine.size();i++){
					Poltroncina p = poltroncine.get(i);
					if (p.contains(e.getX(), e.getY())) {
						selezione = p;
						//sposto la poltroncina in cima
						poltroncine.remove(i);
						
						if(stadioListener!=null) stadioListener.onSelected(p);
					}
		    	}
				repaint();
			}
			
			if(modalita==editMode.AGGIUNGI){
				
				Posto p = new Posto(0, 0,""+poltroncine.size());
				posti.add(p);
				
				Poltroncina pol = new Poltroncina(p,50,50,poltroncinaImg);
				pol.setCanvasSpacePos(e.getX()/canvasSx, e.getY()/canvasSy);
				poltroncine.add(pol);
				
				resetSelezione();
				repaint();
			}
		}

	}
	class MouseMotionListener extends MouseMotionAdapter  {
		public void mouseDragged(MouseEvent e) {
			
			if(modalita==editMode.MODIFICA && selezione!=null){
				selezione.setCanvasSpacePos(e.getX()/canvasSx, e.getY()/canvasSy);
				repaint();
			}
		}
	}
	
	public Poltroncina getSelezione() {
		return selezione;
	}
	
	public void resetSelezione(){
		if(selezione!=null){
			poltroncine.add(selezione);
			if(stadioListener!=null) stadioListener.onReleased(selezione);
			selezione = null;
		}
	}
	
	public void destroySelezione(){
		if(selezione!=null){
			posti.remove(selezione.getPosto());
			if(stadioListener!=null) stadioListener.onReleased(selezione);
			selezione = null;
		}
	}
	
	//calcola il fattore di scala tra lo schermo e la canvas
	public void calculateScaleFactor(){
		canvasSx = getWidth()/(float)background.getFrameW();
		canvasSy = getHeight()/(float)background.getFrameH();
	}
	
	public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	
    	if(background==null) return;
    	
    	//aggiungere questa riga per usare la modalità filtro bilineare (scaling migliorato)
    	((Graphics2D)g).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    	
    	calculateScaleFactor();
    	
    	//portiamo background in screen space
    	background.setPos(getWidth()/2, getHeight()/2);
    	background.setScale(canvasSx, canvasSy);
    	
    	background.draw(g);
    	
    	if(poltroncine!=null)
    	for(Poltroncina p:poltroncine){
    		p.toScreenSpace(canvasSx, canvasSy);
    		p.draw(g);
    	}
    	
    	if(selezione!=null){
    		selezione.toScreenSpace(canvasSx, canvasSy);
    		//NOTA: aggiungo offset di una unità
    		glowBorder.setPos(selezione.getX()-canvasSx, selezione.getY()-canvasSy);
    		glowBorder.setScale(selezione.getScaleX(), selezione.getScaleY());
    		glowBorder.draw(g);
    		selezione.draw(g);
    	}
    }
}
