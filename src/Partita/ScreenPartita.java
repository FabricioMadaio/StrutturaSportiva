package Partita;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sun.corba.se.impl.protocol.BootstrapServerRequestDispatcher;

public class ScreenPartita extends JFrame
{
	public ScreenPartita()
	{
		questoFrame = this;
		operazioniSuFrame();
	}
	
	public void operazioniSuFrame()
	{
		questoFrame.setSize(800, 700);
		questoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		questoFrame.add(creaPannelloInformazioniTransazioni(),BorderLayout.SOUTH);
		
		questoFrame.setVisible(true);
	}
	
	public JPanel creaPannelloInformazioniTransazioni()
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.add(creaTextArea(),BorderLayout.WEST);
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
		
		return panel;
	}
	
	public JButton creaBottonePrenota()
	{
		JButton button = new JButton("Prenota");
		return button;
	}
	
	
	public JButton creaBottoneAcquista()
	{
		JButton button = new JButton("Acquista");
		return button;		
	}
	
	public JButton creaBottoneAcquistaSubito()
	{
		JButton button = new JButton("Acquista Subito");
		return button;
	}
	
	
	public JPanel creaTextArea()
	{
		JPanel panel = new JPanel();
		
		textArea = new JTextArea(10,55);
		JScrollPane scroll = new JScrollPane(textArea);
		panel.add(scroll);
		
		return panel;
	}
	
	private JFrame questoFrame;
	private JTextArea textArea;
}
