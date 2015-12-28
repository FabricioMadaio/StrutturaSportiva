package core.utenti;

public class Utente
{
	public Utente(String username,String password, String nome, String cognome)
	{
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
	}

	public String getPassword() 
	{
		return password;
	}

	public String getUsername() 
	{
		return username;
	}

	public String getNome() 
	{
		return nome;
	}

	public String getCognome() 
	{
		return cognome;
	}

	private String password;
	private String username;
	private String nome;
	private String cognome;
}
