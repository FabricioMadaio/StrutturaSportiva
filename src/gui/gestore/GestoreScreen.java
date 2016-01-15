package gui.gestore;

import core.ListaUtenti;
import core.comparator.OrdineCapienzaStadioComparator;
import core.comparator.OrdineCronologicoComparator;
import gui.incassi.*;
import core.elementi.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Comparator;

import javax.swing.*;


import gui.graphics.ScrollablePanelList;
import gui.graphics.Finestra;
import gui.partita.PartitaComponent;
import gui.sconto.ScontoScreen;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * La classe permette di accedere alla schermata di sconti,stadio,incassi,
 * aggiunta partite e permette di scegliere l'ordine in cui visualizzare le partite.
 */
public class GestoreScreen extends Finestra
{
	/**
	 * Il costruttore della classe GestoreScreen inizializza un oggetto Finestra il quale permette 
	 * di accedere alla schermata di sconti,stadio,incassi,aggiunta partite e permette di scegliere
	 * l'ordine in cui visualizzare le partite.
	 * @param JFrame parent
	 * @param ListaUtenti listaUtenti
	 */
	public GestoreScreen(JFrame parent,ListaUtenti listaUtenti)
	{
		super(parent,800,600);
		this.questoFrame = this;
		this.listaUtenti = listaUtenti;
		comparator = new OrdineCronologicoComparator();
		operazioniSuFrame();
	}

	/**
	 * Il metodo si occupa della disposizione e dell'aggiunta dei componenti sul frame.
	 */
	public void operazioniSuFrame()
	{
		//Aggiungo una menu bar
		questoFrame.setJMenuBar(creaMenuBar());
		//Aggiunge il pannello ordinamento
		questoFrame.add(creaPannelloComboBox(),BorderLayout.NORTH);
		//Aggiunge il pannello delle partite
		questoFrame.add(creaPanelloInterattivo(),BorderLayout.CENTER);
		//aggiunge il pannello dove si trova il bottone aggiungi partita
		questoFrame.add(creaPaneelloAggiugiPartita(),BorderLayout.SOUTH);

		questoFrame.setResizable(false);
		questoFrame.setVisible(true);
	}

	/**
	 * Il metodo crea la menu bar dove all'interno ci sono i bottoni che permettono di sceglere
	 * in quale schermata andare ovvero sconti,incassi o stadio
	 * 
	 * @return JMenuBar
	 */
	public JMenuBar creaMenuBar()
	{
		JMenuBar bar = new JMenuBar();
		//Aggiungo i vari bottoni
		bar.add(creaBottoneIncassi());
		bar.add(creaBottoneSconti());
		bar.add(creaBottoneStadio());
		
		return bar;
	}

