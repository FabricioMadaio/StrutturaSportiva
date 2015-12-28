package core.utente;

import java.io.Serializable;

/**
 * @author Fabricio Nicolas Madaio
 */
public class Gestore extends Utente implements Serializable {

	private static final long serialVersionUID = 3L;
	
	/**
	 * Gestore: classe che modella il Gestore
	 * 
	 * @param nome	nome docente
	 * @param cognome	cognome docente
	 * @param username	username
	 * @param password	password
	 */
	public Gestore(String nome, String cognome, String username, String password) {
		super(nome, cognome, username, password);
	}
}
	
