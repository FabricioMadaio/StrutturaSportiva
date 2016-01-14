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
public class ScontoPerCliente implements Sconto,Serializable  
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9L;
	
	/**
	 * Il costruttore della classe ScontoPerCliente inizializza un nuovo oggetto prendendo 
	 * come riferimento una stringa che contiene la categoria del cliente e una percentuale.
	 * @param String tipoCliente
	 * @param int percentualeSconto
	 */
	
	public  ScontoPerCliente(String tipoCliente, int percentualeSconto) 
	{
		this.tipoCliente = tipoCliente;
		this.percentuale = percentualeSconto;
	}


	/* (non-Javadoc)
	 * @see core.sconti.Sconto#getPercentualeSconto(core.elementi.Partita, core.utente.Cliente)
	 */
	@Override
	public int getPercentualeSconto(Partita p, Cliente c)
	{
		if(c.getCategoria().equalsIgnoreCase(tipoCliente))
		{
			return percentuale;
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see core.sconti.Sconto#verificaApplicabilita(core.elementi.Partita)
	 */
	@Override
	public boolean verificaApplicabilita(Partita p) 
	{
		//sempre true
		return true;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sconto per cliente " + tipoCliente + " - " + percentuale + "%";
	}



	private String tipoCliente;
	private int percentuale;




}
