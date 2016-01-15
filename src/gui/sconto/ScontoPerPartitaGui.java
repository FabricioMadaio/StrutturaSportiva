package gui.sconto;

import javax.swing.*;

import core.ListaUtenti;
import core.elementi.Partita;
import core.sconti.ScontoPerPartita;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import gui.graphics.Finestra;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * ScontoPerPartitaGui:
 * 	schermata per l'inserimento di uno sconto per una determinata partita
 */
public class ScontoPerPartitaGui extends Finestra 
{
	/**
	 * Il costruttore di ScontoPerPartitaGui inizializza un oggetto finestra che prende come riferimento
	 * un framne genitore e un oggetto lista utente. Questo oggetto permette di inserire uno sconto alla
	 * sigola partita.
	 * @param JFarem parent
	 * @param ListaUtenti listaUtenti
	 */

	public ScontoPerPartitaGui(JFrame parent, ListaUtenti listaUtenti) {
		super(parent,550, 260);
		questaFinestra = this;
		this.partite = listaUtenti.getPartite();
		operazioniSuFrame();
	}

	/**
	 * Il metodo si occupa dell'inserimento dei JPanel sul frame e della loro disposizione.
	 */
	public void operazioniSuFrame()
	{
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Sconto Per Partita");
		label.setFont(new Font(null, Font.BOLD, 20));
		panel.add(label);

		questaFinestra.getContentPane().add(panel,BorderLayout.NORTH);
		questaFinestra.getContentPane().add(creaPannelloAggiungiIformazioni(),BorderLayout.CENTER);
		questaFinestra.getContentPane().add(creaaPannelloBottone(),BorderLayout.SOUTH);

		questaFinestra.setResizable(false);
		questaFinestra.setVisible(true);
	}

	/**
	 * Il metodo crea un pannello dove all'interno è presente una combo box dove ci sono tutte le partite
	 * e un campo  dove poter inserire la percentuale di sconto i due componenti sono idicati tramite delle label.
	 * @return JPanel
	 */
	public JPanel creaPannelloAggiungiIformazioni()
	{
		JPanel panel = new JPanel();
		JLabel paritalbl = new JLabel("Partita");
		paritalbl.setBounds(10, 0, 139, 40);
		JLabel percentualeLbl = new JLabel("Percentuale");
		percentualeLbl.setBounds(10, 62, 69, 40);


		BoxPartita = new JComboBox<Partita>();
		for(Partita p : partite)
		{
			BoxPartita.addItem(p);
		}

		BoxPartita.setBounds(159, 0, 364, 40);
		percentualeField = new JTextField(10);
		percentualeField.setBounds(159, 62, 364, 40);
		panel.setLayout(null);

		panel.add(paritalbl);
		panel.add(BoxPartita);
		panel.add(percentualeLbl);
		panel.add(percentualeField);

		return panel;
	}
	/**
	 * Il metodo crea un pannello dove all'interno viene inserito un bottone che permette di aggiunge lo 
	 * sconto alla singola partita.
	 * @return JPanel
	 */
	public JPanel creaaPannelloBottone()
	{
		JPanel panel = new JPanel();
		JButton AggiungiScontoBtn = new JButton("Aggiungi Sconto");
		
		AggiungiScontoBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int percentuale = Integer.parseInt(percentualeField.getText());

				Partita p = (Partita)BoxPartita.getSelectedItem();
				ScontoPerPartita sconto = new ScontoPerPartita(percentuale);
				p.aggiungiSconto(sconto);

				questaFinestra.closeFrame();
			}
		});
		panel.add(AggiungiScontoBtn);
		return panel;
	}


	private JComboBox<Partita> BoxPartita;
	private JTextField percentualeField;
	private Finestra questaFinestra;

	private ArrayList<Partita> partite;

}
