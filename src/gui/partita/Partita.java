package gui.partita;

import java.util.GregorianCalendar;

public class Partita
{
	public Partita(GregorianCalendar data, String squadraA , String squadraB, String idStadio)
	{
		this.data = data;
		this.squadraA = squadraA;
		this.squadraB = squadraB;
		this.idStadio = idStadio;
	}

	public String getGame()
	{
		return squadraA + " vs " + squadraB;
	}


	public GregorianCalendar getData()
	{
		return data;
	}

	public String getSquadraA() 
	{
		return squadraA;
	}

	public String getSquadraB() 
	{
		return squadraB;
	}

	public String getIdStadio() 
	{
		return idStadio;
	}




	private GregorianCalendar data;
	private String squadraA;
	private String squadraB;
	private String idStadio;
}
