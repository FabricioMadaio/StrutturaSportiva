package gui.cliente;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import core.ListaUtenti;
import core.comparator.OrdineCapienzaStadioComparator;
import core.comparator.OrdineCronologicoComparator;
import core.comparator.OrdineIdStadioComparator;
import core.comparator.OrdineLessicoGraficoComparatorNomeSquadre;
import core.elementi.Partita;
import core.elementi.Stadio;
import core.filtri.FiltroPartitaPerSettimana;
import core.filtri.FiltroPartitaSuStadio;
import core.utente.Cliente;
import gui.carrello.Carrello;
import gui.graphics.Finestra;
import gui.partita.PartitaPrenotabileComponent;
import gui.graphics.ScrollablePanelList;
import gui.graphics.WeekPicker;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.ListCellRenderer;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * La classe permette al 
 * clinte di selezionare la settimana in cui si svolgone le partite, lo stadio e inoltre
 * permette di ordinarle per ordine cronologioco, ordine id stadio e ordine lessicografico 
 * fra le sfidanti inoltre presenta un bottone carrello il quale permette 
 * di accedere facilmente alla schermata carrello.
 */
public class ScreenClient extends Finestra {
	/**
	 * Il costruttore della classe ScreenClient inizializza un oggetto finestra che permette al 
	 * clinte di selezionare la settimana in cui si svolgone le partite, lo stadio e inoltre
	 * permette di ordinarle per ordine cronologioco, ordine id stadio e ordine lessicografico 
	 * fra le sfidanti inoltre presenta un bottone carrello il quale permette 
	 * di accedere facilmente alla schermata carrello.
	 * @param JFrame parent
	 * @param Cliente cliente
	 * @param ListaUtenti listaUtenti
	 */

