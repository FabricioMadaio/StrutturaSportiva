package core.utenti;

public class Gestore extends Utente
{

	public Gestore(String username, String password, String nome, String cognome)
	{
		
		super(username, password, nome, cognome);

	}
	
	@Override
	public String getPassword() 
	{
		return super.getPassword();
	}

	@Override
	public String getUsername() 
	{
		return super.getUsername();
	}

	@Override
	public String getNome() 
	{
		return super.getNome();
	}

	@Override
	public String getCognome() 
	{
		return super.getCognome();
	}
	






}
