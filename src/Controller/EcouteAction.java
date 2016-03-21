package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFormattedTextField;

public class EcouteAction implements KeyListener {

	JFormattedTextField text1;
	String l = "1234567890";
	boolean point = true;

	public EcouteAction(JFormattedTextField jtext1) {
		this.text1 = jtext1;
	}
	public EcouteAction(JFormattedTextField jtext1, boolean point) {
		this.text1 = jtext1;
		this.point = point;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == '.' || e.getKeyChar() == ',') {
			if (!text1.getText().contains(",") && point) {
				e.setKeyChar(',');
			} else {
				e.consume();
			}
		} 
		else if(e.getKeyChar() == '-' && !text1.getText().contains('-' + "") && point){
			text1.setText("-" + text1.getText());
			e.consume();
		}
		else if(e.getKeyChar() == '+' && text1.getText().contains('-' + "") && point){
			text1.setText(text1.getText().substring(1, text1.getText().length()));
			e.consume();
		}
		else if (!l.contains(e.getKeyChar() + "")) {
			e.consume();
		}
	}
}