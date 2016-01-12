package core.utente;

/**
 * @author Fabricio Nicolas Madaio
 */
public class ParametroIllegaleException extends Exception {
	/**
	 *	ParametroIllegaleException : eccezione da sollevare nel caso in cui un utente e già stato registrato
	 *	e si tenta di registrarlo di nuovo
	 */
	public ParametroIllegaleException(){
		super("ParametroIllegaleException");
	}
}
