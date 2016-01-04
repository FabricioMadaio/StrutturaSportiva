package gui.carrello;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import core.elementi.Biglietto;
import core.elementi.Partita;

public class BigliettoComponent extends JPanel
{
	public BigliettoComponent(Biglietto b)
	{
		setBackground(SystemColor.activeCaption);
		prenotazione = b;
		Partita partita = b.getPartita();
		setLayout(new BorderLayout(0, 0));
		
		this.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new EmptyBorder(6, 6, 6, 6)));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
				
		JLabel lblMatch = new JLabel(partita.getGame());
		lblMatch.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblMatch);
		
		JLabel lblStadio = new JLabel(" "+partita.getStadio().getNome()+" - posto: " + b.getPosto().getSigla());
		lblStadio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(lblStadio);
		
		panelAppend = new JPanel();
		panelAppend.setBackground(SystemColor.activeCaption);
		add(panelAppend, BorderLayout.EAST);
		panelAppend.setLayout(new BoxLayout(panelAppend, BoxLayout.X_AXIS));
		
		JLabel lblData = new JLabel(partita.getData().getTime().toString()+" ");
		panelAppend.add(lblData);
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 11));
	}

	public void appendComponent(JComponent comp){
		panelAppend.add(comp);
	}

	private JPanel panelAppend;
	private Biglietto prenotazione;
}
