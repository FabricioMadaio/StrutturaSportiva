package core.sconti;

import core.elementi.Partita;
import core.utente.Cliente;

public class ScontoFasciaOraria implements Sconto 
{
	public ScontoFasciaOraria(double orario,int percentuale) 
	{
		this.orario = orario;
		this.percentuale = percentuale;
	}
	
	@Override
	public int getPercentualeSconto(Partita p, Cliente c) 
	{
		if(p.getOra() == orario)
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
	
	private int percentuale;
	private double orario;

}
