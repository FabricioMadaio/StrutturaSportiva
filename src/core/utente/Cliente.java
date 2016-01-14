package core.utente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import core.elementi.Biglietto;
import core.elementi.Posto;


/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */
public class Cliente extends Utente implements Serializable{

	private static final long serialVersionUID = 2L;

	/**
	 * Cliente: classe che modella il cliente
	 * 
	 * @param nome	nome
	 * @param cognome	cognome
	 * @param login	login
	 * @param password	password
	 */
	public Cliente(String nome, String cognome, String username, String password,String categoria) {

		super(nome, cognome, username, password);
		this.categoria = categoria;
		biglietti = new ArrayList<Biglietto>();
	}


	/**
	 * @return categoria cliente
	 */
	public String getCategoria() 
	{
		return categoria;
	}


	/**
	 * @param biglietti
	 */
	public void setBiglietti(ArrayList<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}


	/**
	 * @param nb aggiunge un biglietto nuovo al cliente
	 */
	public void aggiungiBiglietto(Biglietto nb)
	{

		//cerchiamo se esiste già una prenotazione per il biglietto nuovo
		//in caso positivo cancelliamo quella prenotazione e aggiungiamo il biglietto acquistato
		for(int i=0;i<biglietti.size();i++){

			Biglietto b = biglietti.get(i);

			if(nb.getPartita() == b.getPartita() && nb.getPosto() == b.getPosto()){
				biglietti.remove(i);
			}
		}

		biglietti.add(nb);
	}

	/**
	 * @return lista dei biglietti
	 */
	public ArrayList<Biglietto> getBiglietti() {
		return biglietti;
	}

	/**
	 * cancella il biglietto prenotato o acquistato e setta il posto di nuovo come disponibile
	 * @param b biglietto da eliminare
	 */
	public void annullaBiglietto(Biglietto b){

		//setto il posto di nuovo come disponibile
		for(Posto p:b.getPartita().getPosti()){
			if(p.equals(b.getPosto()))
				p.setStato(Posto.Stato.DISPONIBILE);
		}

		biglietti.remove(b);
	}

	//cancella i biglietti scaduti
	public void verificaScadenze()
	{
		
		GregorianCalendar dataAttuale= new GregorianCalendar();
		
		for(int i = 0 ; i<biglietti.size() ; i++)
		{
			Biglietto b = biglietti.get(i);
			if(b.verificaPrenotazioneScaduta(dataAttuale) && !b.isAcquisto())
			{
				annullaBiglietto(b);
				i--;
			}
		}

	}

	private String categoria;
	private ArrayList<Biglietto> biglietti;


}
