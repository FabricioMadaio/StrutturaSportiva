package gui.sconto;

import java.util.GregorianCalendar;

import javax.swing.JFrame;

import core.ListaUtenti;
import core.elementi.Partita;
import core.elementi.Posto;
import core.elementi.Stadio;
import core.sconti.Sconto;
import core.sconti.ScontoFasciaOraria;
import core.sconti.ScontoGiornoDellaSettimana;
import core.sconti.ScontoPerCliente;
import core.sconti.ScontoPerPartita;

public class Tester {

	public static void main(String[] args) {


		//ScontoFasciaOrarariaGui sc = new ScontoFasciaOrarariaGui(null);
		//ScontoCategoriaClienteGui sc2 = new ScontoCategoriaClienteGui(null);
		//ScontoGiornoDellaSettimanaGui sc3 = new ScontoGiornoDellaSettimanaGui(null);

		ListaUtenti ls = new ListaUtenti("utenti");
		
		Sconto s1 = new ScontoGiornoDellaSettimana("Mercoledì", 20);
		Sconto s2 = new ScontoPerCliente("Studente", 20);
		Sconto s3 = new ScontoFasciaOraria(12.00,13.00, 20);
		
		ls.addSconto(s1);
		ls.addSconto(s2);
		ls.addSconto(s3);
		
		for(int i=0;i<4;i++){
			Partita p = new Partita(new GregorianCalendar(), "SquadraA", "SquadraB"+i,"idStadio", i, i);
			
			for(int j=0;j<i;j++)
				p.aggiungiSconto(new ScontoPerPartita(i));
			
			ls.addPartita(p);
			p.aggiungiSconti(ls.getScontiGlobali());
		}
		
		ScontoScreen ss = new ScontoScreen((JFrame)null,ls);
		ss.setVisible(true);

	}
	
}
