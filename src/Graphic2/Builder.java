package Graphic2;

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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

@SuppressWarnings("serial")
public class Builder extends JFrame {
	
	public Builder(){
		
		setSize(800,600);
		this.setJMenuBar(createMenuWithButton());
		getContentPane().setLayout(null);
		getContentPane().add(createActionPanel());
		getContentPane().add(createList());
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
		btnIndietro = new JButton("Indietro");
		
		btnIndietro.setEnabled(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(btnCarrello, BorderLayout.EAST);
		panel.add(btnIndietro, BorderLayout.WEST);
		return panel;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JPanel createActionPanel(){
		
		JLabel lSettimana = new JLabel("Settimana");
		JLabel lStadio = new JLabel("Stadio");
		
		btnEsegui = new JButton("Esegui");
		btnEsegui.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultListModel dml = new DefaultListModel();
				list.setModel(dml);
				dml.addElement("Ciao");
			}
		});
		settimana = new JTextField(12);
		stadio = new JTextField(10);
		
		cmbOrdine = new JComboBox();
		cmbOrdine.addItem("Ordine cronologico");
		cmbOrdine.addItem("Ordine Lessicografico fra le sfidanti");
		cmbOrdine.addItem("Ordine Id Stadio");
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 33);


		panel.add(lSettimana);
		panel.add(settimana);
		panel.add(lStadio);
		panel.add(stadio);
		panel.add(cmbOrdine);
		panel.add(btnEsegui);
		
		return panel;
		
	}
	 public JList createList(){
		 
		 
			list = new JList();
			list.setBounds(10, 44, 764, 471);

			return list;
	 }
	
	
	private JButton btnIndietro;
	private JButton btnCarrello;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbOrdine;
	private JTextField settimana;
	private JTextField stadio;
	private JButton btnEsegui;
	private JList list;
}
