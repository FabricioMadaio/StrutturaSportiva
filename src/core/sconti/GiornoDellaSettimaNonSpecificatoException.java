package core.sconti;
/**
 * Questa eccezione viene chiamata quando un giorno 
 * della settimana non è specificato correttamente.
 */
public class GiornoDellaSettimaNonSpecificatoException extends RuntimeException
{
	public GiornoDellaSettimaNonSpecificatoException() 
	{
		super("Data non specificata correttamente");
	}
}
