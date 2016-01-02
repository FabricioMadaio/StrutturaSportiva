package gui.sconto;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.graphics.Finestra;

public class ScontoGiornoDellaSettimanaGui extends Finestra 
{
	public ScontoGiornoDellaSettimanaGui(JFrame parent) {
		super(parent, 400, 230);
		questaFinestra = this;
		operazioniSuFrame();
	}
	
	
	public void operazioniSuFrame()
	{
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Sconto Giorni Della Setimana");
		label.setFont(new Font(null, Font.BOLD, 20));
		panel.add(label);
		
		questaFinestra.getContentPane().add(panel,BorderLayout.NORTH);
		questaFinestra.getContentPane().add(creaPannelloAggiungiIformazioni(),BorderLayout.CENTER);
		questaFinestra.getContentPane().add(creaaPannelloBottone(),BorderLayout.SOUTH);
		
		questaFinestra.setResizable(false);
		questaFinestra.setVisible(true);
	}
	
	public JPanel creaPannelloAggiungiIformazioni()
	{
		JPanel panel = new JPanel();
		JLabel giornoDellaSettimanalbl = new JLabel("Giorno Della Settimana");
		giornoDellaSettimanalbl.setBounds(10, 0, 139, 40);
		JLabel percentualeLbl = new JLabel("Percentuale");
		percentualeLbl.setBounds(10, 62, 69, 40);

		
		BoxCategorie = new JComboBox(giorniDellaSettimana);
		BoxCategorie.setBounds(201, 0, 183, 40);
		percentualeField = new JTextField(10);
		percentualeField.setBounds(201, 62, 183, 40);
		panel.setLayout(null);
		
		panel.add(giornoDellaSettimanalbl);
		panel.add(BoxCategorie);
		panel.add(percentualeLbl);
		panel.add(percentualeField);
		
		return panel;
	}
	
	public JPanel creaaPannelloBottone()
	{
		JPanel panel = new JPanel();
		JButton AggiungiScontoBtn = new JButton("Aggiungi Sconto");
		panel.add(AggiungiScontoBtn);
		return panel;
	}
	
	
	private JComboBox BoxCategorie;
	private JTextField percentualeField;
	private Finestra questaFinestra;
	private String[] giorniDellaSettimana = {"Lunedì","Martedì","Mecodì","Giovdì","Venerdì","Sabato","Domenica"};


}
