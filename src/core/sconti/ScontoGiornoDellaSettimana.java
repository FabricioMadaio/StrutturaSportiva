package core.sconti;

import java.util.Calendar;

import core.elementi.Partita;
import core.utente.Cliente;

public class ScontoGiornoDellaSettimana implements Sconto 
{
	public ScontoGiornoDellaSettimana(String giornoDellaSettiman,int percentuale) 
	{
		this.giornoDellaSettima = giornoDellaSettiman;
		this.percentuale = percentuale;
	}


	@Override
	public int getPercentualeSconto(Partita p, Cliente c) 
	{
		if(dammiGiornoDellaSettima(p).equalsIgnoreCase(giornoDellaSettima))
		{
			return percentuale;
		}
		return 0;
	}


	@Override
	public boolean verificaApplicabilita(Partita p) 
	{
		return true;
	}
	/**
	 * Il metodo attraverso un case switch trova il giorno della settimana della partita
	 * poichè  p.getData().get(Calendar.DAY_OF_WEEK) ritorno un intero che indica i giorni
	 * della settimana numerati da 1 a 7 partendo da domenica.
	 * @param p
	 * @return
	 */

	public String dammiGiornoDellaSettima(Partita p)
	{
		String giornoSettimana = null;
		int giorno = p.getData().get(Calendar.DAY_OF_WEEK);
		switch (giorno)
		{
		case 1: giornoSettimana = "Domenica"; break;
		case 2: giornoSettimana = "Lunedì"; break;
		case 3: giornoSettimana = "Martedì"; break;
		case 4: giornoSettimana = "Mercoledì"; break;
		case 5: giornoSettimana = "Giovedì"; break;
		case 6: giornoSettimana = "Venerdì"; break;
		case 7: giornoSettimana = "Sabato"; break;
		default:throw new GiornoDellaSettimaNonSpecifcatoException();		
		}
		return giornoSettimana;
	}
	
	
	@Override
	public String toString() {
		return "ScontoGiornoDellaSettimana [giornoDellaSettima=" + giornoDellaSettima + ", percentuale=" + percentuale
				+ "]";
	}



	private String giornoDellaSettima;
	private int percentuale;


}
