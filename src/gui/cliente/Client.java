package gui.cliente;

import javax.swing.JFrame;

import core.ListaUtenti;

public class Client {
	
	public static void main(String[] args){
		
		ListaUtenti ls = new ListaUtenti("utenti");
		
		JFrame frame = new ScreenClient(null,ls);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
