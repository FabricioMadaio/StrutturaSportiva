package gui.gestore;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import core.ListaUtenti;
import core.elementi.Posto.Stato;
import core.elementi.Stadio;
import gui.graphics.Finestra;
import gui.stadio.Poltroncina;
import gui.stadio.StadioCanvas;
import gui.stadio.StadioListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.border.EmptyBorder;

public class StadioGestore extends Finestra
{
	

	private JFrame questoFrame;
	
	//info stadio
	private JTextField costoFieldStadio;
	private JComboBox<Stadio> comboBoxStadio;
	
	//info poltroncina
	private JPanel pannelloSelezione;
	private JComboBox<Stato> comboBoxStatoPosto;
	private JTextField nomeFieldPosto;
	private JButton btnCancellaPoltrona;
	
	//modalita editor
	private JToggleButton buttonAggiungi;
	private JToggleButton buttonModifica;
	
	private ListaUtenti listaUtenti;
	
	private StadioCanvas stadioCanvas;
	private Stadio stadioCorrente;
	
	public StadioGestore(JFrame parent,ListaUtenti listaUtenti)
	{
		super(parent,800, 600);
		this.listaUtenti = listaUtenti;
		questoFrame = this;
		operazioniSuFrame();
		
		caricaStadio(listaUtenti.getStadi().get(0));
		stadioCanvas.setModalita(StadioCanvas.editMode.MODIFICA);
	}
	
	
	public void caricaStadio(Stadio s)
	{
		if(s!=null){
			stadioCorrente = s;
			costoFieldStadio.setText(""+s.getPrezzoBase());
			stadioCanvas.setPosti(s.getPosti());
			stadioCanvas.setBackgroundImage(s.getImage());
			stadioCanvas.repaint();
		}
	}
	//interfaccia grafica
	
	public void operazioniSuFrame()
	{
		
		questoFrame.getContentPane().add(creaPannelloCanvas(),BorderLayout.CENTER);
		questoFrame.getContentPane().add(creaPanelloPrincipale(), BorderLayout.SOUTH);
		
		
		questoFrame.setVisible(true);
	}
	
	public JPanel creaPannelloCanvas()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		stadioCanvas = new StadioCanvas();
		stadioCanvas.addStadioListener(new StadioListener(){

			@Override
			public void onSelected(Poltroncina p) {
				//funzione eseguita alla selezione
				aggiornaSelezione(true);
			}

			@Override
			public void onReleased(Poltroncina p) {
				// TODO Auto-generated method stub
				aggiornaSelezione(false);
			}
			
		});

		panel.add(stadioCanvas,BorderLayout.CENTER);
		
