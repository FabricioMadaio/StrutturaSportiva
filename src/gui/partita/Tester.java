package gui.partita;

import core.elementi.Partita;
import core.elementi.Posto;
import core.elementi.Stadio;
import core.utente.Cliente;

import java.util.GregorianCalendar;

import javax.swing.JFrame;

public class Tester extends JFrame
{
	public static void main(String[] args) 
	{
		GregorianCalendar data1 = new GregorianCalendar(2015, 2+1, 12);
		
		
		Stadio st1 = new Stadio("stadio1",2,"res/Stadio.png");		
		Posto posto1 = new Posto(100,100,"p1");
		Posto posto2 = new Posto(200,200,"p2");
		st1.addPosto(posto1);
		st1.addPosto(posto2);
		//trasferisco i posti dello stadio nella partita
		Cliente c = new Cliente("", "", "", "", "");
		Partita p1 = new Partita(data1, "squadraA", "squadraB", st1);
		p1.copiaPosti(st1.getPosti());
		
		new ScreenPartita(null,p1,c);
		
		//JFrame frame = new JFrame();
		
		/*frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.add(new PartitaComponent(p1));
		frame.setVisible(true);*/
		
	}

	
}
