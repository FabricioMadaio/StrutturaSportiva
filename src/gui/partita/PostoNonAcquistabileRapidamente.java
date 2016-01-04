package gui.partita;

public class PostoNonAcquistabileRapidamente extends Exception
{
	public PostoNonAcquistabileRapidamente()
	{
		super("Impossibile effettuare Acquisto Rapido su questo posto");
	}
}
