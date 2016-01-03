package gui.sconto;

import gui.graphics.Finestra;
import gui.graphics.ScrollablePanelList;

import javax.swing.*;

import core.ListaUtenti;
import core.sconti.Sconto;
import core.sconti.ScontoGiornoDellaSettimana;
import core.sconti.ScontoPerCliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScontoCategoriaClienteGui extends Finestra
{

	public ScontoCategoriaClienteGui(JFrame parent,ListaUtenti listaUtenti,ScrollablePanelList scroll) {
		super(parent, 400, 230);
		questaFinestra = this;
		this.scroll = scroll;
		this.scontiGlobali = listaUtenti.getScontiGlobali();
		operazioniSuFrame();
	}


	public void operazioniSuFrame()
	{
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Sconto Categoria Cliente");
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
		JLabel categorialbl = new JLabel("Categoria");
		categorialbl.setBounds(10, 0, 69, 40);
		JLabel percentualeLbl = new JLabel("Percentuale");
		percentualeLbl.setBounds(10, 62, 69, 40);


		BoxCategorie = new JComboBox(categoria);
		BoxCategorie.setBounds(201, 0, 183, 40);
		percentualeField = new JTextField(10);
		percentualeField.setBounds(201, 62, 183, 40);
		panel.setLayout(null);

		panel.add(categorialbl);
		panel.add(BoxCategorie);
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
				String categoria = (String)BoxCategorie.getSelectedItem();
				int percentuale = Integer.parseInt(percentualeField.getText());
				ScontoPerCliente sconto = new ScontoPerCliente (categoria, percentuale);
				scontiGlobali.add(sconto);
				scroll.add(new ScontoComponent(sconto));

				scroll.revalidate();
				questaFinestra.closeFrame();

			}
		});
		panel.add(AggiungiScontoBtn);
		return panel;
	}


	private JComboBox BoxCategorie;
	private JTextField percentualeField;
	private Finestra questaFinestra;
	private String[] categoria = {"Studente","Pensionato","Bambino"};
	private ArrayList<Sconto> scontiGlobali;
	private ScrollablePanelList scroll;

}
