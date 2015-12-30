package gui.partita;


import core.elementi.Partita;

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
		
		this.setBorder(new EtchedBorder());
	}

	

	private Partita partita;
}
