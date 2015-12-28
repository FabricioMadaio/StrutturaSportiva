package gui.gestore;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import gui.stadio.StadioCanvas;
import javafx.scene.layout.Border;

import java.awt.*;

public class StadioGestore extends JFrame
{
	public StadioGestore()
	{
		questoFrame = this;
		operazioniSuFrame();
	}

	public void operazioniSuFrame()
	{
		questoFrame.setSize(800, 600);
		questoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		questoFrame.add(creaPannelloCanvas(),BorderLayout.CENTER);
		questoFrame.add(creaPanelloPrincipale(), BorderLayout.SOUTH);
		
		
		questoFrame.setVisible(true);
	}
	
	public JPanel creaPannelloCanvas()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		StadioCanvas s = new StadioCanvas();
		
		panel.add(s,BorderLayout.CENTER);
		
		return panel;
	}
	
	public JPanel creaPanelloPrincipale()
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
		panel.add(creaPannelloOperazioniSuBiglietto(),BorderLayout.CENTER);
		panel.add(creaListaDiBottoni(),BorderLayout.EAST);
		
		return panel;
	}
	
	public JPanel creaListaDiBottoni()
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(3, 1));
		panel.add(creaBottoneAggiungi());
		panel.add(creaBottoneModifica());
		panel.add(creaBottoneCancella());
		
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Poltroncine"));
		
		return panel;
	}
	
	public JButton creaBottoneAggiungi()
	{
		JButton button = new JButton("Aggiungi");
		return button;
	}
	
	
	public JButton creaBottoneModifica()
	{
		JButton button = new JButton("Modifica");
		return button;		
	}
	
	public JButton creaBottoneCancella()
	{
		JButton button = new JButton("Cancella");
		return button;
	}
	
	public JPanel creaPannelloOperazioniSuBiglietto()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(creaPannelloSettaggioCosto(),BorderLayout.NORTH);
		panel.add(creaPannelloSelezione(), BorderLayout.CENTER);
		
		return panel;
	}
	
	public JPanel creaPannelloSettaggioCosto()
	{
		JPanel panel = new JPanel();
		JLabel cotoLabel = new JLabel("Costo Biglietto");
		costoField = new JTextField(10);
		
		panel.add(cotoLabel);
		panel.add(costoField);
		
		return panel;
	}
	
	public JPanel creaPannelloSelezione()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		
		JLabel disponibilit‡Label = new JLabel("Disponibilit‡");
		JLabel nomeLabel = new JLabel("Nome");
		nomeField = new JTextField();
		
		panel.add(disponibilit‡Label);
		panel.add(creaComboBox());
		panel.add(nomeLabel);
		panel.add(nomeField);
		
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Seleziona"));
		
		return panel;
	}
	
	public JComboBox creaComboBox()
	{
		comboBox = new JComboBox();
		
		comboBox.addItem("oggetto 1");
		comboBox.addItem("oggetto 2");
		comboBox.addItem("oggetto 3");
		return comboBox;
	}

	private JFrame questoFrame;
	private JTextField costoField;
	private JComboBox  comboBox;
	private JTextField nomeField;
}
