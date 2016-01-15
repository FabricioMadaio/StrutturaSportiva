package gui.sconto;

import javax.swing.JLabel;
import javax.swing.JPanel;

import core.sconti.Sconto;
import javax.swing.border.BevelBorder;
//################## fabri vedi tu ####################
public class ScontoComponent extends JPanel{
	
	public ScontoComponent(Sconto sconto) {
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		this.sconto = sconto;
		
		add(new JLabel(sconto.toString()));
	}

	private Sconto sconto;
}
