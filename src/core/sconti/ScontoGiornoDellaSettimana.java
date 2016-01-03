package core.sconti;

import java.io.Serializable;
import java.util.Calendar;

import core.elementi.Partita;
import core.utente.Cliente;

public class ScontoGiornoDellaSettimana implements Sconto,Serializable  
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8L;
	
	public ScontoGiornoDellaSettimana(String giornoDellaSettiman,int percentuale) 
	{
		this.giornoDellaSettima = giornoDellaSettiman;
		this.percentuale = percentuale;
	}


	@Override
	public int getPercentualeSconto(Partita p, Cliente c) 
	{
		
		return percentuale;

	}


	@Override
	public boolean verificaApplicabilita(Partita p) 
	{
		if(dammiGiornoDellaSettima(p).equalsIgnoreCase(giornoDellaSettima))
			return true;
		
		return false;
	}
	/**
	 * Il metodo attraverso un case switch trova il giorno della settimana della partita
	 * poich�  p.getData().get(Calendar.DAY_OF_WEEK) ritorno un intero che indica i giorni
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
		case 2: giornoSettimana = "Luned�"; break;
		case 3: giornoSettimana = "Marted�"; break;
		case 4: giornoSettimana = "Mercoled�"; break;
		case 5: giornoSettimana = "Gioved�"; break;
		case 6: giornoSettimana = "Venerd�"; break;
		case 7: giornoSettimana = "Sabato"; break;
		default:throw new GiornoDellaSettimaNonSpecificatoException();		
		}
		return giornoSettimana;
	}
	
	
	@Override
	public String toString() {
		return "Sconto per ogni " + giornoDellaSettima + " - " + percentuale
				+ "%";
	}



	private String giornoDellaSettima;
	private int percentuale;


}
