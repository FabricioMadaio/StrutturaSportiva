package gui.sconto;

import javax.swing.JLabel;
import javax.swing.JPanel;

import core.sconti.Sconto;
import javax.swing.border.BevelBorder;
/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * ScontoComponent:
 * 	componente grafico contenente i dettagli sullo sconto generico
 * 	visualizza il metdo toString di sconto
 */
public class ScontoComponent extends JPanel{
	
	public ScontoComponent(Sconto sconto) {
		
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		add(new JLabel(sconto.toString()));
	}
}
