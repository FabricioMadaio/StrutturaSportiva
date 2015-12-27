package gui.partita;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class PartitaComponent extends JPanel
{
	public PartitaComponent(Partita p)
	{
		partita = p;

		JLabel nomeMatchLabel = new JLabel(partita.getGame());
		add(nomeMatchLabel);
		add(creaBottonePrenota());
		this.setBorder(new EtchedBorder());
	}

	public JButton creaBottonePrenota()
	{
		JButton prenotaBtn = new JButton("Prenota");

		return prenotaBtn;
	}

	private Partita partita;
}
