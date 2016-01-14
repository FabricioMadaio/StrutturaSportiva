package core.utente;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */
public class PasswordException extends Exception {
		/**
		 * PasswordException: eccezione da sollevare in caso di password non formattata
		 */
		public PasswordException(){
			super("PasswordException");
		}
}
