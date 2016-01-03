package gui.graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class ScrollablePanelList extends JPanel{
	
	private JPanel mainList;
	
	public ScrollablePanelList(){
		
		setLayout(new BorderLayout());

        mainList = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainList.add(new JPanel(), gbc);

        JScrollPane scrollPane = new JScrollPane(mainList);
        super.add(scrollPane);
	}
	
	public void addPanel(JPanel newPanel){
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainList.add(newPanel, gbc,mainList.getComponentCount()-1);
	}

	public void reset(){
		mainList.removeAll();
	}
	
	@Override
	public void removeAll() {
		while(mainList.getComponentCount() > 1){
			mainList.remove(0);
		}
	}

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
