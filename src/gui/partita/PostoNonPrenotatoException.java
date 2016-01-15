package gui.partita;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * PostoNonPrenotatoException:
 * 	eccezione sollevata nel caso in cui si prova ad acquistare un posto non prenotato
 */
public class PostoNonPrenotatoException extends Exception
{
	public PostoNonPrenotatoException()
	{
		super("Mi dispiace il posto non è prenotato o è venduto o è indisponibile");
	}
}
