package gui.login;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gui.graphics.Finestra;
import core.ListaUtenti;
import core.utente.ParametroIllegaleException;
import core.utente.PasswordException;
import core.utente.Cliente;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * registrazioneCliente:
 * 	interfaccia grafica per registrare il cliente nel sistema
 */

public class registrazioneCliente extends Finestra{
	
	
	/**
	 * registrazioneCliente: interfaccia grafica per registrare lo studente nel sistema
	 * contiene i campi per l'inserimento del nome utente, la password, l'username
	 * e una combobox per le categorie
	 * 
	 * @param parent Finestra genitore
	 * @param utenti lista di utenti	
	 */
	public registrazioneCliente(Finestra parent,ListaUtenti utenti){
		
		super(parent,400,400);
		
		listaUtenti = utenti;
		frame = this;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(26,6,10,6));
		frame.setContentPane(panel);
		
		//Pannello Titolo
		JPanel titoloPanel = new JPanel();
		panel.add(titoloPanel,BorderLayout.NORTH);
		
		JLabel titolo = new JLabel("Registrazione Cliente");
		titoloPanel.add(titolo);
		
		//Pannello Registrazione
		JPanel regPanel = new JPanel();
		regPanel.setBorder(new EmptyBorder(20,0,2,0));
		regPanel.setLayout(new BorderLayout());
		panel.add(regPanel,BorderLayout.CENTER);
		
		JPanel r1Panel = new JPanel();
		r1Panel.setLayout(new GridLayout(6,2,10,10));
		r1Panel.setBorder(new EmptyBorder(2,0,2,0));
		regPanel.add(r1Panel,BorderLayout.NORTH);
		
		JLabel nomelbl = new JLabel("Nome");
		r1Panel.add(nomelbl);
		
		nome = new JTextField();
		r1Panel.add(nome);
		
		JLabel passlbl = new JLabel("Cognome");
		r1Panel.add(passlbl);
		
		cognome = new JTextField();
		r1Panel.add(cognome);
		
		JLabel loginlbl = new JLabel("Username");
		r1Panel.add(loginlbl);
		
		login = new JTextField();
		r1Panel.add(login);
		
		JLabel passwordlbl = new JLabel("Password");
		r1Panel.add(passwordlbl);
		
		password = new JPasswordField();
		r1Panel.add(password);
	
		JLabel lblCategoria = new JLabel("Categoria");
		r1Panel.add(lblCategoria);
		//ComboBox scelta categoria 
		comboBoxCategoria = new JComboBox<String>(categoria);
		r1Panel.add(comboBoxCategoria);
		//pulsante accedi
		JPanel r2Panel = new JPanel();
		r2Panel.setBorder(new EmptyBorder(2,0,2,0));
		regPanel.add(r2Panel,BorderLayout.SOUTH);
		
		JButton RegistraBtn = new JButton("Registra");
		r2Panel.add(RegistraBtn);
		
		frame.setVisible(true);
		
		//listener pulsante di registrazione
		RegistraBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

					
					//creo un nuovo studente		
					Cliente s = new Cliente(nome.getText(),cognome.getText(),login.getText(),password.getText(),(String)comboBoxCategoria.getSelectedItem());
					//lo registro e salvo le modifiche su file
					try {
						listaUtenti.registra(s);
						listaUtenti.salvaDB();
						
					} catch (ParametroIllegaleException | PasswordException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.closeFrame();
				
			}
			
		});
	}
	
	private JTextField nome;
	private JTextField cognome;
	private JTextField login;
	private JTextField password;
	
	private JComboBox<String> comboBoxCategoria;
	
	private Finestra frame;
	private ListaUtenti listaUtenti;
	
	private String[] categoria = {"Studente","Pensionato","Bambino"};
	
}
