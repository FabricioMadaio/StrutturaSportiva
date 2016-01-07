package core.elementi;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Biglietto implements Serializable
{
	private static final long serialVersionUID = 11L;
	
	public Biglietto(Partita partita,double prezzo)
	{
		
		this.partita = partita;
		this.prenotazione = false;
		this.acquisto = false;
		this.prezzo = prezzo;
		this.data = new GregorianCalendar();
	}
	
	
	
	public GregorianCalendar getData() {
		return data;
	}



	public void setData(GregorianCalendar data) {
		this.data = data;
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

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	public boolean verificaPrenotazioneScaduta(GregorianCalendar dataAttuale){
		
		//dataPartita - dataAttuale > 12
		//dataPartita> 12 + dataAttuale
		dataAttuale.add(Calendar.HOUR_OF_DAY, 12);

		if(getPartita().getData().before(dataAttuale) && isPrenotazione())
		{
			return true;
		}
		return false;
	}
	
	public boolean verificaAcquistoScaduto(GregorianCalendar dataAttuale){
		
		//dataPartita - dataAttuale > 0
		//dataPartita> dataAttuale

		if(getPartita().getData().before(dataAttuale))
		{
			return true;
		}
		return false;
	}

	private boolean prenotazione;
	private boolean acquisto;
	private Partita partita;
	private Posto posto;
	private double prezzo;
	private GregorianCalendar data;
}
