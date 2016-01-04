package gui.partita;

import java.awt.BorderLayout;

import javax.swing.JButton;

import core.elementi.Partita;

public class PartitaPrenotabileComponent extends PartitaComponent {

	public PartitaPrenotabileComponent(Partita p) {
		super(p);
		
		JButton prenotaBtn = new JButton("Prenota");
		super.appendComponent(prenotaBtn);
	}

}
