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

public class Carrello extends Finestra
{
	public Carrello(JFrame parent,Cliente c)
	{
		super(parent,800,600);
		questoFrame = this;
		cliente = c;
		operazioniSuFrame();

	}

	public void  operazioniSuFrame()
	{
		questoFrame.setTitle("Carrello");
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		questoFrame.getContentPane().add(creaPannelloPrenotazioni());
		questoFrame.getContentPane().add(creaPannnelloAquistiEffettuati());

		aggiornaListe();

		questoFrame.setVisible(true);
	}

	public JPanel creaPannelloPrenotazioni()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel prenotazioniLabel = new JLabel("Prenotazioni");
		prenotazioniLabel.setFont(new Font(null, Font.BOLD, 22));
		panel.add(prenotazioniLabel,BorderLayout.NORTH);
		panel.add(pannelloVisualizzazioneRimozione(), BorderLayout.CENTER);
		panel.setBorder(new EtchedBorder());
		return panel;
	}

	public ScrollablePanelList pannelloVisualizzazioneRimozione()
	{
		prenotazioni = new ScrollablePanelList();
		return prenotazioni;
	}

	public JPanel creaPannnelloAquistiEffettuati()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel acquistiLabel = new JLabel("Acquisti Effettuati");
		acquistiLabel.setFont(new Font(null, Font.BOLD, 22));
		panel.add(acquistiLabel,BorderLayout.NORTH);
		panel.add(creaListaAcquisti(),BorderLayout.CENTER);

		panel.setBorder(new EtchedBorder());
		return panel;
	}

	public ScrollablePanelList creaListaAcquisti()
	{
		acquisti = new ScrollablePanelList();
		acquisti.setBorder(new EtchedBorder());

		return acquisti;
	}

	public void aggiornaListe(){

		acquisti.removeAll();
		prenotazioni.removeAll();

		for(Biglietto b:cliente.getBiglietti())
			if(b.isAcquisto())
				acquisti.add(new BigliettoComponent(b));
			else
			{
				cliente.verificaScadenze();
				prenotazioni.add(new PrenotazioneComponent(b,(Carrello)questoFrame));
			}	

		revalidate();
		repaint();
	}



	@Override
	public void OnReturnFromChild() {
		// TODO Auto-generated method stub
		aggiornaListe();
	}

	public Cliente getCliente(){
		return cliente;
	}

	private Cliente cliente;

	private ScrollablePanelList acquisti;
	private ScrollablePanelList prenotazioni;

	private Finestra questoFrame;
}
