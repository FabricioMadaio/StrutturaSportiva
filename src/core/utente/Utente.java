package core.utente;

import java.io.Serializable;

import core.ParametroIllegaleException;
import core.PasswordException;

/**
 * @author Fabricio Nicolas Madaio
 */
public class Utente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cognome;
	private String login;
	private String password;
	
	/**
	 * Utente: classe che modella l'utente (può essere studente o docente)
	 * 
	 * @param nome	nome
	 * @param cognome	cognome
	 * @param login	login
	 * @param password	password
	 */
	public Utente(String nome, String cognome, String login, String password) {

		this.nome = nome;
		this.cognome = cognome;
		this.login = login;
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
	 * @return login
	 */
	public String getLogin() {
		return login;
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
		if(this.getLogin().equals(u.getLogin()))
			throw new ParametroIllegaleException();
	}
		
}
