package gui.partita;

@SuppressWarnings("serial")
public class PostoIndisponibileException extends Exception 
{
	public PostoIndisponibileException()
	{
		super("Mi Dispiace il posto non è disponebile ne selezioni un altro.");
	}
}	
