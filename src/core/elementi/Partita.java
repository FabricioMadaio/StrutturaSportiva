package core.elementi;

import java.util.ArrayList;
import java.util.GregorianCalendar;

<<<<<<< HEAD
import core.sconti.Sconto;
import core.utente.Cliente;

public class Partita 
{
	public Partita(GregorianCalendar data, String squadraA , String squadraB, String idStadio,double ora,double prezzoBase)
=======
import core.utente.Cliente;

public class Partita
{
	private GregorianCalendar data;
	private String squadraA;
	private String squadraB;
	private String idStadio;
	
	private ArrayList<Posto> posti;
	
	public Partita(GregorianCalendar data, String squadraA , String squadraB, String idStadio)
>>>>>>> c79c7ceffa571e661a42625c678593506353402c
	{
		this.data = data;
		this.squadraA = squadraA;
		this.squadraB = squadraB;
		this.idStadio = idStadio;
		this.ora = ora;
		this.prezzoBase = prezzoBase;
		this.sconti= new ArrayList<>();
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
	
	public double getOra()
	{
		return ora;
	}
	

<<<<<<< HEAD
	
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
		double prezzo = prezzoBase;
		
		for(int i = 0 ; i < sconti.size() ; i++)
		{
			Sconto s = sconti.get(i);
			prezzo -= prezzo*(s.getPercentualeSconto(this, c)/100.0); 
			
		}
		return prezzo;
	
	}
	
=======
	public ArrayList<Posto> getPosti() {
		return posti;
	}
>>>>>>> c79c7ceffa571e661a42625c678593506353402c

	public void setPosti(ArrayList<Posto> posti) {
		this.posti = posti;
	}

<<<<<<< HEAD
	//sconti che si applicano alla singola partita
	private ArrayList<Sconto> sconti; 
	private GregorianCalendar data;
	private String squadraA;
	private String squadraB;
	private String idStadio;
	private double ora;
	private double prezzoBase;
=======
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


>>>>>>> c79c7ceffa571e661a42625c678593506353402c
	
}
