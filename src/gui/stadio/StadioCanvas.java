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
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 *  StadioCanvas gestisce l'oggetto grafico che rappresenta uno stadio, consente di editare
 *  le poltroncine e settare il prezzo dello stadio
 *  la grafica è proporzionale al contenitore e le poltrone sono proporzionali alla immagine dello
 *  sfondo.
 * 
 *	NOTA SULLA TERMINOLOGIA
 *	Canvas Space: spazio della canvas (coordinate assolute indipendenti dallo schermo)
 *  Screen Space: spazio dello schermo (coordinate proporzionali alle dimensioni del JPanel)
 */
public class StadioCanvas extends JPanel{
	
	
	
	/**
	 *  costruttore di StadioCanvas
	 *  carica le immagini delle poltroncine e del selettore
	 */
	public StadioCanvas(){
		
		//fattori di conversione tra Canvas space e Screen space
		canvasSx = 1;
		canvasSy = 1;
		
		File backgroundFile = new File("res/Stadio.png");
    	File poltroncinaFile = new File("res/Sprite.png");
    	File glowBorderFile = new File("res/glowBorder.png");
    	
    	Image backgroundImg;
    	Image glowBorderImg;
    	
    	poltroncine = new ArrayList<Poltroncina>();
    	selezione = null;
    	
    	//setto la modalità di default come selezione
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
	
	/**
	 * setta una immagine come sfondo dello stadio
	 * @param backgroundImg
	 */
	public void setBackgroundImage(Image backgroundImg){			
		this.background = new Sprite(0,0,0,0,backgroundImg);
	}
	
	/**
	 * aggiunge un listener che reagisce alla selezione di una poltrona
	 * @param st StadioListener
	 */
	public void addStadioListener(StadioListener st){
		stadioListener = st;
	}
	
	/**
	 * setta i posti dello stadio e crea le poltroncine
	 * @param posti
	 */
	public void setPosti(ArrayList<Posto> posti) {
		
		this.posti = posti;
		
		if(poltroncine!=null)
			poltroncine.clear();
		
		poltroncine = new ArrayList<Poltroncina>();
		
		for(Posto p:posti){
			Poltroncina pol = new Poltroncina(p,50,50,poltroncinaImg);
			poltroncine.add(pol);
		}
		
		//annullo la selezione
		resetSelezione();
	}
	
	/**
	 * @return modalita di utilizzo
	 */
	public editMode getModalita() {
		return modalita;
	}

	/**
	 * setta la modalità di utilizzo
	 * @param modalita
	 */
	public void setModalita(editMode modalita) {
		this.modalita = modalita;
		resetSelezione();
		repaint();
	}

	/**
	 * classe interna per gestire l'imput da mouse
	 *
	 */
	class MouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			
			//modalità selezione o modifica
			if(modalita==editMode.MODIFICA || modalita==editMode.SELEZIONA){
				
				resetSelezione();
				//verifico se il mouse tocca una poltroncina
				for(int i=0;i<poltroncine.size();i++){
					
					Poltroncina p = poltroncine.get(i);
					if (p.contains(e.getX(), e.getY())) {
						//metto la poltroncina in selezione, cosi verrà disegnata per ultima
						selezione = p;
						//cancello la poltroncina dall'array, non devo disegnarla due volte
						poltroncine.remove(i);
						
						//lancio il listener della selezione
						if(stadioListener!=null) stadioListener.onSelected(p);
					}
		    	}
				repaint();
			}
			
			//modalità aggiungi poltrona
			if(modalita==editMode.AGGIUNGI){
				
				//creo un nuovo posto per lo stadio
				Posto p = new Posto(0, 0,""+poltroncine.size());
				posti.add(p);
				
				//creo una nuova poltroncina
				Poltroncina pol = new Poltroncina(p,50,50,poltroncinaImg);
				pol.setCanvasSpacePos(e.getX()/canvasSx, e.getY()/canvasSy);
				poltroncine.add(pol);
				
				//aggiorno la selezione e ridisegno il componente
				resetSelezione();
				repaint();
			}
		}

	}
	/**
	 * classe interna per gestire il trascinamento con il mouse
	 */
	class MouseMotionListener extends MouseMotionAdapter  {
		public void mouseDragged(MouseEvent e) {
			//se siamo in modalità modifica e abbiamo selezionato un posto
			if(modalita==editMode.MODIFICA && selezione!=null){
				//setto le coordinate nuove per la selezione
				selezione.setCanvasSpacePos(e.getX()/canvasSx, e.getY()/canvasSy);
				repaint();
			}
		}
	}
	
	/**
	 * @return poltroncina selezionata
	 */
	public Poltroncina getSelezione() {
		return selezione;
	}
	
	/**
	 * setta il posto p come posto selezionato
	 * @param p posto da selezionare
	 */
	public void setSelezione(Posto p) {
		
		//aggiorno la selezione
		resetSelezione();
		
		//cerca la poltroncina con posto p
		for(int i=0;i<poltroncine.size();i++){
			Poltroncina pol = poltroncine.get(i);
			if (pol.getPosto().equals(p)) {
				selezione = pol;
				//sposto la poltroncina in cima
				poltroncine.remove(i);
			}
    	}
	}
	
	/**
	 *  resetta la selezione
	 */
	public void resetSelezione(){
		
		if(selezione!=null){
			//riporto la poltroncina da selezione a array poltroncine
			poltroncine.add(selezione);
			//lancio il listener sul rilascio della poltrona
			if(stadioListener!=null) stadioListener.onReleased(selezione);
			//annullo la selezione
			selezione = null;
		}
	}
	
	/**
	 * cancella la poltroncina selezionata
	 */
	public void destroySelezione(){
		if(selezione!=null){
			
			//cancello il posto
			posti.remove(selezione.getPosto());
			//lancio il listener sul rilascio della poltrona
			if(stadioListener!=null) stadioListener.onReleased(selezione);
			
			//annullo la selezione, il garbage collector distrugge la poltroncina
			selezione = null;
		}
	}
	
	/**
	 * calcola il fattore di scala tra lo schermo e la canvas
	 */
	public void calculateScaleFactor(){
		canvasSx = getWidth()/(float)background.getFrameW();
		canvasSy = getHeight()/(float)background.getFrameH();
	}
	
	/* 
	 * disegna l'oggetto grafico
	 */
	public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	
    	if(background==null) return;
    	
    	//aggiungere questa riga per usare la modalità filtro bilineare (scaling migliorato)
    	((Graphics2D)g).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    	
    	//calcolo il fattore di scala 
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
    	
    	//se ho una poltrona selezionata, disegno anche il bordo glowBorder
    	if(selezione!=null){
    		selezione.toScreenSpace(canvasSx, canvasSy);
    		glowBorder.setPos(selezione.getX()-canvasSx, selezione.getY()-canvasSy);
    		glowBorder.setScale(selezione.getScaleX(), selezione.getScaleY());
    		glowBorder.draw(g);
    		selezione.draw(g);
    	}
    }
	
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
}