	/**
	 * Il metodo aggiunge un bottone incassi il quale permette di accedere
	 * alla schermata degli incassi.
	 * @return JButton button
	 */
	public JButton creaBottoneIncassi()
	{
		//Creo il bottone
		JButton btn = new JButton("Incassi");
		//Aggiungo il listener
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Incassi(questoFrame, listaUtenti.getStadi());
				
			}
		});
		
		return btn;
	}

	/**
	 * Il metodo aggiunge un bottone che permette di accede alla schermata sconti.
	 * @return JButton buttton
	 */
	public JButton creaBottoneSconti()
	{
		//Creo il bootne
		JButton btn = new JButton("Sconti");
		//Aggiungo il listener
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ScontoScreen(questoFrame,listaUtenti);
			}
			
		});
		return btn;
	}

	/**
	 * Il mnetodo crea un bottone che permette di accedere alla schermata stadio.
	 * @return JButton button
	 */
	public JButton creaBottoneStadio()
	{
		//Creo il bottone
		JButton btn = new JButton("Stadio");
		//Aggiungo il listener
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new StadioGestore(questoFrame,listaUtenti);
			}
			
		});
		
		return btn;
	}

	/**
	 * Il metodo aggiunge un bottonr che permette di accedere alla schermata 
	 * aggiungi partita.
	 * @return JButton button
	 */
	public JButton creaBottoneAggiungiPartita()
	{
		//Creo il bottone
		JButton btn = new JButton("Aggiungi Partita");
		//Aggiungo il listener
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AggiungiPartitaScreen(questoFrame, listaUtenti);
			}
			
		});
		return btn;
	}

	/**
	 * Il metodo inizializza una combo box e aggiunge un listener che permette di
	 * cambiare l'ordine di visualizzzione delle partite appena noi selezioniamo un
	 * tipo di ordine.
	 * @return JComboBox<String> cmbox
	 */
	public JComboBox<String> creaComboBoxOrdine()
	{
		//Inizializzo la combo box
		comboBox = new JComboBox<String>();
		
		//Aggiungo le stringhe che mi dicono il tipo di ordine
		comboBox.addItem("Ordine per Capienza");
		comboBox.addItem("Ordine Cronologico");
		//Aggiungo il listener
		comboBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				//Appena cambia lo stato cambia controlla se è uguale all'item  selezionato
				 if (e.getStateChange() == ItemEvent.SELECTED) {
			         
					 int index = comboBox.getSelectedIndex();
					 //Faccio in questo modo poiche selected Index mi ritorna un int
			          if(index==0)
			        	  comparator = new OrdineCapienzaStadioComparator();
			          if(index==1)
			        	  comparator = new OrdineCronologicoComparator();
			          //aggiorno la lista
			          updateListaPartite();
			       }
			}
			
		});

		return comboBox;
	}
	/**
	 * Il metodo crea un pannello dove all'interno è presente una label e una comboBox 
	 * che permette la selezione dell'ordine.
	 * @return JPanel panel
	 */
	public JPanel creaPannelloComboBox()
	{
		//Creo il pannello
		JPanel panel = new JPanel();
		//Creo la label
		JLabel gestoreLabel = new JLabel("Benvenuto Gestore!");
		gestoreLabel.setFont(new Font(null, Font.BOLD, 22));
		panel.setLayout(new BorderLayout());
		//Aggiungo il tutto al pannello
		panel.add(gestoreLabel, BorderLayout.WEST);
		panel.add(creaComboBoxOrdine(),BorderLayout.EAST);
		
		return panel;
	}
	
	/**
	 * Il metodo  crea un pannello dove all'interno si trova un bottone che permette di
	 * accedere alla schermata aggiungi partita.
	 * @return JPanel
	 */
	public JPanel creaPaneelloAggiugiPartita()
	{
		//Creo un pannello
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		//Aggiungo il bottone
		panel.add(creaBottoneAggiungiPartita(),BorderLayout.EAST);
		
		return panel;
	}
	
	/**
	 * Il metodo crea una ScrollablePannelList la quale viene subito aggiornata da updateListaPartite
	 * @return ScrollablePanelList
	 */
	public ScrollablePanelList creaPanelloInterattivo()
	{
		//Creo la lista
		scroll = new ScrollablePanelList();
		//Aggiorno la lista
		updateListaPartite();
		
		return scroll;
	}
	/**
	 * Il metodo rimuove tutto poi inizia con l'aggiornamento, ordina le partite aggiunge degli
	 * oggetti partita component alla ScrollablePanelList  poi rivalidifica il contenitore e i suoi
	 * componeti
	 */
	public void updateListaPartite(){
		//rimuove tutto
		scroll.removeAll();
		//Ordina il tutto
		listaUtenti.getPartite().sort(comparator);	
		//Aggiunge le partite
		for(Partita p:listaUtenti.getPartite()){
			PartitaComponent p1 = new PartitaComponent(p);
			scroll.add(p1);
		}
		//rivalidifica il tutto
		validate();
	}

	
	
	@Override
	public void OnReturnFromChild() {
		//La esegue alla chiusura del frame
		updateListaPartite();
	}

	private Comparator<Partita> comparator;

	private ScrollablePanelList scroll;
	private ListaUtenti listaUtenti;
	private JComboBox<String> comboBox;
	private Finestra questoFrame;
}
