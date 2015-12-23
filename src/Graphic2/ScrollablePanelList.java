package Graphic2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

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
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        super.add(scrollPane);
	}
	
	public void addPanel(JPanel newPanel){
		
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainList.add(newPanel, gbc,0);
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
