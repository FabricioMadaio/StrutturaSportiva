package gui.partita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import core.elementi.Partita;
import gui.cliente.ScreenClient;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * PartitaPrenotabileComponent:
 * 	contiene l'oggetto grafico che visualizza i dettagli di una partita e consente di
 *  prenotarla tramite un apposito pulsante
 */
public class PartitaPrenotabileComponent extends PartitaComponent {

	public PartitaPrenotabileComponent(Partita p,ScreenClient c){
		super(p);
		this.partita = p;
		this.cliente = c;
		
		JButton prenotaBtn = new JButton("Prenota");
		//aggiungo il pulsante all'oggetto PartitaComponent
		this.appendComponent(prenotaBtn);
		
		prenotaBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//apro la schermata ScreenPartita per effettuare la prenotazione
				new ScreenPartita(cliente,partita,cliente.getCliente());	
			}
		});
	}

	
	private ScreenClient cliente;
	private Partita partita;
}
