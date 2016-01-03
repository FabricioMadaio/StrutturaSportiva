package gui.sconto;

import javax.swing.*;

import core.ListaUtenti;
import core.elementi.Partita;
import core.sconti.Sconto;
import core.sconti.ScontoPerPartita;
import core.utente.Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import gui.graphics.Finestra;
import gui.graphics.ScrollablePanelList;

public class ScontoPerPartitaGui extends Finestra 
{


	public ScontoPerPartitaGui(JFrame parent, ListaUtenti listaUtenti,ScrollablePanelList scroll) {
		super(parent,550, 260);
		questaFinestra = this;
		this.scroll = scroll;
		this.partite = listaUtenti.getPartite();
		operazioniSuFrame();
	}


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
				
			
				for(Partita p1 :partite){
					scroll.add(new PartitaTitleComponent(p));
					
					for(Sconto s:p1.getSconti()){
						//aggiungo solo quelli specifici della partita?
						//if(s instanceof ScontoPerPartita)
						scroll.add(new ScontoComponent(s));
					}
				}
			
				scroll.revalidate();
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
	private ScrollablePanelList scroll;

}
