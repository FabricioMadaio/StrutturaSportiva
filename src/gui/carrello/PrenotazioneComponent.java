package gui.carrello;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import core.elementi.Biglietto;
import gui.partita.ScreenPartita;

public class PrenotazioneComponent extends BigliettoComponent
{
	public PrenotazioneComponent(Biglietto prenotazione,Carrello carrello)
	{
		//setLayout(new GridLayout(1, 2));
		super(prenotazione);
		this.carrello = carrello;
		this.prenotazione = prenotazione;
		this.appendComponent(creaBottoneAcquista());
		this.appendComponent(creaBottoneRimuovi());
	}
	
	public JButton creaBottoneRimuovi()
	{
		JButton rimuoviBtn = new JButton("X");
		rimuoviBtn.setBackground(Color.RED);
		
		rimuoviBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				carrello.getCliente().annullaBiglietto(prenotazione);
				
				carrello.aggiornaListe();
			}
			
		});
		
		return rimuoviBtn;
	}
	
	public JButton creaBottoneAcquista()
	{
		JButton prenotaBtn = new JButton("Acquista");
		prenotaBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ScreenPartita sp = new ScreenPartita(carrello,prenotazione.getPartita(),carrello.getCliente());
				sp.caricaSelezione(prenotazione);
			}
			
		});
		return prenotaBtn;
	}
	
	private Carrello carrello;
	private Biglietto prenotazione;
}
