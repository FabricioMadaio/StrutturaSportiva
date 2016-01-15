package gui.partita;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * PostoIndisponibileException:
 * 	eccezione sollevata in caso di posto non disponibile
 */
@SuppressWarnings("serial")
public class PostoIndisponibileException extends Exception 
{
	public PostoIndisponibileException()
	{
		super("Mi dispiace il posto non è disponibile, ne selezioni un altro.");
	}
}	
