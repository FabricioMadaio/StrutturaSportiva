package gui.partita;

public class PostoNonPrenotatoException extends Exception
{
	public PostoNonPrenotatoException()
	{
		super("Mi dispiace il posto non � prenotato o � venduto o � indisponibile");
	}
}
