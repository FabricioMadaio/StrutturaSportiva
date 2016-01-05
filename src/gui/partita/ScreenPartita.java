package gui.partita;

import core.elementi.Biglietto;
import core.elementi.Partita;
import core.elementi.Posto;
import core.elementi.Posto.Stato;
import core.utente.Cliente;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.sun.corba.se.impl.protocol.BootstrapServerRequestDispatcher;

import gui.graphics.Finestra;
import gui.stadio.Poltroncina;
import gui.stadio.StadioCanvas;
import gui.stadio.StadioListener;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

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

		//Generare ogni voltra un biglietto diverso
		//biglietto = new Biglietto(partita,partita.generaPrezzoBiglietto(cliente));

	}



	public void operazioniSuFrame()
	{

		stadioCanvas = new StadioCanvas();
		stadioCanvas.addStadioListener(new StadioListener(){

			@Override
			public void onSelected(Poltroncina p) {
				// TODO Auto-generated method stub
				aggiornaDettaglioPosto();
			}

			@Override
			public void onReleased(Poltroncina p) {
				// TODO Auto-generated method stub
				
			}
			
		});

		questoFrame.getContentPane().add(stadioCanvas,BorderLayout.CENTER);
		questoFrame.getContentPane().add(creaPannelloInformazioniTransazioni(), BorderLayout.SOUTH);

		questoFrame.setVisible(true);
	}

	public JPanel creaPannelloInformazioniTransazioni()
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(creaTextArea(),BorderLayout.CENTER);
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
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Azioni"));

		return panel;
	}

	public JButton creaBottonePrenota()
	{
		JButton button = new JButton("Prenota");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Biglietto biglietto = new Biglietto(partita,partita.generaPrezzoBiglietto(cliente));
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
					partita.getStadio().aggiungiIncasso(0);
					cliente.aggiungiBiglietto(biglietto);
					stadioCanvas.repaint();
	
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

				Biglietto biglietto = new Biglietto(partita,partita.generaPrezzoBiglietto(cliente));


				biglietto.setPosto(stadioCanvas.getSelezione().getPosto());

				if(!controllaStatoPostoPrenotato())
				{
					try {
						throw new PostoNonPrenotatoException();
					} catch (PostoNonPrenotatoException e1) {

						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				else
				{
					//verifica se la prenotazione appartiene al cliente corrente
					if(!postoDisponibilePerAcquisto()){
						JOptionPane.showMessageDialog(null,"il biglietto è stato prenotato da un altro cliente");
					}else{
						biglietto.setAcquisto(true);
						partita.getStadio().aggiungiIncasso(biglietto.getPrezzo());
						stadioCanvas.getSelezione().getPosto().setStato(Stato.VENDUTO);
						stadioCanvas.getSelezione().setStato(Stato.VENDUTO);
						cliente.aggiungiBiglietto(biglietto);
						stadioCanvas.repaint();
					}
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
				Biglietto biglietto = new Biglietto(partita,partita.generaPrezzoBiglietto(cliente));
				biglietto.setPosto(stadioCanvas.getSelezione().getPosto());

				if(!controllaStatoPostoDisponobile() )
				{
					try {
						throw new PostoNonAcquistabileRapidamente();
					} catch (PostoNonAcquistabileRapidamente e1) {

						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
				else
				{
					
					partita.getStadio().aggiungiIncasso(biglietto.getPrezzo());
					biglietto.setPrenotazione(true);
					biglietto.setAcquisto(true);
					stadioCanvas.getSelezione().getPosto().setStato(Stato.VENDUTO);
					stadioCanvas.getSelezione().setStato(Stato.VENDUTO);
					cliente.aggiungiBiglietto(biglietto);
					stadioCanvas.repaint();
				}
			}
		});
		return button;
	}


	public JPanel creaTextArea()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		JLabel labelPrezzo = new JLabel("Prezzo Biglietto:"+partita.generaPrezzoBiglietto(cliente)+"€");
		panel.add(labelPrezzo, BorderLayout.WEST);
		labelPrezzo.setFont(new Font(null, Font.BOLD, 18));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 5, 0, 5));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Partita", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_2);
		textArea = new JTextArea(2,5);
		textArea.setEditable(false);
		textArea.append(partita.getInfo());
		panel_2.setLayout(new BorderLayout(0, 0));
		JScrollPane scroll = new JScrollPane(textArea);
		panel_2.add(scroll);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Posto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		textAreaPosto = new JTextArea();
		textAreaPosto.setEditable(false);
		textAreaPosto.setRows(2);
		textAreaPosto.setColumns(5);
		
		JScrollPane scrollPane = new JScrollPane(textAreaPosto);
		panel_3.add(scrollPane);



		panel.setBorder(new TitledBorder(new EtchedBorder(),"Info Partita"));

		return panel;
	}

	public void aggiornaDettaglioPosto(){
		
		textAreaPosto.setText("");
		textAreaPosto.append(stadioCanvas.getSelezione().getPosto().getSigla()+"\n");
		if(postoDisponibilePerAcquisto()){
			textAreaPosto.append("biglietto prenotato da questo cliente");
		}else{
			textAreaPosto.append("biglietto non prenotato da questo cliente");
		}
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

		if(!stadioCanvas.getSelezione().getPosto().getStato().equals(stato) )
		{
			return false;
		}
		return true;
	}
	
	public boolean postoDisponibilePerAcquisto(){
		
		for(Biglietto b:cliente.getBiglietti()){
			
			if(b.getPartita().equals(partita))
			if(b.getPosto().equals(stadioCanvas.getSelezione().getPosto()))
				return true;
		}
		
		return false;
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
	private JTextArea textAreaPosto;
	private JTextArea textArea;
	private Partita partita;
	private StadioCanvas stadioCanvas;
	private Cliente cliente;

}
