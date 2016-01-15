package gui.carrello;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import core.elementi.Biglietto;
import gui.partita.ScreenPartita;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */
public class PrenotazioneComponent extends BigliettoComponent
{
	/**
	 * Il costruttore della Classe prenotazione component e un oggetto biglietto component modificato
	 * poichè inizializza un oggetto prendendo come riferimento un biglietto e un oggetto carrello.
	 * Questo oggetto permette di Acquistare la prenotazione e cancellarla.
	 * @param Biglietto prenotazione
	 * @param Carrello carrello
	 */
	public PrenotazioneComponent(Biglietto prenotazione,Carrello carrello)
	{
		
		super(prenotazione);
		this.carrello = carrello;
		this.prenotazione = prenotazione;
		this.appendComponent(creaBottoneAcquista());
		this.appendComponent(creaBottoneRimuovi());
	}
	
	/**
	 * Il metodo crea un bottone il quale ha il compito di eliminare la prenotazione.
	 * @return JButton button
	 */
	public JButton creaBottoneRimuovi()
	{
		//Creo il bottone
		JButton rimuoviBtn = new JButton("X");
		//Setto lo sfondo
		rimuoviBtn.setBackground(Color.RED);
		//Listener
		rimuoviBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//Annullo il biglietto
				carrello.getCliente().annullaBiglietto(prenotazione);
				
				carrello.aggiornaListe();
			}
			
		});
		
		return rimuoviBtn;
	}
	/**
	 * Il metodo crea un bottone che permette di acquistare il biglietto.
	 * @return JButton button
	 */
	public JButton creaBottoneAcquista()
	{
		//Creo il bottone
		JButton prenotaBtn = new JButton("Acquista");
		//Aggiungo il listener
		prenotaBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//Creo un oggetto ScreenPartita
				ScreenPartita sp = new ScreenPartita(carrello,prenotazione.getPartita(),carrello.getCliente());
				//Mi dice dove ho prenotato il posto
				sp.caricaSelezione(prenotazione);
			}
			
		});
		return prenotaBtn;
	}
	
	private Carrello carrello;
	private Biglietto prenotazione;
}
