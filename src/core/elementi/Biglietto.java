package core.elementi;

public class Biglietto 
{
	public Biglietto(Partita partita)
	{
		
		this.partita = partita;
		this.prenotazione = false;
		this.acquisto = false;
	}
	
	
	
	public boolean isPrenotazione() 
	{
		return prenotazione;
	}
	
	public void setPrenotazione(boolean prenotazione) 
	{
		this.prenotazione = prenotazione;
	}
	
	public boolean isAcquisto() 
	{
		return acquisto;
	}
	
	public void setAcquisto(boolean acquisto) 
	{
		this.acquisto = acquisto;
	}
	
	public Partita getPartita() 
	{
		return partita;
	}
	
	public void setPartita(Partita partita) 
	{
		this.partita = partita;
	}
	
	public Posto getPosto() 
	{
		return posto;
	}
	
	public void setPosto(Posto posto) 
	{
		this.posto = posto;
	}



	private boolean prenotazione;
	private boolean acquisto;
	private Partita partita;
	private Posto posto;
	private double prezzo;
}
