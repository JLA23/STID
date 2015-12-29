package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class ActionFermer implements ActionListener, WindowListener {

	private JFrame fenetre, fenetre2;
	private JDialog dialog;

	public ActionFermer(JFrame frame, JFrame frame2) {
		this.fenetre = frame;
		this.fenetre2 = frame2;
	}

	public ActionFermer(JDialog dia) {
		this.dialog = dia;
	}

	public void actionPerformed(ActionEvent arg0) {
		fermer();
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
	public void windowClosing(WindowEvent e) {
		fermer();

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

	protected void fermer() {
		if (fenetre != null && dialog == null) {
			fenetre.dispose();
			if (fenetre2 != null) {
				fenetre2.setEnabled(true);
				fenetre2.setVisible(true);
			}
		} else if (fenetre == null && dialog != null) {
			dialog.dispose();
		}
	}
}
