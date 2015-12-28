package gui.partita;

import javax.swing.JButton;

import core.elementi.Partita;

public class PartitaPrenotabileComponent extends PartitaComponent {

	public PartitaPrenotabileComponent(Partita p) {
		super(p);
		add(creaBottonePrenota());
		// TODO Auto-generated constructor stub
	}

	public JButton creaBottonePrenota()
	{
		JButton prenotaBtn = new JButton("Prenota");

		return prenotaBtn;
	}
}
