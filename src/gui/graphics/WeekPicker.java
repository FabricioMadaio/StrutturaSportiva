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
import java.awt.FlowLayout;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;

public class WeekPicker extends JComponent{

	public WeekPicker(){
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnPrev = new BasicArrowButton(BasicArrowButton.WEST);
		panel_1.add(btnPrev, BorderLayout.WEST);
		
		
		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.CENTER);
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
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNext  = new BasicArrowButton(BasicArrowButton.EAST);
		panel_1.add(btnNext, BorderLayout.EAST);
		
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialDate.add(Calendar.DAY_OF_MONTH, 7);
				finalDate.add(Calendar.DAY_OF_MONTH, 7);
				updateFields();
			}
		});
		
		//listeners
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialDate.add(Calendar.DAY_OF_MONTH, -7);
				finalDate.add(Calendar.DAY_OF_MONTH, -7);
				updateFields();
			}
		});
		
		
		
		initialDate = new GregorianCalendar();
		//calcolo il giorno iniziale della settimana
		int amount = initialDate.get(Calendar.DAY_OF_WEEK);
		if(amount == 1) amount = 7;
		//sottraggo 2 perche la settimana parte dal lunedi che ha valore 2
		initialDate.add(Calendar.DAY_OF_MONTH, -(amount-2));
		
		//calcolo il giorno di fine settimana come data iniziale + 6 giorni
		finalDate = (GregorianCalendar) initialDate.clone();
		finalDate.add(Calendar.DAY_OF_MONTH, 6);
		
		updateFields();
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
	
	private JLabel lblYear;
	private JLabel lblWeek;
	
	private GregorianCalendar initialDate;
	private GregorianCalendar finalDate;
}
