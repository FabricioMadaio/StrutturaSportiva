package gui.gestore;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import core.ListaUtenti;
import core.elementi.Posto.Stato;
import core.elementi.Stadio;
import gui.graphics.Finestra;
import gui.stadio.Poltroncina;
import gui.stadio.StadioCanvas;
import gui.stadio.StadioListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.border.EmptyBorder;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13
 * 
 * La classe  permette di effettuare modicheallo stadio
 * come aggiungere posti cancellarli e modicarne lo stato.
 */
public class StadioGestore extends Finestra
{
	/**
	 * Il costruttore della classe StadioGestore inizializza un ogetto Finestra pendend come ferimento 
	 * un frame genitoe e una lista utenti.
	 * Questo ogetto permette di Aggiungere posti cancellarli e modicarne lo stato.
	 * @param JFrame parent
	 * @param ListaUtenti listaUtenti
	 */
	public StadioGestore(JFrame parent,ListaUtenti listaUtenti)
	{
		super(parent,800, 600);
		this.listaUtenti = listaUtenti;
		questoFrame = this;
		operazioniSuFrame();

		caricaStadio(listaUtenti.getStadi().get(0));
		stadioCanvas.setModalita(StadioCanvas.editMode.MODIFICA);
	}

	/**
	 * Il metodo serve a caricare lo stadio.
	 * @param Stadio s
	 */
	public void caricaStadio(Stadio s)
	{
		if(s!=null){
			stadioCorrente = s;
			costoFieldStadio.setText(""+s.getPrezzoBase());
			stadioCanvas.setPosti(s.getPosti());
			stadioCanvas.setBackgroundImage(s.getImage());
			stadioCanvas.repaint();
		}
	}
	//interfaccia grafica

	/**
	 * Il metodo si occupa della disposizione su frame dei pannelli e del loro inserimento 
	 */
	public void operazioniSuFrame()
	{

		questoFrame.getContentPane().add(creaPannelloCanvas(),BorderLayout.CENTER);
		questoFrame.getContentPane().add(creaPanelloPrincipale(), BorderLayout.SOUTH);


		questoFrame.setVisible(true);
	}

	/**
	 * Il metodo crea un pannello dove viene aggiunto un oggetto stadio canvas e viene
	 * gestita la selezione.
	 * @return JPanel
	 */
	public JPanel creaPannelloCanvas()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		stadioCanvas = new StadioCanvas();
		stadioCanvas.addStadioListener(new StadioListener(){

			@Override
			public void onSelected(Poltroncina p) {
				//funzione eseguita alla selezione
				aggiornaSelezione(true);
			}

			@Override
			public void onReleased(Poltroncina p) {
				// TODO Auto-generated method stub
				aggiornaSelezione(false);
			}

		});

		panel.add(stadioCanvas,BorderLayout.CENTER);

