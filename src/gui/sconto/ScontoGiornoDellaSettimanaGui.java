package gui.sconto;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.ListaUtenti;
import core.sconti.Sconto;
import core.sconti.ScontoGiornoDellaSettimana;
import gui.graphics.Finestra;
import gui.graphics.ScrollablePanelList;

public class ScontoGiornoDellaSettimanaGui extends Finestra 
{

	public ScontoGiornoDellaSettimanaGui(JFrame parent,ListaUtenti listaUtenti) {
		super(parent, 400, 230);
		questaFinestra = this;
		this.scontiGlobali = listaUtenti.getScontiGlobali();
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


		BoxGiorni = new JComboBox<String>(giorniDellaSettimana);
		BoxGiorni.setBounds(201, 0, 183, 40);
		percentualeField = new JTextField(10);
		percentualeField.setBounds(201, 62, 183, 40);
		panel.setLayout(null);

		panel.add(giornoDellaSettimanalbl);
		panel.add(BoxGiorni);
		panel.add(percentualeLbl);
		panel.add(percentualeField);

		return panel;
	}

	public JPanel creaaPannelloBottone()
	{
		JPanel panel = new JPanel();
		JButton AggiungiScontoBtn = new JButton("Aggiungi Sconto");
		AggiungiScontoBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String gionoSettimana = (String)BoxGiorni.getSelectedItem();
				int percentuale = Integer.parseInt(percentualeField.getText());
				ScontoGiornoDellaSettimana sconto = new ScontoGiornoDellaSettimana(gionoSettimana, percentuale);
				scontiGlobali.add(sconto);
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
	private ArrayList<Sconto> scontiGlobali;
}
