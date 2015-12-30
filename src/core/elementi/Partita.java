package core.elementi;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import core.utente.Cliente;

public class Partita
{
	private GregorianCalendar data;
	private String squadraA;
	private String squadraB;
	private String idStadio;
	
	private ArrayList<Posto> posti;
	
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

	public ArrayList<Posto> getPosti() {
		return posti;
	}

	public void setPosti(ArrayList<Posto> posti) {
		this.posti = posti;
	}

	public void copiaPosti(ArrayList<Posto> posti) {
		this.posti = new ArrayList<Posto>();
		
		try{
			for(Posto p: posti){
				this.posti.add((Posto) p.clone());
			}
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
	}


	
}
