package gui.partita;

import core.elementi.Biglietto;
import core.elementi.Partita;
import core.elementi.Posto.Stato;
import core.utente.Cliente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import gui.graphics.Finestra;
import gui.stadio.StadioCanvas;

public class ScreenPartita extends Finestra
{
	public ScreenPartita(JFrame parent,Partita partita,Cliente cliente) 
	{
		super(parent, 800, 700);
		this.partita = partita;
		questoFrame = this;
		operazioniSuFrame();
		this.cliente = cliente;
		stadioCanvas.setPosti(partita.getPosti());
		//biglietto = new Biglietto(partita,partita.generaPrezzoBiglietto(cliente));
		biglietto = new Biglietto(partita);
	}



	public void operazioniSuFrame()
	{


		stadioCanvas = new StadioCanvas();

		questoFrame.add(stadioCanvas,BorderLayout.CENTER);
		questoFrame.add(creaPannelloInformazioniTransazioni(),BorderLayout.SOUTH);

		questoFrame.setVisible(true);
	}

	public JPanel creaPannelloInformazioniTransazioni()
	{
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		panel.add(creaTextArea(),BorderLayout.WEST);
		panel.add(creaListaDiBottoni(),BorderLayout.EAST);

		return panel;
	}

	public JPanel creaListaDiBottoni()
	{
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(3, 1));
		panel.add(creaBottonePrenota());
		panel.add(creaBottoneAcquista());
		panel.add(creaBottoneAcquistaSubito());

		return panel;
	}

	public JButton creaBottonePrenota()
	{
		JButton button = new JButton("Prenota");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				biglietto.setPosto(stadioCanvas.getSelezione().getPosto());
				if(!controllaStatoPostoDisponobile())
				{
					try {
						throw new PostoIndisponibileException();
					} catch (PostoIndisponibileException e1) {

						JOptionPane.showMessageDialog(null, e1.getMessage());
					}	
				}
				else 
				{

					biglietto.setPrenotazione(true);
					stadioCanvas.getSelezione().getPosto().setStato(Stato.PRENOTATO);
					stadioCanvas.getSelezione().setStato(Stato.PRENOTATO);
					cliente.aggiungiBiglietto(biglietto);
				}



			}
		});
		return button;
	}


	public JButton creaBottoneAcquista()
	{
		JButton button = new JButton("Acquista");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				biglietto.setPosto(stadioCanvas.getSelezione().getPosto());

				if(!controllaStatoPostoPrenotato() || !biglietto.isPrenotazione())
				{
					try {
						throw new PostoNonPrenotatoException();
					} catch (PostoNonPrenotatoException e1) {

						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				else
				{
					biglietto.setAcquisto(true);
					//TO DO
					//devi aggiungere il prezzo del biglietto agli incassi dello stadio
					stadioCanvas.getSelezione().getPosto().setStato(Stato.VENDUTO);
					stadioCanvas.getSelezione().setStato(Stato.VENDUTO);
					cliente.aggiungiBiglietto(biglietto);
				}
			}
		});
		return button;		
	}

	public JButton creaBottoneAcquistaSubito()
	{
		JButton button = new JButton("Acquista Subito");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!controllaStatoPostoDisponobile() || biglietto.isAcquisto() || biglietto.isPrenotazione())
				{
					try {
						throw new PostoNonAcquistabileRapidamente();
					} catch (PostoNonAcquistabileRapidamente e1) {

						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				else
				{
					biglietto.setPrenotazione(true);
					biglietto.setAcquisto(true);
					stadioCanvas.getSelezione().getPosto().setStato(Stato.VENDUTO);
					stadioCanvas.getSelezione().setStato(Stato.VENDUTO);
					cliente.aggiungiBiglietto(biglietto);
				}
			}
		});
		return button;
	}


	public JPanel creaTextArea()
	{
		JPanel panel = new JPanel();

		textArea = new JTextArea(10,55);
		JScrollPane scroll = new JScrollPane(textArea);
		panel.add(scroll);

		return panel;
	}

	public boolean controllaStatoPosto()
	{
		Stato stato = Stato.NON_DISPONIBILE;

		if(stadioCanvas.getSelezione().getPosto().getStato().equals(stato))
		{
			return false;
		}
		return true;
	}
	
	public boolean controllaStatoPostoPrenotato()
	{
		Stato stato = Stato.PRENOTATO;

		if(!stadioCanvas.getSelezione().getPosto().getStato().equals(stato))
		{
			return false;
		}
		return true;
	}
	
	public boolean controllaStatoPostoDisponobile()
	{
		Stato stato = Stato.DISPONIBILE;

		if(!stadioCanvas.getSelezione().getPosto().getStato().equals(stato))
		{
			return false;
		}
		return true;
	}

	private JFrame questoFrame;
	private JTextArea textArea;
	private Partita partita;
	private StadioCanvas stadioCanvas;
	private Biglietto biglietto;
	private Cliente cliente;
}
