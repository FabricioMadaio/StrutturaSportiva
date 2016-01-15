package core.utente;

import java.io.Serializable;

import core.utente.ParametroIllegaleException;
import core.utente.PasswordException;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */
public class Utente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Utente: classe che modella l'utente (può essere Cliente o Gestore)
	 * 
	 * @param nome	nome
	 * @param cognome	cognome
	 * @param username	username
	 * @param password	password
	 */
	public Utente(String nome, String cognome, String username, String password) {

		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * @return	nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return	cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * verifica se la stringa contiene lettere e numeri
	 * @throws PasswordException la stringa non contiene lettere e numeri		
	 */
	public void checkPassword() throws PasswordException{
		
		boolean containsLetter = false;
		boolean containsNumber = false;
		
		for (int i = 0; i < password.length(); i++){
			
			if ((Character.isAlphabetic(password.charAt(i))))
				containsLetter = true;
			
			if ((Character.isDigit(password.charAt(i))))
				containsNumber = true;
		}
		if(!containsLetter || !containsNumber)
			throw new PasswordException();
	}
	
	/**
	 * verifica se due utenti hanno gli stessi parametri e solleva l'eccezione in caso positivo
	 * @param u	utente
	 * @throws ParametroIllegaleException due utenti hanno gli stessi parametri
	 */
	public void checkSameParameters(Utente u) throws ParametroIllegaleException{
		if(this.getUsername().equals(u.getUsername()))
			throw new ParametroIllegaleException();
	}
	
	private String nome;
	private String cognome;
	private String username;
	private String password;
}
