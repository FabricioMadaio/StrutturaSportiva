package gui.partita;


import core.elementi.Partita;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.GridLayout;

public class PartitaComponent extends JPanel
{
	public PartitaComponent(Partita p)
	{
		setBackground(SystemColor.activeCaption);
		partita = p;
		setLayout(new BorderLayout(0, 0));
		
		this.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new EmptyBorder(6, 6, 6, 6)));
		
		JLabel lblData = new JLabel(p.getData().getTime().toString());
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(lblData, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
				
		JLabel lblMatch = new JLabel(partita.getGame());
		lblMatch.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblMatch);
				
		JLabel lblStadio = new JLabel(" "+p.getStadio().getNome()+" - capienza: " + p.getStadio().getCapienza());
		lblStadio.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblStadio);
	}

	

	private Partita partita;
}
