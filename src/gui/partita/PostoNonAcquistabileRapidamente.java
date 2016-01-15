package gui.partita;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * PostoNonAcquistabileRapidamente:
 * 	eccezione sollevata in caso di posto non disponibile per l'acquisto rapido
 */
public class PostoNonAcquistabileRapidamente extends Exception
{
	public PostoNonAcquistabileRapidamente()
	{
		super("Impossibile effettuare Acquisto Rapido su questo posto");
	}
}
