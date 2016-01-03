package gui.incassi;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import core.elementi.Stadio;
import java.awt.Font;

public class StadioComponet extends JPanel 
{
	public StadioComponet(Stadio stadio) 
	{
		setBorder(new EtchedBorder());
		JLabel stadioLabel = new JLabel(stadio.getNome()+"    "+"Incassi:"+stadio.getIncasso());
		stadioLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(stadioLabel);
	}

}
