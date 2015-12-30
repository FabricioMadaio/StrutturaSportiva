package core.utente;

import java.io.Serializable;


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
	}
	
	
	public String getCategoria() {
		return categoria;
	}


	private String categoria;
	private boolean scontoAttivo;
	
	
	
}
