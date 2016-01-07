package gui.gestore;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

import core.ListaUtenti;
import core.elementi.Partita;
import core.elementi.Stadio;
import gui.graphics.Finestra;
import gui.graphics.OrarioComponent;
import gui.graphics.WeekPicker;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import java.awt.Label;
import java.awt.Font;

public class AggiungiPartitaScreen extends Finestra {
	
	/**
	 * FinestraRegistrazioneStudente: interfaccia grafica per registrare lo studente nel sistema
	 * 
	 * @param parent Finestra genitore
	 * @param utenti lista di utenti	
	 */
	public AggiungiPartitaScreen(Finestra parent,ListaUtenti utenti){
		
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
		
		JLabel titolo = new JLabel("Nuova partita");
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
		
		JLabel datalbl = new JLabel("Data");
		r1Panel.add(datalbl);
		
		data = new JDateChooser();
		r1Panel.add(data);
		
		JLabel orariolbl = new JLabel("Orario");
		r1Panel.add(orariolbl);
		
		panelloOrario = new OrarioComponent();
		r1Panel.add(panelloOrario);

		
		JLabel squadraAlbl = new JLabel("Squadra A");
		r1Panel.add(squadraAlbl);
		
		squadraA = new JTextField();
		r1Panel.add(squadraA);
		
		JLabel squadraBlbl = new JLabel("Squadra B");
		r1Panel.add(squadraBlbl);
		
		squadraB = new JTextField();
		r1Panel.add(squadraB);
	
		JLabel stadiolbl = new JLabel("Stadio");
		r1Panel.add(stadiolbl);
		//ComboBox scelta categoria 
		comboBoxStadio = new JComboBox<Stadio>();
		r1Panel.add(comboBoxStadio);
		//pulsante accedi
		JPanel r2Panel = new JPanel();
		r2Panel.setBorder(new EmptyBorder(2,0,2,0));
		regPanel.add(r2Panel,BorderLayout.SOUTH);
		
		JButton RegistraBtn = new JButton("Registra");
		r2Panel.add(RegistraBtn);
		
		frame.setVisible(true);
		
		GregorianCalendar dataDiOggi = new GregorianCalendar();
		data.setDate(dataDiOggi.getTime());
		
		panelloOrario.setOre(dataDiOggi.get(Calendar.HOUR_OF_DAY));
		panelloOrario.setMinuti(dataDiOggi.get(Calendar.MINUTE));
		
		for(Stadio s: listaUtenti.getStadi()){
			comboBoxStadio.addItem(s);
		}
		
		comboBoxStadio.setRenderer(new ListCellRenderer<Stadio>(){

			@Override
			public Component getListCellRendererComponent(JList<? extends Stadio> list, Stadio value, int index,
					boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				return new JLabel(value.getNome());
			}});
		
		//listeners
		RegistraBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					GregorianCalendar gData = new GregorianCalendar();
					gData.setTime(data.getDate());
					gData.set(Calendar.HOUR_OF_DAY, panelloOrario.getOre());
					gData.set(Calendar.MINUTE, panelloOrario.getMinuti());
					
					Stadio s = (Stadio)comboBoxStadio.getSelectedItem();
					
					if(squadraA.getText().isEmpty() || squadraB.getText().isEmpty()){
						JOptionPane.showMessageDialog(frame,"Riempire tutti i campi","Errore", JOptionPane.ERROR_MESSAGE);
					}else{
						Partita p = new Partita(gData, squadraA.getText(), squadraB.getText(),s);
						p.aggiungiSconti(listaUtenti.getScontiGlobali());
						listaUtenti.addPartita(p);
						
						frame.closeFrame();	
					}
			}
			
		});
	}
	

	private JDateChooser data;
	private JTextField squadraA;
	private JTextField squadraB;
	private OrarioComponent panelloOrario;
	private JComboBox<Stadio> comboBoxStadio;
	
	private Finestra frame;
	private ListaUtenti listaUtenti;
	
}
