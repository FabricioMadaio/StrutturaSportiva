package core.filtri;

import java.util.GregorianCalendar;

import core.elementi.Partita;
import core.elementi.Stadio;

public class FiltroPartitaPerSettimana implements FiltroPartita {

	public FiltroPartitaPerSettimana(GregorianCalendar inizio,GregorianCalendar fine){
		this.inizio = inizio;
		this.fine = fine;
	}
	
	@Override
	public boolean check(Partita p) {
		// TODO Auto-generated method stub
		GregorianCalendar data = p.getData();
		return (data.after(inizio) || data.equals(inizio)) && (data.before(fine) || data.equals(fine));
	}

	private GregorianCalendar inizio;
	private GregorianCalendar fine;
}
