package gui.partita;

@SuppressWarnings("serial")
public class PostoIndisponibileException extends Exception 
{
	public PostoIndisponibileException()
	{
		super("Mi Dispiace il posto non � disponebile ne selezioni un altro.");
	}
}	
