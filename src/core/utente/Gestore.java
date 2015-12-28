package core.utente;

import java.io.Serializable;

/**
 * @author Fabricio Nicolas Madaio
 */
public class Gestore extends Utente implements Serializable {

	private static final long serialVersionUID = 3L;
	
	private String insegnamento;
	
	/**
	 * Docente: classe che modella il docente
	 * 
	 * @param nome	nome docente
	 * @param cognome	cognome docente
	 * @param login	login
	 * @param password	password
	 * @param insegnamento	insegnamento
	 */
	public Gestore(String nome, String cognome, String login, String password,String insegnamento) {
		super(nome, cognome, login, password);
		this.setInsegnamento(insegnamento);
	}

	/**
	 * @return insegnamento
	 */
	public String getInsegnamento() {
		return insegnamento;
	}

	/**
	 * @param insegnamento insegnamento
	 */
	private void setInsegnamento(String insegnamento) {
		this.insegnamento = insegnamento;
	}
}
