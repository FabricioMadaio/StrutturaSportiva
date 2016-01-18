package gui.carrello;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import core.elementi.Biglietto;
import core.elementi.Partita;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * La classe crea oggetti che mostrano gli acquisti effettuati dagli utenti.
 */
public class BigliettoComponent extends JPanel
{
	/**
	 *Il costruttore della classe BigliettoComponente(sottoclasse di JPanel) inizializza un oggetto prendendo
	 *come riferimento un biglietto.Questo oggetto indica gli acquisti effettuauti dall'utente.
	 * @param Biglietto b
	 */
	public BigliettoComponent(Biglietto b)
	{
		//Setto lo sfondo del JPanel sottostante
		setBackground(SystemColor.activeCaption);
		Partita partita = b.getPartita();
		setLayout(new BorderLayout(0, 0));
		//Aggiungo un CompaundBorder ovvero un bordo composto da due bordi
		this.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new EmptyBorder(6, 6, 6, 6)));
		//Creo un nuovo pannello e ci aggiungo lo sfondo del pannello sottostante
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		//Aggiungo una label al pannello che indica il match		
		JLabel lblMatch = new JLabel(partita.getGame());
		lblMatch.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblMatch);
		//Aggiungo una label che indica lo stadio e il numero del posto acquistato
		JLabel lblStadio = new JLabel(" "+partita.getStadio().getNome()+" - posto: " + b.getPosto().getSigla());
		lblStadio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblStadio);
		
		//inserimento nel pannello principale
		panelAppend = new JPanel();
		panelAppend.setBackground(SystemColor.activeCaption);
		add(panelAppend, BorderLayout.EAST);
		panelAppend.setLayout(new BoxLayout(panelAppend, BoxLayout.X_AXIS));
		//Aggiungo la label data
		JLabel lblData = new JLabel(partita.getData().getTime().toString()+" ");
		panelAppend.add(lblData);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 11));
	}
	/**
	 * Il metodo permette di aggiungere componenti in modalita append.
	 * @param JComponent comp
	 */
	public void appendComponent(JComponent comp){
		panelAppend.add(comp);
	}

	private JPanel panelAppend;
}
