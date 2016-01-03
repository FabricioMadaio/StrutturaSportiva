package gui.gestore;

import core.ListaUtenti;
import core.comparator.OrdineCapienzaStadioComparator;
import core.comparator.OrdineCronologicoComparator;
import gui.incassi.*;
import core.elementi.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;
import java.util.GregorianCalendar;

import javax.swing.*;


import gui.graphics.ScrollablePanelList;
import gui.graphics.Finestra;
import gui.partita.PartitaComponent;
import gui.sconto.ScontoScreen;


public class GestoreScreen extends Finestra
{
	public GestoreScreen(JFrame parent,ListaUtenti listaUtenti)
	{
		super(parent,800,600);
		this.questoFrame = this;
		this.listaUtenti = listaUtenti;
		operazioniSuFrame();
		addWindowListener();
	}

	public void operazioniSuFrame()
	{
		questoFrame.setJMenuBar(creaMenuBar());
		questoFrame.add(creaPannelloComboBox(),BorderLayout.NORTH);
		questoFrame.add(creaPanelloInterattivo(),BorderLayout.CENTER);
		questoFrame.add(creaPaneelloAggiugiPartita(),BorderLayout.SOUTH);

		questoFrame.setResizable(false);
		questoFrame.setVisible(true);
	}

	public JMenuBar creaMenuBar()
	{
		JMenuBar bar = new JMenuBar();
		bar.add(creaBottoneIncassi());
		bar.add(creaBottoneSconti());
		bar.add(creaBottoneStadio());
		
		return bar;
	}

	public JButton creaBottoneIncassi()
	{
		JButton btn = new JButton("Incassi");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Incassi incassi = new Incassi(questoFrame, listaUtenti.getStadi());
				
			}
		});
		
		return btn;
	}

	public JButton creaBottoneSconti()
	{
		JButton btn = new JButton("Sconti");
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ScontoScreen s = new ScontoScreen(questoFrame,listaUtenti);
			}
			
		});
		return btn;
	}

	public JButton creaBottoneStadio()
	{
		JButton btn = new JButton("Stadio");
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StadioGestore s = new StadioGestore(questoFrame,listaUtenti);
			}
			
		});
		
		return btn;
	}

	public JButton creaBottoneAggiungiPartita()
	{
		JButton btn = new JButton("Aggiungi Partita");
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AggiungiPartitaScreen ps = new AggiungiPartitaScreen(questoFrame, listaUtenti);
			}
			
		});
		return btn;
	}

	public JComboBox<String> creaComboBoxOrdine()
	{
		comboBox = new JComboBox<String>();
		
		comboBox.addItem("Ordine per Capienza");
		comboBox.addItem("Ordine Cronologico");
		
		comboBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				 if (e.getStateChange() == ItemEvent.SELECTED) {
			         
					 String item = (String)e.getItem();

			          if(item=="Ordine per Capienza")
			        	  listaUtenti.getPartite().sort(new OrdineCronologicoComparator());
			          if(item=="Ordine Cronologico")
			        	  listaUtenti.getPartite().sort(new OrdineCronologicoComparator());
			          
			          updateListaPartite();
			       }
			}
			
		});

		return comboBox;
	}
	
	public JPanel creaPannelloComboBox()
	{
		JPanel panel = new JPanel();
		JLabel gestoreLabel = new JLabel("Benvenuto Gestore!");
		gestoreLabel.setFont(new Font(null, Font.BOLD, 22));
		panel.setLayout(new BorderLayout());
		
		panel.add(gestoreLabel, BorderLayout.WEST);
		panel.add(creaComboBoxOrdine(),BorderLayout.EAST);
		
		return panel;
	}
	
	public JPanel creaPaneelloAggiugiPartita()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
	
		panel.add(creaBottoneAggiungiPartita(),BorderLayout.EAST);
		
		return panel;
	}
	
	public ScrollablePanelList creaPanelloInterattivo ()
	{
		scroll = new ScrollablePanelList();
		
		GregorianCalendar data = new GregorianCalendar();
		//Partita p = new Partita(data, "squadraA" , "squadraB", "idStadio",15.00,true);
		//PartitaComponent p1 = new PartitaComponent(p);
		//scroll.add(p1);
		
		updateListaPartite();
		
		return scroll;
	}
	
	public void addWindowListener(){
		
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				questoFrame.closeFrame();
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
				//aggiorna la lista se torniamo da una finestra diversa
				if(e.getOppositeWindow()!=null)
				updateListaPartite();
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public void updateListaPartite(){
		
		scroll.removeAll();
		
		for(Partita p:listaUtenti.getPartite()){
			PartitaComponent p1 = new PartitaComponent(p);
			scroll.add(p1);
		}
		
		validate();
	}

	private ScrollablePanelList scroll;
	private ListaUtenti listaUtenti;
	private JComboBox<String> comboBox;
	private Finestra questoFrame;
}
