package Controller.Devis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Devis.Devis;
import View.Devis.SearchClient;

public class ActionSearch implements ActionListener {
	protected Devis devis;

	public ActionSearch(Devis fr) {
		this.devis = fr;
	}

	public void actionPerformed(ActionEvent e) {
		new SearchClient(devis.getBase(), devis.getNumClient(), null, true).searchClientNum(devis.getListClient());
	}
}
