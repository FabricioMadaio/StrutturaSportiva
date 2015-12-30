package gui.stadio;

import javax.swing.JFrame;

public class StadiTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		StadioCanvas s = new StadioCanvas();
		s.setBounds(100, 100, 450, 300);
		frame.setBounds(100, 100, 450, 300);
		frame.setContentPane(s);
		//frame.setSize(800,600);
		frame.setVisible(true);
	}

}
