package gui.stadio;

import javax.swing.JFrame;

public class StadiTester {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		StadioCanvas s = new StadioCanvas();
		s.setBounds(100, 100, 450, 300);
		frame.setBounds(100, 100, 450, 300);
		frame.setContentPane(s);
		frame.setVisible(true);
	}

}
