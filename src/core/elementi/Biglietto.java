package core.elementi;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Biglietto implements Serializable
{
	private static final long serialVersionUID = 11L;
	/**
	 * Il costruttore della classe Niglietto inizializza un nuovo oggetto prendendo
	 * come riferimento una partita e un prezzo, verranno inizializzati gli stati del biglietto
	 * ovvero prenotazione e acquisto a false e verra salvato il prezzo passatogli nella variabile
	 * di istanza prezzo, la data del biglietto sarà la data
	 * di creazione(data odierna).
	 * 
	 * @param Partita partita
	 * @param double prezzo
	 */

	public Biglietto(Partita partita,double prezzo)
	{

		this.partita = partita;
		this.prenotazione = false;
		this.acquisto = false;
		this.prezzo = prezzo;
		this.data = new GregorianCalendar();
	}


	/**
	 * Il metodo ritorna la data del biglietto.
	 * @return GregorianCalendar data
	 */
	public GregorianCalendar getData() {
		return data;
	}


	/**
	 * Al metodo gli viene passato una data la quale andra ad aggiornare la variabile 
	 * di istanza.
	 * 
	 * @param GregorianCalendar data
	 */
	public void setData(GregorianCalendar data) {
		this.data = data;
	}


	/**
	 * Il metodo ritorna lo stato della prenotazione.
	 * 
	 * @return boolean prernotazione
	 */
	public boolean isPrenotazione() 
	{
		return prenotazione;
	}

	/**
	 * Il metodo serve a settare lo stato della prenotazione.
	 * 
	 * @param boolean prenotazione
	 */

	public void setPrenotazione(boolean prenotazione) 
	{
		this.prenotazione = prenotazione;
	}

	/**
	 * Ritorna lo stato dell'acquisto.
	 * @return boolean acquisto
	 */

	public boolean isAcquisto() 
	{
		return acquisto;
	}

	/**
	 *  Il metodo serve a settare lo stato dell'acquisto.
	 * @param boolean acquisto
	 */
	public void setAcquisto(boolean acquisto) 
	{
		this.acquisto = acquisto;
	}

	/**
	 *Il metodo ritorna la partita associata al biglietto. 
	 * 
	 * @return Partita partita
	 */

	public Partita getPartita() 
	{
		return partita;
	}

	/**
	 * Il metodo permette di associare manualmente la partita al biglietto.
	 * @param Partita partita
	 */	
	public void setPartita(Partita partita) 
	{
		this.partita = partita;
	}

	/**
	 * Il metodo ritorna il posto associato al biglietto.
	 * @return Posto posto
	 */
	public Posto getPosto() 
	{
		return posto;
	}
	/**
	 * Il metodo fa settare il posto del biglietto.
	 * @param Posto posto
	 */
	public void setPosto(Posto posto) 
	{
		this.posto = posto;
	}

	/**
	 * Il metodo ritorna il prezzo del biglietto.
	 * @return double prezzo
	 */ 
	public double getPrezzo()
	{
		return prezzo;
	}


	/**
	 *Il metodo setta il prezzo del biglietto. 
	 * @param double prezzo
	 */
	public void setPrezzo(double prezzo)
	{
		this.prezzo = prezzo;
	}


	/**
	 *Al metodo viene passata la data attuale la quale verra confrontata con quella della
	 *partita. Se la data attuale e quella della partita differiscono almeno di 
	 *12 ore o più il metodo ritornerà true altrimenti false.
	 * @param GreagorianCalendar dataAttuale
	 * @return boolean stato del biglietto (prenotabile /scaduto)
	 */	
	public boolean verificaPrenotazioneScaduta(GregorianCalendar dataAttuale)
	{

		//dataPartita - dataAttuale > 12
		//dataPartita> 12 + dataAttuale
		dataAttuale.add(Calendar.HOUR_OF_DAY, 12);

		if(getPartita().getData().before(dataAttuale) && isPrenotazione())
		{
			return true;
		}
		return false;
	}

	/**
	 *Al metodo gli viene passata la data attuale la quale viene confrontata con la data
	 *della partita se la partita si è gia conclusa allora non permette di acquistare ritornando false
	 *alrimenti ritorna true. 
	 * @param GreagorianCalendar dataAttuale
	 * @return boolean stato del biglietto (acquistabile/scaduto)
	 */

	public boolean verificaAcquistoScaduto(GregorianCalendar dataAttuale)
	{

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
