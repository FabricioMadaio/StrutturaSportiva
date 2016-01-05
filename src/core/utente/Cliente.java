package core.utente;

import java.io.Serializable;
import java.util.ArrayList;

import core.elementi.Biglietto;


/**
 * @author Fabricio Nicolas Madaio
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
		scontoAttivo = true;
		biglietti = new ArrayList<>();
	}
	
	
	public String getCategoria() 
	{
		return categoria;
	}
	
	

	public ArrayList<Biglietto> getBiglietti() {
		return biglietti;
	}


	public void setBiglietti(ArrayList<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}


	public void aggiungiBiglietto(Biglietto nb)
	{
		
		for(int i=0;i<biglietti.size();i++){
			
			Biglietto b = biglietti.get(i);
			
			if(nb.getPartita() == b.getPartita() && nb.getPosto() == b.getPosto()){
				biglietti.remove(i);
			}
		}
		
		biglietti.add(nb);
	}

	private String categoria;
	private boolean scontoAttivo;
	private ArrayList<Biglietto> biglietti;
	
	
}
