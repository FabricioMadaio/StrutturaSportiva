package gui.partita;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import core.elementi.Partita;
import core.utente.Cliente;

public class PartitaPrenotabileComponent extends PartitaComponent {

	public PartitaPrenotabileComponent(Partita partita,Cliente c){
		super(partita);
		this.partita = partita;
		add(creaBottonePrenota(),BorderLayout.CENTER);
		this.cliente = c;
	}

	public JPanel creaBottonePrenota()
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(SystemColor.activeCaption);
		
		JButton prenotaBtn = new JButton("Prenota");
		prenotaBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ScreenPartita screenPartita = new ScreenPartita(null,partita,cliente);	
			}
		});
		
		panel.add(prenotaBtn,BorderLayout.EAST);
		return panel;
	}
	private Cliente cliente;
	private Partita partita;
}
