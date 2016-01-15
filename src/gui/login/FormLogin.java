package gui.login;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import core.ListaUtenti;
import core.utente.Gestore;
import core.utente.Cliente;
import core.utente.Utente;
import gui.cliente.ScreenClient;
import gui.gestore.GestoreScreen;
import gui.graphics.Finestra;

import javax.swing.border.EmptyBorder;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * FormLogin:
 * 	schermata iniziale della struttura sportiva, estende Finestra e alla chiusura
 *  salva i dati dell'intera struttura su file
 */

public class FormLogin extends Finestra{

	/**
	 *  FormLogin: contiene la schermata di login, consente di aprire le schermate utente e le schermate
	 *	di registrazione
	 */
	public FormLogin(){
		
		super(null,260,300);
		frame = this;
		utenti = new ListaUtenti("utenti");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(26,6,10,6));
		this.setContentPane(panel);
		
		//Pannello Titolo
		JLabel titolo = new JLabel("Benvenuti nella struttura sportiva");
		panel.add(titolo,BorderLayout.NORTH);
		
		//Pannello Login
		JPanel loginPanel = new JPanel();
		loginPanel.setBorder(new EmptyBorder(20,0,2,0));
		loginPanel.setLayout(new BorderLayout());
		panel.add(loginPanel,BorderLayout.CENTER);
		
		JPanel l1Panel = new JPanel();
		l1Panel.setLayout(new GridLayout(2,2,10,10));
		l1Panel.setBorder(new EmptyBorder(2,0,2,0));
		loginPanel.add(l1Panel,BorderLayout.NORTH);
		
		JLabel login = new JLabel("Login");
		l1Panel.add(login);
		
		loginField = new JTextField();
		l1Panel.add(loginField);
		
		JLabel pass = new JLabel("Password");
		l1Panel.add(pass);
		
		passField = new JPasswordField();
		l1Panel.add(passField);
		
		//pulsante accedi
		JPanel l2Panel = new JPanel();
		l2Panel.setBorder(new EmptyBorder(2,0,2,0));
		loginPanel.add(l2Panel,BorderLayout.CENTER);
		
		JButton AccediBtn = new JButton("Accedi");
		l2Panel.add(AccediBtn);
		
		//Pannello Registrazione
		JPanel registrazionePanel = new JPanel();
		registrazionePanel.setBorder(new TitledBorder(new EtchedBorder(),"Registrazione"));
		panel.add(registrazionePanel,BorderLayout.SOUTH);
		
		JPanel rPanel = new JPanel();
		rPanel.setLayout(new GridLayout(1,2,12,0));
		rPanel.setBorder(new EmptyBorder(2,0,2,0));
		registrazionePanel.add(rPanel);

		JButton ClienteBtn = new JButton("Cliente");
		rPanel.add(ClienteBtn);
		
		JButton GestoreBtn = new JButton("Gestore");
		rPanel.add(GestoreBtn);
		
		this.setVisible(true);
		
		//listener pulsante accedi
		AccediBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//effettua il login per l'utente
				Utente u = utenti.login(loginField.getText(), passField.getText());
				
				if(u!=null){
					//se l'utente è un cliente apro la schermata cliente
					if(u instanceof Cliente){
						ScreenClient sc = new ScreenClient(frame,(Cliente)u,utenti);
						sc.setVisible(true);
						loginField.setText("");
						passField.setText("");
					}
					//se l'utente è un gestore apro la schermata gestore
					if(u instanceof Gestore){
						GestoreScreen gs = new GestoreScreen(frame,utenti);
						gs.setVisible(true);
						loginField.setText("");
						passField.setText("");
					}
				}else{
					//non trova il cliente
					JOptionPane.showMessageDialog(frame, "Username e/o password errati");
				}
				
			}
			
		});
		
		//listener pulsante registra cliente
		ClienteBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// apre la schermata registrazione cliente
				new registrazioneCliente(frame,utenti);
			}
			
		});
		
		//listener pulsante registra gestore
		GestoreBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new registrazioneGestore(frame,utenti);
			}
			
		});
		
		//listener chiusura frame
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowClosing(WindowEvent e) {
				// salvo la struttura su file alla chiusura della finestra
				utenti.salvaDB();
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}			
		});
	
	}
	
	private JTextField loginField;
	private JTextField passField;
	
	private ListaUtenti utenti;
	private Finestra frame;
}
