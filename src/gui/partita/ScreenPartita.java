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

public class ScreenPartita extends Finestra
{
	public ScreenPartita(JFrame parent,Partita partita,Cliente cliente) 
	{
		super(parent, 800, 700);
		this.partita = partita;
		questoFrame = this;
		this.cliente = cliente;
		operazioniSuFrame();
		
		
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
				
				Poltroncina selezione = stadioCanvas.getSelezione();
				
				if(selezione!=null){
					Biglietto biglietto = new Biglietto(partita,partita.generaPrezzoBiglietto(cliente));
					
					biglietto.setPosto(selezione.getPosto());
					
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
						selezione.getPosto().setStato(Stato.PRENOTATO);
						selezione.setStato(Stato.PRENOTATO);
						partita.getStadio().aggiungiIncasso(0);
						cliente.aggiungiBiglietto(biglietto);
						stadioCanvas.repaint();
		
					}


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
				
				Poltroncina selezione = stadioCanvas.getSelezione();
				
				if(selezione!=null){
					Biglietto biglietto = new Biglietto(partita,partita.generaPrezzoBiglietto(cliente));
					biglietto.setPosto(selezione.getPosto());
	
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
							selezione.getPosto().setStato(Stato.VENDUTO);
							selezione.setStato(Stato.VENDUTO);
							cliente.aggiungiBiglietto(biglietto);
							stadioCanvas.repaint();
						}
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
				
				Poltroncina selezione = stadioCanvas.getSelezione();
				
				if(selezione!=null){
					
					Biglietto biglietto = new Biglietto(partita,partita.generaPrezzoBiglietto(cliente));
					biglietto.setPosto(selezione.getPosto());
	
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
						selezione.getPosto().setStato(Stato.VENDUTO);
						selezione.setStato(Stato.VENDUTO);
						cliente.aggiungiBiglietto(biglietto);
						stadioCanvas.repaint();
					}
				}
			}
		});
		return button;
	}


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
		
		for(Sconto s:partita.getSconti())
		scontiTextArea.append(s.toString()+"\n");
		
		JScrollPane scontiScrollPane = new JScrollPane(scontiTextArea);
		panel_6.add(scontiScrollPane);

		return panel;
	}

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
