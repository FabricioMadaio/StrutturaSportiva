package gui.sconto;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

import core.ListaUtenti;
import core.elementi.Partita;
import core.sconti.Sconto;
import core.sconti.ScontoFasciaOraria;
import core.sconti.ScontoGiornoDellaSettimana;
import core.sconti.ScontoPerCliente;
import core.sconti.ScontoPerPartita;

public class Tester {

	public static void main(String[] args) {



		ListaUtenti ls = new ListaUtenti("utenti");

		Sconto s1 = new ScontoGiornoDellaSettimana("Mercoledì", 20);
		Sconto s2 = new ScontoPerCliente("Studente", 20);
		Sconto s3 = new ScontoFasciaOraria(12.30,13.00, 20);

		ls.addSconto(s1);
		ls.addSconto(s2);
		ls.addSconto(s3);

		for(int i=0;i<4;i++){

			GregorianCalendar g = new GregorianCalendar();
			g.set(Calendar.MINUTE, 27+i);
			Partita p = new Partita(g, "SquadraA", "SquadraB"+i,ls.getStadi().get(0));

			for(int j=0;j<i;j++)
				p.aggiungiSconto(new ScontoPerPartita(i));

			ls.addPartita(p);
			p.aggiungiSconti(ls.getScontiGlobali());
		}

		ScontoScreen ss = new ScontoScreen((JFrame)null,ls);
		ss.setVisible(true);

	} 

}