	public ScreenClient(JFrame parent,Cliente cliente,ListaUtenti listaUtenti){
		super(parent,800,600);

		this.listaUtenti = listaUtenti;
		this.cliente = cliente;
		this.questoFrame = this;
		this.comparator = new OrdineCapienzaStadioComparator();
		this.cliente = cliente;
		//Creo una menu bar
		this.setJMenuBar(createMenuWithButton());
		//inizializzo la lista delle partite
		listaPartite = new ScrollablePanelList();

		getContentPane().add(listaPartite,BorderLayout.CENTER);


		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		//Pannello di inserimento selettori																
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		//Creo il pannelo dove abdranno i filtri
		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1, BorderLayout.CENTER);
		panel_1.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Filtro", TitledBorder.LEADING, TitledBorder.TOP, null, null), new EmptyBorder(0, 0, 0, 0)));
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//Creo la checkBox settimana
		chbxSettimana = new JCheckBox("Settimana");
		panel_1.add(chbxSettimana);
		settimana = new WeekPicker();
		settimana.setPreferredSize(new Dimension(220,32));
		//La aggiungo al panel
		panel_1.add(settimana);
		//Creo La checkBox Stadio
		chbxStadio = new JCheckBox("Stadio");
		//La aggiungo al panel
		panel_1.add(chbxStadio);
		//Creo la comboBox stadio dove vanno inseriti gli stadi e la aggiungo al panel
		comboBoxStadio = new JComboBox<Stadio>();
		comboBoxStadio.setPreferredSize(new Dimension(140,28));
		comboBoxStadio.setEnabled(false);
		panel_1.add(comboBoxStadio);
		//Creo un panello dove all'interno inserisco i selettori di ordinamento
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2, BorderLayout.EAST);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ordinamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setLayout(new BorderLayout(0, 0));
		//Creo un pannello dove all'interno inserisco la comboBox che mi fa scegliere il tipo di ordinamento
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(8, 0, 8, 0));
		panel_2.add(panel_5, BorderLayout.EAST);
		panel_5.setLayout(new BorderLayout(0, 0));
		//Popolo la comboBox
		comboBoxOrdine = new JComboBox<String>();
		panel_5.add(comboBoxOrdine);
		comboBoxOrdine.addItem("Ordine cronologico");
		comboBoxOrdine.addItem("Ordine Lessicografico fra le sfidanti");
		comboBoxOrdine.addItem("Ordine Id Stadio");

		this.setTitle("Cliente");

		//carichiamo i campi

		updateScrollList();

		for(Stadio s: listaUtenti.getStadi())
			comboBoxStadio.addItem(s);

		comboBoxStadio.setRenderer(new ListCellRenderer<Stadio>(){

			@Override
			public Component getListCellRendererComponent(JList<? extends Stadio> list, Stadio value, int index,
					boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				return new JLabel(value.getNome());
			}});

		//listeners
		chbxSettimana.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				settimana.setEnabled(((JCheckBox)e.getSource()).isSelected());
				updateScrollList();
			}

		});

		chbxStadio.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comboBoxStadio.setEnabled(((JCheckBox)e.getSource()).isSelected());
				updateScrollList();
			}

		});

		comboBoxStadio.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				updateScrollList();
			}

		});

		settimana.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				updateScrollList();
			}

		});

		//cambia ordinamento
		comboBoxOrdine.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {

					int index = comboBoxOrdine.getSelectedIndex();

					//Faccio così poiche get selected index  mi ritorna un intero partendo da zero

					if(index==0)
						comparator = new OrdineCronologicoComparator();
					if(index==1)
						comparator = new OrdineLessicoGraficoComparatorNomeSquadre();
					if(index==2)
						comparator = new OrdineIdStadioComparator();

					updateScrollList();
				}
			}

		});


	}

	/**
	 * Il metodo aggiunge un panello alla menu bar.
	 * @return JMenuBar
	 */
	public JMenuBar createMenuWithButton(){
		JMenuBar SelectionMenu = new JMenuBar();
		JPanel selettori = menuButton();

		SelectionMenu.add(selettori);

		return SelectionMenu;
	}
	/**
	 * Il metodo crea un bottone che permette di accedere alla schermata carrello.
	 * @return JPanel
	 */
	public JPanel menuButton(){

		btnCarrello = new JButton("Carrello");
		//Listener
		btnCarrello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Carrello(questoFrame, cliente);
			}
		});
		//Creo il pannello e aggiungo il bottone
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());
		panel.add(btnCarrello, BorderLayout.EAST);
		//Creo una label e la aggiungo al pannello
		JLabel lblBenvenuto = new JLabel("Benvenuto " + cliente.getNome()+"!");
		lblBenvenuto.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblBenvenuto, BorderLayout.WEST);
		return panel;
	}
	/**
	 * Il metodo elima tutti gli elementi che erano presenti prima con una removeAll
	 * quindi eliminando ache la gerarchia.il metodo inizia il processo di aggiornamento ordinando le partite;
	 * controlla se i checkBox sono attivi o meno, se sono attivi applica il filtraggio, con un for
	 * aggiunge le partite e infine chiama revalitade rep ripristinare la gerarchia e infine con una repain ridisegna tutto.
	 */
	public void updateScrollList(){
		//Rimuove
		listaPartite.removeAll();
		//Ordina
		listaUtenti.getPartite().sort(comparator);
		
		//Copia l'array list di partite
		ArrayList<Partita> partite = new ArrayList<Partita>();
		partite.addAll(listaUtenti.getPartite());
		
		//controlla se le check box sono selezionate
		if(chbxStadio.isSelected())
			partite = ListaUtenti.filtraPartite(new FiltroPartitaSuStadio((Stadio)comboBoxStadio.getSelectedItem()),partite);

		if(chbxSettimana.isSelected())
			partite = ListaUtenti.filtraPartite(new FiltroPartitaPerSettimana(settimana.getInitialDate(),settimana.getFinalDate()),partite);

		//Aggiunge le partite
		for(Partita p:partite)
			listaPartite.add(new PartitaPrenotabileComponent(p,this));
		//Ripristina la gerarchia
		revalidate();
		//Ridisegna tutto
		repaint();
	}

	/**
	 * @return cliente
	 */
	public Cliente getCliente(){
		return cliente;
	}


	private JButton btnCarrello;
	private JComboBox<String> comboBoxOrdine;
	private JComboBox<Stadio> comboBoxStadio;
	private WeekPicker settimana;
	private JCheckBox chbxSettimana;
	private JCheckBox chbxStadio;

	private Comparator<Partita> comparator;

	private Cliente cliente;
	private ListaUtenti listaUtenti;
	private ScrollablePanelList listaPartite;

	private Finestra questoFrame;
}
