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
import core.utente.ParametroIllegaleException;
import core.utente.PasswordException;
import core.elementi.Immagine;
import core.elementi.Partita;
import core.elementi.Stadio;
import core.filtri.FiltroPartita;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * Classe ListaUtenti:
 *  contiene tutte le informazioni sulla struttura sportiva e metodi per la lettura/scrittura
 *  su file
 */

public class ListaUtenti implements Serializable{
	
	private static final long serialVersionUID = 12L;
		
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
		
		immagini = new ArrayList<Immagine>();
		
		File f = new File(path);
		ObjectInputStream ois;

		try {
			//leggo da file tutta la struttura sportiva
			ois = new ObjectInputStream(new FileInputStream(f));
			ListaUtenti users = (ListaUtenti)ois.readObject();
			gestori = users.getGestori();
			clienti = users.getClienti();
			partite = users.getPartite();
			stadi = users.getStadi();
			scontiGlobali = users.getScontiGlobali();
			
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			
			//creo due stadi di default
			Stadio st1 = new Stadio("stadio1",2,"res/Stadio.png");
			Stadio st2 = new Stadio("stadio2",3,"res/Stadio2.png");
			addStadio(st1);
			addStadio(st2);
			
			e.printStackTrace();
		}
		
		//carico tutte le immagini e le assegno ai rispettivi stadi
		caricaImaginiStadi();	

	}
	
	/**
	 *  Assegna a ogni stadio l'immagine del proprio path.
	 *  costruisce l'array di immagini per evitare di caricare più volte la stessa immagine,
	 *  in questo modo si passa solo il riferimento all'oggetto stadio
	 */
	public void caricaImaginiStadi(){
		
		boolean imageNotFound;
		
		for(Stadio s:stadi){
			
			imageNotFound = true;
			
			//cerco il path dello stadio nella lista delle immagini
			for(Immagine i: immagini){
			
				//se trovo una immagine con lo stesso path, la passo allo stadio
				if(s.getPathImmagine().equals(i.getPath())){
					s.setImage(i.getImage());
					imageNotFound = false;
					break;
				}
			}
			
			//se non ho trovato l'immagine, la carico, la passo allo stadio e
			//la inserisco nell'array di immagini
			if(imageNotFound){
				try {
					Immagine i = new Immagine(s.getPathImmagine());
					s.setImage(i.load());
					immagini.add(i);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @return lista di stadi
	 */
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
	 * @param filter filtro
	 * @param pf	array di partite sulle quali applicare il filtro
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
	
	
	/**
	 * @return lista di tutti gli sconti globali
	 */
	public ArrayList<Sconto> getScontiGlobali() {
		return scontiGlobali;
	}
	
	/**
	 * effettua il login e restituisce l'utente trovato
	 * @param username	username utente
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
			//salvo l'intera classe
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * aggiungo un nuovo sconto globale
	 * @param s sconto
	 */
	public void addSconto(Sconto s)
	{
		//aggiungo lo sconto anche a tutte le partite
		for(Partita p:partite)
			p.aggiungiSconto(s);
		
		scontiGlobali.add(s);
	}
	
	/**
	 * aggiungo un nuovo stadio alla struttura sportiva
	 * @param stadio
	 */
	public void addStadio(Stadio stadio) {
		this.stadi.add(stadio);
	}

	/**
	 * aggiungo una nuova partita alla struttura sportiva
	 * @param partita
	 */
	public void addPartita(Partita partita) {
		this.partite.add(partita);
	}
	

	private ArrayList<Gestore> gestori;
	private ArrayList<Cliente> clienti;
	
	//Sconti Che si applicano a più partite
	private ArrayList<Sconto> scontiGlobali;
	
	private ArrayList<Stadio> stadi;
	private ArrayList<Partita> partite;
	
	//lista immagini (coppie Image img - String path)
	private transient ArrayList<Immagine> immagini;
	
	//percorso del file per la lettura/scrittura dei dati utente
	private transient String path;
}
