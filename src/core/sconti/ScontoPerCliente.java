package core.sconti;

import java.io.Serializable;

import core.elementi.Partita;
import core.utente.Cliente;

public class ScontoPerCliente implements Sconto,Serializable  
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9L;
	
	public  ScontoPerCliente(String tipoCliente, int percentualeSconto) 
	{
		this.tipoCliente = tipoCliente;
		this.percentuale = percentualeSconto;
	}


	@Override
	public int getPercentualeSconto(Partita p, Cliente c)
	{
		if(c.getCategoria().equalsIgnoreCase(tipoCliente))
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
		return "Sconto per cliente " + tipoCliente + " - " + percentuale + "%";
	}



	private String tipoCliente;
	private int percentuale;




}
