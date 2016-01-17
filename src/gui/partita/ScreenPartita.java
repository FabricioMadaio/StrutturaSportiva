package gui.partita;

import core.elementi.Biglietto;
import core.elementi.Partita;
import core.elementi.Posto;
import core.elementi.Posto.Stato;
import core.sconti.Sconto;
import core.utente.Cliente;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import gui.graphics.Finestra;
import gui.stadio.Poltroncina;
import gui.stadio.StadioCanvas;
import gui.stadio.StadioListener;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * ScreenPartita:
 * 	schermata partita di cliente, consente al cliente di prenotare o acquistare un biglietto
 *  di una determinata partita
 */
public class ScreenPartita extends Finestra
{
	/**
	 * costruttore:
	 * 	crea una nuova finestra 800x700 e carica i dati della partita in stadioCanvas
	 * @param parent
	 * @param partita	
	 * @param cliente
	 */
	public ScreenPartita(JFrame parent,Partita partita,Cliente cliente) 
	{
		super(parent, 900, 700);
		this.partita = partita;
		questoFrame = this;
		this.cliente = cliente;
		
		//aggiungiamo i pulsanti con le operazioni
		operazioniSuFrame();
		
		
		stadioCanvas.setPosti(partita.getPosti());
		stadioCanvas.setBackgroundImage(partita.getStadio().getImage());

		//Generare ogni voltra un biglietto diverso
		//biglietto = new Biglietto(partita,partita.generaPrezzoBiglietto(cliente));

	}



