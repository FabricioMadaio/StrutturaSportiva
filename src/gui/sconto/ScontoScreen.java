package gui.sconto;

import javax.swing.JFrame;

import core.ListaUtenti;
import core.elementi.Partita;
import core.sconti.Sconto;
import gui.graphics.Finestra;
import gui.graphics.ScrollablePanelList;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTabbedPane;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * ScontoScreen:
 * 	Schermata per visualizzare e inserire nuovi sconti, sia globali e sia per partita
 */
public class ScontoScreen extends Finestra{
	
	
	/**
	 * costruttore 
	 * 	crea una finestra 800x600 e inserisce gli sconti in due tab (uno per gli sconti globali
	 *  e uno per gli sconti per partita)
	 *  	
	 * @param parent
	 * @param listautenti
	 */
	public ScontoScreen(JFrame parent, ListaUtenti listautenti) {

		super(parent, 800, 600);
		setTitle("Sconti");
		questoFrame = this;
		this.listaUtenti = listautenti;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {784};
		gridBagLayout.rowHeights = new int[] {0, 60};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0};
		getContentPane().setLayout(gridBagLayout);

		//creo il pannello per i tab
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		getContentPane().add(tabbedPane, gbc_tabbedPane);

		//creo il pannello per gli sconti globali e ci passo una scrollList
		scrollListGlobal = new ScrollablePanelList();
		JPanel panelScontiGlobali = new JPanel();
		tabbedPane.addTab("Sconti Globali", null, panelScontiGlobali, null);
		panelScontiGlobali.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelScontiGlobali.setLayout(new BorderLayout(0, 0));
		panelScontiGlobali.add(scrollListGlobal);

		//creo il pannello per gli sconti per partita e ci passo una scrollList
		scrollListPartita = new ScrollablePanelList();
		JPanel panelScontiPerPartita = new JPanel();
		panelScontiPerPartita.setBorder(new EmptyBorder(10, 10, 10, 10));
		tabbedPane.addTab("Sconti per partita", null, panelScontiPerPartita, null);
		panelScontiPerPartita.setLayout(new BorderLayout(0, 0));
		panelScontiPerPartita.add(scrollListPartita);

		JPanel panelloBarraInserimento = new JPanel();
		GridBagConstraints gbc_panelloBarraInserimento = new GridBagConstraints();
		gbc_panelloBarraInserimento.insets = new Insets(0, 0, 5, 0);
		gbc_panelloBarraInserimento.fill = GridBagConstraints.BOTH;
		gbc_panelloBarraInserimento.gridx = 0;
		gbc_panelloBarraInserimento.gridy = 1;
		getContentPane().add(panelloBarraInserimento, gbc_panelloBarraInserimento);
		panelloBarraInserimento.setLayout(new BorderLayout(0, 0));

		JPanel panelloTasti = new JPanel();
		panelloTasti.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Aggiungi Sconto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelloBarraInserimento.add(panelloTasti);
		panelloTasti.setLayout(new GridLayout(1, 4, 10, 0));

		JPanel panelPartita = new JPanel();
		panelloTasti.add(panelPartita);
		panelPartita.setLayout(new BorderLayout(0, 0));

		JButton btnPartita = new JButton("Partita");
		btnPartita.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//apro la schermata di inserimento sconto per partita
				new ScontoPerPartitaGui(questoFrame, listaUtenti);

			}
		});
		panelPartita.add(btnPartita, BorderLayout.CENTER);

		JPanel panelFasciaOraria = new JPanel();
		panelloTasti.add(panelFasciaOraria);
		panelFasciaOraria.setLayout(new BorderLayout(0, 0));

		JButton btnFasciaOraria = new JButton("Fascia Oraria");
		btnFasciaOraria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//apro la schermata di inserimento sconto per fascia oraria
				new ScontoFasciaOrarariaGui(questoFrame, listaUtenti);

			}
		});
		panelFasciaOraria.add(btnFasciaOraria);


		JPanel panelGiornoSettimana = new JPanel();
		panelloTasti.add(panelGiornoSettimana);
		panelGiornoSettimana.setLayout(new BorderLayout(0, 0));

		btnGiornoSettimana = new JButton("Giorno Settimana");
		panelGiornoSettimana.add(btnGiornoSettimana);

		btnGiornoSettimana.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//apro la schermata di inserimento sconto per giorno della settimana
				new ScontoGiornoDellaSettimanaGui(questoFrame,listaUtenti);

			}
		});

		JPanel panelCliente = new JPanel();
		panelloTasti.add(panelCliente);
		panelCliente.setLayout(new BorderLayout(0, 0));

		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//apro la schermata di inserimento sconto per categoria di cliente
				new ScontoCategoriaClienteGui(questoFrame,listaUtenti);

			}
		});
		panelCliente.add(btnCliente);


		// aggiungo gli sconti
		updateSconti();
		
		this.setVisible(true);
	}
	
	/**
	 * aggiorna gli sconti visualizzati in entrambe le liste (sconti globali e sconti per partita)
	 */
	public void updateSconti(){
		
		//cancello tutte le voci esistenti nelle scrollList
		scrollListPartita.removeAll();
		scrollListGlobal.removeAll();
		
		//carico gli sconti per partita
		for(Partita p:listaUtenti.getPartite()){
			scrollListPartita.add(new PartitaTitleComponent(p));
			for(Sconto s:p.getSconti()){
				//aggiungo solo quelli specifici della partita?
				//if(s instanceof ScontoPerPartita)
				scrollListPartita.add(new ScontoComponent(s));
			}
		}

		//carico gli sconti globali
		for(Sconto s:listaUtenti.getScontiGlobali()){
			scrollListGlobal.add(new ScontoComponent(s));
		}

		//aggiorno la grafica
		this.revalidate();
	}

	/* 
	 * al ritorno da una schermata figlia, ricarico i campi delle liste
	 */
	@Override
	public void OnReturnFromChild() {
		// TODO Auto-generated method stub
		updateSconti();
	}

	private JButton btnGiornoSettimana;
	private Finestra questoFrame;
	private ListaUtenti listaUtenti;
	private ScrollablePanelList scrollListPartita;
	private ScrollablePanelList scrollListGlobal;
}
