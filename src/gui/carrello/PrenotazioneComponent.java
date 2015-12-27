package gui.carrello;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class PrenotazioneComponent extends JPanel 
{
	public PrenotazioneComponent(String prenotazione)
	{
		//setLayout(new GridLayout(1, 2));
		this.prenotazione = prenotazione;
		JLabel prenotazioneLabel = new JLabel(this.prenotazione);
		setBorder(new EtchedBorder());
		add(prenotazioneLabel);
		add(creaBottoneRimnuovi());
	}
	
	public JButton creaBottoneRimnuovi()
	{
		JButton rimuoviBtn = new JButton("X");
		rimuoviBtn.setBackground(Color.RED);
		
		return rimuoviBtn;
	}
	
	public JButton creaBottonePrenota()
	{
		JButton prenotaBtn = new JButton("Prenota");
		
		return prenotaBtn;
	}
	
	private String prenotazione;
}
