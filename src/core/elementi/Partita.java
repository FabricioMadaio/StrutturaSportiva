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
	
	/**
	 * Al costruttore di partita gli passimo una data ,la Squadra A, la SquadraB e uno stadio poi
	 * all'interno del costruttore viene inizializzato un ArrayList di sconti che vengono aggiunti 
	 * alla partita se applicabili.
	 * @param GregorianCalendar data
	 * @param String squadraA
	 * @param String squadraB
	 * @param Stadio stadio
	 */
	public Partita(GregorianCalendar data, String squadraA , String squadraB, Stadio stadio)
	{
		this.data = data;
		this.squadraA = squadraA;
		this.squadraB = squadraB;
		this.stadio = stadio;
		this.sconti= new ArrayList<>();
		
		copiaPosti(stadio.getPosti());
	}

	/**
	 * Il metodo simula una sorta di toString che ritorna il nome del match.
	 * 
	 * @return String SquadraA vs SquadraB
	 */
	public String getGame()
	{
		return squadraA + " vs " + squadraB;
	}

/**
 * Il metodo ritorna la data della partita.
 * @return GregorianCalendar data
 */
	public GregorianCalendar getData()
	{
		return data;
	}

	/**
	 * Il metodo ritorna il nome della SquadraA.
	 * 
	 * @return String squadraA
 	 */
	public String getSquadraA() 
	{
		return squadraA;
	}

	/**
	 * Il metodo ritorna il nome della SquadraB.
	 * 
	 * @return String squadraB
 	 */
	public String getSquadraB() 
	{
		return squadraB;
	}

	/**
	 * Il metodo ritorna lo stadio dove si disputa la partita.
	 * @return Stadio stadio
	 */
	public Stadio getStadio() 
	{
		return stadio;
	}
	
	/**
	 * Il metodo ritorna l'ora della partita.
	 * @return float ora
	 */
	public float getOra()
	{
		return data.get(Calendar.HOUR_OF_DAY) + data.get(Calendar.MINUTE)*0.01f;
	}
	
	/**
	 * Al metodo gli viene passato un arrayList di sconti globali dove ci sono i sconti
	 * per fascia oraria,giorno della settimana,etc i quali vengono aggiunti se e solo se
	 * e possibile applicarli.
	 * 
	 * @param ArrayList<Sconti> scontiGlobali
	 */
	public void aggiungiSconti(ArrayList<Sconto> scontiGlobali)
	{
		for(Sconto s : scontiGlobali)
		{
			
			aggiungiSconto(s);
		}
	}
	
	/**
	 * Il metodo prima di aggiunge lo sconto chiama il metodo
	 * vrifica applicabilità di sconto che se ritornerà true lo 
	 * aggiunge altrimenti non lo aggiunge
	 * 
	 * @param Sconto sconto
	 */
	public void aggiungiSconto(Sconto s)
	{
			if(s.verificaApplicabilita(this))
			{
				this.sconti.add(s);
			}	
	}
	
	
	/**
	 * Il metodo serve a generare il prezzo del biglietto tenendo conto di tutte le
	 * politiche di sconto attive.Al metodo viene passato il cliente
	 * per controllare se su di esso sono state attivate delle politiche di sconto. 
	 * @param Clienbte cliente
	 * @return double prezzo
	 */
	
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
	/**
	 *Il metodo ritorna la Lista degli sconti applicati alla partita.
	 * @return ArrayList<Sconto> sconti
	 */
	public ArrayList<Sconto> getSconti() {
		return sconti;
	}
	
	/**
	 * Il metodo ritorna la lista dei posti della partita.
	 * @return ArrayList<Posto> posti
	 */
	public ArrayList<Posto> getPosti() {
		return posti;
	}
	

	/**
	 * Il metodo setta la lista dei posti della partita.
	 * @param ArrayList<Posto> posti
	 */
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
	
	/**
	 * il metodo è il toString della classe.
	 * @return String
	 */
	public String toString()
	{
		return this.getGame() + "   " +this.getData().getTime().toString();
	}
	/**
	 * é identico al toString solo che separe ogni campo andando a capo.
	 * @return String
	 */
	public String getInfo()
	{
		return this.getGame()+"\n"+this.getData().getTime().toString();
	}


	//sconti che si applicano alla singola partita
	private ArrayList<Sconto> sconti; 
	private ArrayList<Posto> posti;
	private GregorianCalendar data;
	private String squadraA;
	private String squadraB;
	private Stadio stadio;
	
}
