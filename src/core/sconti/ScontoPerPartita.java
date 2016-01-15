package core.sconti;

import java.io.Serializable;

import core.elementi.Partita;
import core.utente.Cliente;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * La classe permette di aggiungere uno sconto alla singola partita.
 */
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
	public boolean verificaApplicabilita(Partita p) {

		return true;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sconto partita - " + percentuale + "%";
	}



	private int percentuale;
	private String nome;
	private String cognome;
	private String username;
	private String password;
}
