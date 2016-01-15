package core.utente;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
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
