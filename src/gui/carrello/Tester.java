package gui.carrello;

import core.ListaUtenti;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListaUtenti ls = new ListaUtenti("utenti");
		new Carrello(null,ls.getClienti().get(0));
	}

}
