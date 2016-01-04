package gui.partita;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.elementi.Partita;
import core.utente.Cliente;
import gui.cliente.ScreenClient;

public class PartitaPrenotabileComponent extends PartitaComponent {

	public PartitaPrenotabileComponent(Partita p,ScreenClient c){
		super(p);
		this.partita = p;
		this.cliente = c;
		
		JButton prenotaBtn = new JButton("Prenota");
		this.appendComponent(prenotaBtn);
		prenotaBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ScreenPartita screenPartita = new ScreenPartita(cliente,partita,cliente.getCliente());	
			}
		});
	}

	
	private ScreenClient cliente;
	private Partita partita;
}
