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
		panel.setLayout(new BorderLayout(0, 0));
				
		JLabel lblMatch = new JLabel(partita.getGame());
		lblMatch.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblMatch, BorderLayout.WEST);
				
		JLabel lblCapienza = new JLabel(" Capienza: " + p.getStadio().getCapienza());
		lblCapienza.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblCapienza, BorderLayout.EAST);
	}

	

	private Partita partita;
}
