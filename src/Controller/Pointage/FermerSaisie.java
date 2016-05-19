package Controller.Pointage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;
import View.Pointage.HeureSpe;
import View.Pointage.SaisieHeureSpe;

public class FermerSaisie implements ActionListener, WindowListener {

	protected SaisieHeureSpe spe;
	protected HeureSpe hr;

	public FermerSaisie(SaisieHeureSpe spe, HeureSpe hr) {
		this.spe = spe;
		this.hr = hr;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		fermer();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		fermer();
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void fermer(){
		if (!spe.getjLibelle().getText().isEmpty() || !spe.getJhPerdue().getText().equals("0,00")
				|| !spe.getJhRecup().getText().equals("0,00")) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous quitter la saisie des heures spe",
					"Quitter Saisie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				spe.dispose();
				if (!spe.isShowing()) {
					new HeureSpe(hr.getBase(), hr.getFrame());
				}
			}
		} else {
			spe.dispose();
			if (!spe.isShowing()) {
				new HeureSpe(hr.getBase(), hr.getFrame());
			}
		}
	}
}
