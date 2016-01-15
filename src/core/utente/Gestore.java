package core.utente;

import java.io.Serializable;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */
public class Gestore extends Utente implements Serializable {

	private static final long serialVersionUID = 3L;
	
	/**
	 * Gestore: classe che modella il Gestore
	 * 
	 * @param nome	nome 
	 * @param cognome	cognome 
	 * @param username	username
	 * @param password	password
	 */
	public Gestore(String nome, String cognome, String username, String password) {
		super(nome, cognome, username, password);
	}
}
	
