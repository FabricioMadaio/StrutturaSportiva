package core.sconti;

import java.util.Calendar;

import core.elementi.Partita;
import core.utente.Cliente;

public class ScontoPerPartita implements Sconto 
{
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
