package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyAutocomplete implements KeyListener{
	
	protected String type;
	protected Object d;
	
	public KeyAutocomplete(Object d, String typeClasse){
		this.type = typeClasse;
		this.d = d;
	}

	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		new FocusJText(d, type).name();	
	}

}