	/**
	 * inserisce i componenti di input nella schermata
	 */
	public void operazioniSuFrame()
	{

		stadioCanvas = new StadioCanvas();
		stadioCanvas.addStadioListener(new StadioListener(){

			@Override
			public void onSelected(Poltroncina p) {
				//carico nella schermata i dettagli del posto selezionato
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

	/**
	 * @return pannello operazioni sul posto selezionato
	 */
	public JPanel creaPannelloInformazioniTransazioni()
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(creaTextArea(),BorderLayout.CENTER);
		panel.add(creaListaDiBottoni(),BorderLayout.EAST);
		return panel;
	}

	/**
	 * @return pannello con i pulsanti per le operazioni sul posto selezionato
	 */
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

	/**
	 * @return pulsante prenota
	 */
	public JButton creaBottonePrenota()
	{
		JButton button = new JButton("Prenota");
		//aggiungo il listener
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//prendo la poltroncina selezionata
				Poltroncina selezione = stadioCanvas.getSelezione();
				
				if(selezione!=null){
					
					//creo un nuovo biglietto
					Biglietto biglietto = new Biglietto(partita,partita.generaPrezzoBiglietto(cliente));
					biglietto.setPosto(selezione.getPosto());
					
					//controlla se il posto è disponibile per la prenotazione
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
						//setto il biglietto come prenotazione
						biglietto.setPrenotazione(true);
						
						//verifico che il biglietto non sia scaduto
						if(biglietto.verificaPrenotazioneScaduta(new GregorianCalendar())){
							JOptionPane.showMessageDialog(null,"il tempo per le prenotazioni è finito");
						}else{
							//aggiorno lo stato del posto
							selezione.setStato(Stato.PRENOTATO);
							//aggiungo il biglietto al cliente
							cliente.aggiungiBiglietto(biglietto);
							//aggiorno stadioCanvas
							stadioCanvas.repaint();
						}
		
					}


			   }
			}
		});
		return button;
	}


	/**
	 * @return pulsante acquista
	 */
	public JButton creaBottoneAcquista()
	{
		JButton button = new JButton("Acquista");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//prendo la poltroncina selezionata
				Poltroncina selezione = stadioCanvas.getSelezione();
				
				if(selezione!=null){
					//creo un nuovo biglietto
					Biglietto biglietto = new Biglietto(partita,partita.generaPrezzoBiglietto(cliente));
					biglietto.setPosto(selezione.getPosto());
	
					//verifico se il posto è stato prenotato (deve essere prenotato per forza per essere acquistato)
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
							//controllo che il biglietto non sia scaduto
							if(biglietto.verificaAcquistoScaduto(new GregorianCalendar())){
								JOptionPane.showMessageDialog(null,"la partita si è gia conclusa");
							}else{
								//aggiorno lo stato del biglietto
								biglietto.setAcquisto(true);
								//aggiungo il prezzo del biglietto come incasso dello stadio
								partita.getStadio().aggiungiIncasso(biglietto.getPrezzo());
								//aggiorno lo stato del posto
								selezione.setStato(Stato.VENDUTO);
								//aggiungo biglietto al cliente
								cliente.aggiungiBiglietto(biglietto);
								stadioCanvas.repaint();
							}
						}
					}
				}
			}
		});
		return button;		
	}

	/**
	 * @return bottone acquista subito
	 */
	public JButton creaBottoneAcquistaSubito()
	{
		JButton button = new JButton("Acquista Subito");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				//prendo la poltroncina selezionata
				Poltroncina selezione = stadioCanvas.getSelezione();
				
				if(selezione!=null){
					
					//creo un nuovo biglietto
					Biglietto biglietto = new Biglietto(partita,partita.generaPrezzoBiglietto(cliente));
					biglietto.setPosto(selezione.getPosto());
	
					//verifico se il posto è disponibile per l'acquisto (non può essere gia prenotato)
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
						//verifico se l'acquisto non è scaduto
						if(biglietto.verificaAcquistoScaduto(new GregorianCalendar())){
							JOptionPane.showMessageDialog(null,"la partita si è gia conclusa");
						}else{
							//aggiungo il prezzo del biglietto come incasso per lo stadio
							partita.getStadio().aggiungiIncasso(biglietto.getPrezzo());
							
							//aggiorno il biglietto
							biglietto.setPrenotazione(true);
							biglietto.setAcquisto(true);
							//aggiorno lo stato del posto
							selezione.setStato(Stato.VENDUTO);
							//aggiungo il biglietto al cliente
							cliente.aggiungiBiglietto(biglietto);
							stadioCanvas.repaint();
						}
					}
				}
			}
		});
		return button;
	}


	/**
	 * @return pannello con dettagli sulla partita (descrizione, prezzo e sconti applicati)
	 */
	public JPanel creaTextArea()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));

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
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(0, 5, 0, 5));
		panel_4.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		String prezzo = String.format("%.2f",partita.generaPrezzoBiglietto(cliente));
		
		JLabel labelPrezzo = new JLabel("Prezzo Biglietto:"+prezzo+"€");
		panel_5.add(labelPrezzo);
		labelPrezzo.setFont(new Font(null, Font.BOLD, 18));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), "Sconti:", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_4.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		
		JTextArea scontiTextArea = new JTextArea();
		scontiTextArea.setBackground(SystemColor.menu);
		scontiTextArea.setEditable(false);
		scontiTextArea.setTabSize(1);
		scontiTextArea.setColumns(21);
		scontiTextArea.setRows(3);
		
		scontiTextArea.append("Prezzo base: "+partita.getStadio().getPrezzoBase()+"\n");
		
		//aggiungo la lista degli sconti per la partita
		for(Sconto s:partita.getSconti())
		scontiTextArea.append(s.toString()+"\n");
		
		JScrollPane scontiScrollPane = new JScrollPane(scontiTextArea);
		panel_6.add(scontiScrollPane);

		return panel;
	}

	/**
	 * aggiorna la schermata con i dettagli del posto selezionato su stadioCanvas
	 */
	public void aggiornaDettaglioPosto(){
		
		Poltroncina selezione = stadioCanvas.getSelezione();
		
		if(selezione!=null){
			textAreaPosto.setText("Posto: ");
			textAreaPosto.append(stadioCanvas.getSelezione().getPosto().getSigla()+"\n");
			if(postoDisponibilePerAcquisto()){
				Posto.Stato stato = stadioCanvas.getSelezione().getPosto().getStato();
				if(stato.equals(Posto.Stato.PRENOTATO))
					textAreaPosto.append("posto già prenotato da te");
				if(stato.equals(Posto.Stato.VENDUTO))
					textAreaPosto.append("posto già acquistato da te");
			}
		}
	}
	
	/**
	 * @return true se il posto selezionato è disponibile
	 */
	public boolean controllaStatoPosto()
	{
		Stato stato = Stato.NON_DISPONIBILE;

		if(stadioCanvas.getSelezione().getPosto().getStato().equals(stato))
		{
			return false;
		}
		return true;
	}

	/**
	 * @return  true se il posto selezionato è stato prenotato
	 */
	public boolean controllaStatoPostoPrenotato()
	{
		Stato stato = Stato.PRENOTATO;

		if(!stadioCanvas.getSelezione().getPosto().getStato().equals(stato) )
		{
			return false;
		}
		return true;
	}
	
	/**
	 * @return true se il posto selezionato è stato acquistato dal cliente attuale
	 */
	public boolean postoDisponibilePerAcquisto(){
		
		//cerco se tra i biglietti del cliente (per questa partita) il posto selezionato è presente
		for(Biglietto b:cliente.getBiglietti()){
			
			if(b.getPartita().equals(partita))
			if(b.getPosto().equals(stadioCanvas.getSelezione().getPosto()))
				return true;
		}
		
		return false;
	}

	/**
	 * @return true se il posto selezionato è diverso da disponibile
	 */
	public boolean controllaStatoPostoDisponobile()
	{
		Stato stato = Stato.DISPONIBILE;

		if(!stadioCanvas.getSelezione().getPosto().getStato().equals(stato))
		{
			return false;
		}
		return true;
	}
	
	/**
	 * 	imposta il selettore posto con il posto del biglietto
	 */
	public void caricaSelezione(Biglietto b){ 
		stadioCanvas.setSelezione(b.getPosto());
		aggiornaDettaglioPosto();
	}
	

	private JFrame questoFrame;
	private JTextArea textAreaPosto;
	private JTextArea textArea;
	private Partita partita;
	private StadioCanvas stadioCanvas;
	private Cliente cliente;

}
