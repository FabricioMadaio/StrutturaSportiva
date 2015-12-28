package gui.gestore;

import java.awt.*;
import java.util.GregorianCalendar;

import javax.swing.*;


import gui.cliente.ScrollablePanelList;
import gui.graphics.Finestra;
import gui.partita.Partita;
import gui.partita.PartitaComponent;
import javafx.scene.layout.Border;


public class GestoreScreen extends Finestra
{
	public GestoreScreen(JFrame parent)
	{
		super(parent,800,600);
		this.questoFrame = this;
		operazioniSuFrame();
	}

	public void operazioniSuFrame()
	{
		questoFrame.setJMenuBar(creaMenuBar());
		questoFrame.add(creaPannelloComboBox(),BorderLayout.NORTH);
		questoFrame.add(creaPanelloInterattivo(),BorderLayout.CENTER);
		questoFrame.add(creaPaneelloAggiugiPartita(),BorderLayout.SOUTH);

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

		return btn;
	}

	public JButton creaBottoneSconti()
	{
		JButton btn = new JButton("Sconti");

		return btn;
	}

	public JButton creaBottoneStadio()
	{
		JButton btn = new JButton("Stadio");

		return btn;
	}

	public JButton creaBottoneAggiungiPartita()
	{
		JButton btn = new JButton("Aggiungi Partita");

		return btn;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JComboBox creaComboBoxOrdine()
	{
		comboBox = new JComboBox();
		
		comboBox.addItem("Ordine 1");
		comboBox.addItem("Ordine 1");
		comboBox.addItem("Ordine 1");

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
		ScrollablePanelList scroll = new ScrollablePanelList();
		
		GregorianCalendar data = new GregorianCalendar();
		Partita p = new Partita(data, "squadraA" , "squadraB", "idStadio");
		PartitaComponent p1 = new PartitaComponent(p);
		scroll.add(p1);
		
		return scroll;
	}


	private JComboBox comboBox;
	private JFrame questoFrame;
}
