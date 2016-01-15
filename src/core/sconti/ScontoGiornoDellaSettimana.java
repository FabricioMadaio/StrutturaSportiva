package core.sconti;

import java.io.Serializable;
import java.util.Calendar;

import core.elementi.Partita;
import core.utente.Cliente;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * La classe permette di aggiungere uno sconto per giorno della settiman.
 */
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
	 * @param String giornoDellaSettimana
	 * @param int percentuale
	 */
	public ScontoGiornoDellaSettimana(String giornoDellaSettiman,int percentuale) 
	{
		this.giornoDellaSettima = giornoDellaSettiman;
		this.percentuale = percentuale;
	}


	/* (non-Javadoc)
	 * @see core.sconti.Sconto#getPercentualeSconto(core.elementi.Partita, core.utente.Cliente)
	 */
	@Override
	public int getPercentualeSconto(Partita p, Cliente c) 
	{
		
		return percentuale;

	}


	/* (non-Javadoc)
	 * @see core.sconti.Sconto#verificaApplicabilita(core.elementi.Partita)
	 */
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
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sconto per ogni " + giornoDellaSettima + " - " + percentuale
				+ "%";
	}



	private String giornoDellaSettima;
	private int percentuale;


}
