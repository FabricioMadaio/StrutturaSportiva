package gui.gestore;

import javax.swing.JFrame;

import core.ListaUtenti;
import core.elementi.Posto;
import core.elementi.Stadio;

public class Tester {

	public static void main(String[] args) {

		ListaUtenti ls = new ListaUtenti("utenti");
		
		Stadio st1 = new Stadio("stadio1",2);
		Stadio st2 = new Stadio("stadio2",3);
		st1.setIncasso(400);
		st2.setIncasso(500);
		ls.addStadio(st1);
		ls.addStadio(st2);
		
		Posto p1 = new Posto(0,0,"p1");
		st1.addPosto(p1);
		
		GestoreScreen s = new GestoreScreen(null,ls);
		//StadioGestore s = new StadioGestore((JFrame)null,ls);
	}

}
