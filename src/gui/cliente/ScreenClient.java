package gui.cliente;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

import core.ListaUtenti;
import core.comparator.OrdineCapienzaStadioComparator;
import core.comparator.OrdineCronologicoComparator;
import core.comparator.OrdineIdStadioComparator;
import core.comparator.OrdineLessicoGraficoComparatorNomeSquadre;
import core.elementi.Partita;
import core.elementi.Stadio;
import core.filtri.FiltroPartitaSuStadio;
import core.utente.Cliente;
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
import java.awt.GridLayout;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.CompoundBorder;

public class ScreenClient extends Finestra {

	public ScreenClient(JFrame parent,Cliente cliente,ListaUtenti listaUtenti){
		super(parent,800,600);
		
		this.listaUtenti = listaUtenti;
		this.comparator = new OrdineCapienzaStadioComparator();
		this.cliente = cliente;
		
		this.setJMenuBar(createMenuWithButton());
		listaPartite = new ScrollablePanelList();
		
		getContentPane().add(listaPartite,BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
																				
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1, BorderLayout.CENTER);
		panel_1.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Filtro", TitledBorder.LEADING, TitledBorder.TOP, null, null), new EmptyBorder(0, 0, 0, 0)));
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		chbxSettimana = new JCheckBox("Settimana");
		panel_1.add(chbxSettimana);
		settimana = new WeekPicker();
		settimana.setPreferredSize(new Dimension(220,32));
		panel_1.add(settimana);
		
		chbxStadio = new JCheckBox("Stadio");
		panel_1.add(chbxStadio);
		
		comboBoxStadio = new JComboBox<Stadio>();
		comboBoxStadio.setPreferredSize(new Dimension(140,28));
		comboBoxStadio.setEnabled(false);
		panel_1.add(comboBoxStadio);
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2, BorderLayout.EAST);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ordinamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EmptyBorder(8, 0, 8, 0));
		panel_2.add(panel_5, BorderLayout.EAST);
		panel_5.setLayout(new BorderLayout(0, 0));

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
		
		//cambia ordinamento
		comboBoxOrdine.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				 if (e.getStateChange() == ItemEvent.SELECTED) {
			         
					 int index = comboBoxOrdine.getSelectedIndex();
					 
			          
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
	
	
	public JMenuBar createMenuWithButton(){
		JMenuBar SelectionMenu = new JMenuBar();
		JPanel selettori = menuButton();

		SelectionMenu.add(selettori);

		return SelectionMenu;
	}

	public JPanel menuButton(){

		btnCarrello = new JButton("Carrello");
		btnCarrello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());
		panel.add(btnCarrello, BorderLayout.EAST);
		
		JLabel lblBenvenuto = new JLabel("Benvenuto Cliente!");
		lblBenvenuto.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblBenvenuto, BorderLayout.WEST);
		return panel;
	}
	
	public void updateScrollList(){
		
		listaPartite.removeAll();
		
		listaUtenti.getPartite().sort(comparator);
		
		ArrayList<Partita> partite = (ArrayList<Partita>) listaUtenti.getPartite().clone();
		
		if(chbxSettimana.isSelected())
			partite = ListaUtenti.filtraPartite(new FiltroPartitaSuStadio((Stadio)comboBoxStadio.getSelectedItem()),partite);
		
		if(chbxStadio.isSelected())
			partite = ListaUtenti.filtraPartite(new FiltroPartitaSuStadio((Stadio)comboBoxStadio.getSelectedItem()),partite);
		
		
		for(Partita p:partite)
			listaPartite.add(new PartitaPrenotabileComponent(p,cliente));
		
		revalidate();
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
}
