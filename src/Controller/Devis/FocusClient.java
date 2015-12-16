package Controller.Devis;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import View.Devis.Devis;

public class FocusClient implements FocusListener {

	Devis devis;

	public FocusClient(Devis d) {
		this.devis = d;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		nameClient();
	}
	
	public void nameClient(){
		if (!devis.getNumClient().getText().equals("")) {
			int i = 0;
			while (!devis.getListClient()[i][0].toString().equals(devis.getNumClient().getText()) && i < devis.getListClient().length) {
				i++;
			}
			if (i < devis.getListClient().length) {
				devis.getNameClient().setText("Client : " + devis.getListClient()[i][1]);
				devis.getNameClient().setBounds(devis.getNameClient().getX(), devis.getNameClient().getY(), devis.getNameClient().getPreferredSize().width,
						devis.getNameClient().getPreferredSize().height);
			} else {
				devis.getNameClient().setText("Error");
				devis.getNameClient().setBounds(devis.getNameClient().getX(), devis.getNameClient().getY(), devis.getNameClient().getPreferredSize().width,
						devis.getNameClient().getPreferredSize().height);
			}
			devis.repaint();
		}

	}

}