		return panel;
	}
	/**
	 * Il metodo crea un pannello dove sono allocati il pannello di settaggio costo e modalit‡.
	 * @return JPanel panel
	 */
	public JPanel creaPanelloPrincipale()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(creaPannelloSettaggioStadio());
		panel.add(creaPannelloModalita());

		return panel;
	}

	/**
	 * questo pannello serve a cambiare lo stadio visualizzato e settare il prezzo
	 * @return pannello stadio
	 */
	/**
	 * @return
	 */
	public JPanel creaPannelloSettaggioStadio()
	{
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 5, 10));
		JLabel labelStadio = new JLabel("Stadio");
		panel.setLayout(new GridLayout(3, 2, 10, 10));

		panel.add(labelStadio);

		//creo una combobox di oggetti di tipo stadio
		comboBoxStadio = new JComboBox<Stadio>();
		//carico tutti gli stadi nella combo box
		for(Stadio s:listaUtenti.getStadi()){
			comboBoxStadio.addItem(s);
		}

		//modifico la visualizzazione grafica della combobox e visualizzo solo il nome dello stadio
		//per ogni voce
		comboBoxStadio.setRenderer( new ListCellRenderer<Stadio>(){

			/* 
			 * questo metodo modifica la visualizzazione del singolo elemento della combobox
			 * JList<? extends Stadio> prende la lista di oggetti generici che estendono Stadio
			 */
			@Override
			public Component getListCellRendererComponent(JList<? extends Stadio> list, Stadio value, int index,
					boolean isSelected, boolean cellHasFocus) {
				//creo una label con il nome dello stadio
				JLabel nome = new JLabel(value.getNome());
				return nome;
			}

		});
		
		/* 
		 * implemento un listener di tipo ItemListener, che reagisce al cambiamento
		 * della selezione dentro una combobox
		 */
		comboBoxStadio.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// carica lo stadio selezionato
				caricaStadio((Stadio)e.getItem());
			}

		});

		panel.add(comboBoxStadio);

		JLabel labelCosto = new JLabel("Costo Biglietto");
		panel.add(labelCosto);
		costoFieldStadio = new JTextField(10);
		panel.add(costoFieldStadio);

		/*aggiungo un listener per il textfield, lancio la funzione update ogni volta che si
		 *modifica il testo
		 */
		costoFieldStadio.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				update();
			}
			public void removeUpdate(DocumentEvent e) {
				update();
			}
			public void insertUpdate(DocumentEvent e) {
				update();
			}
			
			/**
			 * setta un prezzo base nuovo
			 */
			public void update() {
				if(stadioCorrente!=null && !costoFieldStadio.getText().isEmpty()){
					stadioCorrente.setPrezzoBase(Double.parseDouble(costoFieldStadio.getText()));
				}
			}
		});

		return panel;
	}
	
	
	/**
	 * Il metodo crea un pannello dove all'interno ci sono il pannello di selezione e il pannello
	 * di scelta di modalita. 
	 * @return
	 */
	public JPanel creaPannelloModalita()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		pannelloSelezione = creaPannelloSelezione();
		panel.add(pannelloSelezione);
		panel.add(creaPannelloBottoni());

		return panel;
	}
	
	/**
	 * questo metodo restituisce un pannello che contiene gli oggetti per modificare
	 * lo stato del posto selezionato
	 * @return pannello 
	 */
	public JPanel creaPannelloSelezione()
	{
		JPanel panel = new JPanel();
		GridLayout gl_panel = new GridLayout(3, 2);
		gl_panel.setHgap(4);
		gl_panel.setVgap(4);
		panel.setLayout(gl_panel);

		JLabel disponibilit‡Label = new JLabel("Disponibilit‡");
		JLabel nomeLabel = new JLabel("Nome");

		nomeFieldPosto = new JTextField();
		comboBoxStatoPosto = creaComboBox();

		panel.add(disponibilit‡Label);
		panel.add(comboBoxStatoPosto);
		panel.add(nomeLabel);
		panel.add(nomeFieldPosto);

		panel.setBorder(new TitledBorder(new EtchedBorder(),"Selezione"));
		btnCancellaPoltrona = new JButton("Cancella Poltrona");
		panel.add(btnCancellaPoltrona);

		abilitaPanel(panel,false);

		//setto i listeners
		comboBoxStatoPosto.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// modifico lo stato del posto selezionato
				stadioCanvas.getSelezione().setStato((Stato)e.getItem());
				stadioCanvas.repaint();
			}

		});

		/*aggiungo un listener per il textfield, lancio la funzione update ogni volta che si
		 *modifica il testo
		 */
		nomeFieldPosto.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				update();
			}
			public void removeUpdate(DocumentEvent e) {
				update();
			}
			public void insertUpdate(DocumentEvent e) {
				update();
			}

			public void update() {
				// aggiorno il nome del posto selezionato
				if(!nomeFieldPosto.getText().isEmpty()){
					stadioCanvas.getSelezione().getPosto().setSigla(nomeFieldPosto.getText());
				}
			}
		});

		//listener bottone cancella posto
		btnCancellaPoltrona.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// cancello il posto
				stadioCanvas.destroySelezione();
				stadioCanvas.repaint();
			}

		});

		return panel;
	}

	/**
	 * Il metodo crea un pannello dove all'interno ci sono dei toggleButton
	 * che permettono di modificare e aggiungere poltroncine.
	 * @return
	 */
	public JPanel creaPannelloBottoni()
	{
		//Creo il pannello
		JPanel panel = new JPanel();
		TitledBorder b = new TitledBorder(new EtchedBorder(),"Modalit‡ Editor");
		panel.setBorder(b);

		panel.setLayout(new GridLayout(3, 1));
		//Aggiungo i bottoni
		buttonAggiungi = new JToggleButton("Aggiungi");
		buttonModifica = new JToggleButton("Modifica");
		buttonModifica.setSelected(true);

		panel.add(buttonAggiungi);
		panel.add(buttonModifica);


		//setto i listeners

		buttonAggiungi.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stadioCanvas.setModalita(StadioCanvas.editMode.AGGIUNGI);
				buttonAggiungi.setSelected(true);
				buttonModifica.setSelected(false);
			}

		});

		buttonModifica.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stadioCanvas.setModalita(StadioCanvas.editMode.MODIFICA);
				buttonAggiungi.setSelected(false);
				buttonModifica.setSelected(true);
			}

		});

		return panel;
	}


	/**
	 * Il metodo crea una combo box che permette di settare lo stato delle poltroncine
	 * @return JComboBox<Stato> cmBox
	 */
	public JComboBox<Stato> creaComboBox()
	{
		JComboBox<Stato> comboBox = new JComboBox<Stato>();

		comboBox.addItem(Stato.DISPONIBILE);
		comboBox.addItem(Stato.NON_DISPONIBILE);
		comboBox.addItem(Stato.PRENOTATO);
		comboBox.addItem(Stato.VENDUTO);

		return comboBox;
	}


	
	/**
	 * abilita un panel e i suoi figli sottostanti
	 * @param panel
	 * @param enabled 
	 */
	public void abilitaPanel(JPanel panel, boolean enabled){
		panel.setEnabled(enabled);
		
		//prendo i figli del panel
		Component[] comps = panel.getComponents();
		//scorro i figli e li setto a enabled
		for (Component comp:comps){
			comp.setEnabled(enabled);
		}
	}

	/**
	 * aggiorna i campi della schermata con i dati del posto selezionato
	 * @param enabled
	 */
	public void aggiornaSelezione(boolean enabled){
		comboBoxStatoPosto.setSelectedItem(stadioCanvas.getSelezione().getStato());
		nomeFieldPosto.setText(stadioCanvas.getSelezione().getPosto().getSigla());
		abilitaPanel(pannelloSelezione,enabled);
	}

	private JFrame questoFrame;

	//info stadio
	private JTextField costoFieldStadio;
	private JComboBox<Stadio> comboBoxStadio;

	//info poltroncina
	private JPanel pannelloSelezione;
	private JComboBox<Stato> comboBoxStatoPosto;
	private JTextField nomeFieldPosto;
	private JButton btnCancellaPoltrona;

	//modalita editor
	private JToggleButton buttonAggiungi;
	private JToggleButton buttonModifica;

	private ListaUtenti listaUtenti;

	private StadioCanvas stadioCanvas;
	private Stadio stadioCorrente;

}
