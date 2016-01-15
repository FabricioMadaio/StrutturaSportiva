package core.sconti;

import java.io.Serializable;

import core.elementi.Partita;
import core.utente.Cliente;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */

public class ScontoFasciaOraria implements Sconto,Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;
	
	/**
	 * Il costruttore della classe ScontoFaciaOrararia inizializza un oggetto 
	 * prendendo come riferimento un oro iniziale un ora finale e una percentuale. 
	 * @param double oraInizio
	 * @param double oraFine
	 * @param int percentuale
	 */
	public ScontoFasciaOraria(double oraInizio,double oraFine,int percentuale) 
	{
		this.oraFine = oraFine;
		this.oraInizio = oraInizio;
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
		if(p.getOra() >= oraInizio && p.getOra() <= oraFine)
		{
			return true;
		}
		return false;
	}

	/**
	 * converte double in stringa
	 * 
	 * @param ora orario di tipo double nel formato ore.minuti (es. 10.30) 
	 * @return orario di tipo stringa nel formato "ore:minuti" (es. 10:30)
	 */
	private String formatTime(double ora){
		return String.format("%.2f", ora).replace(",", ":"); 
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "Sconto sulla fascia oraria [ " + formatTime(oraInizio)  + " - " + formatTime(oraFine) + " ] - "+ percentuale + "%";
	}

	private int percentuale;
	private double oraInizio;
	private double oraFine;

}
