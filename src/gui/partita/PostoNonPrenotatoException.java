package gui.partita;

public class PostoNonPrenotatoException extends Exception
{
	public PostoNonPrenotatoException()
	{
		super("Il posto non è prenotato per favore prenotalo prima di acquistare o seleziona Acquisto Rapido");
	}
}
