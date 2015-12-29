package Controller.Commandes.SelectDevis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import View.Commandes.Commandes;
import View.Commandes.SelectDevis;

public class ActionFermerListDevis implements ActionListener, WindowListener {

	private SelectDevis select;
	private Commandes commandes;

	public ActionFermerListDevis(SelectDevis sd, Commandes com) {
		this.select = sd;
		this.commandes = com;
	}

	public void actionPerformed(ActionEvent arg0) {
		fermer();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		fermer();
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
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
	
	protected void fermer() {
		for (int i = 0; i < select.getModel().getRowCount(); i++) {
			Object[] val = new Object[select.getModel().getColumnCount()];
			for (int j = 0; j < select.getModel().getColumnCount(); j++) {
				val[j] = select.getModel().getValueAt(i, j);
			}
			boolean trouve = false;
			for(int k = 0; k < commandes.getListDevis().size(); k++){
				if(((String)commandes.getListDevis().get(k)[0]).equals((String)val[0])){
					trouve = true;
					break;
				}
			}
			if(!trouve){
				select.getModel().removeRow(i);
				i --;
			}

		}
		commandes.setEnabled(true);
		commandes.setVisible(true);
		select.dispose();

	}
}
