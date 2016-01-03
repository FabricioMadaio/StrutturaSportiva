package core.sconti;

import java.io.Serializable;

import core.elementi.Partita;
import core.utente.Cliente;

public class ScontoFasciaOraria implements Sconto,Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;
	
	public ScontoFasciaOraria(double oraInizio,double oraFine,int percentuale) 
	{
		this.oraFine = oraFine;
		this.oraInizio = oraInizio;
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
		if(p.getOra() >= oraInizio && p.getOra() <= oraFine)
		{
			return true;
		}
		return false;
	}
	
	//converte double in stringa
	private String formatTime(double ora){
		return String.format("%.2f", ora).replace(",", ":"); 
	}
	
	@Override
	public String toString() {
		
		return "Sconto sulla fascia oraria [ " + formatTime(oraInizio)  + " - " + formatTime(oraFine) + " ] - "+ percentuale + "%";
	}



	private int percentuale;
	private double oraInizio;
	private double oraFine;

}
