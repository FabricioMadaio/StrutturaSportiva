package gui.sconto;

import gui.graphics.Finestra;
import javax.swing.*;
import java.awt.*;

public class ScontoFasciaOrarariaGui extends Finestra 
{
	private JTextField textField;

	public ScontoFasciaOrarariaGui(JFrame parent) 
	{
		super(parent, 400, 300);
		
		questaFinestra = this;
		operazioniSuFrame();
	}
	
	public void operazioniSuFrame()
	{
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Sconto Fascia Oraria");
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
		
		oraFineField = new JTextField(10);
		oraFineField.setBounds(197, 68, 180, 30);
		oraInizioField = new JTextField(10);
		oraInizioField.setBounds(197, 11, 180, 30);
		percentualeFiel = new JTextField(10);
		percentualeFiel.setBounds(197, 127, 180, 30);
		
		JLabel oraInizioLbl = new JLabel("Ora Inizio");
		oraInizioLbl.setBounds(0, 0, 72, 44);
		JLabel oraFineLbl = new JLabel("Ora Fine");
		oraFineLbl.setBounds(0, 61, 72, 44);
		JLabel percentualeLbl = new JLabel("Percentuale");
		percentualeLbl.setBounds(0, 120, 72, 44);
		panel.setLayout(null);
		
	
		panel.add(oraInizioLbl);
		panel.add(oraInizioField);
		panel.add(oraFineLbl);
		panel.add(oraFineField);
		panel.add(percentualeLbl);
		panel.add(percentualeFiel);

		
		return panel;
	}
	
	public JPanel creaaPannelloBottone()
	{
		JPanel panel = new JPanel();
		JButton AggiungiScontoBtn = new JButton("Aggiungi Sconto");
		panel.add(AggiungiScontoBtn);
		return panel;
		
	}
	
	
	private JTextField oraInizioField;
	private JTextField oraFineField;
	private JTextField percentualeFiel;
	private Finestra questaFinestra;
}
