package core.sconti;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import core.ListaUtenti;
import core.elementi.Partita;
import core.utente.Cliente;

public class Tester 
{
	public static void main(String[] args) {

		ListaUtenti lS = new ListaUtenti("utenti");
		Sconto s1 = new ScontoGiornoDellaSettimana("Mercoledì", 20);
		Sconto s = new ScontoPerCliente("Studente", 20);
		Sconto s2 = new ScontoFasciaOraria(12.00,13.00, 20);
		lS.addSconto(s);
		lS.addSconto(s1);
		lS.addSconto(s2);
		GregorianCalendar data1 = new GregorianCalendar(2015, 11, 29,23,31);
		Partita p = new Partita(data1, "squadraA", "squadraB", "idStadio", 12);
		Cliente c = new Cliente("Nome", "Cognome", "aaa", "a1", "Studente");
		p.aggiungiSconti(lS.getScontiGlobali());
		GregorianCalendar data = new GregorianCalendar(2015, 11, 30, 12,30);
		Partita p1 = new Partita(data, "squadraA", "squadraB", "idStadio", 12);
		p1.aggiungiSconti(lS.getScontiGlobali());
		System.out.println(p.generaPrezzoBiglietto(c));
		System.out.println(p1.generaPrezzoBiglietto(c));

		System.out.println(p.getOra());
	}
}
