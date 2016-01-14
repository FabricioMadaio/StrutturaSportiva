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
	/**
	 * Il costruttore della classe ScontoGiornoDellaSettimana inizializza un nuovo 
	 * oggetto prendendo come riferimento un giorno della settimana e una percentuale
	 *  
	 * @param String giornoDellaSettiman
	 * @param int percentuale
	 */
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
	 * poichè  p.getData().get(Calendar.DAY_OF_WEEK) ritorno un intero che indica i giorni
	 * della settimana numerati da 1 a 7 partendo da domenica.
	 * @param  Partita p
	 * @return String giornoDellaSettimana
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
