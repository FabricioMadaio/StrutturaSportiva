package core.filtri;

import java.util.GregorianCalendar;

import core.elementi.Partita;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */

public class FiltroPartitaPerSettimana implements FiltroPartita {
	/**
	 * Il costruttore della cladde FiltroPartitePerSettimana inizializza un oggetto prendendo come
	 * riferimento una data d'inizio e una data di fine.
	 * 
	 * @param GregorianCalendar inizio
	 * @param GregorianCalendar fine
	 */
	public FiltroPartitaPerSettimana(GregorianCalendar inizio,GregorianCalendar fine){
		this.inizio = inizio;
		this.fine = fine;
	}

	@Override
	public boolean check(Partita p) {
		GregorianCalendar data = p.getData();
		return (data.after(inizio) || data.equals(inizio)) && (data.before(fine) || data.equals(fine));
	}

	private GregorianCalendar inizio;
	private GregorianCalendar fine;
}
