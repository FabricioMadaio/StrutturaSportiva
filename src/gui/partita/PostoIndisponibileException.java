package gui.partita;

@SuppressWarnings("serial")
public class PostoIndisponibileException extends Exception 
{
	public PostoIndisponibileException()
	{
		super("Mi dispiace il posto non � disponibile, ne selezioni un altro.");
	}
}	
