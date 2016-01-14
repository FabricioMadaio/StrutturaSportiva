package gui.graphics;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;


/**
 * @author Giovanni Leo 
 * @author Fabricio Nicolas Madaio 
 * @version 1.0
 * @since   2016-01-13 
 */
public class Finestra extends JFrame{
	
	private JFrame parent;

	/**
	 * la classe Finestra definisce un JFrame posizionato al centro esatto dello schermo,
	 * alla sua apertura disabilita il frame genitore per poi riabilitarlo alla chiusura del frame stesso
	 * 
	 * @param parent	JFrame genitore
	 * @param width		larghezza frame
	 * @param height	altezza frame
	 */
	public Finestra(JFrame parent, int width, int height) {
		
		this.parent = parent;
		
		if(parent!=null){
			parent.setEnabled(false);
		}
		
		//posiziono il Jframe al centro dello schermo
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((int)(screenSize.getWidth()-width)/2,(int)(screenSize.getHeight()-height)/2, width, height);
		
		//definisco il comportamento alla chiusura
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				//chiudo il frame
				closeFrame();
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}			
		});
	}
	
	/**
	 * questo metodo chiude il frame e abilita il frame genitore
	 */
	public void closeFrame(){
		if(parent!=null){
			parent.setEnabled(true);
			parent.toFront();
			if(parent instanceof Finestra)
				((Finestra)parent).OnReturnFromChild();
		}
		this.dispose();
	}
	
	/**
	 * funzione eseguita al ritorno da una finestra figlia
	 */
	public void OnReturnFromChild(){
	}
}
