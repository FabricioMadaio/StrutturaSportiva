package gui.incassi;

import javax.swing.JFrame;

import gui.graphics.Finestra;
import gui.graphics.ScrollablePanelList;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.xml.bind.SchemaOutputResolver;

import com.sun.corba.se.impl.protocol.BootstrapServerRequestDispatcher;

import core.elementi.Stadio;

public class Incassi extends Finestra
{

	public Incassi(JFrame parent,ArrayList<Stadio> stadi) 
	{
		super(parent, 700, 500);
		this.stadi= stadi;
		
		JPanel pannelloIncassiStadio = new JPanel(new BorderLayout());
		getContentPane().add(pannelloIncassiStadio, BorderLayout.CENTER);
		ScrollablePanelList scrollIncassiStadi = new ScrollablePanelList();
		
		
		JPanel pannelloVisualizzazioneIncassiTotali = new JPanel();
		getContentPane().add(pannelloVisualizzazioneIncassiTotali, BorderLayout.SOUTH);
		pannelloVisualizzazioneIncassiTotali.setLayout(new BorderLayout(0, 0));
		
		JLabel lblIncassiTotatli = new JLabel("Incassi Totali:"+calcolaIncassoTotale());
		lblIncassiTotatli.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pannelloVisualizzazioneIncassiTotali.add(lblIncassiTotatli);
		
		for(Stadio s :stadi)
		{
			scrollIncassiStadi.add(new StadioComponet(s));
		}
		
		pannelloIncassiStadio.add(scrollIncassiStadi,BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public double calcolaIncassoTotale()
	{
		double totale = 0;
		
		for(Stadio s : stadi)
		{
			totale +=s.getIncasso();
		}
		
		return totale;
	}
	
	private ArrayList<Stadio> stadi;

}
