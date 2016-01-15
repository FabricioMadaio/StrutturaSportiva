package gui.graphics;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.components.JSpinField;

/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 * 
 * OrarioComponent definisce un elemento di interfaccia che consente di inserire
 * un orario nel formato double  ore.minuti (es. 10.30f)
 */
public class OrarioComponent extends JPanel{

	/**
	 * 	crea gli elementi grafici
	 */
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
	
	/**
	 * @return orario in formato ore.minuti
	 */
	public float getOrario(){
		return getOre() + getMinuti()*0.01f;
	}
	
	/**
	 * @return ore
	 */
	public int getOre(){
		return ore.getValue();
	}
	
	/**
	 * @return minuti
	 */
	public int getMinuti(){
		return minuti.getValue();
	}
	
	/**
	 * @param h ore
	 */
	public void setOre(int h){
		ore.setValue(h);
	}
	
	/**
	 * @param m minuti
	 */
	public void setMinuti(int m){
		minuti.setValue(m);
	}
	
	private JSpinField ore;
	private JSpinField minuti;
}
