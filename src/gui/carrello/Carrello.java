package gui.carrello;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;


import gui.cliente.ScrollablePanelList;

public class Carrello extends JFrame
{
	public Carrello()
	{
		questoFrame = this;
		operazioniSuFrame();
	}

	public void  operazioniSuFrame()
	{
		questoFrame.setSize(800, 500);
		questoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		questoFrame.setTitle("Carrello");

		questoFrame.add(creaPannelloPrenotazioni(),BorderLayout.NORTH);
		questoFrame.add(creaPannnelloAquistiEffettuati(),BorderLayout.CENTER);

		questoFrame.setVisible(true);
	}

	public JPanel creaPannelloPrenotazioni()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel prenotazioniLabel = new JLabel("Prenotazioni");
		prenotazioniLabel.setFont(new Font(null, Font.BOLD, 22));
		panel.add(prenotazioniLabel,BorderLayout.NORTH);
		panel.add(pannelloVisualizzazioneRimozione(), BorderLayout.CENTER);
		panel.setBorder(new EtchedBorder());
		return panel;
	}

	public JPanel pannelloVisualizzazioneRimozione()
	{
		JPanel panel = new ScrollablePanelList();
		panel.add(new PrenotazioneComponent("Prenotazione1"));
		panel.add(new PrenotazioneComponent("Prenotazione2"));
		return panel;
	}

	public JPanel creaPannnelloAquistiEffettuati()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel acquistiLabel = new JLabel("Acquisti Effettuati");
		acquistiLabel.setFont(new Font(null, Font.BOLD, 22));
		panel.add(acquistiLabel,BorderLayout.NORTH);
		panel.add(creaTextArea(),BorderLayout.CENTER);

		panel.setBorder(new EtchedBorder());
		return panel;
	}

	public JPanel creaTextArea()
	{
		JPanel panel = new JPanel();
		textArea = new JTextArea();
		textArea.setText("Qui Verranno Aggiunti Gli Acquisti");
		textArea.setFont(new Font(null, Font.PLAIN, 20));
		JScrollPane scroll = new JScrollPane(textArea);
		panel.add(scroll);
		panel.setBorder(new EtchedBorder());

		return panel;
	}


	private JFrame questoFrame;
	private JTextArea textArea;
}
