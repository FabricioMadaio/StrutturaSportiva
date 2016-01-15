package gui.sconto;

import gui.graphics.Finestra;

import gui.graphics.OrarioComponent;
import javax.swing.*;
import core.ListaUtenti;
import core.sconti.ScontoFasciaOraria;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * ScontoFasciaOrarariaGui:
 * 	schermata che consente di inserire un nuovo sconto per fascia oraria nel sistema
 */

public class ScontoFasciaOrarariaGui extends Finestra 
{

	/**
	 * Il costruttore dellla classe ScontoFasciaOrarariaGui inizializza un oggetto Finestra che prende come riferimento
	 * un frame genitore e una oggetto lista utenti.Questo oggetto permette di inserire uno sconto per fascia oraria
	 * selezionando un intervallo di tempo.
	 * @param JFrame parent
	 * @param ListaUtenti listaUtenti
	 */
	public ScontoFasciaOrarariaGui(JFrame parent,ListaUtenti listaUtenti) 
	{
		super(parent, 400, 300);
		questaFinestra = this;
		this.listaUtenti = listaUtenti;
		operazioniSuFrame();
	}

	/**
	 * Il metodo si occupa dell'inserimento dei vari pannelli all'interno del frame e della loro disposizione
	 */
	public void operazioniSuFrame()
	{
		//Creo un pannello e una label 
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Sconto Fascia Oraria");
		label.setFont(new Font(null, Font.BOLD, 20));
		panel.add(label);
		//Aggiungo i componenti alla finestra
		questaFinestra.getContentPane().add(panel,BorderLayout.NORTH);
		questaFinestra.getContentPane().add(creaPannelloAggiungiIformazioni(),BorderLayout.CENTER);
		questaFinestra.getContentPane().add(creaPannelloBottone(),BorderLayout.SOUTH);

		questaFinestra.setResizable(false);
		questaFinestra.setVisible(true);
	}

	/**
	 * crea il pannello di inserimento per orainizio, orafine e percentuale sconto
	 * @return pannello
	 */
	public JPanel creaPannelloAggiungiIformazioni()
	{
		JPanel panel = new JPanel();
		//Inizializzo oggetti OrarioComponent
		oraFineField = new OrarioComponent();
		oraFineField.setBounds(197, 68, 180, 30);
		oraInizioField = new OrarioComponent();
		oraInizioField.setBounds(197, 11, 180, 30);
		percentualeFiel = new JTextField(10);
		percentualeFiel.setBounds(197, 127, 180, 30);
		//Creo delle label e le posiziono
		JLabel oraInizioLbl = new JLabel("Ora Inizio");
		oraInizioLbl.setBounds(0, 0, 72, 44);
		JLabel oraFineLbl = new JLabel("Ora Fine");
		oraFineLbl.setBounds(0, 61, 72, 44);
		JLabel percentualeLbl = new JLabel("Percentuale");
		percentualeLbl.setBounds(0, 120, 72, 44);
		panel.setLayout(null);

		//Aggiungo al pannello
		panel.add(oraInizioLbl);
		panel.add(oraInizioField);
		panel.add(oraFineLbl);
		panel.add(oraFineField);
		panel.add(percentualeLbl);
		panel.add(percentualeFiel);


		return panel;
	}

	/**
	 * crea il pannello con il pulsante di conferma
	 * @return pannello
	 */
	public JPanel creaPannelloBottone()
	{
		//Creo un pannello e un bottone
		JPanel panel = new JPanel();
		JButton AggiungiScontoBtn = new JButton("Aggiungi Sconto");
		AggiungiScontoBtn.addActionListener(new ActionListener() {

			//Listener pulsante aggiungi sconto
			@Override
			public void actionPerformed(ActionEvent e) {

				double oraInizio = oraInizioField.getOrario();
				double oraFine = oraFineField.getOrario();
				int percentuale = Integer.parseInt(percentualeFiel.getText());
				//creo il nuovo sconto
				ScontoFasciaOraria sconto = new ScontoFasciaOraria(oraInizio, oraFine, percentuale);
				listaUtenti.addSconto(sconto);
				
				questaFinestra.closeFrame();
			}
		});
		
		panel.add(AggiungiScontoBtn);
		return panel;

	}


	private OrarioComponent oraInizioField;
	private OrarioComponent oraFineField;
	private JTextField percentualeFiel;
	private Finestra questaFinestra;

	private ListaUtenti listaUtenti;
}
