package gui.carrello;

import core.ListaUtenti;
import core.elementi.Biglietto;
import core.elementi.Partita;
import core.utente.Cliente;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListaUtenti ls = new ListaUtenti("utenti");
		Carrello c = new Carrello(null,ls.getClienti().get(0));
	}

}
