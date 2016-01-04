package gui.graphics;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Button;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;

public class WeekPicker extends JComponent{

	public WeekPicker(){
		
		thisPicker = this;
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnPrev = new BasicArrowButton(BasicArrowButton.WEST);
		mainPanel.add(btnPrev, BorderLayout.WEST);
		
		
		JPanel panel = new JPanel();
		mainPanel.add(panel, BorderLayout.CENTER);
		panel.setBorder(new EmptyBorder(0, 6, 0, 6));
		panel.setBackground(SystemColor.menu);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblYear = new JLabel("2015/2016");
		lblYear.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblYear.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblYear, BorderLayout.NORTH);
		
		lblWeek = new JLabel("2 Gennaio - 9 Gennaio");
		lblWeek.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblWeek.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWeek.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblWeek);
		
		JButton btnNext  = new BasicArrowButton(BasicArrowButton.EAST);
		mainPanel.add(btnNext, BorderLayout.EAST);
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialDate.add(Calendar.DAY_OF_MONTH, 7);
				finalDate.add(Calendar.DAY_OF_MONTH, 7);
				updateFields();
				itemListener.actionPerformed(new ActionEvent(thisPicker, ActionEvent.ACTION_PERFORMED, null));
			}
		});
		
		//listeners
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialDate.add(Calendar.DAY_OF_MONTH, -7);
				finalDate.add(Calendar.DAY_OF_MONTH, -7);
				updateFields();
				itemListener.actionPerformed(new ActionEvent(thisPicker, ActionEvent.ACTION_PERFORMED, null));
			}
		});
		
		
		
		initialDate = new GregorianCalendar();
		//calcolo il giorno iniziale della settimana
		int amount = initialDate.get(Calendar.DAY_OF_WEEK);
		if(amount == 1) amount = 7;
		//sottraggo 2 perche la settimana parte dal lunedi che ha valore 2
		initialDate.add(Calendar.DAY_OF_MONTH, -(amount-2));
		
		//setto la mezzanotte come riferimento
		initialDate.set(Calendar.HOUR_OF_DAY, 0);
		initialDate.set(Calendar.MINUTE, 0);
		
		//calcolo il giorno di fine settimana come data iniziale + 6 giorni
		finalDate = (GregorianCalendar) initialDate.clone();
		finalDate.add(Calendar.DAY_OF_MONTH, 6);
		
		updateFields();

		setEnabled(false);
	}
	
	public String getDayAndMonth(GregorianCalendar gc){
		return new SimpleDateFormat("d MMMMM").format(gc.getTime());
	}
	
	public void updateFields(){
		
		lblWeek.setText(getDayAndMonth(initialDate) + " - "+ getDayAndMonth(finalDate));
		
		if(initialDate.get(Calendar.YEAR) == finalDate.get(Calendar.YEAR)){
			lblYear.setText(""+initialDate.get(Calendar.YEAR));
		}else{
			lblYear.setText(initialDate.get(Calendar.YEAR)+"/"+finalDate.get(Calendar.YEAR));
		}
		
	}
	
	void setEnabled(Component component, boolean enabled) {
	    component.setEnabled(enabled);
	    if (component instanceof Container) {
	        for (Component child : ((Container) component).getComponents()) {
	            setEnabled(child, enabled);
	        }
	    }
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		setEnabled(this.getComponent(0),enabled);
		super.setEnabled(enabled);
	}
	
	public GregorianCalendar getInitialDate(){
		return initialDate;
	}
	
	public GregorianCalendar getFinalDate(){
		return finalDate;
	}
	
	public void addActionListener(ActionListener l){
		itemListener = l;
	}


	private ActionListener itemListener;
	
	private JLabel lblYear;
	private JLabel lblWeek;
	
	private GregorianCalendar initialDate;
	private GregorianCalendar finalDate;
	
	private WeekPicker thisPicker;
}
