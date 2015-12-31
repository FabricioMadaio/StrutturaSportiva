package core.sconti;

import core.elementi.Partita;
import core.utente.Cliente;

public class ScontoFasciaOraria implements Sconto 
{
	public ScontoFasciaOraria(double oraInizio,double oraFine,int percentuale) 
	{
		this.oraFine = oraFine;
		this.oraInizio = oraInizio;
		this.percentuale = percentuale;
	}
	
	@Override
	public int getPercentualeSconto(Partita p, Cliente c) 
	{
		if(p.getOra() >= oraInizio && p.getOra() <= oraFine)
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
	
	
	
	@Override
	public String toString() {
		return "ScontoFasciaOraria [percentuale=" + percentuale + ", oraInizio=" + oraInizio + ", oraFine=" + oraFine
				+ "]";
	}



	private int percentuale;
	private double oraInizio;
	private double oraFine;

}
