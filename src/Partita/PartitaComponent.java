package Partita;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PartitaComponent extends JPanel
{
	public PartitaComponent(Partita p)
	{
		partita = p;

		JLabel nomeMatchLabel = new JLabel(partita.getGame());
		add(nomeMatchLabel);
		add(creaBottonePrenota());
	}

	public JButton creaBottonePrenota()
	{
		JButton prenotaBtn = new JButton("Prenota");

		return prenotaBtn;
	}

	private Partita partita;
}
