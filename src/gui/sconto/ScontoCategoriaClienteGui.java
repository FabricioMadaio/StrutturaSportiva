package gui.sconto;

import gui.graphics.Finestra;

import javax.swing.*;

import core.ListaUtenti;
import core.sconti.ScontoPerCliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * ScontoCategoriaClienteGui:
 * 	schermata per l'inserimento nel sistema di un nuovo sconto per categoria di cliente
 */
public class ScontoCategoriaClienteGui extends Finestra
{

	/**
	 * La Classe ScontoPerCategoriaGui inizializza un nuovo oggetto del tipo Finistra il quale prende 
	 * per riferimento una lista degli utenti e un frame genitore.questo oggetto permette di inserire
	 * uno sconto selezionando una categoria utente.
	 * @param JFrame parent
	 * @param ListaUtenti listaUtenti
	 */

	public ScontoCategoriaClienteGui(JFrame parent,ListaUtenti listaUtenti) {
		super(parent, 400, 230);
		questaFinestra = this;
		this.listaUtenti = listaUtenti;
		operazioniSuFrame();
	}

	/**
	 * Il metodo si occupa di tutte le operazioni su frame come l'nserimento dei JPanel e la loro
	 * disposizione su frame.
	 */
	public void operazioniSuFrame()
	{
		//Creo un pannello dove aggiungio una label
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Sconto Categoria Cliente");
		label.setFont(new Font(null, Font.BOLD, 20));
		panel.add(label);
		//Posiziono i vari pannelli nella finestra
		questaFinestra.getContentPane().add(panel,BorderLayout.NORTH);
		questaFinestra.getContentPane().add(creaPannelloAggiungiIformazioni(),BorderLayout.CENTER);
		questaFinestra.getContentPane().add(creaaPannelloBottone(),BorderLayout.SOUTH);
	
		questaFinestra.setResizable(false);
		questaFinestra.setVisible(true);
	}
	/**
	 * Il metodo inserisce all'interno di un pannello una combobox che indica la categoria del cliente e
	 * un campo di testo per scrive la percentuale di sconto, entrambi gli elementi sono indicati con delle label.
	 * @return JPanel panel
	 */
	public JPanel creaPannelloAggiungiIformazioni()
	{
		//Creo un pannello e due label 
		JPanel panel = new JPanel();
		JLabel categorialbl = new JLabel("Categoria");
		categorialbl.setBounds(10, 0, 69, 40);
		JLabel percentualeLbl = new JLabel("Percentuale");
		percentualeLbl.setBounds(10, 62, 69, 40);

		//Inizializzo e posiziono una combo box e un text field
		BoxCategorie = new JComboBox<String>(categoria);
		BoxCategorie.setBounds(201, 0, 183, 40);
		percentualeField = new JTextField(10);
		percentualeField.setBounds(201, 62, 183, 40);
		panel.setLayout(null);
		//Aggiungo al pannello principale
		panel.add(categorialbl);
		panel.add(BoxCategorie);
		panel.add(percentualeLbl);
		panel.add(percentualeField);

		return panel;
	}
	/**
	 * Il metodo crea un pannello dove all'interno è inserito un bottone che serve ad applicare lo sconto per
	 * categoria e quindi  lo sconto verra aggiunto nell'array list di sconti globali.
	 * @return JPanel 
	 */
	public JPanel creaaPannelloBottone()
	{
		//Creo un pannello dove aggiungero il bottone
		JPanel panel = new JPanel();
		//Creo il bottone
		JButton AggiungiScontoBtn = new JButton("Aggiungi Sconto");
		//Listener
		AggiungiScontoBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String categoria = (String)BoxCategorie.getSelectedItem();
				int percentuale = Integer.parseInt(percentualeField.getText());
				ScontoPerCliente sconto = new ScontoPerCliente (categoria, percentuale);
				listaUtenti.addSconto(sconto);

				questaFinestra.closeFrame();

			}
		});
		panel.add(AggiungiScontoBtn);
		return panel;
	}


	private JComboBox<String> BoxCategorie;
	private JTextField percentualeField;
	private Finestra questaFinestra;
	private String[] categoria = {"Studente","Pensionato","Bambino"};
	private ListaUtenti listaUtenti;

}
