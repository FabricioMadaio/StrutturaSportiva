package core;

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
import core.elementi.Stadio;

/**
 * @author Fabricio Nicolas Madaio
 */
public class ListaUtenti implements Serializable{
	

	private static final long serialVersionUID = 6L;
	
	private ArrayList<Gestore> gestori;
	private ArrayList<Cliente> clienti;
	
	private ArrayList<Stadio> stadi;
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
		
		stadi = new ArrayList<Stadio>();
		
		File f = new File(path);
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(new FileInputStream(f));
			ListaUtenti users = (ListaUtenti)ois.readObject();
			gestori = users.getGestori();
			clienti = users.getClienti();
			stadi = users.getStadi();
			
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

	public void addStadio(Stadio stadio) {
		this.stadi.add(stadio);
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
}
