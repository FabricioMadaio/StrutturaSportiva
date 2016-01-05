package core;

import core.sconti.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import core.utente.Gestore;
import core.utente.Cliente;
import core.utente.Utente;
import core.ParametroIllegaleException;
import core.elementi.Partita;
import core.elementi.Stadio;
import core.filtri.FiltroPartita;

/**
 * @author Fabricio Nicolas Madaio
 */
public class ListaUtenti implements Serializable{
	
	private static final long serialVersionUID = 12L;
	
	private ArrayList<Gestore> gestori;
	private ArrayList<Cliente> clienti;
	//Sconti Che si applicano a più partite
	private ArrayList<Sconto> scontiGlobali;
	
	private ArrayList<Stadio> stadi;
	private ArrayList<Partita> partite;
	
	private transient String path;
		
	/**
	 * ListaUtenti: registro dei dati di tutti gli utenti, contiene i metodi per 
	 *	la lettura e la scrittura su file
	 * @param path	percorso del file per la lettura/scrittura dei dati utente
	 */
	public ListaUtenti(String path){
		
		this.path = path; 
		gestori = new ArrayList<Gestore>();
		clienti = new ArrayList<Cliente>();
		scontiGlobali = new ArrayList<Sconto>();
		
		stadi = new ArrayList<Stadio>();
		partite = new ArrayList<Partita>();
		
		File f = new File(path);
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(new FileInputStream(f));
			ListaUtenti users = (ListaUtenti)ois.readObject();
			gestori = users.getGestori();
			clienti = users.getClienti();
			partite = users.getPartite();
			stadi = users.getStadi();
			scontiGlobali = users.getScontiGlobali();
			
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			Stadio st1 = new Stadio("stadio1",2);
			Stadio st2 = new Stadio("stadio2",3);
			addStadio(st1);
			addStadio(st2);
			
			e.printStackTrace();
		}
			

	}
	
	public ArrayList<Stadio> getStadi() {
		return stadi;
	}

	
	/**
	 * @return lista di gestori
	 */
	public ArrayList<Gestore> getGestori() {
		return gestori;
	}

	/**
	 * @return lista di clienti
	 */
	public ArrayList<Cliente> getClienti() {
		return clienti;
	}
	
	/**
	 * @return lista di partite
	 */
	public ArrayList<Partita> getPartite() {
		return partite;
	}
	
	/**
	 * filtra partite
	 * @return lista di partite filtrate
	 */
	static public ArrayList<Partita> filtraPartite(FiltroPartita filter,ArrayList<Partita> pf) {
		
		ArrayList<Partita> partiteFiltrate = new ArrayList<Partita>();
		
		for(Partita p:pf){
			if(filter.check(p))
			partiteFiltrate.add(p);
		}
		return partiteFiltrate;
	}
	
	
	public ArrayList<Sconto> getScontiGlobali() {
		return scontiGlobali;
	}
	
	/**
	 * effettua il login e restituisce l'utente trovato
	 * @param username		username utente
	 * @param password	password
	 * @return utente loggato
	 */
	public Utente login(String username,String password){
		
		for(Gestore ui:gestori){
			if(ui.getUsername().equals(username) && ui.getPassword().equals(password))
				return ui;
		}
		
		for(Cliente ui:clienti){
			if(ui.getUsername().equals(username) && ui.getPassword().equals(password))
				return ui;
		}
		
		return null;
	}

	/**
	 * registra l'utente nella lista utenti
	 * @param u utente da registrare
	 * @throws ParametroIllegaleException	l'utente è gia stato registrato
	 * @throws PasswordException	la password non è valida
	 */
	
	public void registra(Utente u) throws ParametroIllegaleException, PasswordException{
		
		for(Gestore ui:gestori){
			ui.checkSameParameters(u);
		}
		
		for(Cliente ui:clienti){
			ui.checkSameParameters(u);
		}
		
		u.checkPassword();
		
		if(u instanceof Gestore)	gestori.add((Gestore)u);
		else						clienti.add((Cliente)u);
	}
	
	
	/**
	 * salva i dati sul file
	 */
	public void salvaDB(){
		
		File f = new File(path);
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void addSconto(Sconto s)
	{
		//aggiungo lo sconto anche a tutte le partite
		for(Partita p:partite)
			p.aggiungiSconto(s);
		
		scontiGlobali.add(s);
	}
	
	public void addStadio(Stadio stadio) {
		this.stadi.add(stadio);
	}

	public void addPartita(Partita partita) {
		this.partite.add(partita);
	}
}
