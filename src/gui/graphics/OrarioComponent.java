package gui.graphics;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.components.JSpinField;

public class OrarioComponent extends JPanel{

	public OrarioComponent(){
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		ore = new JSpinField(0,23);
		this.add(ore);
		
		JLabel separatorelbl = new JLabel(" : ");
		separatorelbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.add(separatorelbl);
		
		minuti = new JSpinField(0,59);
		this.add(minuti);
		
	}
	
	public float getOrario(){
		return getOre() + getMinuti()*0.01f;
	}
	
	public int getOre(){
		return ore.getValue();
	}
	
	public int getMinuti(){
		return minuti.getValue();
	}
	
	public void setOre(int h){
		ore.setValue(h);
	}
	
	public void setMinuti(int m){
		minuti.setValue(m);
	}
	
	private JSpinField ore;
	private JSpinField minuti;
}
