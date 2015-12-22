package Partita;

import java.util.GregorianCalendar;

import javax.swing.JFrame;

public class Tester 
{
	GregorianCalendar data1 = new GregorianCalendar(2015, 2+1, 12);
	Partita p1 = new Partita(data1, "squadraA", "squadraB", "idStadio");
	
	JFrame frame1 = new JFrame();
}