		return panel;
	}
	
	public JPanel creaPanelloPrincipale()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(creaPannelloSettaggioCosto());
		panel.add(creaPannelloModalita());
		
		return panel;
	}
	
	public JPanel creaPannelloSettaggioCosto()
	{
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 5, 10));
		JLabel labelStadio = new JLabel("Stadio");
		panel.setLayout(new GridLayout(3, 2, 10, 10));
		
		panel.add(labelStadio);
		
		comboBoxStadio = new JComboBox<Stadio>();
		for(Stadio s:listaUtenti.getStadi()){
			comboBoxStadio.addItem(s);
		}
		
		comboBoxStadio.setRenderer( new ListCellRenderer<Stadio>(){

			@Override
			public Component getListCellRendererComponent(JList<? extends Stadio> list, Stadio value, int index,
					boolean isSelected, boolean cellHasFocus) {
				JLabel nome = new JLabel(value.getNome());
				return nome;
			}
			
		});
		
		comboBoxStadio.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				caricaStadio((Stadio)e.getItem());
			}
			
		});
		
		panel.add(comboBoxStadio);
		
		JLabel labelCosto = new JLabel("Costo Biglietto");
		panel.add(labelCosto);
		costoFieldStadio = new JTextField(10);
		panel.add(costoFieldStadio);
		
		costoFieldStadio.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  update();
			  }
			  public void removeUpdate(DocumentEvent e) {
				  update();
			  }
			  public void insertUpdate(DocumentEvent e) {
				  update();
			  }
		
			  public void update() {
				 if(stadioCorrente!=null && !costoFieldStadio.getText().isEmpty()){
						stadioCorrente.setPrezzoBase(Double.parseDouble(costoFieldStadio.getText()));
				 }
			  }
		});
		
		return panel;
	}
	
	public JPanel creaPannelloModalita()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		pannelloSelezione = creaPannelloSelezione();
		panel.add(pannelloSelezione);
		panel.add(creaPannelloBottoni());
		
		return panel;
	}
	
	public JPanel creaPannelloSelezione()
	{
		JPanel panel = new JPanel();
		GridLayout gl_panel = new GridLayout(3, 2);
		gl_panel.setHgap(4);
		gl_panel.setVgap(4);
		panel.setLayout(gl_panel);
		
		JLabel disponibilit�Label = new JLabel("Disponibilit�");
		JLabel nomeLabel = new JLabel("Nome");
		
		nomeFieldPosto = new JTextField();
		comboBoxStatoPosto = creaComboBox();
		
		panel.add(disponibilit�Label);
		panel.add(comboBoxStatoPosto);
		panel.add(nomeLabel);
		panel.add(nomeFieldPosto);
		
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Selezione"));
		btnCancellaPoltrona = new JButton("Cancella Poltrona");
		panel.add(btnCancellaPoltrona);
		
		abilitaPanel(panel,false);
		
		//setto i listeners
		comboBoxStatoPosto.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// modifico lo stato del posto selezionato
				stadioCanvas.getSelezione().setStato((Stato)e.getItem());
				stadioCanvas.repaint();
			}
			
		});
		
		nomeFieldPosto.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				  update();
			  }
			  public void removeUpdate(DocumentEvent e) {
				  update();
			  }
			  public void insertUpdate(DocumentEvent e) {
				  update();
			  }
		
			  public void update() {
				// aggiorno il nome del posto selezionato
				 if(!nomeFieldPosto.getText().isEmpty()){
					 stadioCanvas.getSelezione().getPosto().setSigla(nomeFieldPosto.getText());
				 }
			  }
		});
		
		btnCancellaPoltrona.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// cancello il posto
				stadioCanvas.destroySelezione();
				stadioCanvas.repaint();
			}
			
		});
		
		return panel;
	}
	
	public JPanel creaPannelloBottoni()
	{
		JPanel panel = new JPanel();
		TitledBorder b = new TitledBorder(new EtchedBorder(),"Modalit� Editor");
		panel.setBorder(b);
		
		panel.setLayout(new GridLayout(3, 1));
		
		buttonAggiungi = new JToggleButton("Aggiungi");
		buttonModifica = new JToggleButton("Modifica");
		buttonModifica.setSelected(true);
		
		panel.add(buttonAggiungi);
		panel.add(buttonModifica);
		
		
		//setto i listeners
		
		buttonAggiungi.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stadioCanvas.setModalita(StadioCanvas.editMode.AGGIUNGI);
				buttonAggiungi.setSelected(true);
				buttonModifica.setSelected(false);
			}
			
		});
		
		buttonModifica.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stadioCanvas.setModalita(StadioCanvas.editMode.MODIFICA);
				buttonAggiungi.setSelected(false);
				buttonModifica.setSelected(true);
			}
			
		});
		
		return panel;
	}
	
	
	public JComboBox<Stato> creaComboBox()
	{
		JComboBox<Stato> comboBox = new JComboBox<Stato>();
		
		comboBox.addItem(Stato.DISPONIBILE);
		comboBox.addItem(Stato.NON_DISPONIBILE);
		comboBox.addItem(Stato.PRENOTATO);
		comboBox.addItem(Stato.VENDUTO);
		
		return comboBox;
	}
	
	
	public void abilitaPanel(JPanel panel, boolean enabled){
		panel.setEnabled(enabled);
		Component[] comps = panel.getComponents();
		for (Component comp:comps){
		    comp.setEnabled(enabled);
		}
	}
	
	public void aggiornaSelezione(boolean enabled){
		comboBoxStatoPosto.setSelectedItem(stadioCanvas.getSelezione().getStato());
		nomeFieldPosto.setText(stadioCanvas.getSelezione().getPosto().getSigla());
		abilitaPanel(pannelloSelezione,enabled);
	}

}
