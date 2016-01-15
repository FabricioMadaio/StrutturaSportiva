package gui.incassi;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import core.elementi.Stadio;
import java.awt.Font;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */

public class StadioComponent extends JPanel 
{
	/**
	 * Il costruttore della classe StadioComponent la quale è una sottoclasse di JPanel
	 * inizializza un oggetto prendendo come riferimento lo stadio. In questo oggetto
	 * vengono visualizzati gli incassi dello stadio passato come riferimento.
	 * @param Stadio stadio
	 */
	public StadioComponent(Stadio stadio) 
	{
		setBorder(new EtchedBorder());
		JLabel stadioLabel = new JLabel(stadio.getNome()+"    "+"Incassi:"+stadio.getIncasso());
		stadioLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(stadioLabel);
	}

}
