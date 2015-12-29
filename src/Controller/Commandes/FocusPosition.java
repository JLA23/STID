package Controller.Commandes;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFormattedTextField;


public class FocusPosition implements MouseListener, FocusListener {
	protected JFormattedTextField jtext;
	protected int met;
	protected ExecuteClick click;

	public FocusPosition(JFormattedTextField j, int methode, ExecuteClick exe) {
		this.jtext = j;
		this.met = methode;
		this.click = exe;
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		int buttonDown = arg0.getButton();

		if (buttonDown == MouseEvent.BUTTON1) {
			// Bouton GAUCHE enfoncé
		} else if (buttonDown == MouseEvent.BUTTON2) {
			// Bouton du MILIEU enfoncé
		} else if (buttonDown == MouseEvent.BUTTON3) {
			click.execute(arg0, null, jtext, met);
			
		}
	}
	
	@Override
	public void focusGained(FocusEvent arg0) {
		jtext.select(0, jtext.getText().length());
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		click.execute(null, arg0, jtext, met);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}