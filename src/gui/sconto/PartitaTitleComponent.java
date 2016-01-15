package gui.sconto;

import javax.swing.JLabel;
import javax.swing.JPanel;

import core.elementi.Partita;
import core.sconti.Sconto;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;

//#################################Fabri vedi tu ##############################
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */
public class PartitaTitleComponent extends JPanel{
	
	public PartitaTitleComponent(Partita partita) {
		setBackground(UIManager.getColor("textHighlight"));
		setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		this.partita = partita;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new EmptyBorder(0, 10, 0, 10));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel(" "+partita.getSquadraA()+" vs "+partita.getSquadraB()+" ");
		panel.add(label, BorderLayout.WEST);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel data = new JLabel(partita.getData().getTime().toString()+"  ");
		panel.add(data, BorderLayout.EAST);
	}

	private Partita partita;
}
