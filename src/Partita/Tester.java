package Partita;

import java.awt.Frame;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

public class Tester extends JFrame
{
	public static void main(String[] args) 
	{
		GregorianCalendar data1 = new GregorianCalendar(2015, 2+1, 12);
		Partita p1 = new Partita(data1, "squadraA", "squadraB", "idStadio");
		
		
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		frame.add(new PartitaComponent(p1));
		frame.setVisible(true);
	}

	
}
