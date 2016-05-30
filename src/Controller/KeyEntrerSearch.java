package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEntrerSearch implements KeyListener {
	
	private Object frame;
	private String classe;
	
	public KeyEntrerSearch(Object frame, String classe){
		this.frame = frame;
		this.classe = classe;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if((int)e.getKeyChar() == 10){
			new ActionValiderVerif(frame, classe).verif();
		}
		
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
	
	}

}
