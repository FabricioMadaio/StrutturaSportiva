package gui.carrello;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import core.elementi.Biglietto;
import core.utente.Cliente;
import gui.graphics.Finestra;
import gui.graphics.ScrollablePanelList;
import java.awt.GridLayout;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * La classe mostra i dati relativi agli acquisti e alle prenotazioni.
 */
public class Carrello extends Finestra
{
	/**
	 * Il costruttore della Classe Carrello inizializza un oggetto del tipo finestra il quale serve
	 *prende come riferimento un frame genitore e un cliente e permette di visionare un frame
	 *dove sono presenti le prenotazioni del cliente e gli acquisti da lui effettuati.
	 * @param JFrame parent
	 * @param Cliente c
	 */
	public Carrello(JFrame parent,Cliente c)
	{
		super(parent,800,600);
		questoFrame = this;
		cliente = c;
		operazioniSuFrame();

	}

	/**
	 * Il metodo si occupa dell'inserimento e della disposizione sul frame dei vari pannelli.
	 */
	public void  operazioniSuFrame()
	{
		//Do il titolo al frame
		questoFrame.setTitle("Carrello");
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		//Agiiungo i pannelli al frame
		questoFrame.getContentPane().add(creaPannelloPrenotazioni());
		questoFrame.getContentPane().add(creaPannnelloAquistiEffettuati());
		//aggiorno la lista
		aggiornaListe();

		questoFrame.setVisible(true);
	}

	/**
	 * Il metodo crea un pannello dove è possibile vedere una serie di prenotazioni.
	 * @return JPanel panel
	 */
	public JPanel creaPannelloPrenotazioni()
	{
		//Creo un pannello
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		//Aggiungo un label
		JLabel prenotazioniLabel = new JLabel("Prenotazioni");
		prenotazioniLabel.setFont(new Font(null, Font.BOLD, 22));
		panel.add(prenotazioniLabel,BorderLayout.NORTH);
		//Aggiungo una ScrollablePanelList che mi permette di rimuovere le prenotazioni
		panel.add(pannelloVisualizzazioneRimozione(), BorderLayout.CENTER);
		panel.setBorder(new EtchedBorder());
		return panel;
	}

	/**
	 * Il metodo inizializza una ScrollablePanelList.
	 * @return ScrollablePanelList list
	 */
	public ScrollablePanelList pannelloVisualizzazioneRimozione()
	{
		prenotazioni = new ScrollablePanelList();
		return prenotazioni;
	}
	
	/**
	 * Il metodo crea un pannello dove è possibile visualizzare gli acquisti effettuati.
	 * @return JPanel panel
	 */
	public JPanel creaPannnelloAquistiEffettuati()
	{
		//Creo un pannello
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		//Creo una label
		JLabel acquistiLabel = new JLabel("Acquisti Effettuati");
		acquistiLabel.setFont(new Font(null, Font.BOLD, 22));
		//Aggiungo una label e una lista acquisti
		panel.add(acquistiLabel,BorderLayout.NORTH);
		panel.add(creaListaAcquisti(),BorderLayout.CENTER);

		panel.setBorder(new EtchedBorder());
		return panel;
	}

	/**
	 * Il metodo inizializza una ScrollablePanelList dove verranno inseriti gli acquisti.
	 * @return ScrollablePanelList list
	 */
	public ScrollablePanelList creaListaAcquisti()
	{
		acquisti = new ScrollablePanelList();
		acquisti.setBorder(new EtchedBorder());

		return acquisti;
	}

	/**
	 * Il metodo per prima cosa rimuove tutto quallo che c'è poi verifica che le prenotazioni siano ancora attive.
	 * Scorre i biglietti degli utenti e ne controlla lo stato se sono prenotati li aggiunge alla lista prenotazioni
	 * se sono acquistati alla lista di acquisti.Viene chiamato revalidate per rendere valida la gerarchia e poi repaint 
	 * per riempiere il tutto.
	 */
	public void aggiornaListe(){

		//Rimuove tutto
		acquisti.removeAll();
		prenotazioni.removeAll();
		//Verifica le prenotazioni
		cliente.verificaScadenze();
		//Inserisci gli acquisti e le prenotazioni
		for(Biglietto b:cliente.getBiglietti())
			if(b.isAcquisto())
				acquisti.add(new BigliettoComponent(b));
			else
			{
				prenotazioni.add(new PrenotazioneComponent(b,(Carrello)questoFrame));
			}	
		//Ripristina la gerarchia del componet eliminata dai removeAll
		revalidate();
		//Aggiorna il tutto disegnado quello che manca
		repaint();
	}



	@Override
	public void OnReturnFromChild() {
		// Alla chiusura della finistra aggiorna le liste
		aggiornaListe();
	}

	/**
	 * Il metodo ritorna il cliente.
	 * @return Cliente cliente
	 */
	public Cliente getCliente(){
		return cliente;
	}

	private Cliente cliente;

	private ScrollablePanelList acquisti;
	private ScrollablePanelList prenotazioni;

	private Finestra questoFrame;
}
