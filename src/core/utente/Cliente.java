package core.utente;

import java.io.Serializable;


/**
 * @author Fabricio Nicolas Madaio
 */
public class Cliente extends Utente implements Serializable{
	
	private static final long serialVersionUID = 2L;

	private String matricola;

	/**
	 * Studente: classe che modella lo studente
	 * 
	 * @param nome	nome
	 * @param cognome	cognome
	 * @param matricola	matricola
	 * @param piano	piano
	 * @param login	login
	 * @param password	password
	 */
	public Cliente(String nome, String cognome,String matricola, String login, String password) {
		
		super(nome, cognome, login, password);
		this.matricola = matricola;
	}
	
	/**
	 * @return	matricola
	 */
	public String getMatricola() {
		return matricola;
	}
		
	
}
