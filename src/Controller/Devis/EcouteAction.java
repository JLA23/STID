package Controller.Devis;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFormattedTextField;

public class EcouteAction implements KeyListener {

	JFormattedTextField text1;
	String l = "1234567890,";

	public EcouteAction(JFormattedTextField jtext1) {
		this.text1 = jtext1;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == '.') {
			if (!text1.getText().contains(",")) {
				e.setKeyChar(',');
			} else {
				e.consume();
			}
		} else if (!l.contains(e.getKeyChar() + "")) {
			e.consume();
		}
	}
}