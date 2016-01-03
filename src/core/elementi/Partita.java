package core.elementi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import core.sconti.Sconto;
import core.utente.Cliente;


public class Partita implements Serializable
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	
	public Partita(GregorianCalendar data, String squadraA , String squadraB, Stadio stadio)
	{
		this.data = data;
		this.squadraA = squadraA;
		this.squadraB = squadraB;
		this.stadio = stadio;
		this.sconti= new ArrayList<>();
		
		copiaPosti(stadio.getPosti());
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

	
	public Stadio getStadio() 
	{
		return stadio;
	}
	
	public float getOra()
	{
		return data.get(Calendar.HOUR_OF_DAY) + data.get(Calendar.MINUTE)*0.01f;
	}
	
	public void aggiungiSconti(ArrayList<Sconto> scontiGlobali)
	{
		for(Sconto s : scontiGlobali)
		{
			
			aggiungiSconto(s);
		}
	}
	
	public void aggiungiSconto(Sconto s)
	{
			if(s.verificaApplicabilita(this))
			{
				this.sconti.add(s);
			}	
	}
	
	
	public double generaPrezzoBiglietto(Cliente c)
	{
		double prezzo = stadio.getPrezzoBase();
		
		for(int i = 0 ; i < sconti.size() ; i++)
		{
			Sconto s = sconti.get(i);
			prezzo -= prezzo*(s.getPercentualeSconto(this, c)/100.0); 
			
		}
		return prezzo;
	
	}
	
	public ArrayList<Sconto> getSconti() {
		return sconti;
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
	
	
	public String toString()
	{
		return this.getGame() + "   " +this.getData().getTime().toString();
	}


	//sconti che si applicano alla singola partita
	private ArrayList<Sconto> sconti; 
	private ArrayList<Posto> posti;
	private GregorianCalendar data;
	private String squadraA;
	private String squadraB;
	private Stadio stadio;
	
}
