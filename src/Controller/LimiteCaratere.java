package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class LimiteCaratere implements KeyListener{
		JTextField text1;
		int longeur;

		public LimiteCaratere(JTextField jtext1, int l) {
			this.text1 = jtext1;
			this.longeur = l;
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if(text1.getText().length() >= longeur){
				e.consume();
			}
		}
	}