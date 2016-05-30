package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEntrerSearchList implements KeyListener {
		
		private Object frame;
		private String classe;
		private String fonction;
		
		public KeyEntrerSearchList(Object frame, String classe, String fonction){
			this.frame = frame;
			this.classe = classe;
			this.fonction = fonction;
			
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if((int)e.getKeyChar() == 10){
				new SelectionAction(frame, classe, fonction).action();
			}
			
		}
		

		@Override
		public void keyReleased(KeyEvent e) {
		
		}

	}
