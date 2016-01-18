package gui.graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Component;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 *	ScrollablePanelList definisce un oggetto contenitore che mette gli oggetti in lista
 *  dall'alto verso il basso e li scorre con una scrollbar
 */
public class ScrollablePanelList extends JPanel{
	
	private JPanel mainList;
	
	/**
	 * costruisce la lista e inserisce l'elemento finale
	 */
	public ScrollablePanelList(){
		
		setLayout(new BorderLayout());

        mainList = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        
        /*TRUCCO: 
        * inserendo questo elemento alla fine, esso si espande e fa in modo che gli
        * elementi che stanno sopra vengano schiacciati e portati verso l'alto
        */
        mainList.add(new JPanel(), gbc);

        JScrollPane scrollPane = new JScrollPane(mainList);
        super.add(scrollPane);
	}
	
	/**
	 * aggiungo un pannello con i parametri compatibili con il GridBagLayout di mainList
	 * @param newPanel 
	 */
	public void addPanel(JPanel newPanel){
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainList.add(newPanel, gbc,mainList.getComponentCount()-1);
	}

	/**
	 * reset della lista
	 */
	public void reset(){
		mainList.removeAll();
	}
	
	/* 
	 * cancello tutti gli elementi tranne il terminatore (Vedi trucco sopra)
	 */
	@Override
	public void removeAll() {
		while(mainList.getComponentCount() > 1){
			mainList.remove(0);
		}
	}

	/* aggiungo un nuovo elemento
	 * NOTA: 
	 * 		ogni nuovo elemento viene inserito dentro un panel formattato
	 */
	@Override
	public Component add(Component comp) {
		if(comp instanceof JPanel){
			this.addPanel((JPanel)comp);
		}else{
			this.addPanel(new JPanel());
		}
		return comp;
	}
}
