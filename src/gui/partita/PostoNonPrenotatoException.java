package gui.partita;

public class PostoNonPrenotatoException extends Exception
{
	public PostoNonPrenotatoException()
	{
		super("Mi dispiace il posto non è prenotato o è venduto o è indisponibile");
	}
}
