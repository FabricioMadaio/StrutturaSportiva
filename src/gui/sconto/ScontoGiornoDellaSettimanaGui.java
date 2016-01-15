package gui.sconto;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.ListaUtenti;
import core.sconti.ScontoGiornoDellaSettimana;
import gui.graphics.Finestra;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * ScontoGiornoDellaSettimanaGui:
 * 	schermata per l'inserimento di un nuovo sconto per giorno della settimana
 */
public class ScontoGiornoDellaSettimanaGui extends Finestra 
{
	/**
	 * Il cotruttore della classe ScontoGiornoDellaSettimanGui inizializza un oggetto del tipo Finestra che prende come riferimento 
	 * un frame genitore e un oggetto lista utenti. Questo oggetto permette di inserire uno sconto selezionando un giorno della settimana.
	 * @param JFrame parent
	 * @param ListaUtenti listaUtenti
	 */
	public ScontoGiornoDellaSettimanaGui(JFrame parent,ListaUtenti listaUtenti) {
		super(parent, 400, 230);
		questaFinestra = this;
		this.listaUtenti = listaUtenti;
		operazioniSuFrame();
	}

	/**
	 * Il metodo si occupa di inserire i pannelli nel frame e della loro disposizione.
	 */
	public void operazioniSuFrame()
	{
		//Creo un pannello e una label
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Sconto Giorni Della Setimana");
		label.setFont(new Font(null, Font.BOLD, 20));
		panel.add(label);
		//Aggiungo i componeti al frame
		questaFinestra.getContentPane().add(panel,BorderLayout.NORTH);
		questaFinestra.getContentPane().add(creaPannelloAggiungiIformazioni(),BorderLayout.CENTER);
		questaFinestra.getContentPane().add(creaaPannelloBottone(),BorderLayout.SOUTH);

		questaFinestra.setResizable(false);
		questaFinestra.setVisible(true);
	}
	/**
	 * Il metodo inserisce in un panello una combo box dove all'interno ci sono i giorni della settimana e un campo dove
	 * poter scrivere la percentuale di sconti, i due componenti sono indicati con delle label.
	 * @return JPanel
	 */
	public JPanel creaPannelloAggiungiIformazioni()
	{
		//Creo il pannello e due label
		JPanel panel = new JPanel();
		JLabel giornoDellaSettimanalbl = new JLabel("Giorno Della Settimana");
		giornoDellaSettimanalbl.setBounds(10, 0, 139, 40);
		JLabel percentualeLbl = new JLabel("Percentuale");
		percentualeLbl.setBounds(10, 62, 69, 40);

		//Creo la combo box e il text field
		BoxGiorni = new JComboBox<String>(giorniDellaSettimana);
		BoxGiorni.setBounds(201, 0, 183, 40);
		percentualeField = new JTextField(10);
		percentualeField.setBounds(201, 62, 183, 40);
		panel.setLayout(null);
		//Aggiungo al pannello i componenti
		panel.add(giornoDellaSettimanalbl);
		panel.add(BoxGiorni);
		panel.add(percentualeLbl);
		panel.add(percentualeField);

		return panel;
	}
	
	/**
	 * Il metodo cre un pannello dove all'interno è stato inserito un bottone cche serve ad applicare lo sconto per
	 * giorno della settimana e quindo aggiunto nell'array list di scontoi globali.
	 * @return JPanel
	 */
	public JPanel creaaPannelloBottone()
	{
		//Creo un pannello dove aggiungo un bottone
		JPanel panel = new JPanel();
		JButton AggiungiScontoBtn = new JButton("Aggiungi Sconto");
		AggiungiScontoBtn.addActionListener(new ActionListener() {

			//Listener pulsante aggiungiSconto
			@Override
			public void actionPerformed(ActionEvent e) {
				String gionoSettimana = (String)BoxGiorni.getSelectedItem();
				int percentuale = Integer.parseInt(percentualeField.getText());
				ScontoGiornoDellaSettimana sconto = new ScontoGiornoDellaSettimana(gionoSettimana, percentuale);
				listaUtenti.addSconto(sconto);
				questaFinestra.closeFrame();

			}
		});
		panel.add(AggiungiScontoBtn);
		return panel;
	}


	private JComboBox<String> BoxGiorni;
	private JTextField percentualeField;
	private Finestra questaFinestra;

	private String[] giorniDellaSettimana = {"Lunedì","Martedì","Mercoledì","Giovedì","Venerdì","Sabato","Domenica"};
	private ListaUtenti listaUtenti;
}
