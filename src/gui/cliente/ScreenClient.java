package gui.cliente;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

import core.elementi.Partita;
import core.elementi.Stadio;
import gui.graphics.Finestra;
import gui.partita.PartitaPrenotabileComponent;
import gui.graphics.ScrollablePanelList;
import gui.graphics.WeekPicker;

import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

@SuppressWarnings("serial")
public class ScreenClient extends Finestra {

	public ScreenClient(JFrame parent){
		super(parent,800,600);
		this.setJMenuBar(createMenuWithButton());
		getContentPane().add(createActionPanel(),BorderLayout.NORTH);
		
		ScrollablePanelList sl = new ScrollablePanelList();
		
		/*GregorianCalendar data = new GregorianCalendar(2015, 11, 29,23,31);
		Stadio st1 = new Stadio("stadio1",2);
		sl.add(new PartitaPrenotabileComponent(new Partita(data,"a","b",st1)));
		*/
		getContentPane().add(sl,BorderLayout.CENTER);
		this.setTitle("Cliente");

	}
	public JMenuBar createMenuWithButton(){
		JMenuBar SelectionMenu = new JMenuBar();
		JPanel selettori = menuButton();

		SelectionMenu.add(selettori);

		return SelectionMenu;
	}

	public JPanel menuButton(){

		btnCarrello = new JButton("Carrello");
		btnCarrello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());
		panel.add(btnCarrello, BorderLayout.EAST);
		
		JLabel lblBenvenuto = new JLabel("Benvenuto Cliente!");
		lblBenvenuto.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel.add(lblBenvenuto, BorderLayout.WEST);
		return panel;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JPanel createActionPanel(){

		JLabel lSettimana = new JLabel("Settimana");
		JLabel lStadio = new JLabel("Stadio");

		btnEsegui = new JButton("Esegui");
		btnEsegui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		settimana = new WeekPicker();
		stadio = new JTextField(10);

		cmbOrdine = new JComboBox();
		cmbOrdine.addItem("Ordine cronologico");
		cmbOrdine.addItem("Ordine Lessicografico fra le sfidanti");
		cmbOrdine.addItem("Ordine Id Stadio");

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 0, 0, 0));
		


		panel.add(lSettimana);
		panel.add(settimana);
		panel.add(lStadio);
		panel.add(stadio);
		panel.add(cmbOrdine);
		panel.add(btnEsegui);

		return panel;

	}

	private JButton btnCarrello;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbOrdine;
	private WeekPicker settimana;
	private JTextField stadio;
	private JButton btnEsegui;
}
