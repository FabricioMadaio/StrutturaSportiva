package gui.sconto;

import javax.swing.JFrame;

import core.ListaUtenti;
import core.elementi.Partita;
import core.sconti.Sconto;
import core.sconti.ScontoPerPartita;
import gui.graphics.Finestra;
import gui.graphics.ScrollablePanelList;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTabbedPane;
import java.awt.Insets;

public class ScontoScreen extends Finestra{
	private JButton btnGiornoSettimana;

	public ScontoScreen(JFrame parent, ListaUtenti listaUtenti) {
		
		super(parent, 800, 600);
		setTitle("Sconti");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{784, 0};
		gridBagLayout.rowHeights = new int[] {0, 60};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		ScrollablePanelList scrollListGlobal = new ScrollablePanelList();
		JPanel panelScontiGlobali = new JPanel();
		tabbedPane.addTab("Sconti Globali", null, panelScontiGlobali, null);
		panelScontiGlobali.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelScontiGlobali.setLayout(new BorderLayout(0, 0));
		panelScontiGlobali.add(scrollListGlobal);
		
		ScrollablePanelList scrollListPartita = new ScrollablePanelList();
		JPanel panelScontiPerPartita = new JPanel();
		panelScontiPerPartita.setBorder(new EmptyBorder(10, 10, 10, 10));
		tabbedPane.addTab("Sconti per partita", null, panelScontiPerPartita, null);
		panelScontiPerPartita.setLayout(new BorderLayout(0, 0));
		panelScontiPerPartita.add(scrollListPartita);
		
		JPanel panelloBarraInserimento = new JPanel();
		GridBagConstraints gbc_panelloBarraInserimento = new GridBagConstraints();
		gbc_panelloBarraInserimento.insets = new Insets(0, 0, 5, 0);
		gbc_panelloBarraInserimento.fill = GridBagConstraints.BOTH;
		gbc_panelloBarraInserimento.gridx = 0;
		gbc_panelloBarraInserimento.gridy = 1;
		getContentPane().add(panelloBarraInserimento, gbc_panelloBarraInserimento);
		panelloBarraInserimento.setLayout(new BorderLayout(0, 0));
		
		JPanel panelloTasti = new JPanel();
		panelloTasti.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Aggiungi Sconto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelloBarraInserimento.add(panelloTasti);
		panelloTasti.setLayout(new GridLayout(1, 4, 10, 0));
		
		JPanel panelPartita = new JPanel();
		panelloTasti.add(panelPartita);
		panelPartita.setLayout(new BorderLayout(0, 0));
		
		JButton btnPartita = new JButton("Partita");
		panelPartita.add(btnPartita, BorderLayout.CENTER);
		
		JPanel panelFasciaOraria = new JPanel();
		panelloTasti.add(panelFasciaOraria);
		panelFasciaOraria.setLayout(new BorderLayout(0, 0));
		
		JButton btnFasciaOraria = new JButton("Fascia Oraria");
		panelFasciaOraria.add(btnFasciaOraria);
		
		JPanel panelGiornoSettimana = new JPanel();
		panelloTasti.add(panelGiornoSettimana);
		panelGiornoSettimana.setLayout(new BorderLayout(0, 0));
		
		btnGiornoSettimana = new JButton("Giorno Settimana");
		panelGiornoSettimana.add(btnGiornoSettimana);
		
		JPanel panelCliente = new JPanel();
		panelloTasti.add(panelCliente);
		panelCliente.setLayout(new BorderLayout(0, 0));
		
		JButton btnCliente = new JButton("Cliente");
		panelCliente.add(btnCliente);
		
		
		// aggiungo gli sconti
		
		for(Partita p:listaUtenti.getPartite()){
			scrollListPartita.add(new PartitaTitleComponent(p));
			
			for(Sconto s:p.getSconti()){
				//aggiungo solo quelli specifici della partita?
				if(s instanceof ScontoPerPartita)
				scrollListPartita.add(new ScontoComponent(s));
			}
		}
		
		for(Sconto s:listaUtenti.getScontiGlobali()){
			scrollListGlobal.add(new ScontoComponent(s));
		}
		
		this.setVisible(true);
	}

}
