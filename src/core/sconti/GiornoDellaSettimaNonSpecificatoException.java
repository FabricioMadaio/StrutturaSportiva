package core.sconti;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * Questa eccezione viene chiamata quando un giorno 
 * della settimana non è specificato correttamente.
 */
@SuppressWarnings("serial")
public class GiornoDellaSettimaNonSpecificatoException extends RuntimeException
{
	public GiornoDellaSettimaNonSpecificatoException() 
	{
		super("Data non specificata correttamente");
	}
}
