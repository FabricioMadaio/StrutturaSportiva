package Stadio;

import javax.swing.JFrame;

public class StadiTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		StadioCanvas s = new StadioCanvas();
		frame.getContentPane().add(s);
		frame.setSize(800,600);
		frame.setVisible(true);
	}

}
