package gui.incassi;

import javax.swing.JFrame;

import gui.graphics.Finestra;
import gui.graphics.ScrollablePanelList;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import core.elementi.Stadio;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */
public class Incassi extends Finestra
{
	/**
	 * Il costruttore della Classe Incassi(la quale è una sottoclasse della classe Finistra)
	 * inizializza un oggetto prendendo come riferimento un frame genitore e una lista di stadi.
	 * Viene creata una finesta 700x500 poi all'interno di esse viene creato  un pannello dove 
	 * all'interno è contenuta una JScrollablePanelList la quale
	 * viene popolata di oggetti StadioComponent attraverso un for che scorre la lista degli 
	 * stadi e infiene viene inserita una JLabel in fondo a sinista che mostra gli incassi totali. 
 	 * @param JFrame parent
	 * @param ArrayList<Stadio> stadi
	 */
	public Incassi(JFrame parent,ArrayList<Stadio> stadi) 
	{
		super(parent, 700, 500);
		this.stadi= stadi;
		
		//Pannello visione incassi
		JPanel pannelloIncassiStadio = new JPanel(new BorderLayout());
		getContentPane().add(pannelloIncassiStadio, BorderLayout.CENTER);
		ScrollablePanelList scrollIncassiStadi = new ScrollablePanelList();
		
		//Pannello inserimento label
		JPanel pannelloVisualizzazioneIncassiTotali = new JPanel();
		getContentPane().add(pannelloVisualizzazioneIncassiTotali, BorderLayout.SOUTH);
		pannelloVisualizzazioneIncassiTotali.setLayout(new BorderLayout(0, 0));
		
		//Label che mosttra gli incassi totali
		JLabel lblIncassiTotatli = new JLabel("Incassi Totali:"+calcolaIncassoTotale());
		lblIncassiTotatli.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pannelloVisualizzazioneIncassiTotali.add(lblIncassiTotatli);
		
		//for chw inseriscw gli stadio component
		for(Stadio s :stadi)
		{
			scrollIncassiStadi.add(new StadioComponet(s));
		}
		
		pannelloIncassiStadio.add(scrollIncassiStadi,BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	/**
	 * Il metodo viene utilizzato per calcolare il totale degli incassi
	 * degli stadi che fanno parte della lista passata per riferimento al costruttore.
	 * @return double totaleIncassi
	 */
	public double calcolaIncassoTotale()
	{
		double totale = 0;
		
		for(Stadio s : stadi)
		{
			totale +=s.getIncasso();
		}
		
		return totale;
	}
	
	private ArrayList<Stadio> stadi;

}
