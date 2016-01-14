package core.sconti;

import java.io.Serializable;
import java.util.Calendar;

import core.elementi.Partita;
import core.utente.Cliente;

public class ScontoPerPartita implements Sconto,Serializable  
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	/**
	 * Il costruttore della classe ScontoPerPartita inizializza un ogetto prendendo
	 * come riferimento una percentuale.
	 * 
	 * @param int percentuale
	 */
	public  ScontoPerPartita(int percentuale)
	{
		this.percentuale = percentuale;
	}


	@Override
	public int getPercentualeSconto(Partita p, Cliente c) 
	{
		return percentuale;
	}

	@Override
	public boolean verificaApplicabilita(Partita p) {

		return true;
	}
	
	

	@Override
	public String toString() {
		return "Sconto partita - " + percentuale + "%";
	}



	private int percentuale;

}
